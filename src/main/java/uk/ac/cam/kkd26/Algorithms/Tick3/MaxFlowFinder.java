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

package uk.ac.cam.kkd26.Algorithms.Tick3;

import uk.ac.cam.cl.tester.Algorithms.LabelledGraph;
import uk.ac.cam.cl.tester.Algorithms.LabelledGraph.Edge;
import uk.ac.cam.cl.tester.Algorithms.MaxFlow;

import java.io.IOException;
import java.util.*;

public class MaxFlowFinder implements MaxFlow {

	private LabelledGraph g;
	private int source;
	private int[][] flow;
	private boolean[] cut;

	int[][] edges;
	private int[] come_from;
	private boolean[] reachable;

	private boolean getAugmentingPath(int src, int dst) {
		int n = g.numVertices();
		edges = new int[n][n];

		for (Edge e : g.edges()) {

			if (flow[e.from][e.to] < e.label) {
				edges[e.from][e.to] += 1;
			}
			if (flow[e.from][e.to] > 0) {
				edges[e.to][e.from] += 2;
			}
		}

		Queue<Integer> q = new LinkedList();
		q.offer(src);
		come_from = new int[n];
		reachable = new boolean[n];
		reachable[src] = true;
		while (!q.isEmpty()) {
			int f = q.peek();
			reachable[f] = true;
			for (int i = 0; i < n; i++) {
				if (i != f && edges[f][i] != 0 && !reachable[i]) {
					come_from[i] = f;
					if (i == dst) {
						return true;
					}
					q.offer(i);
				}
			}
			q.poll();
		}
		return false;
	}

	// Define a function getAugmentingPath(int src, int dst)
	// that returns an augmenting path from src to dst,
	// which it finds using BFS.

	@Override
	public void maximize(LabelledGraph g, int s, int t) {
		this.g = g;
		this.source = s;
		int n = g.numVertices();
		this.flow = new int[n][n];
		this.cut = new boolean[n];

		for (Edge e : g.edges()) {
			flow[e.from][e.to] = 0;
		}

		// run Ford-Fulkerson to fill in flow[][] and cut[]
		// using your getAugmentingPath function.

		while (true) {
			int d = Integer.MAX_VALUE;
			if (getAugmentingPath(s, t)) {
				int i = t;
				int c = come_from[i];
				while (c != s) {
					if (edges[c][i] == 1 || edges[c][i] == 3) {
						d = Math.min(d, g.capacity(c, i) - flow[c][i]);
					} else {
						d = Math.min(d, flow[i][c]);
					}
					i = c;
					c = come_from[i];
				}

				if (edges[c][i] == 1 || edges[c][i] == 3) {
					d = Math.min(d, g.capacity(c, i) - flow[c][i]);
				} else {
					d = Math.min(d, flow[i][c]);
				}

				i = t;
				c = come_from[i];
				while (c != s) {
					if (edges[c][i] == 1 || edges[c][i] == 3) {
						flow[c][i] += d;
					} else {
						flow[i][c] = flow[i][c] - d;
					}
					i = c;
					c = come_from[i];
				}
				if (edges[c][i] == 1 || edges[c][i] == 3) {
					flow[c][i] += d;
				} else {
					flow[i][c] = flow[i][c] - d;
				}
			} else {
				cut = reachable.clone();
				return;
			}
		}
	}

	@Override
	public int value() {
		int v = 0;
		for (int i = 0; i < g.numVertices(); i++) {
			v += flow[source][i];
			v -= flow[i][source];
		}
		return v;
	}

	@Override
	public Iterable<LabelledGraph.Edge> flows() {
		List<LabelledGraph.Edge> res = new ArrayList<LabelledGraph.Edge>();
		for (int i = 0; i < g.numVertices(); i++) {
			for (int j = 0; j < g.numVertices(); j++) {
				if (flow[i][j] > 0) {
					res.add(new LabelledGraph.Edge(i, j, flow[i][j]));
				}
			}
		}
		return res;
	}

	@Override
	public Set<Integer> cut() {
		Set<Integer> res = new HashSet<Integer>();
		for (int i = 0; i < cut.length; i++) if (cut[i]) res.add(i);
		return res;
	}

	public static void main(String[] args) throws IOException {
//        LabelledGraph g = new LabelledGraph(new int[][]{
//                {0, 12, 7, 0},
//                {0, 0, 15, 10},
//                {0, 0, 0, 10},
//                {0, 0, 0, 0},
//        });
		LabelledGraph g = new LabelledGraph("https://www.cl.cam.ac.uk/teaching/1920/Algorithms/ticks/res/flownetwork_02.csv");
		MaxFlowFinder m = new MaxFlowFinder();
		m.maximize(g, 0, 5);
		System.out.println(m.flows());
		System.out.println(m.cut());
	}

}
