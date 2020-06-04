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
import java.util.Map.Entry;

import org.w3c.dom.Node;

import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Solution0908 {
	
	HashMap<String,Integer> hm;
	Solution0908(){
		String[] num = {"2","3","4","5","6","7","8"};
		atMostNGivenDigitSet(num,5487819);
	}
	public int dfs(String[] D, StringBuilder sb, int N, int len) {
		StringBuilder tmpsb = null;
		int ret = 1;
		if(sb.length() > len || (sb.length() != 0 && Long.parseLong(sb.toString()) > N))
			return 0;
		if(hm.containsKey(sb.toString())) {
			return hm.get(sb.toString());
		}
		for(int i = 0; i < D.length; i++) {
			tmpsb = new StringBuilder(sb);
	    	//System.out.printf("%s \n",sb.toString());
			tmpsb.append(String.valueOf(D[i]));
			ret += dfs(D,tmpsb,N,len);
		}
		hm.put(tmpsb.toString(), ret);
		return ret;
	}
    public int atMostNGivenDigitSet(String[] D, int N) {
    	hm = new HashMap<String,Integer>();
    	int len = String.valueOf(N).length();
    	StringBuilder sb = new StringBuilder();
    	int ret = dfs(D,sb,N,len);
    	System.out.printf("%d \n",ret);
    	return ret-1;
    }
	class StockSpanner {
		Stack<int[]> s;
	    public StockSpanner() {
	        s = new Stack<int[]>();
	    }
	    
	    public int next(int price) {
	    	if(s.size() != 0) {
	    		int[] pre = s.lastElement();
	    		int num = 1;
	    		if(pre[0] > price) {
		    		int[] tmp = new int[2];
		    		tmp[0] = price;
		    		tmp[1] = num;
	    			s.push(tmp);
	    			return num;
	    		}
	    		while(!s.isEmpty() && s.lastElement()[0] <= price) {
	                //System.out.printf("price:%d top:%d rep:%d num:%d\n",price,s.lastElement()[0],pre[1],num);
		    		num += pre[1];
		    		pre = s.pop();
	    		}
	    		int[] tmp = new int[2];
	    		tmp[0] = price;
	    		tmp[1] = num;
	    		s.push(tmp);
	    		return num;
	    	}
	    	else {
	    		int[] tmp = new int[2];
	    		tmp[0] = price;
	    		tmp[1] = 1;
	    		s.push(tmp);
	    		return 1;
	    	}
	    }
	}

	class RLEIterator {
	    List<int[]> retList;
	    int curIdx;
	    public RLEIterator(int[] A) {
	        retList = new ArrayList<int[]>();
	        curIdx = 0;
	        int repNum = 0;
	        for(int i = 0; i < A.length; i++){
	            if(i % 2 == 0){
	                repNum = A[i];
	            }
	            else{
	            	int[] tmpArr = {repNum,A[i]};
	            	retList.add(tmpArr);//repNum,num
	                repNum = 0;
	            }
	        }
	        
	    }
	    
	    public int next(int n) {
	    	if(n == 0) {
		    	for(int i = 0; i < retList.size(); i++) {
		    		if(retList.get(i)[0] != 0) {
		    			return retList.get(i)[1];
		    		}
		    	}
		    	return (-1);
	    	}
	    	for(int i = 0; i < retList.size(); i++) {
	    		if(n < retList.get(i)[0]) {
	    			retList.get(i)[0] -= n;
	    			return retList.get(i)[1];
	    		}
	    		else {
	    			n -= retList.get(i)[0];
	    			retList.get(i)[0] = 0;
	    		}
	    	}
	    	return (-1);
	    }
	}
}
