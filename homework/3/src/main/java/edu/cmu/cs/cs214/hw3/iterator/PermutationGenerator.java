package edu.cmu.cs.cs214.hw3.iterator;

import java.util.Iterator;
import java.util.List;

public interface PermutationGenerator<T> extends Iterable<T[]> {
	List<T[]> perm(T[] list, int size);

	Iterator<T[]> iterator();
}
