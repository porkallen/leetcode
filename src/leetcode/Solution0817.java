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
		//int[][] a = {{2,10}};
		//int[][] a = {{36,77},{5,54},{5,42},{31,37},{10,36},{15,66},{58,68}};
		//getOutput("111111111111111111111111111111111111111111");
		//modernLudo(86,a);
		int[] a = {1,1,1};
		int[] b = {2,3,4};
		socialNetwork(4,a,b);
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
    	HashMap<Integer,LinkedList<Integer>> hm = new HashMap<Integer,LinkedList<Integer>>();
    	HashMap<Integer,Integer> shortestMap = new HashMap<Integer,Integer>();
    	Queue<MyPair<Integer,Integer>> q = new LinkedList<MyPair<Integer,Integer>>();
    	int DICEIDX = 6;
    	
    	if(length == 0)
    		return 0;
    	if(length == 1)
    		return 1;
    	
    	for(int i = 1; i < length; i++) {
    		LinkedList<Integer> l = new LinkedList<Integer>();
    		for(int j = 1; j <= 6; j++) {
    			l.add(j);
    		}
    		hm.put(i, l);
    	}
    	for(int[] connection : connections) {
    		if(connection.length > 1) {
    			if(hm.get(connection[0]) != null) {
    				LinkedList<Integer> l = hm.get(connection[0]);
    				l.add(connection[1]);
    				hm.put(connection[0],l);
    			}
    		}
    	}
    	
    	q.add(new MyPair<Integer,Integer>(1,0));
		while(!q.isEmpty()) {
			MyPair<Integer,Integer> p = q.poll();
			int curPoint = p.key;
			int curSteps = p.value;
			if(shortestMap.get(curPoint) != null) {
				int sSteps = shortestMap.get(curPoint);
				if(curSteps > sSteps)
					continue;
			}
			if(curPoint == length) {
				shortestMap.put(curPoint,curSteps);
				break;
			}
			
			LinkedList<Integer> l = hm.get(curPoint);
			for(int i = 0; i < l.size(); i++) {
				int jSteps = 0;
				if(i >= DICEIDX) {
					jSteps = curSteps;
				}
				else {
					jSteps = curSteps + 1;
				}
				shortestMap.put(curPoint,jSteps);
				q.add(new MyPair<Integer,Integer>(curPoint + l.get(i),jSteps));
			}
    	}
		/*for (Map.Entry<Integer, Integer> entry : shortestMap.entrySet()) {
			Integer key = entry.getKey();
			Integer value = entry.getValue();
			System.out.printf("key:%d val:%d \n",key,value);
		}*/
		return 0;
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
