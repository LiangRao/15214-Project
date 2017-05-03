package edu.cmu.cs.cs214.hw6.bank;

import jdk.nashorn.internal.ir.annotations.Immutable;
import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class SimInterest implements Runnable {
    @Immutable
    private static final double INTEREST_RATE = 0.01;
    private final Economy economy;

    public SimInterest(Economy e) {
        this.economy = e;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            paySalaries();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    private void paySalaries() {

       // synchronized (economy.getBank().getAccountsMap()) {
            for (Account account : economy.getBank().getAccounts()) {
                if (account.getOwner() != economy.getBank()) {
                    //earn interest
                    long interest = Math.round(INTEREST_RATE * account.getBalance());
                    economy.getBank().transferFunds(economy.getBank(), account.getOwner(), interest);
                    //pay fees
                    economy.getBank().transferFunds(account.getOwner(), economy.getBank(), account.getFee());
                }
            }
        //}
    }

}
