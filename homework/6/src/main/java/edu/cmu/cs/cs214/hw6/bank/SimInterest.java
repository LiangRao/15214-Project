package edu.cmu.cs.cs214.hw6.bank;

import jdk.nashorn.internal.ir.annotations.Immutable;
import net.jcip.annotations.NotThreadSafe;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class SimInterest implements Runnable {
    private static final double INTEREST_RATE = 0.01;
    @Immutable
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

            for (Account account : economy.getBank().getAccounts()) {
                if (account.getOwner() != economy.getBank()) {
                    //earn interest
                    long interest = Math.round(INTEREST_RATE * account.getBalance());
                    economy.getBank().transferFunds(economy.getBank(), account.getOwner(), interest);
                    //pay fees
                    economy.getBank().transferFunds(account.getOwner(), economy.getBank(), account.getFee());
                }
            }
    }

}
