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
import static uk.ac.cam.kkd26.sorting.QuickSortWithInsert.hasSame;
import static uk.ac.cam.kkd26.sorting.QuickSortWithInsert.sort;

@RunWith(JUnit4.class)
public class QuickSortWithInsertTest {

	@Test
	public void sort_array_size_0() {
		// ARRANGE
		int[] initial = {};
		System.out.println("Initial: " + Arrays.toString(initial));

		// ACT
		sort(initial);
		System.out.println("Sorted: " + Arrays.toString(initial) + "\n");

		// ASSERT
		int[] out = {};
		assertThat(hasSame(initial, out)).isTrue();
	}

	@Test
	public void sort_array_size_6_decreasing() {
		// ARRANGE
		int[] initial = {6, 5, 4, 3, 2, 1};
		System.out.println("Initial: " + Arrays.toString(initial));

		// ACT
		sort(initial);
		System.out.println("Sorted: " + Arrays.toString(initial) + "\n");

		// ASSERT
		int[] out = {1, 2, 3, 4, 5, 6};
		assertThat(hasSame(initial, out)).isTrue();
	}

	@Test
	public void sort_array_size_20() {
		// ARRANGE
		int[] initial = {1, 6, 3, 9, 10, 4, 6, 2, 41, 8, 0, 0, -1, 84, 2, 0, 13, 5, 2, 0};
		System.out.println("Initial: " + Arrays.toString(initial));

		// ACT
		sort(initial);
		System.out.println("Sorted: " + Arrays.toString(initial) + "\n");

		// ASSERT
		int[] out = {-1, 0, 0, 0, 0, 1, 2, 2, 2, 3, 4, 5, 6, 6, 8, 9, 10, 13, 41, 84};
		assertThat(hasSame(initial, out)).isTrue();
	}

	@Test
	public void sort_array_size_12_all_equal() {
		// ARRANGE
		int[] initial = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		System.out.println("Initial: " + Arrays.toString(initial));

		// ACT
		sort(initial);
		System.out.println("Sorted: " + Arrays.toString(initial) + "\n");

		// ASSERT
		int[] out = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		assertThat(hasSame(initial, out)).isTrue();
	}

	@Test
	public void sort_array_size_5() {
		// ARRANGE
		int[] initial = {5, 4, 1, 3, 3};
		System.out.println("Initial: " + Arrays.toString(initial));

		// ACT
		sort(initial);
		System.out.println("Sorted: " + Arrays.toString(initial) + "\n");

		// ASSERT
		int[] out = {1, 3, 3, 4, 5};
		assertThat(hasSame(initial, out)).isTrue();
	}

//    @Test
//    public void sort_array_size_10000000_random() {
//        // ARRANGE
//        int[] initial = createRandom(10000000);
//        int[] second = initial.clone();
//
//        //System.out.println(Arrays.toString(initial));
//        // ACT
//        long startTime = System.currentTimeMillis();
//        QuickSort.sort(initial);
//        long endTime = System.currentTimeMillis();
//        System.out.println(endTime - startTime + "ms");
//
//        //System.out.println(Arrays.toString(second));
//        startTime = System.currentTimeMillis();
//        QuickSortWithInsert.sort(second);
//        endTime = System.currentTimeMillis();
//        System.out.println(endTime - startTime + "ms");
//        // ASSERT
//        assertThat(isSorted(initial)).isTrue();
//        assertThat(isSorted(second)).isTrue();
//    }
}
