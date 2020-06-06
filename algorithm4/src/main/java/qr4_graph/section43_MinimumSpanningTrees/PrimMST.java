package qr4_graph.section43_MinimumSpanningTrees;

import edu.princeton.cs.algs4.IndexMinPQ;
import qr4_graph.section41_UndirectedGraphs.Queue;
import qr4_graph.section41_UndirectedGraphs.TinyData;

import java.util.Arrays;

import static edu.princeton.cs.algs4.StdOut.println;

/**
 * Prim 算法的即时实现.
 * @author leongfeng created on 2017/12/3.
 */
public class PrimMST  extends MST {
    /**
     * 距离树最近的边.
     */
    private Edge[] edgeTo;
    /**
     * 最近的边的权重：distTo = edge[W].weight().
     */
    private double[] distTo;
    /**
     * 如果v在树中则为true.
     */
    private boolean[] marked;
    /**
     * 有效的横切边.
     */
    private IndexMinPQ<Double> pq;

    private PrimMST(EdgeWeightedGraph G){
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++){
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<>(G.V());
        //从不同的顶点开始查找,得到同一个最小生成树 1 2 3...
        distTo[0] = 0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty()){
            visit(G, pq.delMin());
        }
    }

    /**
     * 向最小生成树中添加边的顺序和延时本版相同,不同之处在于优先队列的操作,
     * 延时版本全部加入,然后每次循环删除权重最小的边检查其两个顶点是否已在树中,如果不都在,将其加入最小生成树,继续访问不在的顶点(如果都在将其忽略,判断下一个权重最小的边)
     * 非延时版本加入部分不是最小的边,以顶点坐标访问其他邻接边,每次验证是否比已加入的边权重更小,如果更小,在优先队列中添加或者将其替换
     */
    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)){
            int w = e.other(v);
            if (marked[w]){
                continue;
            }
            // 更新 edgeTo 和 distTo
            if (e.weight() < distTo[w]){
                edgeTo[w] = e;
                distTo[w] = e.weight();
                // 更换为更小的权重.
                if (pq.contains(w)){
                    pq.changeKey(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    @Override
    public Iterable<Edge> edges() {
        final Queue<Edge> mst = new Queue<>();
        Arrays.asList(edgeTo).forEach(e -> {
            if (e != null){
                mst.enqueue(e);
            }
        });
        return mst;
    }

    @Override
    public double weight() {
        return 0;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph G = new EdgeWeightedGraph(TinyData.fromFilename(TinyData.FILENAME_TINYEWG));
        PrimMST mst = new PrimMST(G);
        mst.edges().forEach(e -> {
            println(e);
        });
    }
}
