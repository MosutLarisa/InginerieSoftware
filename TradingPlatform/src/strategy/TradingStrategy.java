package strategy;

import model.*;

public interface TradingStrategy {
    void onBuy(User user, TradableAsset asset, int quantity);
    void onSell(User user, TradableAsset asset, int quantity);
    String getStrategyName();
}