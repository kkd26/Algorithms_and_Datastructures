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
import java.util.HashMap;

public class HuffmanCodes {

	private static HashMap<String, String> codes;
	private static ArrayList<Node> nodesTab;

	public static class Node<T> implements Comparable<Node<T>> {
		private Node<T> right;
		private Node<T> left;
		private T data;
		private int freq;
		private String code = "";

		public Node(T c, int f) {
			data = c;
			freq = f;
		}

		public Node(Node a, Node b) {
			data = (T) (a.data.toString() + b.data.toString());
			freq = a.freq + b.freq;
			right = a;
			left = b;
		}

		@Override
		public int compareTo(Node<T> o) {
			return Integer.compare(this.freq, o.freq);
		}

		@Override
		public String toString() {
			return "" + data + ":" + freq;
		}
	}

	public static Node makeHuffmanCode(Node[] nodes) throws EmptyHeapException {
		MinHeap<Node> H = new MinHeap<>();

		nodesTab = new ArrayList<>();

		for (Node n : nodes) {
			nodesTab.add(n);
			H.insert(n);
		}
		while (H.size() >= 2) {
			Node n1 = H.popMin();
			Node n2 = H.popMin();
			Node n = new Node(n1, n2);
			H.insert(n);
		}

		Node h = H.popMin();

		codes = new HashMap<>();
		findCodes(h, "");
		return h;
	}

	private static void findCodes(Node n, String s) {
		if (n == null) return;

		if (n.left == null && n.right == null) {
			n.code = s;
			codes.put((String) n.data, s);
			return;
		}

		if (n.left != null) findCodes(n.left, s + "0");
		if (n.right != null) findCodes(n.right, s + "1");
	}

	public static void printCodes() {
		System.out.println("Codes:");
		for (Node n : nodesTab) {
			System.out.println(n.data + ": " + n.code);
		}
	}

	public static void main(String[] args) throws EmptyHeapException {
		Node h = makeHuffmanCode(new Node[]{
			new Node("0", 20),
			new Node("b", 3),
			new Node("c", 6),
			new Node("d", 7),
			new Node("e", 8),
			new Node("f", 56)
		});
		System.out.println(h);
		printCodes();
	}
}
