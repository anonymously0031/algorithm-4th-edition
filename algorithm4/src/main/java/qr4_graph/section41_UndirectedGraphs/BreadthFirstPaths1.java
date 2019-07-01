package qr4_graph.section41_UndirectedGraphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import qr4_graph.BaseSearch;

import java.io.File;

/**
 * 使用广度优先搜索查找图中的路径.
 * 单点最短路径.给定一幅图和一个起点s,从s到给定目的顶点v是否存在一条路径?
 * 如果有,找出其中最短的那条(所含边数最少)---广度优先搜索
 * @author leongfeng created on 2017/11/26.
 */
public class BreadthFirstPaths1 {
    private boolean[] marked;//这个顶点上调用过dfs()了吗?(被访问到的顶点)
    private int[] edgeTo;//从起点到一个顶点的已知路径上的最后一个顶点
    private int s;//起点
    Queue<Integer> queue;

    public BreadthFirstPaths1(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        queue = new Queue<>();
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            // 删除顶点
            int v = queue.dequeue();
            StdOut.print(v + "->");
            for (int w : G.adj(v)){
                // 没有被标记过的相邻顶点
                if (!marked[w]){
                    // 保存最短路径的最后一条边
                    edgeTo[w] = v;
                    // 标记它，因为最短路径已知
                    marked[w] = true;
                    // 并将它添加到队列中
                    queue.enqueue(w);
                }
            }
        }
        StdOut.println();
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
                + File.separator + "dfs-bfs.txt")));
        StdOut.println("-----------graph---------------");
        StdOut.println(G);
        StdOut.println("-----------path---------------");
        int s = 1;
        BreadthFirstPaths1 bfp = new BreadthFirstPaths1(G, s);

        for (int v = 0; v < G.V(); v ++){
            if (bfp.hasPathTo(v)){
                StdOut.printf("%d to %d: ", s, v);
                for (int x : bfp.pathTo(v)){
                    if (x == s){
                        StdOut.print(x);
                    } else {
                        StdOut.print("-" + x);
                    }
                }
                StdOut.println();
            } else {
                StdOut.printf("%d to %d (-):  not connected\n", s, v);
            }
        }

    }
}
