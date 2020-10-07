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

public class InsertSort extends Sort {

	public static void sort(int[] arr) {
		int s = arr.length;
		for (int i = 0; i < s; i++) {
			int g = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > g) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = g;
		}
	}

	public static void main(String[] args) {
		int[] a = createRandom(10);
		System.out.println(Arrays.toString(a));
		sort(a);
		System.out.println(Arrays.toString(a));
	}
}
