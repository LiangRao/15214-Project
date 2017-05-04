package edu.cmu.cs.cs214.hw6.bank;


import jdk.nashorn.internal.ir.annotations.Immutable;
import net.jcip.annotations.NotThreadSafe;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Map;

/**
 * A simulation thread that regularly pays salary to employees
 * every 200ms.
 * If a shop is no longer solvent, it closes.
 */
@ThreadSafe
public class SimSalary implements Runnable {
    private static final int SALARY = 1000;
    @Immutable
    private final Economy economy;

    public SimSalary(Economy e) {
        this.economy = e;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            paySalaries();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    private void paySalaries() {
            for (Shop s : new ArrayList<>(economy.getShops())) {

                //sychronized employees to avoid ConcurrentModification Exception
                synchronized (s.getEmployees()) {
                    for (Person employee : s.getEmployees())
                        economy.getBank().transferFunds(s, employee, SALARY);
                }
                //close shops without funds (except for the very last one)
                Account shopAccount = economy.getBank().getAccount(s);
                if (shopAccount.getBalance() < 0 && economy.getShops().size() > 1) {
                    economy.closeShop(s);
                }

        }
    }
}
