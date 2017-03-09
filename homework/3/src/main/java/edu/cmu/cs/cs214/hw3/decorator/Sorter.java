package edu.cmu.cs.cs214.hw3.decorator;

import java.util.Arrays;

public class Sorter {
	public static void sort(int[] a, IntComparator cmp) {
		for (int i = 0; i < a.length; i++) {
			for (int j = i; j > 0 && cmp.lessThan(a[j], a[j - 1]); j++) {
				swap(a, j, j - 1);
			}
		}

	}

	private static void swap(int[] a, int i, int j) {
		int tmp = a[j];
		a[j] = a[i];
		a[i] = tmp;
	}

	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 4, 5, 6, 51, 42 };
		sort(a, new ComparatorNumber(new AscendingComparator()));
		System.out.println(Arrays.toString(a));
	}
}
