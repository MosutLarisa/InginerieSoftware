package strategy;

import java.time.LocalDate;
import model.*;

public class DayTradingStrategy implements TradingStrategy {

    @Override
    public void onBuy(User user, TradableAsset asset, int quantity) {
        if (asset instanceof Stock) {
            System.out.println("⚠️  [Day Trading] Remember to sell " + asset.getTicker() +
                    " today to avoid 5% penalty!");
        }
    }
    @Override
    public void onSell(User user, TradableAsset asset, int quantity) {
        if (asset instanceof Stock) {
            Holding holding = user.getPortfolio().getHolding(asset.getTicker());
            if (holding != null && holding.isDayTrade()) {
                LocalDate today = LocalDate.now();
                if (holding.getPurchaseDate().equals(today)) {
                    System.out.println("[Day Trading] Successfully completed day trade for " +
                            asset.getTicker());
                }
            }
        }
    }
    @Override
    public String getStrategyName() {
        return "Day Trading Strategy";
    }
}