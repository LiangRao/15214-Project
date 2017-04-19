package edu.cmu.cs.cs214.hw6.bank;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ckaestne on 3/23/2017.
 */
public class Shop implements AccountOwner {
    private final Set<Person> employees = new HashSet<>();
    private boolean isClosed = false;

    public void addEmployee(Person p) {
        employees.add(p);
    }

    public Set<Person> getEmployees() {
        return employees;
    }

    public void close() {
        isClosed = true;
        //fire all employees
        employees.clear();
    }

    public boolean isClosed() {
        return isClosed;
    }
}
