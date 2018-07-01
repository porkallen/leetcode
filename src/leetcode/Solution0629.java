package leetcode;

import java.util.HashMap;

public class Solution0629 {
    /**
     * @param arr: The given matrix
     * @return: Return the mininum sum
     */
	Solution0629(){
		int[][] a = {{-3,-2,-1},{-2,3,-2},{-1,3,-1}};
		System.out.printf("ret:%d \n",minimumSubmatrix(a));
	}
	public long dpKadeneCal(long[] sum) {
		long dpSum = 0;
    	long min = 0xffffffff;
		for(int i = 0; i < sum.length; i++) {
			dpSum = Math.min(dpSum + sum[i], sum[i]);
			min = Math.min(dpSum, min);
		}
		return dpSum;
	}
	public long dpCal(long[] sum) {
		long ret = Integer.MAX_VALUE;
		long maxVal = 0;
		long[] prefixSum = new long[sum.length];
		prefixSum[0] = sum[0];
		for(int i = 1; i < sum.length; i++) {
			prefixSum[i] = prefixSum[i-1] + sum[i]; 
		}
		for(int i = 0; i < sum.length; i++) {
			ret = Math.min(ret, prefixSum[i] - maxVal);
			maxVal = Math.max(maxVal, prefixSum[i]);
		}
		return ret;
	}
    public long minimumSubmatrix(int[][] arr) {
        //arr[i][j] = arr[i-1][j-1] + arr[i-1][j] + arr[i][j-1];
    	int m = arr.length;
    	int n = arr[0].length;
    	long min = 0xffffffff;
    	if(m == 0 && n == 0)
    		return 0;
    	if(m == 1 && n == 1)
    		return arr[0][0];
    	
        for(int r1 = 0 ; r1 < m; r1++) {
        	long[] sum = new long[n+1];
        	for(int r2 = r1; r2 < m; r2++) {
        		for(int h = 0; h < n; h++) {
        			sum[h] += arr[r2][h];
        			/*
        			 * Accumulated Column.
        			 * For exampl,e sum[h] = arr[r2] + arr[r2 -1] + ...
        			 * */
        		}
        		if(false)
        			min = Math.min(dpKadeneCal(sum), min);
        		else
        			min = Math.min(dpCal(sum), min);
        	}
        }
        return min;
    }
}
