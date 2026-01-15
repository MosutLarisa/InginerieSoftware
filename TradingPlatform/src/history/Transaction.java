package history;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String type;
    private String ticker;
    private int quantity;
    private double price;
    private LocalDateTime timestamp;

    public Transaction(String type, String ticker, int quantity, double price, LocalDateTime timestamp) {
        this.type = type;
        this.ticker = ticker;
        this.quantity = quantity;
        this.price = price;
        this.timestamp = timestamp;
    }

    public double getTotalAmount() {
        return price * quantity;
    }

    public String getType() { return type; }
    public String getTicker() { return ticker; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = timestamp.format(formatter);

        if (type.equals("PENALTY")) {
            return String.format("[%s] %s - Penalty: $%.2f for %s",
                    formattedTime, type, price, ticker);
        } else {
            return String.format("[%s] %s - %s: %d @ $%.2f (Total: $%.2f)",
                    formattedTime, type, ticker, quantity, price, getTotalAmount());
        }
    }
}