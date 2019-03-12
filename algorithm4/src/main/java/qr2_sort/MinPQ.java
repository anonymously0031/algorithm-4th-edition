package qr2_sort;

import java.util.Comparator;

public class MinPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int size;
    private Comparator<Key> comparator;
    public MinPQ(int capacity){
        this.pq = (Key[]) new Object[capacity];
    }
    public void insert(Key key){
        pq[size++] = key;
    }
    public Key min(){
        return null;
    }

    public Key delMin(){
        return null;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }
}