package edu.cmu.cs.cs214.hw3.decorator;

public class DescendingComparator implements IntComparator {

	@Override
	public boolean lessThan(int i, int j) {
		// TODO Auto-generated method stub
		return i > j;
	}

}
