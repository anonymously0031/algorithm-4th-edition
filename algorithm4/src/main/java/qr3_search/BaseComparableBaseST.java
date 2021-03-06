package qr3_search;

/**
 * 有序符号表，只要声明中含有泛型变量 Key extends Comparable<Key> 的都要实现所有的API.
 * @author leongfeng created on 2017-11-10.
 */
public abstract class BaseComparableBaseST<Key extends Comparable<Key>, Value> extends BaseST<Key, Value> {

    public abstract Key min();
    public abstract Key max();

    /**
     * 小于等于 key 的最大键
     * @param key
     * @return
     */
    public abstract Key floor(Key key);

    /**
     * 大于等于 key 的最小键
     * @param key
     * @return
     */
    public abstract Key ceiling(Key key);

    /**
     * 小于 key 的键数量
     * @param key
     * @return
     */
    public abstract int rank(Key key);

    /**
     * 排名为 k 的键（树中正好有k个小于它的键）
     * @param k
     * @return key
     */
    public abstract Key select(int k);

    public abstract void deleteMin();
    public abstract void deleteMax();

    /**
     * [lo...hi] 之间键的数量
     * @param lo
     * @param hi
     */
    public abstract int size(Key lo, Key hi);

    /**
     * [lo...hi] 之间的所有键，已排序.
     * 此处使用中序遍历.
     * @param lo
     * @param hi
     * @return
     */
    public abstract Iterable<Key> keys(Key lo, Key hi);
}
