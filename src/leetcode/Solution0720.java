package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.util.Pair;

public class Solution0720 {
	Solution0720(){
		//System.out.printf("%s \n",isTwin("abcd","cbad"));
		int[] arr = {2,4,6,8,10};
		//System.out.printf("%d \n",findTheSumOfTheArray(arr));//"she","was","bad","in","singing"
		//"i","love","lintcode","so","much"
		String[] str = {"she","was","bad","in","singing"};
		System.out.printf("%d \n",getLength(2,10,str));

	}    
    public int getLength(int k, int lim, String[] str) {
    	int consNum = 0;
    	int accLen = 0;
    	int ret = Integer.MAX_VALUE;
    	int lIdx,rIdx;
    	lIdx = 0;
    	rIdx = str.length;
    	int[] arrs = new int[str.length]; 
    	for(int i = 0; i < str.length; i++) {
    		arrs[i] = str[i].length();
    	}
    	for(int i = lIdx; i < str.length; i++) {
    		int sum = arrs[i];
    		int len = 0;
    		boolean isFind = false;
    		for(int j = i + 1; j < Math.min(str.length,rIdx+1); j++) {
    			sum += arrs[j];
    			len = j - i + 1;
    			if(len >= k && sum >= lim) {
    				rIdx = j;
    				isFind = true;
    				break;
    			}
    		}
			System.out.printf("%d L:%d R:%d sum:%d\n",i,lIdx,rIdx,sum);
    		if(isFind)
    			ret = Math.min(sum, ret);
    		else
    			rIdx++;
    	}
    	return ret;
    }
    public long findTheSumOfTheArray(int[] arr) {
    	long sum = 0;
    	for(int i = 0 ; i < arr.length; i++) {
    		sum = (sum + arr[i] * (arr.length - i)) % 1000000007;
        	for(int j = i + 1 ; j < arr.length; j++) {
        		sum = (sum + arr[j] * (arr.length - j)) % 1000000007;
        	}
    	}
    	return sum;
    }
	public String isTwin(String s, String t) {
		int sSum,tSum;
		sSum = tSum = 0;
		for(int i = 0; i < s.length(); i++) {
			int sNum = s.charAt(i)-'\0';
			int tNum = t.charAt(i)-'\0';
			if(i%2 == 0) {
				sSum += sNum * 100;
				tSum += tNum * 100;
			}
			else {
				sSum += sNum;
				tSum += tNum;
			}
			//System.out.printf("s:%d t:%d sSum:%d tSum:%d \n",sNum,tNum,sSum,tSum);
		}
		return (sSum == tSum)?"Yes":"No";
    }
}
