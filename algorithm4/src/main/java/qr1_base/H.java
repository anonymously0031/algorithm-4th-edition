package qr1_base;

import edu.princeton.cs.algs4.StdRandom;

public class H {
    public static void main(String[] args) {
        System.out.println(H(1500000000));
        System.out.println(StdRandom.random());
    }

    public static double H(int N) {
        double sum = 0.0;
        for (int i = 1; i <= N; i++)
            sum += 1.0 / i;
        return sum;
    }
}
