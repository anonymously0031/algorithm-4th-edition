package qr4_graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;

/**
 * @author leongfeng created on 2017/11/25.
 */
public class DepthFirstPaths1 {

    private boolean[] marked;//这个顶点上调用过dfs()了吗?(被访问到的顶点)
    private int[] edgeTo;//从起点到一个顶点的已知路径上的最后一个顶点
    private int s;//起点

    public DepthFirstPaths1(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    //原理二叉树后序遍历
    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> paths = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            paths.push(x);
        }
        paths.push(s);
        return paths;
    }

    public static void main(String[] args) {
        Graph G = new Graph(new In(new File(BaseSearch.class.getResource("").getPath()
                + File.separator + "tinyGG.txt")));
        StdOut.println(G);
        int s = 0;
        DepthFirstPaths1 dfp = new DepthFirstPaths1(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (dfp.hasPathTo(v)) {
                StdOut.printf("%d to %d:  ", s, v);
                for (int x : dfp.pathTo(v)) {
                    if (x == s) {
                        StdOut.print(x);
                    } else {
                        StdOut.print("-" + x);
                    }
                }
                StdOut.println();
            } else {
                StdOut.printf("%d to %d:  not connected\n", s, v);
            }
        }
    }
}
