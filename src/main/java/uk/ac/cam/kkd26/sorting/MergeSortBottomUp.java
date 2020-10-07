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
import java.util.List;
import java.util.stream.Collectors;

public class MergeSortBottomUp extends Sort_old {
	//private List<Comparable> list;

	public MergeSortBottomUp(List<Comparable> list) {
		super(list);
	}

	public MergeSortBottomUp() {
		super();
	}

	public List<Comparable> sort() {
		long startTime = System.currentTimeMillis();
		int s = 1;
		List<List<Comparable>> a = new ArrayList<List<Comparable>>();
		a = list.stream().map(List::of).collect(Collectors.toList());
		while (s < list.size()) {
			for (int i = 0; i < Math.ceil((double) a.size() / s); i += 2) {
				List<Comparable> list1 = a.get(i);
				List<Comparable> list2 = null;
				if (i + 1 < Math.ceil((double) a.size() / s)) {
					list2 = a.get(i + 1);
				} else {
					list2 = List.of();
				}
				a.set(i / 2, merge(list1, list2));
			}
			s *= 2;
		}
		try {
			sorted = a.get(0);
		} catch (IndexOutOfBoundsException e) {
			sorted = new ArrayList<>();
		}
		long endTime = System.currentTimeMillis();
		time = endTime - startTime;
		return sorted;
	}

	public static void main(String[] args) {
		MergeSortBottomUp m = new MergeSortBottomUp();
		m.sort(1000000);
		System.out.println(m);
	}
}