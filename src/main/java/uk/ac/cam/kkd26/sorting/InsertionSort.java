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

public class InsertionSort extends Sort_old {

	public InsertionSort(List<Comparable> c) {
		super(c);
	}

	public InsertionSort() {
		super();
	}

	@Override
	public List<Comparable> sort() {
		long startTime = System.currentTimeMillis();
		sorted = sort(this.list);
		long endTime = System.currentTimeMillis();
		time = endTime - startTime;
		return sorted;
	}

	private static ArrayList<Comparable> insert(ArrayList<Comparable> list, Comparable c) {
		if (list == null) return new ArrayList<>(List.of(c));
		list.add(c);
		for (int i = list.size() - 1; i > 0; i--) {
			Comparable e = list.get(i - 1);
			if (c.compareTo(e) < 0) {
				list.set(i, e);
			} else {
				list.set(i, c);
				return list;
			}
		}
		list.set(0, c);
		return list;
	}

	public static List<Comparable> sort(List<Comparable> list) {
		ArrayList<Comparable> sorted = null;
		for (int i = 0; i < list.size(); i++) {
			sorted = insert(sorted, list.get(i));
		}
		return sorted;
	}

	public static void main(String[] args) {
		InsertionSort i = new InsertionSort();
		i.sort(1000);
		System.out.println(i);
	}
}