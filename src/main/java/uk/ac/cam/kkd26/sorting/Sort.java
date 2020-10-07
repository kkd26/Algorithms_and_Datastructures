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

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class Sort {

	public static final boolean isSorted(int[] arr) {
		for (int i = 1; i < arr.length; i++) { // iterating through an array arr
			if (arr[i] < arr[i - 1]) { // if there is a pair with incorrect order return false
				return false;
			}
		}
		return true; // if false wasn't returned return true
	}

	public static final boolean hasSame(int[] a, int[] b) {
		if (a.length != b.length) return false;
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i]) return false;
		}
		return true;
	}

	public static int[] createRandom(int size) {
		List<Integer> a = IntStream.range(1, size + 1).boxed().collect(Collectors.toList()); // creating a list of integers from one to size
		Collections.shuffle(a); // shuffling the list
		return a.stream().mapToInt(x -> x).toArray(); // converting the shuffled list to an array
	}
}
