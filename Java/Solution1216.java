package leetcode;

import java.util.Arrays;

public class Solution1216 {
	Solution1216(){
		//int arr[] = {0,1,2,2};
		int arr[] = {1,3,3,1};
		minCostClimbingStairs(arr);
	}
    public int minCostClimbingStairs(int[] cost) {
    	/*746*/
        int[] dp = new int[cost.length + 1];
        for (int i = 2; i <= cost.length; i++)
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        return dp[cost.length];
    }
    public String shortestCompletingWord(String s, String[] words) {
        int[] counts = new int[26]; // count for each chars in licensePlate
        int total = 0; // total number of chars licensePlate
        for (char c : s.toLowerCase().toCharArray()) {
            if ('a' <= c && c <= 'z') {
                counts[c - 'a']++;
                total++;
            }
        }

        String res = null;
        for (String w : words) {
            int n = total;
            int[] cnts = counts.clone();
            for (char c : w.toCharArray())
                if (cnts[c - 'a']-- > 0) n--;
            if (n == 0 && (res == null || w.length() < res.length()))
                res = w;
        }
        return res;
    }
}
