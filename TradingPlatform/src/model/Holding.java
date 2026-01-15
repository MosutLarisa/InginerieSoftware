package model;

import java.time.LocalDate;

public class Holding {
    private TradableAsset asset;
    private int quantity;
    private boolean isDayTrade;
    private LocalDate purchaseDate;

    public Holding(TradableAsset asset, int quantity, boolean isDayTrade) {
        this.asset = asset;
        this.quantity = quantity;
        this.isDayTrade = isDayTrade;
        this.purchaseDate = LocalDate.now();
    }

    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    public void reduceQuantity(int amount) {
        this.quantity -= amount;
    }

    public double getTotalValue() {
        return asset.getPrice() * quantity;
    }

    public TradableAsset getAsset() { return asset; }
    public int getQuantity() { return quantity; }
    public boolean isDayTrade() { return isDayTrade; }
    public void setDayTrade(boolean isDayTrade) { this.isDayTrade = isDayTrade; }
    public LocalDate getPurchaseDate() { return purchaseDate; }

    @Override
    public String toString() {
        String tradeType = isDayTrade ? "[DAY TRADE]" : "[LONG-TERM]";
        return String.format("%s %s - Quantity: %d, Value: $%.2f (Purchased: %s)",
                tradeType, asset.getTicker(), quantity, getTotalValue(), purchaseDate);
    }
}