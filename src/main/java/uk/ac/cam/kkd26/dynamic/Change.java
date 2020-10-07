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

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Change {

	private static ArrayList<Integer> MONEY = new ArrayList<>(List.of(1, 5, 25, 50, 100, 200));

	public static void setMONEY(ArrayList<Integer> a) {
		MONEY = (ArrayList<Integer>) a.clone();
	}

	public static ArrayList<Integer> give(int a) {
		int[][] map = new int[a + 1][MONEY.size()];
		for (int i = 0; i <= a; i++) {
			for (int j = 0; j < MONEY.size(); j++) {
				int current = Integer.MAX_VALUE;
				if (i == 0) {
					map[i][j] = 0;
					continue;
				} else if (i - MONEY.get(j) >= 0) {
					current = map[i - MONEY.get(j)][j] + 1;
				}
				if (j > 0) {
					current = Math.min(current, map[i][j - 1]);
				}
				map[i][j] = current;
			}
		}
		int s = map[a][MONEY.size() - 1];
		ArrayList<Integer> l = new ArrayList<>();

		int i = a;
		int j = MONEY.size() - 1;
		while (i > 0) {
			while (j > 0 && map[i][j] == map[i][j - 1]) {
				j--;
			}

			l.add(MONEY.get(j));
			i -= MONEY.get(j);
		}
		return l;
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		System.out.println(give(n));
	}
}
