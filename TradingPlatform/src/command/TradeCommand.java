package command;

import history.Transaction;

public interface TradeCommand {
    void execute();
    Transaction getTransaction();
}