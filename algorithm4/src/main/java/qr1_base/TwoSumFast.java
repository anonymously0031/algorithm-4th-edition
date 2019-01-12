package qr1_base;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class TwoSumFast {
    public static int count(int[] a){
        Arrays.sort(a);
        int cnt = 0, N = a.length;
        for (int i=0; i<N; i++){
            if (BinarySearch.indexOf(a, -a[i]) > i){
                cnt ++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] a = In.readInts("D:\\Hawkins\\algs4-data\\8Kints.txt");
        StdOut.println(count(a));
    }
}
