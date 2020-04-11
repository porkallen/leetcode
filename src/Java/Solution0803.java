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
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Solution0803 {
	Solution0803(){
		//System.out.printf("ret: %d \n",coinProblem(100,29));
		int[] dp = {2,4,2,1,2,1};
		//int[] dp = {3,2,1};
		int n = 6;
		System.out.printf("ret: %d \n",Solve(n,dp));
		//int[][] getMaxArrs = {{1,9},{2,8},{3,7},{4,6},{5,5}};
		//System.out.printf("ret: %d \n",getMax(getMaxArrs));


	}
	class SortbyKey implements Comparator<int[]>{
	    // Used for sorting in ascending order of
	    // roll number
	    public int compare(int[] a, int[] b)
	    {
	        return a[1] - b[1];
	    }
	}
    public int getMax(int[][] a) {
    	int ret = 0;
    	ArrayList<Integer[]> aList = new ArrayList<Integer[]>();
    	Arrays.sort(a, new SortbyKey());
    	for(int i = 0; i < a.length; i++) {
    		if(a[i][0] >= a[i][1])
    			continue;
    		List<Integer> list = Arrays.stream(a[i]).boxed().collect(Collectors.toList());
    		aList.add(list.toArray(new Integer[list.size()]));
    	}
    	//list.add(a[0][1]);
    	//ret = list.size();
    	int lIdx = 0, rIdx = 0;
    	for(int i = 1; i < aList.size(); i++) {
    		Integer[] arrs = aList.get(i);
    		rIdx = i;
    		for(int j = lIdx; j < rIdx; j++) {
        		Integer[] arrSet = aList.get(j);
        		//System.out.printf("set:%d 0:%d 1:%d\n",arrSet[1],arrs[0],arrs[1]);
    			if(arrSet[1] <= arrs[0] || arrSet[1] >= arrs[1])
    				lIdx++;
    		}
    		ret = Math.max(ret, rIdx - lIdx + 1);
    		//System.out.printf("L:%d R:%d cur:%d\n",lIdx,rIdx,ret);
    	}
    	return ret;
    }
    public long Solve(int n, int[] num) {
        // Write your code here
        int[] dp1 = new int[n+2];
        int MOD = (int)(1e9 + 7);
        Arrays.fill(dp1, 0);
        int cur = 1;
        dp1[0] = 1;
        dp1[1] = -1;
        for(int i = 0; i < num.length; i++)
        	System.out.printf("%d ",num[i]);
    	System.out.printf("\n\n");

        for(int i = 0; i < dp1.length; i++)
        	System.out.printf("%d ",i);
    	System.out.printf("\n");
        for (int i = 0; i < n; i++) {
            int L = i + 1, R = Math.min(n + 1, i + num[i] + 1);
            dp1[L] = (dp1[L]  + cur) % MOD;
            dp1[R] = (dp1[R] - cur) % MOD;
            cur = (cur + dp1[i+1]) % MOD;
            for(int a : dp1)
            	System.out.printf("%d ",a);
            System.out.printf(" (cur): %d \n", cur);
        }
        for(int i = 1; i < n+1; i++)
        	dp1[i] = (dp1[i] + dp1[i-1]) % MOD;
    	System.out.printf("========Final=======\n");
        for(int a : dp1)
        	System.out.printf("%d ",a);
        System.out.printf(" (cur): %d \n", cur);
        return dp1[n];
    }
    public int coinProblem(int n, int m) {
    	int[] changes = {100, 50, 20, 10, 5, 2, 1};
        int rest = n-m;
        int ret = 0;
        if(rest < 0)
        	return 0;
        if(n <= 0)
        	return 0;
        for(int change : changes) {
        	int curCnt = rest/change;
    		//System.out.printf("change:%d now: %d \n",change,rest/change);
        	rest -= curCnt * change;
        	ret += curCnt;
        	if(rest <= 0)
        		break;
        }
        return ret;
    }
	
}
