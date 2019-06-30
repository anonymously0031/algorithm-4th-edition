package qr4_graph.section41_UndirectedGraphs;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 间隔的度数.
 * @author leongfeng created on 2017/11/27.
 */
public class DegreesOfSeparation {
    public static void main(String[] args) {
        SymbolGraph sg = new SymbolGraph(TinyData.ROUTES_TXT, " ");
        Graph graph = sg.G();
        String source = "JFK";
        if (!sg.contains(source)){
            StdOut.println(source + " not fromFilename database.");
            return;
        }
        int s = sg.index(source);
        BreadthFirstPaths bfs = new BreadthFirstPaths(graph, s);
        int count = 0;
        while (!StdIn.isEmpty()){
            String sink = StdIn.readLine();
            if (sg.contains(sink)){
                int t = sg.index(sink);
                if (bfs.hasPathTo(t)){
                    for (int v : bfs.pathTo(t)){
                        StdOut.println(" " + sg.name(v));
                    }
                    Stack<Integer> paths = (Stack<Integer>)bfs.pathTo(t);
                    StdOut.println((paths.size() - 1) + " Degrees");
                } else {
                    StdOut.println("Not Connected!");
                }
            }else {
                StdOut.println("Not fromFilename database!");
            }
            count ++;
            if (count == 2){
                break;
            }
        }
    }
}
