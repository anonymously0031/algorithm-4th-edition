package qr4_graph.section42_DirectedGraphs;


import edu.princeton.cs.algs4.StdOut;
import qr4_graph.section41_UndirectedGraphs.TinyData;
import qr4_graph.section44_ShortestPaths.EdgeWeightedDigraph;
import qr4_graph.section44_ShortestPaths.EdgeWeightedDirectedCycle;

/**
 * 顶点的深度优先次序与拓扑排序.
 * 拓扑顺序：给定一幅有向图，将所有的顶点排序，使得所有的有向边均从排在前面的元素指向排在后面的元素。
 * 当且仅当一幅有向图是DAG时它才能进行拓扑排序。
 * @author leongfeng created on 2017-11-29.
 */
public class Topological {
    /**
     * 顶点的拓扑顺序：有向图的逆后序顺序.
     */
    private Iterable<Integer> order;

    /**
     * 需要是DAG.
     * @param G DAG 图
     */
    public Topological(Digraph G){
        DirectedCycle cycleFinder = new DirectedCycle(G);
        if (!cycleFinder.hasCycle()){
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    public Topological(EdgeWeightedDigraph G){
        EdgeWeightedDirectedCycle cycleFinder = new EdgeWeightedDirectedCycle(G);
        if (!cycleFinder.hasCycle()){
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    /***
     * @author wsl
     * @date 2019/7/1 17:30
     * @param
     * @return boolean
     * @desc 不是一个有向无环图
     */
    public boolean isDAG(){
        return order != null;
    }

    public Iterable<Integer> order(){
        return order;
    }

    public static void main(String[] args) {
        Digraph digraph = new Digraph(TinyData.fromFilename("tinyDAG.txt"));
        Topological topo = new Topological(digraph);
        if(topo.isDAG()){
            for (int v : topo.order()){
                StdOut.print(v + " ");
            }
        }else {
            StdOut.print("Directed Cycle Graph...");
        }
    }
}
