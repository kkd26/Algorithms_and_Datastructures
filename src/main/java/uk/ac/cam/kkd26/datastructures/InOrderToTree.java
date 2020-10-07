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

package uk.ac.cam.kkd26.datastructures;

public class InOrderToTree {

	public static class Node {
		private String data;
		private Node left = null;
		private Node right = null;

		public Node(String s, Node l, Node r) {
			data = s;
			right = r;
			left = l;
		}

		@Override
		public String toString() {
			String s = "";
			if (left != null) s += "(" + left + ")";
			s += data;
			if (right != null) s += "(" + right + ")";
			return s;
		}
	}

	private static int find_opening(String s, int start) {
		return s.indexOf('(', start);
	}

	private static int find_closing(String s, int start) {
		if (start < 0) return -1;
		int l = 1;
		int i;
		for (i = start + 1; i < s.length(); i++) {
			if (s.charAt(i) == ')') {
				l--;
			}
			if (s.charAt(i) == '(') {
				l++;
			}
			if (l == 0) break;
		}
		return i;
	}

	public static Node make(String s) {
		if (s.compareTo("") == 0) return null;

		int s1 = 0, s2 = 0, k1 = 0, k2 = 0;
		Node L = null, R = null;

		String left = "", right = "", middle = "";

		int start = 0, end = s.length() - 1;

		if (s.charAt(0) == '(') {
			s1 = find_opening(s, 0);
			k1 = find_closing(s, s1);
		}

		s2 = find_opening(s, k1);
		k2 = find_closing(s, s2);

		if (k1 != 0) {
			left = s.substring(s1 + 1, k1);
			start = k1 + 1;
		}

		if (k2 != -1 && s2 != -1) {
			right = s.substring(s2 + 1, k2);
			end = s2 - 1;
		}

		middle = s.substring(start, end + 1);
		if (middle.compareTo(s) == 0) {
			return new Node(s, null, null);
		}

		L = make(left);
		R = make(right);

		return new Node(middle, L, R);
	}

	public static String make_latex(Node x) {
		if (x == null) return "";
		String right = "", left = "";
		if (x.right != null) {
			right = make_latex(x.right);
		}

		if (x.left != null) {
			left = make_latex(x.left);
		}

		return "[" + x.data + left + right + "]";
	}

	public static void main(String[] args) {
		String s = "((X)A((G)C))I(((O)M(K))L(N))";
		Node root = make(s);
		System.out.println(root);
		System.out.println(make_latex(root));
	}
}
