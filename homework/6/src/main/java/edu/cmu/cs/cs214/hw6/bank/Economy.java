package edu.cmu.cs.cs214.hw6.bank;

import net.jcip.annotations.GuardedBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Main class of the simulation. Contains the bank, and a list of
 * people and shops.
 */
public class Economy {
    @GuardedBy("bank")
    private final Bank bank = new Bank();
    @GuardedBy("people")
    private final List<Person> people = new ArrayList<>();
    @GuardedBy("shops")
    private final List<Shop> shops = new ArrayList<>();
    private final List<Shop> closedShops = new ArrayList<>();
    private final Random random = new Random();

    public  Person getRandomCustomer() {
       synchronized (people) {
            return people.get(random.nextInt(people.size()));
        }
    }

    public Shop getRandomShop() {
        synchronized (shops) {
            return shops.get(random.nextInt(shops.size()));
        }
    }

    public  Bank getBank() {
        return bank;
    }

    public  List<Shop> getShops() {
        return shops;
    }

    public  void addShop(Shop s) {
        synchronized (shops) {
            shops.add(s);
        }
    }

    public void closeShop(Shop s) {
        synchronized (shops) {
            s.close();
            shops.remove(s);
        }
        synchronized (closedShops) {
            closedShops.add(s);
        }

    }

    public void addPerson(Person p) {
       synchronized (people) {
            people.add(p);
       }
    }

    public void removePerson(Person p) {
       synchronized (people) {
            people.remove(p);
        }
    }


    public void printReport() {

        synchronized (bank) {
            long privateFunds = 0;
            long shopFunds = 0;
            for (Shop s : shops)
                shopFunds += bank.getAccount(s).getBalance();
            for (Shop s : closedShops)
                shopFunds += bank.getAccount(s).getBalance();
            for (Person p : people)
                privateFunds += bank.getAccount(p).getBalance();
            long totalFunds = bank.getOwnFunds().getBalance() + privateFunds + shopFunds;
            System.out.println("Money in the economy: " + totalFunds);
            System.out.println("Money in private households: " + privateFunds);
            System.out.println("Money in corporations: " + shopFunds);
            System.out.println("Bank capital: " + bank.getOwnFunds().getBalance());
            System.out.println("Shops: " + shops.size());
            System.out.println("ClosedShops: " + closedShops.size());
        }
    }


}
