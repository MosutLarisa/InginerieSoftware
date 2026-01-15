package market;

import java.util.*;
import model.*;

public class Portfolio {
    private List<Holding> holdings;

    public Portfolio() {
        this.holdings = new ArrayList<>();
    }

    public void addHolding(TradableAsset asset, int quantity, boolean isDayTrade) {
        for (Holding holding : holdings) {
            if (holding.getAsset().getTicker().equals(asset.getTicker())) {
                holding.addQuantity(quantity);
                return;
            }
        }
        holdings.add(new Holding(asset, quantity, isDayTrade));
    }

    public void removeHolding(String ticker, int quantity) {
        Iterator<Holding> iterator = holdings.iterator();
        while (iterator.hasNext()) {
            Holding holding = iterator.next();
            if (holding.getAsset().getTicker().equals(ticker)) {
                holding.reduceQuantity(quantity);
                if (holding.getQuantity() <= 0) {
                    iterator.remove();
                }
                return;
            }
        }
    }

    public boolean hasAsset(String ticker, int quantity) {
        for (Holding holding : holdings) {
            if (holding.getAsset().getTicker().equals(ticker) &&
                    holding.getQuantity() >= quantity) {
                return true;
            }
        }
        return false;
    }

    public Holding getHolding(String ticker) {
        for (Holding holding : holdings) {
            if (holding.getAsset().getTicker().equals(ticker)) {
                return holding;
            }
        }
        return null;
    }

    public double getTotalValue() {
        double total = 0;
        for (Holding holding : holdings) {
            total += holding.getTotalValue();
        }
        return total;
    }

    public void displayPortfolio() {
        if (holdings.isEmpty()) {
            System.out.println("No holdings.");
            return;
        }

        for (Holding holding : holdings) {
            System.out.println(holding);
        }
        System.out.println("Total Portfolio Value: $" + String.format("%.2f", getTotalValue()));
    }

    public List<Holding> getHoldings() {
        return new ArrayList<>(holdings);
    }
}