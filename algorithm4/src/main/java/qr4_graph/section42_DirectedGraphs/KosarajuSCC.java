package qr4_graph.section42_DirectedGraphs;

import edu.princeton.cs.algs4.StdOut;
import qr4_graph.section41_UndirectedGraphs.Bag;
import qr4_graph.section41_UndirectedGraphs.TinyData;

/**
 * 强连通： v -> w; w -> v.
 * 强连通分量（Strong connectivity in digraphs）：
 * 强连通性将所有顶点分为了一些造价类，每个等价类都是由相互均为强连通的顶点的最大子集组成，这些子集就称为强连通分量。
 * 解决“给定的两个顶点是连通的吧？这幅有向图中含有多少个连通分量？”
 * @author leongfeng created on 2017-11-29.
 */
/**
 * 核心的思想或许可以总结成两点：
 * 1.圈反过来也是圈
 * 2.把圈反一半过来会得到两条路径
 * 你可以认为第一次搜索之后按倒过来的顺序重新搜，就是为了避开上次走过的路径。
 * 如果这次能找到另一条路径，和上次那条拼起来就是个圈了。
 * */
public class KosarajuSCC {
    private boolean[] marked;
    /**
     * 强连通分量的标识符.
     */
    private int[] id;
    private int count;

    public KosarajuSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        // G 的逆后序排列
        //反向加边构造成无向图,按照无向图求连通分量的方式进行计算
        //构造反向图的深度优先搜索排序(第一次深度优先搜索)
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        //所得到的反向图的逆后序-再倒置的顺序,在原图中亦存在这样间隔的深度优先搜索顺序
        StdOut.println(order.reversePost());
        //按照反向图的逆后序(非拓扑排序,有环图不能拓扑排序)对原图进行深度优先搜索
        for (int s : order.reversePost()) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)){
            if (!marked[w]){
                dfs(G, w);
            }
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(TinyData.fromFilename("tinyDG.txt"));
        StdOut.println(G);
        StdOut.println(new DepthFirstOrder(G).reversePost());
        KosarajuSCC cc = new KosarajuSCC(G);
        int M = cc.count();
        StdOut.println(M + " components");

        Bag<Integer>[] components = (Bag<Integer>[]) new Bag[M];
        for (int i = 0; i < M; i++ ){
            components[i] = new Bag<>();
        }

        for (int v = 0; v < G.V(); v++){
            components[cc.id(v)].add(v);
        }

        for (int i = 0; i < M; i++){
            for (int v : components[i]){
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }
}
