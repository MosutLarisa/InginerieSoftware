package strategy;

import model.*;
import market.StockMarket;
import command.*;

public class AutoTradingStrategy implements TradingStrategy {
    private String targetTicker;
    private double buyThreshold;
    private double sellThreshold;

    public AutoTradingStrategy(String targetTicker, double buyThreshold, double sellThreshold) {
        this.targetTicker = targetTicker;
        this.buyThreshold = buyThreshold;
        this.sellThreshold = sellThreshold;
    }

    @Override
    public void onBuy(User user, TradableAsset asset, int quantity) {
        System.out.println("[Auto Trading] Manual buy executed for " + asset.getTicker());
    }

    @Override
    public void onSell(User user, TradableAsset asset, int quantity) {
        System.out.println("[Auto Trading] Manual sell executed for " + asset.getTicker());
    }

    public void checkAndExecuteTrade(User user, String ticker, double currentPrice) {
        if (!ticker.equals(targetTicker)) {
            return;
        }

        TradableAsset asset = StockMarket.getInstance().getAsset(ticker);
        if (asset == null || !asset.canTradeNow()) {
            return;
        }

        if (currentPrice <= buyThreshold) {
            int quantity = (int) (user.getBalance() / currentPrice);
            if (quantity > 0 && quantity <= asset.getAvailableQuantity()) {
                System.out.println("[Auto Trading] Triggering auto-buy for " + ticker +
                        " @ $" + currentPrice);
                core.TradingPlatform platform = core.TradingPlatform.getInstance();
                platform.buyAsset(user, ticker, Math.min(quantity, 10));
            }
        }

        if (currentPrice >= sellThreshold) {
            Holding holding = user.getPortfolio().getHolding(ticker);
            if (holding != null && holding.getQuantity() > 0) {
                System.out.println("[Auto Trading] Triggering auto-sell for " + ticker +
                        " @ $" + currentPrice);
                core.TradingPlatform platform = core.TradingPlatform.getInstance();
                platform.sellAsset(user, ticker, holding.getQuantity());
            }
        }
    }

    @Override
    public String getStrategyName() {
        return String.format("Auto Trading Strategy (Buy: $%.2f, Sell: $%.2f)",
                buyThreshold, sellThreshold);
    }

//    public String getTargetTicker() { return targetTicker; }
//    public double getBuyThreshold() { return buyThreshold; }
//    public double getSellThreshold() { return sellThreshold; }
}