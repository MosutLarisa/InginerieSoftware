package market;

import java.util.*;
import model.TradableAsset;

public class StockMarket {
    private static StockMarket instance;
    private Map<String, TradableAsset> assets;

    private StockMarket() {
        this.assets = new HashMap<>();
    }

    public static synchronized StockMarket getInstance() {
        if (instance == null) {
            instance = new StockMarket();
        }
        return instance;
    }

    public void addAsset(TradableAsset asset) {
        assets.put(asset.getTicker(), asset);
    }

    public TradableAsset getAsset(String ticker) {
        return assets.get(ticker.toUpperCase());
    }

    public TradableAsset findAsset(String query) {
        String upperQuery = query.toUpperCase();

        TradableAsset asset = assets.get(upperQuery);
        if (asset != null) {
            return asset;
        }

        for (TradableAsset a : assets.values()) {
            if (a.getName().toUpperCase().contains(upperQuery)) {
                return a;
            }
        }

        return null;
    }

    public void updateAssetPrice(String ticker, double newPrice) {
        TradableAsset asset = getAsset(ticker);
        if (asset != null) {
            asset.setPrice(newPrice);
            System.out.println("Price updated for " + ticker + ": $" + newPrice);
        } else {
            System.out.println("Asset not found: " + ticker);
        }
    }

    public List<TradableAsset> getAllAssets() {
        return new ArrayList<>(assets.values());
    }
}