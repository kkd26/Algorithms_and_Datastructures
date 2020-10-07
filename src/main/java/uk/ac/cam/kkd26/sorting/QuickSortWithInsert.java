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

public class QuickSortWithInsert extends Sort {

	/**
	 * Sorts a whole array arr
	 *
	 * @param arr array to sort
	 */
	public static void sort(int[] arr) {
		sort(arr, 0, arr.length - 1);
	}

	/**
	 * Sorts array arr from index i to index j included using quick sort approach.
	 *
	 * @param arr array to sort
	 * @param i   starting index
	 * @param j   end index
	 */
	public static void sort(int[] arr, int i, int j) {
		int s = j - i + 1; // size of array to sort
		if (s <= 5) { // case of smaller array
			inSort(arr, i, j); // insertion sort on a small array
			return;
		}
		int pivot = i; // index of pivot element, the first element in the array.
		int end = j; // second index initially the last element in the segment to sort

		while (pivot != end) { // until these two indexes are different
			if (pivot < end) {
				if (arr[pivot] > arr[end]) { // a swap must be made
					//Assert: the pivot element is before the arr[end] element which is smaller than pivot
					swap(arr, pivot, end);
					int t = pivot; // changing pivot and end index after the swap
					pivot = end;
					end = t;
				}
			} else {
				if (arr[pivot] < arr[end]) { // the second case when a swap must be made
					//Assert: the pivot element is after the arr[end] element in the array and is smaller than arr[end]
					swap(arr, pivot, end);
					int t = pivot;
					pivot = end;
					end = t;
				}
			}

			if (pivot < end) { // end index wants to go in the direction of pivot index, decreasing the distance between them
				end--;
			} else {
				end++;
			}
		}
		//Assert: all elements on the left side of the pivot are smaller or equal to the pivot
		//Assert: all elements on the right side of the picot are greater or equal to the pivot
		sort(arr, i, pivot - 1); // recursive algorithm sorts the part on the left
		sort(arr, pivot + 1, j); // and on the right
	}

	/**
	 * Insertion sort used inside quick sort.
	 *
	 * @param arr array to sort
	 * @param a   starting index
	 * @param b   ending index
	 */
	public static void inSort(int[] arr, int a, int b) {
		int s = b - a + 1;
		for (int i = a; i < a + s; i++) {
			int g = arr[i];
			int j = i - 1;
			while (j >= a && arr[j] > g) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = g;
		}
	}

	/**
	 * Swaps two elements in array arr. One has index i and other j.
	 *
	 * @param arr array
	 * @param i   first index
	 * @param j   second index
	 */
	public static void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
}
