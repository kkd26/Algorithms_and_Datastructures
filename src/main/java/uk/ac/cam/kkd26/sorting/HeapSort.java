/*
 * Copyright (c) 2020 Krzysztof Druciarek.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms are permitted
 * provided that the above copyright notice and this paragraph are
 * duplicated in all such forms and that any documentation,
 * advertising materials, and other materials related to such
 * distribution and use acknowledge that the software was developed
 * by the <organization>. The name of the
 * <organization> may not be used to endorse or promote products derived
 * from this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND WITHOUT ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 */

package uk.ac.cam.kkd26.sorting;

public class HeapSort extends Sort {

	private static int border;

	private static Integer getRight(int[] arr, int i) {
		if (2 * i + 2 > border) return null;
		return 2 * i + 2;
	}

	private static Integer getLeft(int[] arr, int i) {
		if (2 * i + 1 > border) return null;
		return 2 * i + 1;
	}

	private static Integer getParent(int[] arr, int i) {
		if ((i - 1) / 2 >= 0) return (i - 1) / 2;
		return null;
	}

	private static Integer getGreater(int[] arr, int i) {
		Integer r = getRight(arr, i);
		Integer l = getLeft(arr, i);

		if (r != null && l != null) {
			if (arr[r] > arr[l]) {
				return r;
			}
			return l;
		}
		if (r != null) return r;
		return l;
	}

	private static boolean isMaxHeap(int[] arr, Integer i) {
		if (i == null) return true;

		Integer r = getRight(arr, i);
		Integer l = getLeft(arr, i);

		if (r != null && l != null) {
			return (arr[i] >= arr[r]) && (arr[i] >= arr[l]);
		}
		if (r != null) return arr[i] >= arr[r];
		if (l != null) return arr[i] >= arr[l];
		return true;
	}

	public static void swap(int[] arr, Integer i, Integer j) {
		if (i == null || j == null) return;
		if (i > border || j > border) return;
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	public static void heapify(int[] arr, Integer i) {
		if (i == null) return;
		Integer g = getGreater(arr, i);
		swap(arr, i, g);
		if (!isMaxHeap(arr, g)) heapify(arr, g);
	}

	public static void init(int[] arr) {
		int s = arr.length;
		border = s - 1;
		for (int i = s - 1; i >= 0; i--) {
			if (isMaxHeap(arr, i)) continue;
			heapify(arr, i);
		}
	}

	public static void sort(int[] arr) {
		init(arr);
		while (border > 0) {
			swap(arr, 0, border);
			border--;
			if (!isMaxHeap(arr, 0)) heapify(arr, 0);
		}
	}

	public static void print(int[] a) {
		for (int i : a) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] a = createRandom(10000000); //time 8760
		//System.out.println(Arrays.toString(a));
		long startTime = System.currentTimeMillis();
		sort(a);
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
		//System.out.println(Arrays.toString(a));
	}
}