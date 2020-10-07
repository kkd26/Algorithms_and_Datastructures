/*
 * Copyright (c) 2020, Krzysztof Druciarek
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package uk.ac.cam.kkd26.dynamic;

import uk.ac.cam.cl.tester.Algorithms.EmptyHeapException;

import java.util.ArrayList;

public class MinHeap<T extends Comparable> {

	private int length = 0;
	private ArrayList<T> heap = new ArrayList<>() {
		@Override
		public boolean add(T t) {
			length++;
			return super.add(t);
		}

		@Override
		public T remove(int index) {
			if (length > 0) length--;
			return super.remove(index);
		}
	};

	{
		heap.add(null);
		length = 0;
	}

	private void swap(int a, int b) {
		T t = heap.get(a);
		heap.set(a, heap.get(b));
		heap.set(b, t);
	}

	public void insert(T e) {
		heap.add(e);
		int i = length;
		while (i > 1) {
			T current = heap.get(i);
			T parent = heap.get(i / 2);
			if (current.compareTo(parent) < 0) {
				swap(i, i / 2);
			} else {
				return;
			}
			i /= 2;
		}
	}

	public T popMin() throws EmptyHeapException {
		T min;
		if (length > 0) {
			min = heap.get(1);
		} else {
			throw new EmptyHeapException();
		}

		T last = heap.remove(length);

		if (length == 0) return min;

		heap.set(1, last);
		int i = 1;
		while (i <= length / 2) {
			int l = i * 2;
			int r = l + 1;

			T current = heap.get(i);
			T left = heap.get(l);
			T right;
			if (r > length) {
				if (current.compareTo(left) > 0) {
					swap(i, l);
					i = l;
				} else {
					break;
				}
			} else {
				right = heap.get(r);
				if (right.compareTo(left) > 0) {
					if (current.compareTo(left) > 0) {
						swap(i, l);
						i = l;
					} else {
						break;
					}
				} else if (current.compareTo(right) > 0) {
					swap(i, r);
					i = r;
				} else {
					break;
				}
			}
		}
		return min;
	}

	public boolean isEmpty() {
		return length == 0;
	}

	public int size() {
		return length;
	}

	@Override
	public String toString() {
		return "" + heap.subList(1, length + 1);
	}
}
