package observer;

public interface PriceObserver {
    void update(String ticker, double oldPrice, double newPrice);
}