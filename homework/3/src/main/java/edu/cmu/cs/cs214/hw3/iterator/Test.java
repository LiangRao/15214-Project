package edu.cmu.cs.cs214.hw3.iterator;

public class Test {
	public int foo(int a, int b) {
		if (a > 3)
			a++;
		else
			b++;
		if (a + b > 10)
			return a + b;
		return b;
	}

	public static void main(String[] args) {
		Test aTest = new Test();
		String aString = "abc";
		String bString = "abc";
		System.out.println(aString.equals(bString));
		System.out.println(aTest.foo(6, 5));
	}

}
