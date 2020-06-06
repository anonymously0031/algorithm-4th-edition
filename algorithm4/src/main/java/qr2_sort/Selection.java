package qr2_sort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Selection {
    private Selection() {
    }

    public static void main(String[] args) {
//        String[] a = StdIn.readAllStrings();
        String[] a = {"12", "ed", "rf", "4r", "y5", "na"};
        sort(a);
        show(a);
    }

    public static void sort(Comparable[] a) { // 将a[]按升序排列
        int N = a.length; // 数组长度
        for (int i = 0; i < N; i++) { // 将a[i]和a[i+1..N]中最小的元素交换
            int min = i; // 最小元素的索引
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
    }
    // less()、exch()、isSorted()和main()方法见“排序算法类模板”


    public static void sort(Object[] a, Comparator comparator) {
        int n = a.length;

        for (int i = 0; i < n; ++i) {
            int min = i;

            for (int j = i + 1; j < n; ++j) {
                if (less(comparator, a[j], a[min])) {
                    min = j;
                }
            }

            exch(a, i, min);

            assert isSorted(a, comparator, 0, i);
        }

        assert isSorted(a, comparator);

    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static boolean less(Comparator comparator, Object v, Object w) {
        return comparator.compare(v, w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; ++i) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }

        return true;
    }

    private static boolean isSorted(Object[] a, Comparator comparator) {
        return isSorted(a, comparator, 0, a.length - 1);
    }

    private static boolean isSorted(Object[] a, Comparator comparator, int lo, int hi) {
        for (int i = lo + 1; i <= hi; ++i) {
            if (less(comparator, a[i], a[i - 1])) {
                return false;
            }
        }

        return true;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; ++i) {
            StdOut.println(a[i]);
        }

    }
}