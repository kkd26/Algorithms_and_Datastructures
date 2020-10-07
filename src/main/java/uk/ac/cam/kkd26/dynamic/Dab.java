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

public class Dab {

	public static class Segment {
		private final double left;
		private final double right;
		private Segment next = null;

		public Segment(double a, double b) {
			if (a > b) {
				double t = a;
				a = b;
				b = t;
			}
			left = a;
			right = b;
		}

		public Segment(Segment a) {
			this.left = a.left;
			this.right = a.right;
		}

		@Override
		public String toString() {
			if (next == null) return "{" + left + ", " + right + "}";
			return "{" + left + ", " + right + "}, " + next;
		}

		public boolean isEqual(Segment o) {
			if (o == null) return false;
			return this.left == o.left && this.right == o.right;
		}
	}

	private Segment dab;

	public Dab(Segment s) {
		dab = s;
	}

	public Dab(double[][] a) {
		int s = a.length;
		dab = null;
		for (int i = 0; i < s; i++) {
			Segment seg = new Segment(a[i][0], a[i][1]);

			if (dab == null || seg.left < dab.left) {
				seg.next = dab;
				dab = seg;
				continue;
			}

			Segment j = dab;
			while (j.next != null && j.next.left < seg.left) {
				j = j.next;
			}
			seg.next = j.next;
			j.next = seg;
		}
		System.out.println(dab);
	}

	public boolean isEqual(Dab o) {
		Segment t = dab;
		while (t != null) {
			if (!t.isEqual(o.dab)) return false;
			t = t.next;
			o.dab = o.dab.next;
		}
		return o.dab == null;
	}

	public static Dab findMaxDab(Dab[] d) {
		int s = d.length;
		double M;
		int id = 0;
		Segment ans = null;
		boolean allNull = false;

		while (!allNull) {
			allNull = true;
			M = Double.MAX_VALUE;
			for (int i = 0; i < s; i++) {
				if (d[i].dab != null) {
					if (d[i].dab.right < M) {
						M = d[i].dab.right;
						id = i;
						allNull = false;
					}
				}
			}
			if (allNull) break;
			for (int i = 0; i < s; i++) {
				if (i != id) {
					while (d[i].dab != null && d[i].dab.left < M) {
						d[i].dab = d[i].dab.next;
					}
				}
			}
			Segment t = new Segment(d[id].dab);
			d[id].dab = d[id].dab.next;
			t.next = ans;
			ans = t;
		}
		return new Dab(rev(ans, null));
	}

	@Override
	public String toString() {
		return "" + dab;
	}

	private static Segment rev(Segment b, Segment ans) {
		if (b == null) return ans;
		else {
			Segment t = new Segment(b);
			t.next = ans;
			return rev(b.next, t);
		}
	}

	public static void main(String[] args) {
		Dab d1 = new Dab(new double[][]{{1, 2}, {4, 3}, {0, 0.5}, {-10, -14}});
		Dab d2 = new Dab(new double[][]{{1, 2}, {4, 3}, {0, 0.5}, {-10, -14}, {82, 90}});
		Dab d3 = new Dab(new double[][]{{-2.3, -1}, {10, 24.53}, {2, 6}});
		System.out.println(findMaxDab(new Dab[]{d1, d2, d3}));
	}
}
