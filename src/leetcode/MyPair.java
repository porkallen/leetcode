package leetcode;

public class MyPair {
	int key;
	int value;
	MyPair(int key, int value){
		this.key = key;
		this.value = value;
	}
	public MyPair set(int key, int value) {
		this.key = key;
		this.value = value;
		return this;
	}
	public int getKey() {
		return this.key;
	}
	public int getValue() {
		return this.value;
	}
}
