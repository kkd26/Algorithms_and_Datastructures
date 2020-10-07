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

package uk.ac.cam.kkd26.Algorithms.Tick2;

import uk.ac.cam.cl.tester.Algorithms.LCSFinder;

public class LCSBottomUp extends LCSFinder {

	public LCSBottomUp(String s1, String s2) {
		super(s1, s2);
	}

	private static int get(int i) {
		return Math.max(i, 0);
	}

	@Override
	public int getLCSLength() {

		if (mString1.equals("") || mString2.equals("")) return 0;

		mTable = new int[mString1.length()][mString2.length()];

		for (int i = 0; i < mString1.length(); i++) {
			for (int j = 0; j < mString2.length(); j++) {
				boolean same = mString1.charAt(i) == mString2.charAt(j);
				int M = 0;
				if (i == 0) {
					M = mTable[0][get(j - 1)];
					if (same) M = 1;
				} else if (j == 0) {
					M = mTable[get(i - 1)][0];
					if (same) M = 1;
				} else {
					if (same) {
						M = mTable[get(i - 1)][get(j - 1)] + 1;
					} else {
						M = Math.max(mTable[get(i - 1)][j], mTable[i][get(j - 1)]);
					}
				}
				mTable[i][j] = M;
			}
		}

		return mTable[mString1.length() - 1][mString2.length() - 1];
	}

	@Override
	public String getLCSString() {
		if (mString1.equals("") || mString2.equals("")) return "";

		this.getLCSLength();
		int i = mString1.length() - 1;
		int j = mString2.length() - 1;
		String s = "";
		while (i >= 0 && j >= 0) {
			if (mString1.charAt(i) == mString2.charAt(j)) {
				s = mString1.charAt(i) + s;
				i--;
				j--;
			} else {
				if (j == 0) {
					i--;
					continue;
				}
				if (i == 0) {
					j--;
					continue;
				}
				int m1 = mTable[get(i - 1)][j];
				int m2 = mTable[i][get(j - 1)];
				if (m1 > m2) {
					i--;
				} else {
					j--;
				}
			}
		}
		return s;
	}
}
