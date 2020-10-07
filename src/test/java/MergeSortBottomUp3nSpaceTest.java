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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

import static com.google.common.truth.Truth.assertThat;
import static uk.ac.cam.kkd26.sorting.MergeSortBottomUp3nSpace.*;

@RunWith(JUnit4.class)
public class MergeSortBottomUp3nSpaceTest {

	@Test
	public void sort_array_size_0() {
		// ARRANGE
		int[] initial = {};
		System.out.println("Initial: " + Arrays.toString(initial));

		// ACT
		int[] sorted = take(sort(initial));
		System.out.println("Sorted: " + Arrays.toString(sorted) + "\n");

		// ASSERT
		assertThat(isSorted(sorted)).isTrue();
	}

	@Test
	public void sort_array_size_6_decreasing() {
		// ARRANGE
		int[] initial = {6, -3, 4, -1, 2, -7, 9, 9, 9};
		System.out.println("Initial: " + Arrays.toString(initial));

		// ACT
		int[] sorted = take(sort(initial));
		System.out.println("Sorted: " + Arrays.toString(sorted) + "\n");

		// ASSERT
		assertThat(isSorted(sorted)).isTrue();
	}

	@Test
	public void sort_array_size_20_random() {
		// ARRANGE
		int[] initial = createRandom(20);
		System.out.println("Initial: " + Arrays.toString(initial));

		// ACT
		int[] sorted = take(sort(initial));
		System.out.println("Sorted: " + Arrays.toString(sorted) + "\n");

		// ASSERT
		assertThat(isSorted(sorted)).isTrue();
	}

	@Test
	public void sort_array_size_12_all_equal_but_one() {
		// ARRANGE
		int[] initial = new int[]{1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0};
		System.out.println("Initial: " + Arrays.toString(initial));

		// ACT
		int[] sorted = take(sort(initial));
		System.out.println("Sorted: " + Arrays.toString(sorted) + "\n");

		// ASSERT
		assertThat(isSorted(sorted)).isTrue();
	}

	@Test
	public void sort_array_size_10000000_random() {
		// ARRANGE
		int[] initial = createRandom(10000000);
		//System.out.println(Arrays.toString(initial));
		// ACT
		long startTime = System.currentTimeMillis();
		int[] sorted = sort(initial);
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime + "ms");

		sorted = take(sorted);
		//System.out.println(Arrays.toString(sorted));
		// ASSERT
		assertThat(isSorted(sorted)).isTrue();
	}
}
