package model;

import java.util.*;
import observer.PriceObserver;
import market.Portfolio;
import strategy.TradingStrategy;
import strategy.AutoTradingStrategy;
import history.TransactionHistory;

public class User implements PriceObserver {
    private String username;
    private String password;
    private double balance;
    private Portfolio portfolio;
    private TransactionHistory transactionHistory;
    private TradingStrategy tradingStrategy;
    private List<String> notifications;

    public User(String username, String password, double initialBalance) {
        this.username = username;
        this.password = password;
        this.balance = initialBalance;
        this.portfolio = new Portfolio();
        this.transactionHistory = new TransactionHistory();
        this.notifications = new ArrayList<>();
    }

    @Override
    public void update(String ticker, double oldPrice, double newPrice) {
        String notification = String.format("[%s] Price changed: $%.2f -> $%.2f (%.2f%%)",
                ticker, oldPrice, newPrice, ((newPrice - oldPrice) / oldPrice) * 100);
        notifications.add(notification);
        System.out.println("Notification for " + username + ": " + notification);

        if (tradingStrategy instanceof AutoTradingStrategy) {
            AutoTradingStrategy autoStrategy = (AutoTradingStrategy) tradingStrategy;
            autoStrategy.checkAndExecuteTrade(this, ticker, newPrice);
        }
    }

    public void addBalance(double amount) {
        this.balance += amount;
    }

    public void deductBalance(double amount) {
        this.balance -= amount;
    }

    public boolean hasBalance(double amount) {
        return balance >= amount;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public double getBalance() { return balance; }
    public Portfolio getPortfolio() { return portfolio; }
    public TransactionHistory getTransactionHistory() { return transactionHistory; }
    public TradingStrategy getTradingStrategy() { return tradingStrategy; }
    public void setTradingStrategy(TradingStrategy strategy) { this.tradingStrategy = strategy; }
    public List<String> getNotifications() { return notifications; }
}