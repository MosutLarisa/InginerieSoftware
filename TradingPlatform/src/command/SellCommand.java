package command;

import java.time.LocalDateTime;
import model.*;
import history.Transaction;

public class SellCommand implements TradeCommand {

    private User user;
    private TradableAsset asset;
    private int quantity;
    private Transaction transaction;

    public SellCommand(User user, TradableAsset asset, int quantity) {
        this.user = user;
        this.asset = asset;
        this.quantity = quantity;
        this.transaction = null;
    }

    @Override
    public void execute() {
        double totalRevenue = asset.getPrice() * quantity;

        user.addBalance(totalRevenue);
        asset.increaseQuantity(quantity);
        user.getPortfolio().removeHolding(asset.getTicker(), quantity);
        transaction = new Transaction(
                "SELL",
                asset.getTicker(),
                quantity,
                asset.getPrice(),
                LocalDateTime.now()
        );
        user.getTransactionHistory().addTransaction(transaction);

        System.out.println("Executed SELL: " + quantity + " × " +
                asset.getTicker() + " @ $" + asset.getPrice());
    }
    @Override
    public Transaction getTransaction() {
        return transaction;
    }
}
