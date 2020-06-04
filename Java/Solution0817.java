package leetcode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution0817 {
	Solution0817(){
		int[][] a = {{7,9},{8,14}};
		//int[][] a = {{36,77},{5,54},{5,42},{31,37},{10,36},{15,66},{58,68}};
		//getOutput("111111111111111111111111111111111111111111");
		modernLudo(15,a);
		//int[] a = {1,1,1};
		//int[] b = {2,3,4};
		//socialNetwork(4,a,b);
	}
    public void socialNetworkDFS(int n, int cur, HashMap<Integer, LinkedList<Integer>> hm, boolean[] visited){
        LinkedList<Integer> l = hm.get(cur);
        if(l == null)
            return;
        visited[cur] = true;
        for(int i : l){
            if(visited[i])
                continue;
            socialNetworkDFS(n, i, hm, visited);
        }
    }
    public String socialNetwork(int n, int[] a, int[] b) {
        // Write your code here
        boolean[] visited = new boolean[n + 1];
        HashMap<Integer, LinkedList<Integer>> hm = new HashMap<Integer,LinkedList<Integer>>();
        //Arrays.fill(visited,false);
        for(int i = 0; i < a.length; i++){
        	LinkedList<Integer> la = hm.getOrDefault(a[i], new LinkedList<Integer>());
        	LinkedList<Integer> lb = hm.getOrDefault(b[i], new LinkedList<Integer>());
        	la.add(b[i]);
        	lb.add(a[i]);
        	hm.put(a[i], la);
        	hm.put(b[i], lb);
        }
        visited[0] = true;
        //visited[a[0]] = true;
        socialNetworkDFS(n,a[0],hm,visited);
        for(boolean v : visited){
            if(v == false)
                return "no";
        }
        return "yes";
    }
    public int modernLudo(int length, int[][] connections) {
    	HashMap<Integer,LinkedList<Integer>> connMap = new HashMap<Integer,LinkedList<Integer>>();
    	HashMap<Integer,Integer> distMap = new HashMap<Integer,Integer>();
    	Queue<MyPair<Integer,Integer>> q = new LinkedList<MyPair<Integer,Integer>>();
    	int DICENUM = 6;
    	int ret = Integer.MAX_VALUE;
    	
    	for(int[] conn : connections) {
    		LinkedList<Integer> ll = connMap.getOrDefault(conn[0], new LinkedList<Integer>());
    		ll.add(conn[1]);
    		connMap.put(conn[0], ll);
    	}
    	
    	q.add(new MyPair<Integer,Integer>(1,0));
		while(!q.isEmpty()) {
			MyPair<Integer,Integer> p = q.poll();
			int curPoint = p.key;
			int curSteps = p.value;

			/*Jump Conditions*/
			if(connMap.get(curPoint) != null) {
				LinkedList<Integer> l = connMap.get(curPoint);
				for(Integer dest : l) {
					if(distMap.get(dest) != null &&
							curSteps >= distMap.get(dest)) {
					        continue;
			        }
					distMap.put(dest,curSteps);
					q.add(new MyPair<Integer,Integer>(dest,curSteps));
				}
			}
			for(int i = 1; i <= DICENUM; i++) {
				if((curPoint + i) > length || ((distMap.get(curPoint + i) != null) &&
						(curSteps + 1) >= distMap.get(curPoint + i) )) {
				        continue;
		        }
				distMap.put(curPoint + i,curSteps + 1);
				q.add(new MyPair<Integer,Integer>(curPoint + i,curSteps + 1));
			}
    	}
		return distMap.get(length);
    }
    public String isInterval(List<List<Integer>> intervalList, int number) {
    	
    	if(intervalList.isEmpty())
    		return "False";
    	for(List<Integer> l : intervalList) {
    		int start = l.get(0), end = l.get(1);
    		if(start <= number && end >= number )
    			return "True";
    	}
    	return "False";
    }
	
}
