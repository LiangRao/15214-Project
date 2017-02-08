package edu.cmu.cs.cs214.hw3.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class HeapGenerator<T> implements PermutationGenerator<T> {
	private List<T[]> resultList = new ArrayList<T[]>();

	@Override
	public List<T[]> perm(T[] list, int size) {
		if (size == 1) {
			T[] add = Arrays.copyOf(list, list.length);
			resultList.add(add);
		} else {
			for (int i = 0; i < size; i++) {
				perm(list, size - 1);
				if ((size & 1) == 1) {
					T temp = list[0];
					list[0] = list[size - 1];
					list[size - 1] = temp;
				} else {
					T temp = list[i];
					list[i] = list[size - 1];
					list[size - 1] = temp;
				}
			}
		}

		return resultList;
	}

	@Override
	public Iterator<T[]> iterator() {
		return new HeapIterator();
	}

	private class HeapIterator implements Iterator<T[]> {
		private int count = 0;

		@Override
		public boolean hasNext() {
			if (resultList.size() > count) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public T[] next() {
			T[] result = resultList.get(count);
			count++;
			return result;
		}

	}

}
