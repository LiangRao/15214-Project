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
        assert p.getEmployer() == null;
        //synchronized (employees) {
            employees.add(p);
        //}
        p.setEmployer(this);
    }

    public void removeEmployee(Person p) {
        //synchronized (employees) {
            employees.remove(p);
            p.setEmployer(null);
        //}
    }

    public Set<Person> getEmployees() {
        return employees;
    }

    public void close() {
        isClosed = true;
        //fire all employees
        for (Person p : employees)
            p.setEmployer(null);
        employees.clear();
    }

    public boolean isClosed() {
        return isClosed;
    }
}
