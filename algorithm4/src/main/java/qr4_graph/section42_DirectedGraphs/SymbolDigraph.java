package qr4_graph.section42_DirectedGraphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import qr3_search.ST;
import qr4_graph.section41_UndirectedGraphs.TinyData;

/**
 * @author leongfeng created on 2017-11-29.
 */
public class SymbolDigraph {
    /**
     * 符号(key) -> 索引
     */
    ST<String, Integer> st;
    /**
     * 索引 -> 符号(key)
     */
    String[] keys;
    Digraph G;

    /**
     * 1. 建立索引{@linkplain #st}；
     * 2. 建立反向索引 {@linkplain #keys}；
     * 3. 构建有向图。
     * @param filename
     * @param delim
     */
    public SymbolDigraph(String filename, String delim){
        st = new ST<>();
        In in = new In(filename);
        while (in.hasNextLine()){
            String[] a = in.readLine().split(delim);
            for (int i = 0; i < a.length; i++){
                if (!st.contains(a[i])){
                    st.put(a[i], st.size());
                }
            }
        }

        keys = new String[st.size()];
        for (String name : st.keys()){
            keys[st.get(name)] = name;
        }

        in = new In(filename);
        G = new Digraph(st.size());
        while (in.hasNextLine()){
            String[] a = in.readLine().split(delim);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++){
                G.addEdge(v, st.get(a[i]));
            }
        }
    }

    public boolean contains(String s){
        return st.contains(s);
    }

    /**
     * key 索引.
     * @param key key
     * @return 索引
     */
    public int index(String key){
        return st.get(key);
    }

    /**
     * 索引v的符号名.
     * @param v 索引
     * @return 符号名
     */
    public String name(int v){
        return keys[v];
    }

    public Digraph G(){
        return G;
    }

    public static void main(String[] args) {
        SymbolDigraph symbolDigraph = new SymbolDigraph(TinyData.BASE_PATH + "jobs.txt", "/");
        Topological topo = new Topological(symbolDigraph.G());
        for (int v : topo.order()){
            StdOut.println(symbolDigraph.name(v));
        }
    }
}
