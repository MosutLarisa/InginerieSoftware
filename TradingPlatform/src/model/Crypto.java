package model;

public class Crypto extends TradableAsset {
    public Crypto(String ticker, String name, double price, int availableQuantity) {
        super(ticker, name, price, availableQuantity);
    }

    @Override
    public boolean canTradeNow() {
        return true;
    }

    @Override
    public String toString() {
        return "[CRYPTO] " + super.toString();
    }
}