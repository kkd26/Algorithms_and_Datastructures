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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class Sort_old {

	protected List<Comparable> list;
	protected List<Comparable> sorted = List.of();
	protected long time;

	public Sort_old() {
		this(List.of());
	}

	;

	public Sort_old(List<Comparable> c) {
		this.list = new ArrayList<Comparable>(c);
	}

	public abstract List<Comparable> sort();

//    public List<Comparable> sort(List<Comparable> list){
//        this.list = new ArrayList<>(list);
//        this.list = sort();
//        return this.list;
//    };

	public List<Comparable> sort(int i) {
		this.list = IntStream.range(1, i + 1).boxed().collect(Collectors.toList());
		Collections.shuffle(list);

		long startTime = System.currentTimeMillis();
		sorted = sort();
		long endTime = System.currentTimeMillis();
		time = endTime - startTime;
		return sorted;
	}

	public static List<Comparable> merge(List<Comparable> a, List<Comparable> b) {
		List<Comparable> c = new ArrayList<Comparable>();
		int ai = 0, bi = 0;
		while (a.size() > ai && b.size() > bi) {
			if (a.get(ai).compareTo(b.get(bi)) < 0) {
				c.add(a.get(ai++));
			} else {
				c.add(b.get(bi++));
			}
		}

		while (b.size() > bi) {
			c.add(b.get(bi++));
		}

		while (a.size() > ai) {
			c.add(a.get(ai++));
		}

		return c;
	}

	static boolean isSorted(List<Comparable> c) {
		for (int i = 1; i < c.size(); i++) {
			if (c.get(i).compareTo(c.get(i - 1)) < 0) return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Shuffled list:\n" + this.list + "\nSorted list:\n" + this.sorted + "\nIs sorted? " + isSorted(sorted) + "\nSort runtime: " + this.time + "ms";
	}
}
