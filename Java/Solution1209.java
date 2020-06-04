package leetcode;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


public class Solution1209 {
	Solution1209(){
		int[][] times = {{1,2,1}};
		int n = 2;
		int k = 2;
		int ret = networkDelayTime(times,n,k);
		System.out.println("ret:"+Integer.toString(ret));
	}
    public int findClosestLeaf(TreeNode root, int k) {
    	TreeNode node = root;
    	TreeNode nodeParent = node;
    	boolean isLift = false; 
    	while(true) {
    		if(node == null)
    			break;
    		if(node.val != k) {
    			nodeParent = node;
    			node = node.left;
    			isLift = true;
    		}
    		else {
    			break;
    		}
    	}
    	if(node == null) {
    		node = root;
    		nodeParent = node;
        	while(true) {
        		if(node == null)
        			break;
        		if(node.val != k) {
        			nodeParent = node;
        			node = node.right;
        		}
        		else {
        			break;
        		}
        	}
    	}
    	if(nodeParent != null) {
    		if(nodeParent.equals(node)) {
    			if(node.left!= null || node.right != null) {
    				if(node.left != null)
    					return node.left.val;
    				if(node.right != null)
    					return node.right.val;
    			}
    			return node.val;
    		}
    		if(isLift) {
    			if(nodeParent.right != null) {
    				return nodeParent.right.val;
    			}
    		}
    		else {
    			if(nodeParent.left != null) {
    				return nodeParent.left.val;
    			}
    		}
    	}
    	else
    		return node.val;
		return 0;
        
    }
    public int networkDelayTime(int[][] times, int N, int K) {
        int times_len = times.length, max = Integer.MAX_VALUE;
        
        Map<Integer,List<Integer>> map = new HashMap<>();
        for(int i=0;i<times_len;i++){
            int[] nums = times[i];
            int u = nums[0];
            int v = nums[1];
            /*
             * Add the key mapping list(u) or get the existed list(new Arraylist)
             * 
             */
            List<Integer> list = map.getOrDefault(u,new ArrayList<>());
            
            list.add(i);
            
            map.put(u,list);
        }
        if(map.get(K) == null){
            return -1;
        }
        /*dist is the distance which K go to each point*/
        int[] dist = new int[N+1];
        /*set default value to array*/
        Arrays.fill(dist,max);
        
        dist[K] = 0;
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(K);
        
        while(!queue.isEmpty()){
            int u = queue.poll();
            int t = dist[u];
            List<Integer> list = map.get(u);
            if(list == null)
                continue;
            
            for(int n:list){
                int v = times[n][1];//end point
                int time = times[n][2];//distance
                 if(dist[v] > t + time){
                    dist[v] = t + time;
                    queue.add(v);
                }                
            }
        }
        
        int res = -1;
        for(int i=1;i<=N;i++){
            int d = dist[i];
            if(d == max){
                return -1;
            }
            res = d > res ? d : res;
        }
        
        return res;
    }
    public char nextGreatestLetter(char[] letters, char target) {
        int targetAscii = (int)target;
        int min = 128;
        int cnt = 0;
        int idx = 0;
		for (int i = 0; i< letters.length; i++) {
			char key = letters[i];
			if(targetAscii < (int)key) {
				if(min >= key) {
					min = key;
					//cnt++;
					//idx = i;
				}
			}
		}
		System.out.format("cnt:%d i:%d len:%d \n",cnt,idx,letters.length);
		if(min == 128)
			min = (int)letters[0];
		//if(cnt == 1 && idx!= (letters.length-1))
		//	min = (int)letters[idx+1];
			
		return (char)min;
    }

}
