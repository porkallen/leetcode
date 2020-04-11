package leetcode;
import java.text.Collator;
import java.util.ArrayList;
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
    private int[] f;
	Solution0706(){
		//Given list = [[1,2,3],[3,9,7],[4,5,10]], return 2.
		//Given list = [[1],[1,2,3],[4],[8,7,4,5]], return 2
		int[][] sets = {{1,2,3},{4,5,6},{3,4},{6,9,10}};
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
        int len = sets.length;
        f = new int[len];
        for (int i = 0; i < len; i++) {
            f[i] = i;
        }
        
        int[] eleF = new int[100001];
        Arrays.fill(eleF, -1);
        
        for (int i = 0; i < len; i++) {
            for (int ele: sets[i]) {
                if (eleF[ele] == -1) {
                    eleF[ele] = find(i);
                } else {
                    int fa = find(eleF[ele]);
                    int fb = find(i);
                    if (fa != fb) {
                        f[fa] = fb;
                    }
                }
            }
            for (int j = 0; j < len; j++) {
                System.out.printf("%d round: idx:%d val:%d \n",i,j,f[j]);
            }
        }
        
        int ans = 0;
        for (int i = 0; i < len; i++) {
            if (f[i] == i) {
                ans ++;
            }
        }
        return ans;
    }
    
    private int find(int x) {
        if (f[x] == x) {
            return x;
        }
        
        return f[x] = find(f[x]);
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
