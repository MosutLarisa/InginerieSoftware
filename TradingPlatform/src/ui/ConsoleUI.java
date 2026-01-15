package ui;

import java.util.Scanner;
import core.TradingPlatform;
import model.*;
import market.StockMarket;
import factory.AssetFactory;
import command.*;
import strategy.*;

public class ConsoleUI {
    private TradingPlatform platform;
    private Scanner scanner;

    public ConsoleUI() {
        this.platform = TradingPlatform.getInstance();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("=== Welcome to Trading Platform ===\n");
        initializeSampleAssets();

        User currentUser = null;
        while (true) {
            if (currentUser == null) {
                currentUser = handleLoginMenu();
                if (currentUser == null && shouldExit()) {
                    break;
                }
            } else {
                handleMainMenu(currentUser);
                currentUser = null;
            }
        }

        scanner.close();
        System.out.println("Goodbye!");
    }

    private void initializeSampleAssets() {
        StockMarket market = StockMarket.getInstance();

        market.addAsset(AssetFactory.createAsset("STOCK", "AAPL", "Apple Inc.", 150.00, 1000));
        market.addAsset(AssetFactory.createAsset("STOCK", "GOOGL", "Google", 2800.00, 500));
        market.addAsset(AssetFactory.createAsset("STOCK", "MSFT", "Microsoft", 300.00, 800));
        market.addAsset(AssetFactory.createAsset("STOCK", "TSLA", "Tesla", 700.00, 600));

        market.addAsset(AssetFactory.createAsset("CRYPTO", "BTC", "Bitcoin", 45000.00, 100));
        market.addAsset(AssetFactory.createAsset("CRYPTO", "ETH", "Ethereum", 3000.00, 500));

        System.out.println("Sample assets loaded!\n");
    }

    private User handleLoginMenu() {
        System.out.println("\n1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choose option: ");

        int choice = InputHelper.getIntInput(scanner);

        switch (choice) {
            case 1: return registerUser();
            case 2: return loginUser();
            case 3: return null;
            default:
                System.out.println("Invalid option!");
                return null;
        }
    }

    private boolean shouldExit() {
        return true;
    }

    private User registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        System.out.print("Enter initial balance: ");
        double balance = InputHelper.getDoubleInput(scanner);

        User user = platform.registerUser(username, password, balance);
        if (user != null) {
            System.out.println("Registration successful!");
            return user;
        } else {
            System.out.println("Registration failed! Username may already exist.");
            return null;
        }
    }

    private User loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        User user = platform.login(username, password);
        if (user != null) {
            System.out.println("Login successful! Welcome " + username);
            return user;
        } else {
            System.out.println("Login failed! Invalid credentials.");
            return null;
        }
    }

    private void handleMainMenu(User user) {
        while (true) {
            displayMainMenu();
            int choice = InputHelper.getIntInput(scanner);

            if (choice == 11) {
                System.out.println("Logging out...");
                return;
            }

            processMenuChoice(choice, user);
        }
    }

    private void displayMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Portfolio");
        System.out.println("2. Search");
        System.out.println("3. Buy");
        System.out.println("4. Sell");
        System.out.println("5. Set Trading Strategy");
        System.out.println("6. Transaction History");
        System.out.println("7. Add to Watchlist");
        System.out.println("8. View Watchlist");
        System.out.println("9. Update Price ");
        System.out.println("10. Penalties");
        System.out.println("11. Logout");
        System.out.print("Choose option: ");
    }

    private void processMenuChoice(int choice, User user) {
        switch (choice) {
            case 1: viewPortfolio(user); break;
            case 2: searchAssets(); break;
            case 3: buyAsset(user); break;
            case 4: sellAsset(user); break;
            case 5: setTradingStrategy(user); break;
            case 6: viewTransactionHistory(user); break;
            case 7: addToWatchlist(user); break;
            case 8: viewWatchlist(user); break;
            case 9: updateAssetPrice(); break;
            case 10: checkPenalties(user); break;
            default: System.out.println("Invalid option!");
        }
    }

    private void viewPortfolio(User user) {
        System.out.println("\n=== Your Portfolio ===");
        System.out.println("Cash Balance: $" + String.format("%.2f", user.getBalance()));
        System.out.println("\nHoldings:");
        user.getPortfolio().displayPortfolio();
    }

    private void searchAssets() {
        System.out.print("Enter ticker symbol or name to search: ");
        String query = scanner.nextLine().trim();

        StockMarket market = StockMarket.getInstance();
        TradableAsset asset = market.findAsset(query);

        if (asset != null) {
            System.out.println("\n" + asset);
        } else {
            System.out.println("No asset found matching: " + query);
        }
    }

    private void buyAsset(User user) {
        System.out.print("Enter ticker symbol: ");
        String ticker = scanner.nextLine().trim().toUpperCase();
        System.out.print("Enter quantity: ");
        int quantity = InputHelper.getIntInput(scanner);

        if (platform.buyAsset(user, ticker, quantity)) {
            System.out.println("Purchase successful!");
        } else {
            System.out.println("Purchase failed!");
        }
    }

    private void sellAsset(User user) {
        System.out.print("Enter ticker symbol: ");
        String ticker = scanner.nextLine().trim().toUpperCase();
        System.out.print("Enter quantity: ");
        int quantity = InputHelper.getIntInput(scanner);

        if (platform.sellAsset(user, ticker, quantity)) {
            System.out.println("Sale successful!");
        } else {
            System.out.println("Sale failed!");
        }
    }

    private void setTradingStrategy(User user) {
        System.out.println("\n=== Select Trading Strategy ===");
        System.out.println("1. Day Trading");
        System.out.println("2. Long-term Investing");
        System.out.println("3. Auto Trading");
        System.out.print("Choose strategy: ");

        int choice = InputHelper.getIntInput(scanner);
        TradingStrategy strategy = null;

        switch (choice) {
            case 1:
                strategy = new DayTradingStrategy();
                System.out.println("Day Trading strategy activated.");
                break;
            case 2:
                strategy = new LongTermInvestingStrategy();
                System.out.println("Long-term Investing strategy activated.");
                break;
            case 3:
                System.out.print("Enter ticker: ");
                String ticker = scanner.nextLine().trim().toUpperCase();
                System.out.print("Enter buy threshold: ");
                double buyThreshold = InputHelper.getDoubleInput(scanner);
                System.out.print("Enter sell threshold: ");
                double sellThreshold = InputHelper.getDoubleInput(scanner);
                strategy = new AutoTradingStrategy(ticker, buyThreshold, sellThreshold);
                System.out.println("Auto Trading strategy activated for " + ticker);
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }

        user.setTradingStrategy(strategy);
    }

    private void viewTransactionHistory(User user) {
        System.out.println("\n=== Transaction History ===");
        user.getTransactionHistory().displayHistory();
    }

    private void addToWatchlist(User user) {
        System.out.print("Enter ticker symbol to watch: ");
        String ticker = scanner.nextLine().trim().toUpperCase();

        StockMarket market = StockMarket.getInstance();
        TradableAsset asset = market.getAsset(ticker);

        if (asset != null) {
            asset.addObserver(user);
            System.out.println(ticker + " added to your watchlist.");
        } else {
            System.out.println("Asset not found!");
        }
    }

    private void viewWatchlist(User user) {
        System.out.println("\n=== Watchlist Notifications ===");
        if (user.getNotifications().isEmpty()) {
            System.out.println("No notifications.");
        } else {
            for (String notification : user.getNotifications()) {
                System.out.println(notification);
            }
        }
    }

    private void updateAssetPrice() {
        System.out.print("Enter ticker symbol: ");
        String ticker = scanner.nextLine().trim().toUpperCase();
        System.out.print("Enter new price: ");
        double newPrice = InputHelper.getDoubleInput(scanner);

        StockMarket market = StockMarket.getInstance();
        market.updateAssetPrice(ticker, newPrice);
    }

    private void checkPenalties(User user) {
        platform.checkAndApplyDayTradingPenalties(user);
    }
}