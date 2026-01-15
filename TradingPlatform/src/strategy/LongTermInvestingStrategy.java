package strategy;

import model.*;

public class LongTermInvestingStrategy implements TradingStrategy {

    @Override
    public void onBuy(User user, TradableAsset asset, int quantity) {
        System.out.println("[Long-Term] Added " + quantity + " " + asset.getTicker() +
                " to your long-term portfolio.");
    }

    @Override
    public void onSell(User user, TradableAsset asset, int quantity) {
        System.out.println("[Long-Term] Sold " + quantity + " " + asset.getTicker() +
                " from your portfolio.");
    }

    @Override
    public String getStrategyName() {
        return "Long-Term Investing Strategy";
    }
}