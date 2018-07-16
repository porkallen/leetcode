package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import javafx.util.Pair;

public class Solution0714 {
	public static int[] checked;
	Solution0714(){
		//System.out.printf("ret:%d \n", binaryGap(8));			
		int[] A = {2,7,11,15};
		int[] B = {1,10,4,11};
		int[] C = advantageCount(A,B);
		//for(int i : C)
		//	System.out.printf("ret: %d ", i);

		System.out.printf("%s \n",reorderedPowerOf2(281));
	}
	public boolean allCombine1(char a[],Set<Integer> s) {
		boolean flag = false;
		Stack<Character> st = new Stack<Character>();
		for(int i = 0 ; i < a.length; i++) {
			ArrayList<Character> retList = new ArrayList<Character>();
			st.push(a[i]);
			while(!st.isEmpty()) {
				char c = st.pop();
				System.out.printf("%c \n",c);
				if(retList.contains(c)) {
					continue;
				}
				retList.add(c);
				for(int j = 0 ; j < a.length; j++) {
					if(!retList.contains(a[j])) {
						System.out.printf("push %c \n",a[j]);
						st.push(a[j]);
					}
				}		
			}
			System.out.printf("%s \n",retList.toString());
		}
		
		return flag;
	}
	public boolean allCombine(char a[],char ret[],int start, int end, int index, Set<Integer> s) {
		boolean flag = false;
		if(start == end) {
			System.out.println("result:"+Arrays.toString(ret));
			if(s.contains(Integer.parseInt(String.valueOf(ret)))) {
				//System.out.println("result:"+Arrays.toString(ret));
				return true;
			}
			return flag;
		}
		for(int i = 0 ; i < end; i++) {
			/*No repeated number in one set*/
			if(checked[i] == 0) {
				checked[i] = 1;
				ret[index] = a[i];
				flag |= allCombine(a,ret,start+1,end,index+1,s);
				checked[i] = 0;
			}
		}
		return flag;
	}
    public boolean reorderedPowerOf2(int N) {
        // Current combination is ready to be printed, print it
    	boolean ret = false;
    	String s = Integer.toString(N);
    	char[] data = new char[s.length()];
    	Set<Integer> s1 = new HashSet<Integer>();
		checked = new int[9];
		Arrays.fill(checked, 0);
		if(N == 1)
			return true;
    	for(int i = 1 ; i < 31; i++) {
    		s1.add((int) Math.pow(2, i));
    	}
    	//ret |= allCombine(s.toCharArray(),data,0,s.length(),0,s1);
    	allCombine1(s.toCharArray(),s1);
    	return ret;
    }
    public int[] advantageCount(int[] A, int[] B) {
    	int[] rets = new int[A.length];
    	if(A.length == 0 || A.length == 1)
    		return A;
    	ArrayList<Integer> aList = new ArrayList<Integer>();
    	ArrayList<Pair<Integer,Integer>> bList = new ArrayList<Pair<Integer,Integer>>();
    	for(int i : A)
    		aList.add(i);
    	for(int i = 0; i < B.length; i++) {
    		bList.add(new Pair <Integer,Integer> (B[i],i));
    	}
    	Collections.sort(aList);
    	Collections.sort(bList, new Comparator<Pair<Integer, Integer>>() {
    	    @Override
    	    public int compare(final Pair<Integer, Integer> o1, final Pair<Integer, Integer> o2) {
    	        if(o1.getKey() > o2.getKey())
    	        	return -1;
    	        else
    	        	return 1;
    	    }
    	});
    	for(int i = 0; i < B.length; i++) {
    		int j = 0;
    		int len = aList.size();
			//System.out.printf("%d sz:%d \n",bList.get(i).getKey(),aList.size());
    		if(aList.size() == 0)
    			break;
    		for(j = 0; j < len; j++) {
    			if(aList.get(j) > bList.get(i).getKey()) {
    				rets[bList.get(i).getValue()] = aList.get(j);
    				aList.remove(j);
    				break;
    			}	
    		}
    		if(j == len) {
				rets[bList.get(i).getValue()] = aList.get(0);
				aList.remove(0);
    		}
    	}
    	return rets;
    }
    public int binaryGap(int N) {
    	int[] num = new int[31];
    	int numIdx = 0;
    	int tmpN = N;
    	int preIdx;
    	int ret = 0;
    	
    	if(N == 0)
    		return 0;
    	if(N == 1)
    		return 0;
    	
    	preIdx = -1;
    	Arrays.fill(num, 0);
    	while(tmpN != 0) {
    		num[numIdx++] = tmpN % 2;   		
    		tmpN = tmpN / 2;
    	}
    	for(int i = 0; i < num.length; i++) {
    		//System.out.printf("idx:%d(%d) preIdx%d\n", i,num[i],preIdx);
    		if(num[i] == 1) {
    			if(preIdx != -1 && Math.abs(preIdx - i) >= ret) {
    				ret = Math.abs(preIdx - i);
    			}
    			preIdx = i;
    		}
    	}
    	return ret;
    }
}
