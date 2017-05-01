package edu.cmu.cs.cs214.hw6.bank;

import net.jcip.annotations.GuardedBy;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * A bank has a number of accounts and also has an own account
 * it owns itself.
 */
public class Bank implements AccountOwner {
    @GuardedBy("accounts")
    private final Map<AccountOwner, Account> accounts = new ConcurrentHashMap<>();
    private final Account bankAccount = new Account(this, 0, 0);

    public boolean transferFunds(AccountOwner from, AccountOwner to, long funds) {
        Account f = getAccount(from);
        Account t = getAccount(to);
        if (f != null && t != null/* && f.getBalance() >= funds && (Long.MAX_VALUE - funds > t.getBalance())*/) {

            synchronized (f) {
                f.setBalance(f.getBalance() - funds);
            }
            synchronized (t) {
                t.setBalance(t.getBalance() + funds);
            }
                //}

            return true;
        }
        return false;
    }

    public Account getAccount(AccountOwner owner) {
        //synchronized (accounts) {
            if (bankAccount.getOwner() == owner)
                return bankAccount;
            return accounts.get(owner);
        //}
    }

    public Account getOwnFunds() {
        return bankAccount;
    }

    public void addAccount(Account account) {
        synchronized (accounts) {
            accounts.put(account.getOwner(), account);
        }
    }

    public Collection<Account> getAccounts() {
        //synchronized (accounts) {
            return accounts.values();
        //}
    }

    public synchronized void closeAccount(AccountOwner owner) {
       // synchronized (accounts) {
            accounts.remove(owner);
       // }
    }
    public Map<AccountOwner, Account> getAccountsMap() {
        //synchronized (accounts) {
        return accounts;
        //}
    }

}
