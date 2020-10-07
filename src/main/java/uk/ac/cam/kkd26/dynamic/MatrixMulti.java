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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class MatrixMulti {
	private static HashMap<Pair<Integer, Integer>, Integer> m;

	public static int mult(List<Integer> l) {
		m = new HashMap<>();
		for (int i = 0; i < l.size(); i++) {
			m.put(new Pair<>(i, i), 0);
		}
		for (int i = 0; i < l.size() - 1; i++) {
			for (int j = i - 1; j >= 0; j--) {
				int M = Integer.MAX_VALUE;
				for (int k = j; k < i; k++) {
					M = Math.min(M, m.get(new Pair<>(j, k)) + m.get(new Pair<>(k + 1, i)) + l.get(j) * l.get(k + 1) * l.get(i + 1));
				}
				m.put(new Pair<>(j, i), M);
			}
		}
		return m.get(new Pair<>(0, l.size() - 2));
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		ArrayList l = new ArrayList<>();

		for (int i = 0; i <= n; i++) {
			l.add(s.nextInt());
		}
		int m = mult(l);
		System.out.println(m);
	}
}
