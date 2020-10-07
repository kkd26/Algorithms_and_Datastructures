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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MergeSortBottomUp3nSpace {

	/**
	 * Sorts an array.
	 *
	 * @param arr array of integers
	 * @return a sorted copy of array arr
	 */
	public static int[] sort(int[] arr) {
		arr = arr.clone(); // cloning array to make it immutable
		int n = arr.length / 3; // length of the scratch area
		int s = 1; // maximum size of a segment
		while (s < 2 * n) { // segments are smaller than an array to sort
			for (int i = s; i < 2 * n; i += 2 * s) {
				int size1 = Math.min(i + s, 2 * n) - i; // calculating size of the second segment
				copy(arr, i, 2 * n, size1); // moving the second segment to the scratch space
				copy(arr, i - s, i - s + size1, s); // shifting the first segment by th size of the second one
				merge(arr, i - s + size1, 2 * n, i - s, s, size1); // merging segments
			}
			s *= 2; //increasing the maximum size of a segment
		}
		for (int i = 2 * n; i < arr.length; i++) { // filling the scratch area with zeros
			arr[i] = 0;
		}
		return arr;
	}

	/**
	 * This function merges two segments - one starting at i1 with length len1
	 * and the second starting at position i2 with length len2 - and puts them
	 * in array arr at the position j.
	 *
	 * @param arr  array of integers
	 * @param i1   index of the first segment
	 * @param i2   index of the second segment
	 * @param j    index of the final place of merged segments
	 * @param len1 length of the first segment
	 * @param len2 length of the second segment
	 */
	public static void merge(int[] arr, int i1, int i2, int j, int len1, int len2) {
		while (len1 > 0 && len2 > 0) { // when both segments have elements
			if (arr[i1] > arr[i2]) { // comparing values at the beginning of the segments
				arr[j++] = arr[i2++]; // adding smaller value to the final palace
				len2--;
			} else {
				arr[j++] = arr[i1++];
				len1--;
			}
		}
		while (len1 > 0) { // adding the elements that are sill in segments
			arr[j++] = arr[i1++];
			len1--;
		}
		while (len2 > 0) {
			arr[j++] = arr[i2++];
			len2--;
		}
	}


	/**
	 * This function takes a segment from an array arr starting at position i1 with length len1
	 * and copies it to the array starting from position j.
	 *
	 * @param arr  array of integers
	 * @param i1   index of a segment
	 * @param j    index of a copy
	 * @param len1 length of the segment
	 */
	public static void copy(int[] arr, int i1, int j, int len1) {
		int a1 = i1 + len1 - 1; // the last position of a segments
		int a2 = j + len1 - 1; // the last position of the copy of the segment
		while (len1 > 0) { // copying segments from the back (ensures no errors when segment and the copy overlaps)
			arr[a2--] = arr[a1--];
			len1--;
		}
	}

	/**
	 * Concatenates two arrays of integers.
	 *
	 * @param a the first array of integers
	 * @param b the second array of integers
	 * @return Concatenated array with length a.length + b.length.
	 */
	public static int[] concat(int[] a, int[] b) {
		int[] c = new int[a.length + b.length]; // creating a new array
		int i = 0;
		for (int as : a) { // adding elements from array a to array c
			c[i++] = as;
		}
		for (int bs : b) { // adding elements from array b to array c
			c[i++] = bs;
		}
		return c;
	}

	/**
	 * Takes first n elements from array arr. If the number n is greater than arr.length then
	 * it returns a copy of array arr.
	 *
	 * @param arr array of integers
	 * @param n   number of elements to take from array arr
	 * @return an array which has first n elements from array arr
	 */
	public static int[] take(int[] arr, int n) {
		n = Math.min(n, arr.length); // length of the array to return
		if (n <= 0) return new int[]{}; //if n is no-positive then return an empty array

		int[] c = new int[n];
		int i = 0;
		while (n > 0) { // adding elements from array arr to array c
			c[i] = arr[i++];
			n--;
		}
		return c;
	}

	/**
	 * This function returns array arr without the scratch space
	 *
	 * @param arr array of integers
	 * @return an array without the scratch space
	 */
	public static int[] take(int[] arr) {
		int n = (arr.length / 3) * 2; // the length of scratch space
		return take(arr, n);
	}

	/**
	 * This function checks if array arr is sorted.
	 *
	 * @param arr array of integers
	 * @return boolean value true if array arr is sorted in ascending order else returns false
	 */
	public static boolean isSorted(int[] arr) {
		for (int i = 1; i < arr.length; i++) { // iterating through an array arr
			if (arr[i] < arr[i - 1]) { // if there is a pair with incorrect order return false
				return false;
			}
		}
		return true; // if false wasn't returned return true
	}


	/**
	 * This function generates an array with numbers from 1 to size included, shuffles it and adds a scratch
	 * space at the end.
	 *
	 * @param size size of random array
	 * @return array of random numbers with a scratch space at the end
	 */
	public static int[] createRandom(int size) {
		size++; // rounding size to the closest even number greater or equal to size
		size /= 2;
		size *= 2;
		List<Integer> a = IntStream.range(1, size + 1).boxed().collect(Collectors.toList()); // creating a list of integers from one to size
		Collections.shuffle(a); // shuffling the list
		int[] b = a.stream().mapToInt(x -> x).toArray(); // converting the shuffled list to an array

		int[] zeros = new int[size / 2]; // creating a scratch space filed with zeros
		for (int i = 0; i < size / 2; i++) {
			zeros[i] = 0;
		}
		return concat(b, zeros); // returning concatenated array
	}

	public static void main(String[] args) {
		int[] a = {4, 5, 9, 10, 3, 2, 0, 0, 0};
		int[] b = {1, 2, 3, 5, 4, -1, 0, 0, 0};
		System.out.println(Arrays.toString(sort(a)));
		System.out.println(Arrays.toString(sort(b)));
		System.out.println(Arrays.toString(createRandom(10)));
	}
}
