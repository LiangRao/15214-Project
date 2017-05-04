package edu.cmu.cs.cs214.hw6.bank;

import jdk.nashorn.internal.ir.annotations.Immutable;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.NotThreadSafe;
import net.jcip.annotations.ThreadSafe;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * A bank has a number of accounts and also has an own account
 * it owns itself.
 */
@NotThreadSafe
public class Bank implements AccountOwner {
    @GuardedBy("accounts")
    private final Map<AccountOwner, Account> accounts = new ConcurrentHashMap<>();
    @Immutable
    private final Account bankAccount = new Account(this, 0, 0);

    public boolean transferFunds(AccountOwner from, AccountOwner to, long funds) {
        Account f = getAccount(from);
        Account t = getAccount(to);
        if (f != null && t != null/* && f.getBalance() >= funds && (Long.MAX_VALUE - funds > t.getBalance())*/) {
            synchronized (this){
                f.setBalance(f.getBalance() - funds);
                t.setBalance(t.getBalance() + funds);
            }
            return true;
        }
        return false;
    }

    public Account getAccount(AccountOwner owner) {
            if (bankAccount.getOwner() == owner)
                return bankAccount;
            return accounts.get(owner);
    }

    public Account getOwnFunds() {
        return bankAccount;
    }

    public void addAccount(Account account) {
            accounts.put(account.getOwner(), account);
    }

    public Collection<Account> getAccounts() {
            return accounts.values();
    }

    public void closeAccount(AccountOwner owner) {
            accounts.remove(owner);
    }
}
