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

public class MergeSort extends Sort {

	public static void sort(int[] arr) {
		sort(arr, 0, arr.length - 1);
	}

	public static void sort(int[] arr, int i, int j) {
		int s = j - i + 1;
		if (s < 2) return;

		int m = (i + j) / 2;
		sort(arr, i, m);
		sort(arr, m + 1, j);

		merge(arr, i, m, m + 1, j);
	}

	public static void merge(int[] arr, int b1, int e1, int b2, int e2) {
		int i1 = b1;
		int i2 = b2;
		int k = 0;

		int[] t = new int[e2 - b1 + 1];
		while (i1 <= e1 && i2 <= e2) {
			if (arr[i1] < arr[i2]) {
				t[k++] = arr[i1++];
			} else {
				t[k++] = arr[i2++];
			}
		}

		while (i1 <= e1) {
			t[k++] = arr[i1++];
		}

		while (i2 <= e2) {
			t[k++] = arr[i2++];
		}
		k--;
		while (k >= 0) {
			arr[b1 + k] = t[k];
			k--;
		}
	}

	public static void main(String[] args) {
		int[] a = createRandom(10000000); //time 2672
		//System.out.println(Arrays.toString(a));
		long startTime = System.currentTimeMillis();
		sort(a);
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
		//System.out.println(Arrays.toString(a));
	}
}
