package core;

import java.time.*;
import java.util.*;
import model.*;
import market.StockMarket;
import command.*;
import history.Transaction;

public class TradingPlatform {
    private static TradingPlatform instance;
    private UserManager userManager;
    private StockMarket stockMarket;

    private TradingPlatform() {
        this.userManager = UserManager.getInstance();
        this.stockMarket = StockMarket.getInstance();
    }

    public static synchronized TradingPlatform getInstance() {
        if (instance == null) {
            instance = new TradingPlatform();
        }
        return instance;
    }

    public User registerUser(String username, String password, double initialBalance) {
        return userManager.registerUser(username, password, initialBalance);
    }

    public User login(String username, String password) {
        return userManager.login(username, password);
    }

    public boolean buyAsset(User user, String ticker, int quantity) {
        TradableAsset asset = stockMarket.getAsset(ticker);
        if (asset == null) {
            System.out.println("Asset not found!");
            return false;
        }

        if (!asset.canTradeNow()) {
            System.out.println("Trading not allowed at this time for " + ticker);
            return false;
        }

        double totalCost = asset.getPrice() * quantity;
        if (user.getBalance() < totalCost) {
            System.out.println("Insufficient balance!");
            return false;
        }

        if (asset.getAvailableQuantity() < quantity) {
            System.out.println("Insufficient quantity available!");
            return false;
        }

        TradeCommand buyCommand = new BuyCommand(user, asset, quantity);
        buyCommand.execute();

        if (user.getTradingStrategy() != null) {
            user.getTradingStrategy().onBuy(user, asset, quantity);
        }

        return true;
    }

    public boolean sellAsset(User user, String ticker, int quantity) {
        TradableAsset asset = stockMarket.getAsset(ticker);
        if (asset == null) {
            System.out.println("Asset not found!");
            return false;
        }

        if (!asset.canTradeNow()) {
            System.out.println("Trading not allowed at this time for " + ticker);
            return false;
        }

        if (!user.getPortfolio().hasAsset(ticker, quantity)) {
            System.out.println("Insufficient holdings!");
            return false;
        }

        TradeCommand sellCommand = new SellCommand(user, asset, quantity);
        sellCommand.execute();

        if (user.getTradingStrategy() != null) {
            user.getTradingStrategy().onSell(user, asset, quantity);
        }

        return true;
    }

    public void checkAndApplyDayTradingPenalties(User user) {
        LocalDate today = LocalDate.now();
        List<Holding> holdings = user.getPortfolio().getHoldings();

        for (Holding holding : holdings) {
            if (holding.getAsset() instanceof Stock && holding.isDayTrade()) {
                LocalDate purchaseDate = holding.getPurchaseDate();

                if (!purchaseDate.equals(today)) {
                    double penalty = holding.getTotalValue() * 0.05;
                    user.deductBalance(penalty);
                    holding.setDayTrade(false);

                    System.out.println("Day trading penalty applied: $" + String.format("%.2f", penalty)
                            + " for " + holding.getAsset().getTicker());

                    Transaction penaltyTransaction = new Transaction(
                            "PENALTY",
                            holding.getAsset().getTicker(),
                            0,
                            penalty,
                            LocalDateTime.now()
                    );
                    user.getTransactionHistory().addTransaction(penaltyTransaction);
                }
            }
        }
    }
}