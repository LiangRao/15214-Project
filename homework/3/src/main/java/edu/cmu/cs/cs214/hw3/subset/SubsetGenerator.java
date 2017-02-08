package edu.cmu.cs.cs214.hw3.subset;

import java.util.ArrayList;
import java.util.List;

public class SubsetGenerator {
	/**
	 * return all subset of digits array
	 * 
	 * @param n
	 *            the length of the subset
	 * @return all subset of digits array
	 */
	public List<Integer[]> subset(int n) {
		Integer[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		List<Integer[]> resultList = new ArrayList<Integer[]>();
		int k = array.length;
		if (k < n) {
			throw new IllegalStateException("the input" + n + "is larger than the length of digits array");
		}

		// intialize a array for late use
		Integer[] temp = new Integer[k];
		for (int i = 0; i < k; i++) {
			temp[i] = 0;
		}
		for (int i = 0; i < n; i++) {
			temp[i] = 1;
		}

		boolean condition = true;
		resultList.add(getSubset(temp, array, n));
		do {
			int index = 0;
			int count = 0;
			boolean tempCondition = true;

			for (int i = 0; i < k - 1; i++) {
				if (temp[i] == 1 && temp[i + 1] == 0) {
					temp[i] = 0;
					temp[i + 1] = 1;
					index = i;
					break;
				}
			}
			//
			for (int i = 0; i < index; i++) {
				if (temp[i] == 1) {
					count++;
				}
			}
			if (count != index) {
				for (int i = 0; i < index; i++) {
					if (i < count) {
						temp[i] = 1;
					} else {
						temp[i] = 0;
					}
				}
			}

			resultList.add(getSubset(temp, array, n));
			// check if the all one integers in the temp[] are on the right side
			for (int i = k - n; i < k; i++) {
				if (temp[i] == 0) {
					tempCondition = false;

					break;
				}
			}

			if (tempCondition) {
				condition = false;
			} else {
				condition = true;
			}
		} while (condition);

		return resultList;
	}

	private Integer[] getSubset(Integer[] temp, Integer[] array, int m) {
		Integer[] result = new Integer[m];
		int pos = 0;
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] == 1) {
				result[pos] = array[i];
				pos++;
			}
		}

		return result;
	}
}
