package leetcode;
import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Interval {
     int start, end;
     Interval(int start, int end) {
         this.start = start;
         this.end = end;
     }
 }
public class Solution0706 {
	Solution0706(){
		//Given list = [[1,2,3],[3,9,7],[4,5,10]], return 2.
		//Given list = [[1],[1,2,3],[4],[8,7,4,5]], return 2
		int[][] sets = {{1,2,3},{1,9,7},{4,5,10},{11,12,13}};
		System.out.printf("test:%d \n", setUnion(sets));
	}
    public int getColumn(int[][] arr) {
    	int end = Integer.MAX_VALUE;
    	if(arr.length == 0)
    	    return 0;
        for(int i = 0; i < arr.length; i++) {
        	end = Math.min(arr[i].length,end);
        	for(int j = 0; j < end; j++) {
        		if(arr[i][j] == 1) {
        			end = j;
        			break;
        		}
        	}
        }
        return end;
    }
    public int setUnion(int[][] sets) {
    	Integer[][] tempSets = new Integer[sets.length][100];
    	for (int i = 0; i < sets.length; i++) {
    		for(int j = 0; j < sets[i].length; j++)
    			tempSets[i][j] = Integer.valueOf(sets[i][j]);
    	}
    	Map<Integer,HashSet<Integer>> m = new HashMap<Integer,HashSet<Integer>>();
    	HashSet<Integer> s = new HashSet<Integer>();
    	for(int i = 0; i < sets.length; i++) {
    		s = new HashSet<Integer>(Arrays.asList(tempSets[i]));
    		boolean isContain = false;
    		for(int j = 0; j < i; j++) {
    			for(int k = 0; k < sets[i].length; k++) {
        			if(m.get(j) != null && m.get(j).contains(sets[i][k])) {
        				HashSet<Integer> tmp = m.get(j);
        				tmp.addAll(s);
        				m.put(j, tmp);
        				isContain = true;
        				break;
        			}
    			}
    			if(isContain == true)
    				break;
    		}
			if(!isContain)
        		m.put(i, s);
    	}
    	return m.size();
    }
    public List<Interval> timeIntersection(List<Interval> seqA, List<Interval> seqB) {
        List<Interval> ret = new LinkedList<Interval>();
        int[] a = new int[10000000];
        int[] b = new int[10000000];
        int start,end;
        if(a.length == 0 || b.length == 0)
        	return ret;
        
        start = end = -1;
        for(Interval itv:seqA) {
        	for(int i = itv.start; i < itv.end; i++) {
        		a[i] = 1; 
        	}
        }
        for(Interval itv:seqB) {
        	for(int i = itv.start; i < itv.end; i++) {
        			b[i] = 1;
        	}
        }
        for(int i = 0; i < a.length; i++) {
        	if(a[i] == 1 && b[i] == 1) {
        		if(start == -1) 
        			start = i;
        	}
        	else {
        		if(start != -1) {
        			end = i;
        			Interval tmp = new Interval(start,end);
        			ret.add(tmp);
        		}
        		start = end = -1;
        	}
        }
        return ret;
    }
}
