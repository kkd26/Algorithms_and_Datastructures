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

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Scanner;

public class LongestCommonSubsequence {

	private static HashMap<Pair<Integer, Integer>, Integer> m;

	public static String lcs(String s1, String s2) {
		m = new HashMap<>();

		for (int i = 0; i <= s1.length(); i++) {
			for (int j = 0; j <= s2.length(); j++) {
				int M = 0;
				if (i == 0 || j == 0) {
					M = 0;
				} else {
					if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
						M = m.get(new Pair<>(i - 1, j - 1)) + 1;
					} else {
						M = Math.max(m.get(new Pair<>(i - 1, j)), m.get(new Pair<>(i, j - 1)));
					}
				}
				m.put(new Pair<>(i, j), M);
			}
		}
		int i = s1.length();
		int j = s2.length();
		int L = m.get(new Pair<>(i, j));
		String s = "";
		while (i >= 0 && j >= 0) {
			if (!m.containsKey(new Pair<>(i - 1, j - 1))) {
				break;
			}
			if (m.get(new Pair<>(i - 1, j - 1)) == L - 1 && s1.charAt(i - 1) == s2.charAt(j - 1)) {
				s = s1.charAt(i - 1) + s;
				L--;
				i--;
				j--;
			} else {
				int m1 = m.get(new Pair<>(i - 1, j));
				int m2 = m.get(new Pair<>(i, j - 1));
				if (m1 > m2) {
					i--;
				} else if (m2 > m1) {
					j--;
				} else {
					i--;
					j--;
				}
			}
		}
		return s;
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String s1 = s.next();
		String s2 = s.next();
		System.out.println(lcs(s1, s2));
	}
}
