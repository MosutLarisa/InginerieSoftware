package model;

import java.time.*;

public class Stock extends TradableAsset {
    public Stock(String ticker, String name, double price, int availableQuantity) {
        super(ticker, name, price, availableQuantity);
    }

    @Override
    public boolean canTradeNow() {
        LocalDateTime now = LocalDateTime.now();
        DayOfWeek day = now.getDayOfWeek();
        int hour = now.getHour();

        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            return false;
        }

        return hour >= 9 && hour < 18;
    }

    @Override
    public String toString() {
        return "[STOCK] " + super.toString();
    }
}