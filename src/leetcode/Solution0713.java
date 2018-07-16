package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class MyGraph {
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

public class Solution0713 {
	Solution0713(){
		int[] A = {1,2,3,6};
		int[] B = {1,5,10};
		//int[] arr = getAnswer(A,B);
		//System.out.printf("ret:%d %d %d \n", arr[0], arr[1], arr[2]);
		int[] arr1= {1,1,2,2};
		System.out.printf("ret:%d \n", askForCoolingTime(arr1,2));

	}
    public int longestPath(int n, int[] starts, int[] ends, int[] lens) {
    	int ret = 0;
    	MyGraph g = new MyGraph(starts[0]);
    	for(int i = 0; i < starts.length; i++) {
    		g.add
    	}
    	return ret;
    }
    public int askForCoolingTime(int[] arr, int n) {
    	int ret = 0;
    	HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
    	for(int i = 0; i < arr.length; i++) {
			ret++;
    		if(hm.get(arr[i]) != null) {
    			int preIdx = hm.get(arr[i]);
    			if(ret - preIdx - 1 < n) {
    				ret += n - (ret - preIdx) + 1;
    			}
    		}
    		hm.put(arr[i],ret);
			System.out.printf("(%d): ret:%d \n",i,ret);
    	}  	
    	return ret;
    }
    public int[] getAnswer(int[] A, int[] B) {
        int[] ret = new int[3];
        int unionNum, itsNum, diffNum;
        unionNum = itsNum = diffNum = 0;
        Map<Integer,Integer> m = new HashMap<Integer,Integer>();
        
        if(A.length >= 0 && B.length == 0) {
        	ret[0] = ret[1] = ret[2] = A.length;
        	return ret;
        }
        if(A.length == 0 && B.length >= 0) {
        	ret[0] = ret[1] = ret[2] = B.length;
        	return ret;
        }
        if(A.length == 0 && B.length == 0) {
        	ret[0] = ret[1] = ret[2] = 0;
        	return ret;
        }
        for(int e: A) {
        	m.put(e, 1);
        }
        for(int e: B) {
        	if(m.get(e) != null)
        		itsNum++;
        	else {
        		m.put(e, 2);
        	}
        }
        unionNum = m.size();
        diffNum = A.length - itsNum;
        ret[0] = unionNum;
        ret[1] = itsNum;
        ret[2] = diffNum;    
        return ret;
    }

}
