package leetcode;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class MyGraph {
	int vCnt;
	LinkedList<Integer> adj[];
	MyGraph(int vCnt){
        this.vCnt= vCnt;
        adj = new LinkedList[this.vCnt];
        for (int i = 0; i < this.vCnt; i++) {
        		adj[i] = new LinkedList();		
        }
	}
	public void add(int v, int adjV){
		if(v < this.vCnt) {
			adj[v].add(adjV);
		}
	}
}
