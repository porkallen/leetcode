package leetcode;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Graph {
	int vCnt;
	LinkedList<Integer> adj[];
	Graph(int vCnt){
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
	public void bfs(int start) {
		if(start >= this.vCnt) {
			System.out.printf("Out of bound \n");
			return;
		}
		/*
		 * put starting node in the queue
		 * while queue is not empty
		 * 		get first node from the queue, name it v
		 * 		(process v)
		 * 		for each edge e going from v to other nodes
		 * put the other end of edge e at the end of the queue
		 * */
		boolean visited[] = new boolean[this.vCnt+1];
		Arrays.fill(visited, false);
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		visited[start] = true;
		while(!q.isEmpty()) {
			int v = q.poll();
			System.out.printf("(BFS)===start point:%d\n",v);
			for (Iterator<Integer> it = this.adj[v].iterator(); it.hasNext();) {
				int adj = (int)it.next();
				if(!visited[adj]) {
					System.out.printf("point:%d \n",adj);
	            	  	visited[adj] = true;
	            	  	q.add(adj);
	            	}
			}
			System.out.printf("====================\n",start);
		}
	}
	public void dfs(int start) {
		boolean visited[] = new boolean[this.vCnt+1];
		Stack<Integer> s = new Stack<Integer>();
		Arrays.fill(visited, false);
		s.push(start);
		while(!s.isEmpty()) {
			int v = s.pop();
			visited[start] = true;
			System.out.printf("dfs:visited:%d \n",v);
			for(Iterator<Integer> it = this.adj[v].iterator();it.hasNext();) {
				int next = it.next();
				if(!visited[next]) {
					s.push(next);
					visited[next] = true;
				}
			}
		}
		System.out.printf("==========\n");
		
	}
}
