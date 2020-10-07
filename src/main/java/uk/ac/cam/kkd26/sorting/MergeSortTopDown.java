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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeSortTopDown extends Sort_old {

	public MergeSortTopDown(List<Comparable> list) {
		super(list);
	}

	public List<Comparable> sort() {
		long startTime = System.currentTimeMillis();
		if (list.size() < 2) {
			return list;
		}

		int h = list.size() / 2;
		List<Comparable> list1 = list.subList(0, h);
		List<Comparable> list2 = list.subList(h, list.size());
		list1 = sort(list1);
		list2 = sort(list2);

		sorted = merge(list1, list2);
		long endTime = System.currentTimeMillis();
		time = endTime - startTime;
		return sorted;
	}

	public static List<Comparable> sort(List<Comparable> list) {
		return new MergeSortTopDown(list).sort();
	}

	public static void main(String[] args) {
		List<Comparable> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		Collections.shuffle(list);
		MergeSortTopDown m = new MergeSortTopDown(list);
		m.sort(10000000);
		System.out.println(m.time);
	}
}
