package leetcode;
import java.io.*;
import java.util.Objects;

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
    @Override
    public boolean equals(Object obj){
         
    // checking if both the object references are 
    // referring to the same object.
    if(this == obj)
            return true;
         
        // it checks if the argument is of the 
        // type Geek by comparing the classes 
        // of the passed argument and this object.
        // if(!(obj instanceof Geek)) return false; ---> avoid.
        if(obj == null || obj.getClass()!= this.getClass())
            return false;
         
        // type casting of the argument. 
        MyPair<K,V> p = (MyPair<K,V>) obj;
         
        // comparing the state of argument with 
        // the state of 'this' Object.
        return (p.key == this.key && p.value == this.value);
    }
     
    @Override
    public int hashCode()
    {
        return Objects.hash(this.key,this.value);
    }
}
