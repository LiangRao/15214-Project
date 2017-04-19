package edu.cmu.cs.cs214.hw6.bank;


import java.util.ArrayList;

/**
 * A simulation thread that regularly pays salary to employees
 * every 200ms.
 * If a shop is no longer solvent, it closes.
 */
public class SimSalary implements Runnable {
    private static final int SALARY = 1000;
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
            for (Person employee : s.getEmployees())
                economy.getBank().transferFunds(s, employee, SALARY);
            //close shops without funds
            Account shopAccount = economy.getBank().getAccount(s);
            if (shopAccount.getBalance() < 0) {
                economy.closeShop(s);
            }
        }
    }
}
