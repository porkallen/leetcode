package leetcode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;



public class Solution0723 {
	Solution0723(){
		uniqueSubstring("caaab",2);
	}
	public boolean reachEndpointDfs(MyPair curLoc,int[][] map,boolean[][] checked) {
		boolean ret = false;
		int curX = curLoc.getKey();
		int curY = curLoc.getValue();
		
		if(curX < 0 || curY < 0 || curX >= map.length || curY >= map.length)
			return false;
		if(checked[curX][curY] == true)
			return false;
		if(map[curX][curY] == 0)
			return false;
		if(map[curX][curY] == 9)
			return true;
		
		checked[curX][curY] = true;
		int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
		for (int i = 0; i < dirs.length; i++) {
			int dx = dirs[i][0], dy = dirs[i][1];
			ret |= reachEndpointDfs(curLoc.set(curX + dx, curY + dy),map,checked);
		}
		checked[curX][curY] = false;

		return ret;
	}
    public boolean reachEndpoint(int[][] map) {
    	MyPair start = new MyPair(0,0);
    	boolean[][] checked = new boolean[map.length][map[0].length];
    	if(map.length == 0 || map[0].length == 0)
    		return false;
    	if(map.length == 1 && map[0].length == 1) {
    		if(map[0][0] == 9)
    			return true;
    		else
    			return false; 
    	}
    	return reachEndpointDfs(start,map,checked);
    }
    public void minimumSumDfs(TreeNode node, ArrayList<Integer> l, int sum) {
    	if(node == null)
    		return;
    	if(node.left == null && node.right == null) {
    		sum += node.val;
    		l.add(sum);
    	}
    	minimumSumDfs(node.left,l,sum+node.val);
    	minimumSumDfs(node.right,l,sum+node.val);
    }
    public int minimumSum(TreeNode root) {
    	ArrayList<Integer> l = new ArrayList<Integer>();
    	if(root == null)
    		return 0;
    	if(root.left == null && root.right == null)
    		return root.val;
    	
    	minimumSumDfs(root,l,0);
    	Collections.sort(l);
    	return l.get(0);
    	
    }
    public List<String> uniqueSubstring(String s, int k) {
    	List<String> ret = new ArrayList<String>();
    	HashSet<String> hs = new HashSet<String>();
    	if(s.length() == 0 || k == 0)
    		return ret;
    	if(k > s.length())
    		return ret;
    	if(k == s.length()) {
    		ret.add(s);
    		return ret;
    	}
    	for(int i = 0; i < s.length()- k + 1; i++) {
    		hs.add(s.substring(i, i + k));
    	}
    	for(String tmpS : hs)
    		ret.add(tmpS);
    	Collections.sort(ret);
    	return ret;
    }
    public int dotProduct(int[] A, int[] B) {
    	long[] arrs;
    	long ret = 0;
    	if(A.length == 0 && B.length == 0)
    		return (-1);
    	if(A.length != B.length)
    		return (-1);
    	arrs = new long[A.length];
    	for(int i = 0; i < A.length; i++) {
    		arrs[i] = A[i]*B[i];
    	}
    	Arrays.sort(arrs);
    	for(long i : arrs) {
    		ret += i;
    	}
    	return (int)ret;
    }
    public int closestTargetValue(int target, int[] array) {
    	HashMap<Integer,Integer> m = new HashMap<Integer,Integer>();
    	int minDiff = Integer.MAX_VALUE;
    	int sum = -1;
    	if(array.length == 2)
    		return array[0] + array[1];
    	for(int i = 0; i < array.length; i++) {
    		m.put(array[i], target-array[i]);
    	}
    	for(int i = 0; i < array.length; i++) {
    		int diff = m.get(array[i]);
    		for(int j = 0; j < array.length && j != i; j++) {
    			if(diff >= array[j] && (diff - array[j]) <= minDiff) {
    				minDiff= diff - array[j];
    				sum = array[i] + array[j];
    			}
    		}
    	}
    	return sum;
    }
}
