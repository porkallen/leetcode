package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

public class Solution0831 {
	Solution0831(){
		System.out.printf("%d \n",getAns("Hadoop"));
	}
    public int movingStones(int[] arr) {
        // Write your code here
    	int ret = 0,ret1 = 0;
    	//odd
    	Arrays.sort(arr);
    	for(int i = 0; i < arr.length; i++) {
    		ret += Math.abs(arr[0] - 2*(i + 1));
    		ret1 += Math.abs(arr[0] - (2*i + 1));
    	}
    	return Math.min(ret, ret1);
    }
    public int getAns(String s) {
    	int[] dp = new int[s.length() + 1], dp1 = new int[s.length() + 1];
    	dp[0] = 0;// Keep lowerbound
    	dp1[0] = 1;//Keep upperbound
    	for(int i = 1; i <= s.length(); i++) {
    		boolean isLower = ((s.charAt(i-1) - 'a') >= 0);
    		dp[i] = Math.min(dp[i - 1] + (isLower?1:2), dp1[i - 1] + (isLower?2:2));
    		dp1[i] = Math.min(dp[i - 1] + (isLower?2:2), dp1[i - 1] + (isLower?2:1));
    	}    	
    	return Math.min(dp[s.length()], dp[s.length()]);
    }
}