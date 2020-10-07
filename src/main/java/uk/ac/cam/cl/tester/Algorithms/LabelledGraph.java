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

package uk.ac.cam.cl.tester.Algorithms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.Math.max;

public class LabelledGraph {

    static public class Edge {
        public final int from;
        public final int to;
        public final int label;

        public Edge(int from, int to, int label) {
            this.from = from;
            this.to = to;
            this.label = label;
        }
    }

    public final int numVertices() {
        return n;
    }

    public final int capacity(int from, int to) {
        return capacity[from][to];
    }

    public Iterable<Edge> edges() {
        return Collections.unmodifiableList(edges);
    }

    public LabelledGraph(String url) throws NumberFormatException, IOException {
        BufferedReader in;
        if (url.startsWith("http"))
            in = new BufferedReader(new InputStreamReader((new URL(url)).openStream()));
        else
            in = new BufferedReader(new FileReader(url));
        String line = in.readLine(); // ignore the header
        while ((line = in.readLine()) != null) {
            Scanner scanner = new Scanner(line);
            scanner.useDelimiter(",");
            int i = Integer.parseInt(scanner.next());
            int j = Integer.parseInt(scanner.next());
            int w = Integer.parseInt(scanner.next());
            scanner.close();
            edges.add(new Edge(i, j, w));
        }
        // What's the highest-labelled vertex? We'll take the vertices to be [0,1,...,n-1].
        n = 0;
        for (Edge e : edges) n = max(max(n, e.from), e.to);
        n = n + 1;
        // Store the capacities in an adjacency matrix.
        capacity = new int[n][n];
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) capacity[i][j] = 0;
        for (Edge e : edges) {
            assert capacity[e.from][e.to] == 0 : "Multi-edges not supported (" + e.from + " -> " + e.to + ")";
            capacity[e.from][e.to] = e.label;
        }
    }

    public LabelledGraph(int[][] capacity) {
        this.capacity = capacity;
        this.n = capacity.length;
        this.edges = new ArrayList<Edge>();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (capacity[i][j] > 0)
                    edges.add(new Edge(i, j, capacity[i][j]));
    }

    protected ArrayList<Edge> edges = new ArrayList<Edge>(); // capacities, stored as a list of edges
    protected int[][] capacity; // capacities, stored as adjacency matrix
    protected int n; // number of vertices
}
