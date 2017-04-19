package edu.cmu.cs.cs214.hw6.bank;


/**
 * a private bank account owner with a name
 */
public class Person implements AccountOwner {
    private String name;

    private static int personCounter = 0;

    public Person(String name) {
        this.name = name;
    }

    public Person() {
        this.name = "Person" + (++personCounter);
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }
}
