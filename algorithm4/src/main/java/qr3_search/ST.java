package qr3_search;

import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {

    private TreeMap<Key, Value> st;

    public ST(){
        st = new TreeMap<>();
    }

    public void put(Key key, Value value) {
        if (value == null) {
            st.remove(key);
        }
        st.put(key, value);
    }


    public Value get(Key key) {
        return st.get(key);
    }

    public Key min() {
        return st.firstKey();
    }

    public Key max() {
        return st.lastKey();
    }

    public Key floor(Key key) {
        if (st.containsKey(key)){
            return key;
        }
        SortedMap<Key, Value> head = st.headMap(key);
        if (head.isEmpty()){
            return null;
        }
        return head.lastKey();
    }

    public int size() {
        return st.size();
    }


    public Key ceiling(Key key) {
        SortedMap<Key, Value> tail = st.tailMap(key);
        if (tail.isEmpty()){
            return null;
        }
        return tail.firstKey();
    }

    @Override
    public Iterator<Key> iterator() {
        return st.keySet().iterator();
    }

    public boolean contains(Key key) {
        return st.containsKey(key);
    }

    public Iterable<Key> keys() {
        return st.keySet();
    }
}
