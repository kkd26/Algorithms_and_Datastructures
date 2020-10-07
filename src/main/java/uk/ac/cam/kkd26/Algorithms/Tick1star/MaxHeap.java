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

package uk.ac.cam.kkd26.Algorithms.Tick1star;

import uk.ac.cam.cl.tester.Algorithms.ComparisonCountingString;
import uk.ac.cam.cl.tester.Algorithms.EmptyHeapException;
import uk.ac.cam.cl.tester.Algorithms.MaxHeapInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxHeap<T extends Comparable<T>> implements MaxHeapInterface {

	public ArrayList<Comparable> heap = new ArrayList<>(Arrays.asList(new Integer[1]));

	private int length;

	public MaxHeap(List<T> x) {
		length = 1;
		for (T i : x) {
			insertEl(i);
		}
		for (int k = (length - 1) / 2; k >= 1; k--)
			heapify(length - 1, k);
	}

	private void swap(int i, int j) {
		Comparable t = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, t);
	}

	private void insertEl(Comparable e) {
		if (length >= heap.size()) {
			ArrayList<Comparable> newHeap = new ArrayList<>(Arrays.asList(new Integer[heap.size() * 2]));

			int i = 0;
			for (Comparable c : heap) {
				newHeap.set(i++, c);
			}
			heap = newHeap;
		}
		heap.set(length, e);
		length++;
	}

	private boolean isMax(int i) {
		int l = i * 2;
		int r = l + 1;
		if (l >= length) {
			return true;
		}
		if (r >= length) {
			return heap.get(i).compareTo(heap.get(l)) >= 0;
		}
		if (heap.get(l).compareTo(heap.get(r)) > 0) {
			return heap.get(i).compareTo(heap.get(l)) >= 0;
		} else {
			return heap.get(i).compareTo(heap.get(r)) >= 0;
		}
	}

	private void heapify(int iEnd, int iRoot) {
		int i = iRoot;
		if (isMax(iRoot) || iEnd <= iRoot) return;
		i++;
		while (i > 1) {
			i--;
			int l = i * 2;
			int r = l + 1;
			if (l >= length) {
				continue;
			}
			if (r >= length) {
				if (heap.get(i).compareTo(heap.get(l)) < 0) {
					swap(i, l);
					heapify(iEnd, l);
				} else {
					continue;
				}
			}
			if (heap.get(l).compareTo(heap.get(r)) > 0) {
				if (heap.get(i).compareTo(heap.get(l)) < 0) {
					swap(i, l);
					heapify(iEnd, l);
				}
			} else {
				if (heap.get(i).compareTo(heap.get(r)) < 0) {
					swap(i, r);
					heapify(iEnd, r);
				}
			}
		}
	}

	@Override
	public void insert(Comparable e) {
		insertEl(e);
		int i = length - 1;
		while (i > 1) {
			if (heap.get(i).compareTo(heap.get(i / 2)) > 0) {
				swap(i, i / 2);
			} else {
				return;
			}
			i /= 2;
		}
	}

	@Override
	public Comparable popMax() throws EmptyHeapException {
		if (length - 1 == 0) {
			throw new EmptyHeapException();
		}
		Comparable max = heap.get(1);
		heap.set(1, heap.get(--length));
		int i = 1;
		while (i < length) {
			int l = i * 2;
			int r = l + 1;
			if (l >= length) {
				break;
			}
			if (r >= length) {
				if (heap.get(i).compareTo(heap.get(l)) < 0) {
					swap(i, l);
					i = l;
				} else {
					break;
				}
			}
			if (heap.get(l).compareTo(heap.get(r)) > 0) {
				if (heap.get(i).compareTo(heap.get(l)) < 0) {
					swap(i, l);
					i = l;
				} else {
					break;
				}
			} else {
				if (heap.get(i).compareTo(heap.get(r)) < 0) {
					swap(i, r);
					i = r;
				} else {
					break;
				}
			}
		}
		return max;
	}

	@Override
	public int getLength() {
		return length - 1;
	}

	public static void main(String[] args) {
		MaxHeap<ComparisonCountingString> maxHeap = new MaxHeap<>(List.of(
			new ComparisonCountingString("asd"),
			new ComparisonCountingString("wie"),
			new ComparisonCountingString("wke"),
			new ComparisonCountingString("fsfd"),
			new ComparisonCountingString("orqw"),
			new ComparisonCountingString("nvsdj"),
			new ComparisonCountingString("fdfk")
		));
		System.out.println(maxHeap.heap);
	}
}
