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

package uk.ac.cam.kkd26.Algorithms.Tick1;

import uk.ac.cam.cl.tester.Algorithms.EmptyHeapException;
import uk.ac.cam.cl.tester.Algorithms.MaxCharHeapInterface;

public class MaxCharHeap implements MaxCharHeapInterface {

	public char[] heap = new char[1];

	private int length;

	public MaxCharHeap(char[] x) {
		length = 1;
		for (char c : x) {
			insertEl(c);
		}
		for (int k = (length - 1) / 2; k >= 1; k--)
			heapify(length - 1, k);
	}

	private boolean isMax(int i) {
		int l = i * 2;
		int r = l + 1;
		if (l >= length) {
			return true;
		}
		if (r >= length) {
			return heap[i] >= heap[l];
		}
		if (heap[l] > heap[r]) {
			return heap[l] <= heap[i];
		} else {
			return heap[r] <= heap[i];
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
				if (heap[i] < heap[l]) {
					swap(i, l);
					heapify(iEnd, l);
				} else {
					continue;
				}
			}
			if (heap[l] > heap[r]) {
				if (heap[l] > heap[i]) {
					swap(i, l);
					heapify(iEnd, l);
				}
			} else {
				if (heap[r] > heap[i]) {
					swap(i, r);
					heapify(iEnd, r);
				}
			}
		}
	}

	private void swap(int i, int j) {
		char t = heap[i];
		heap[i] = heap[j];
		heap[j] = t;
	}

	private void insertEl(char e) {
		if (length >= heap.length) {
			char[] newHeap = new char[heap.length * 2];
			int i = 0;
			for (char c : heap) {
				newHeap[i++] = c;
			}
			heap = newHeap;
		}
		heap[length] = e;
		length++;
	}

	public void print() {
		int k = (int) Math.ceil(Math.log(length - 1) / Math.log(2));
		if (Math.pow(2, k) == length - 1) k++;
		int n = 1;
		int w = 1;
		for (int i = k - 1; i >= 0; i--) {
			for (int j = 0; j < n; j++) {
				if (w >= length) {
					System.out.println();
					return;
				}
				String s = "%" + ((int) Math.pow(2, i + 1)) + "c";
				if (j == 0) s = "%" + ((int) Math.pow(2, i)) + "c";
				//System.out.println(s);
				System.out.printf(s, heap[w++]);
			}
			n *= 2;
			System.out.println();
		}
	}

	@Override
	public void insert(char e) {
		insertEl(e);
		int i = length - 1;
		while (i > 1) {
			if (heap[i] > heap[i / 2]) {
				swap(i, i / 2);
			} else {
				return;
			}
			i /= 2;
		}
	}

	@Override
	public char popMax() throws EmptyHeapException {
		if (length - 1 == 0) {
			throw new EmptyHeapException();
		}
		char max = heap[1];
		heap[1] = heap[--length];
		int i = 1;
		while (i < length) {
			int l = i * 2;
			int r = l + 1;
			if (l >= length) {
				break;
			}
			if (r >= length) {
				if (heap[i] < heap[l]) {
					swap(i, l);
					i = l;
				} else {
					break;
				}
			}
			if (heap[l] > heap[r]) {
				if (heap[l] > heap[i]) {
					swap(i, l);
					i = l;
				} else {
					break;
				}
			} else {
				if (heap[r] > heap[i]) {
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

	public static void main(String[] args) throws EmptyHeapException {
		MaxCharHeap maxCharHeap = new MaxCharHeap(new char[]{'1', '8', '7', '6', '5', '4', '3'});
		maxCharHeap.print();
		maxCharHeap.insert('5');
		maxCharHeap.print();
		maxCharHeap.popMax();
		maxCharHeap.print();
		//System.out.println(maxCharHeap.heap);
	}
}
