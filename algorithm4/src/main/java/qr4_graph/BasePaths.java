package qr4_graph;
/**
 * @author leongfeng created on 2017/11/25.
 */
public abstract class BasePaths {
    /**
     * 起点
     */
    public int s;
    /**
     * 到达该顶点的最短路径已知吗？
     */
    public boolean[] marked;
    /**
     * 到达该顶点的已经路径上的最后一个顶点.
     */
    public int[] edgeTo;
    public BasePaths(Graph graph, int s){
        this.s = s;
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
    }
    public BasePaths(){
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }
    public Iterable<Integer> pathTo(int v){
        if (!hasPathTo(v)){
            return null;
        }
        Stack<Integer> paths = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]){
            paths.push(x);
        }
        paths.push(s);
        return paths;
    }
}
