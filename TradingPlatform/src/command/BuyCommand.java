package command;

import java.time.LocalDateTime;
import model.*;
import history.Transaction;
import strategy.DayTradingStrategy;

public class BuyCommand implements TradeCommand {

    private User user;
    private TradableAsset asset;
    private int quantity;
    private Transaction transaction;

    public BuyCommand(User user, TradableAsset asset, int quantity) {
        this.user = user;
        this.asset = asset;
        this.quantity = quantity;
        this.transaction = null;
    }

    @Override
    public void execute() {
        double totalCost = asset.getPrice() * quantity;

        user.deductBalance(totalCost);
        asset.reduceQuantity(quantity);

        boolean isDayTrade = user.getTradingStrategy() instanceof DayTradingStrategy;

        user.getPortfolio().addHolding(asset, quantity, isDayTrade);
        transaction = new Transaction(
                "BUY",
                asset.getTicker(),
                quantity,
                asset.getPrice(),
                LocalDateTime.now()
        );

        user.getTransactionHistory().addTransaction(transaction);

        System.out.println("Executed BUY: " + quantity + " × " +
                asset.getTicker() + " @ $" + asset.getPrice());
    }

    @Override
    public Transaction getTransaction() {
        return transaction;
    }
}