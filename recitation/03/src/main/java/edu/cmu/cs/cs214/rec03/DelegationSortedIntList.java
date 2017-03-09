package edu.cmu.cs.cs214.rec03;

/**
 * DelegationSortedIntList -- a variant of a SortedIntList that keeps count of
 * the number of attempted element insertions (not to be confused with the
 * current size, which goes down when an element is removed) and exports an
 * accessor (totalAdded) for this count.
 * 
 * @author Nora Shoemaker
 *
 */

// HINT: Take a look at the UML diagram to see what DelegationSortedIntList
// should implement.
public class DelegationSortedIntList implements IntegerList {
	// the number of attempted element insertions
	private int totalAdded;
	private IntegerList delegate = new SortedIntList();

	/**
	 * Gets the total number of attempted int insertions to the list since it.
	 * was created.
	 * 
	 * @return total number of integers added to the list.
	 */
	public int getTotalAdded() {
		return totalAdded;
	}

	@Override
	public int get(int index) {
		// TODO Auto-generated method stub

		return 0;
	}

	@Override
	public boolean remove(int num) {
		// TODO Auto-generated method stub
		return delegate.remove(num);
	}

	@Override
	public boolean removeAll(IntegerList list) {
		// TODO Auto-generated method stub
		return delegate.removeAll(list);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return delegate.size();
	}

	@Override
	public boolean add(int num) {
		totalAdded++;
		return delegate.add(num);
	}

	@Override
	public boolean addAll(IntegerList list) {
		// TODO Auto-generated method stub
		totalAdded += list.size();
		return delegate.addAll(list);
	}

}
