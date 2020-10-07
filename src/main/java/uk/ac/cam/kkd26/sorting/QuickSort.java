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

public class QuickSort extends Sort {

	public static void sort(int[] arr) {
		sort(arr, 0, arr.length - 1);
	}

	public static void sort(int[] arr, int i, int j) {
		int s = j - i + 1;
		if (s < 2) return;
		int pivot = i;
		int end = j;

		while (pivot != end) {
			if (pivot < end) {
				if (arr[pivot] > arr[end]) {
					swap(arr, pivot, end);
					int t = pivot;
					pivot = end;
					end = t;
				}
			} else {
				if (arr[pivot] < arr[end]) {
					swap(arr, pivot, end);
					int t = pivot;
					pivot = end;
					end = t;
				}
			}

			if (pivot < end) {
				end--;
			} else {
				end++;
			}
		}
		sort(arr, i, pivot);
		sort(arr, pivot + 1, j);
	}

	public static void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	public static void main(String[] args) {
		int[] a = createRandom(10000000); //time 1859
		//System.out.println(Arrays.toString(a));
		long startTime = System.currentTimeMillis();
		sort(a);
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
		//System.out.println(Arrays.toString(a));
	}
}
