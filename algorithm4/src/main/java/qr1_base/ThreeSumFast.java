package qr1_base;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class ThreeSumFast {

    // print distinct triples (i, j, k) such that a[i] + a[j] + a[k] = 0
    public static void printAll(int[] a) {
        int N = a.length;
        Arrays.sort(a);
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                int k = Arrays.binarySearch(a, -(a[i] + a[j]));
                if (k > j) StdOut.println(a[i] + " " + a[j] + " " + a[k]);
            }
        }
    }

    // return number of distinct triples (i, j, k) such that a[i] + a[j] + a[k] = 0
    public static int count(int[] a) {
        int N = a.length;
        Arrays.sort(a);
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
//                int k = Arrays.binarySearch(a, -(a[i] + a[j]));
                if (BinarySearch.indexOf(a, -a[i] - a[j]) > j) {
                    cnt++;
                    System.out.println(a[i] + " ** " + a[j] + " ** " + (-a[i] - a[j]));
                }

            }
        }
        return cnt;
    }

    public static void main(String[] args)  {
//        int[] a = In.readInts(args[0]);

        int[] a = new In("D:\\Hawkins\\algs4-data\\2Kints.txt").readAllInts();
        Stopwatch timer = new Stopwatch();
        int cnt = count(a);
        StdOut.println("elapsed time = " + timer.elapsedTime());
        StdOut.println(cnt);
    }
}