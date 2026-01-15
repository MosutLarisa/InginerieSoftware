package history;

import java.util.*;

public class TransactionHistory {
    private List<Transaction> transactions;

    public TransactionHistory() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public void displayHistory() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

//    public List<Transaction> getTransactionsByType(String type) {
//        List<Transaction> filtered = new ArrayList<>();
//        for (Transaction t : transactions) {
//            if (t.getType().equals(type)) {
//                filtered.add(t);
//            }
//        }
//        return filtered;
//    }
//
//    public List<Transaction> getTransactionsByTicker(String ticker) {
//        List<Transaction> filtered = new ArrayList<>();
//        for (Transaction t : transactions) {
//            if (t.getTicker().equals(ticker)) {
//                filtered.add(t);
//            }
//        }
//        return filtered;
//    }
}