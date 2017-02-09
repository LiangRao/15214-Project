package edu.cmu.cs.cs214.hw3.iterator;

import java.util.Iterator;
import java.util.List;

/**
 * Implementing this interface allows to generate all permutation of Array with
 * any types, and can iterate all permutations by inheriting the method from
 * Iterable<T>. It is a iterator pattern.
 * 
 * @author raoliang
 *
 * @param <T>
 */
public interface PermutationGenerator<T> extends Iterable<T[]> {
	/**
	 * Return all permutations of a specific array
	 * 
	 * @param list
	 *            a array needs to be permutated
	 * @param size
	 *            the size of the array
	 * @return all permutations of the array
	 */
	List<T[]> perm(T[] list, int size);

	/**
	 * In order to traverse permutations in the permutation list
	 * 
	 * @return a iterator used to traverse permutations
	 */
	Iterator<T[]> iterator();
}
