package model;

import java.util.*;
import observer.PriceObserver;

public abstract class TradableAsset {
    protected String ticker;
    protected String name;
    protected double price;
    protected int availableQuantity;
    protected List<PriceObserver> observers;

    public TradableAsset(String ticker, String name, double price, int availableQuantity) {
        this.ticker = ticker;
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
        this.observers = new ArrayList<>();
    }

    public void addObserver(PriceObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(PriceObserver observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(double oldPrice, double newPrice) {
        for (PriceObserver observer : observers) {
            observer.update(ticker, oldPrice, newPrice);
        }
    }

    public void setPrice(double newPrice) {
        double oldPrice = this.price;
        this.price = newPrice;
        if (oldPrice != newPrice) {
            notifyObservers(oldPrice, newPrice);
        }
    }

    public abstract boolean canTradeNow();

    public void reduceQuantity(int quantity) {
        this.availableQuantity -= quantity;
    }

    public void increaseQuantity(int quantity) {
        this.availableQuantity += quantity;
    }

    public String getTicker() { return ticker; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getAvailableQuantity() { return availableQuantity; }

    @Override
    public String toString() {
        return String.format("%s (%s) - Price: $%.2f, Available: %d",
                name, ticker, price, availableQuantity);
    }
}