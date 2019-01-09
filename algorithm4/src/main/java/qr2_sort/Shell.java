package qr2_sort;

import edu.princeton.cs.algs4.StdOut;

public class Shell {
    private Shell() {
    }

    public static void main(String[] args) {
//        String[] a = StdIn.readAllStrings();
        String[] a = {"12", "ed", "rf", "4r", "y5", "na", "m"};
        sort(a);
        show(a);
    }

    public static void sort(Comparable[] a) { // 将a[]按升序排列
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
        }
        while (h >= 1) { // 将数组变为h有序
            for (int i = h; i < N; i++) { // 将a[i]插入到a[i-h], a[i-2*h], a[i-3*h]... 之中
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            assert isHsorted(a, h);
            h = h / 3;
        }
        assert isSorted(a);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; ++i) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }

        return true;
    }

    private static boolean isHsorted(Comparable[] a, int h) {
        for (int i = h; i < a.length; ++i) {
            if (less(a[i], a[i - h])) {
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

