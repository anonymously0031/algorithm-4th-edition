package qr4_graph;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author leongfeng created on 2017/11/25.
 */
public class Search extends BaseSearch {

    public Search(Graph g, int s){
        super(g, s);
    }
    @Override
    public boolean marked(int v) {
        return false;
    }

    @Override
    public int count() {
        return 0;
    }

    public static void main(String[] args) {
        Search s = new Search(new Graph(5), 0);
    }
}
