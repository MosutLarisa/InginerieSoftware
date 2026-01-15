package factory;

import model.*;

public class AssetFactory {
    public static TradableAsset createAsset(String type, String ticker, String name,
                                            double price, int quantity) {
        if (type.equalsIgnoreCase("STOCK")) {
            return new Stock(ticker, name, price, quantity);
        } else if (type.equalsIgnoreCase("CRYPTO")) {
            return new Crypto(ticker, name, price, quantity);
        }
        throw new IllegalArgumentException("Unknown asset type: " + type);
    }
}