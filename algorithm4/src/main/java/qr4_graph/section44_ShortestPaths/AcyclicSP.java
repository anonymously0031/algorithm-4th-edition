package qr4_graph.section44_ShortestPaths;

import qr4_graph.section41_UndirectedGraphs.Stack;
import qr4_graph.section41_UndirectedGraphs.TinyData;
import qr4_graph.section42_DirectedGraphs.Topological;

import static edu.princeton.cs.algs4.StdOut.print;
import static edu.princeton.cs.algs4.StdOut.printf;
import static edu.princeton.cs.algs4.StdOut.println;

/**
 * 无环加权有向图的最短路径算法（O(E+V)）。
 * 1. 先将distTo[s]初始化为0，其它元素为无穷大；
 * 2. 然后一个一个地按照拓扑顺序放松所有的顶点。
 *  使用DFS 搜索 tinyEWDAG.txt 得到的图的顶点的拓扑顺序 5 1 3 6 4 7 0 2
 *
 * @author leongfeng created on 2017/12/4.
 */
public class AcyclicSP {

    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightedDigraph G, int s){
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        for (int v = 0; v < G.V(); v++){
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        Topological top = new Topological(G);
        for (int v : top.order()){
            relax(G, v);
        }
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)){
            int w = e.to();
            if (distTo[v] + e.weight() < distTo[w]){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
            }
        }
    }

    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v){
        if (!hasPathTo(v)){
            return null;
        }
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]){
            path.push(e);
        }
        return path;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(TinyData.fromFilename("tinyEWDAG.txt"));
        println(G);
        println("---------------------------");
        int s  = 5;
        AcyclicSP sp = new AcyclicSP(G, s);
        for (int v = 0; v < G.V(); v ++){
            if (sp.hasPathTo(v)){
                printf("%d to %d: (%.2f) ", s, v, sp.distTo(v));
                for (DirectedEdge e : sp.pathTo(v)){
                    print(e + " ");
                }
                println();
            } else {
                printf("%d to %d       no path", s, v);
                println();
            }
        }
    }
}
