package edu.cmu.cs.cs214.hw3.decorator;

public class ComparatorNumber extends IntComparatorDecorator implements IntComparator {
	public ComparatorNumber(IntComparator intComparator) {
		super(intComparator);
	}

	@Override
	public boolean lessThan(int i, int j) {
		if (i != 42) {
			return super.lessThan(i, j);
		} else {
			return true;
		}
	}

}
