package edu.cmu.cs.cs214.hw6.bank;


/**
 * a thread in the simulation that performs a large
 * number of random transactions, where solvent people
 * buy from a random shop for 100$.
 */
public class SimShopping implements Runnable {
    private static final int STEPS = 10000000;
    private final Economy economy;

    public SimShopping(Economy e) {
        this.economy = e;
    }

    @Override
    public void run() {
        for (int i = 0; i < STEPS; i++)
            performRandomTransaction();

    }

    private void performRandomTransaction() {


            Bank bank = economy.getBank();

            //synchronized (economy.getBank().getAccountsMap()) {
                Person customer = economy.getRandomCustomer();
                Shop shop = economy.getRandomShop();
                //System.out.println(bank.getAccount(customer));
                if (bank.getAccount(customer).getBalance() >= 100) {
                    //System.out.println(bank.getAccount(customer).getBalance());
                    bank.transferFunds(customer, shop, 100);
                }
           // }

        //}
    }
}
