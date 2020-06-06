package qr2_sort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Transaction;

public class TopM {
    //获取最大的M个值
    public static void main(String[] args) {
        int M = Integer.parseInt(args[0]);
        MinPQ<Transaction> pq = new MinPQ<>(M+1);
        while (StdIn.hasNextLine()){
            pq.insert(new Transaction(StdIn.readLine()));
            if (pq.size() > M){
                pq.delMin();
            }
        }
    }
}
