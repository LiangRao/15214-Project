package edu.cmu.cs.cs214.hw3.decorator;

public class IntComparatorDecorator implements IntComparator {
	private final IntComparator intComparator;

	public IntComparatorDecorator(IntComparator intComparator) {
		this.intComparator = intComparator;
	}

	@Override
	public boolean lessThan(int i, int j) {
		return intComparator.lessThan(i, j);
	}

}
