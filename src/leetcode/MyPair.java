package leetcode;

public class MyPair<K,V> {
	public K key;
	public V value;
	MyPair(K key, V value){
		this.key = key;
		this.value = value;
	}
	public MyPair<K,V> set(K key, V value) {
		this.key = key;
		this.value = value;
		return (MyPair<K, V>) this;
	}
	public K getKey() {
		return this.key;
	}
	public V getValue() {
		return this.value;
	}
}
