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
import java.util.Stack;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Solution0810 {
	boolean[] checked;
	Solution0810(){
		int[] arr = {1,2,3,4,5};
		//System.out.printf("%d \n",takeTheElementAndQueryTheSum(arr));
		String s = "cabbcbb";
		int k = 5;
		System.out.printf("%s \n",MinimumString(s.toCharArray(), k));

	}
    public String MinimumString(char[] s, int k) {//Notes
    	StringBuffer ret = new StringBuffer("");
    	int curIdx = 0;
    	Stack<Character> st = new Stack<Character>();
    	if(s.length <= k)
    		return ret.toString();
    	st.push(s[curIdx++]);
    	while(curIdx < s.length) {
    			while(!st.isEmpty()) {
    				if(st.peek() > s[curIdx] && k > 0) {
    					st.pop();
    					k--;
    				}
    				else
    					break;
    			}
    			st.push(s[curIdx++]);
    	}
    	while(!st.isEmpty() && k > 0) {
				st.pop();
				k--;
		}
    	for(int i = 0; i < st.size(); i++) {
    		ret.append(st.get(i));
    	}
    	return ret.toString();
    }
    public int takeTheElementAndQueryTheSum(int[] arr) {
    	long ret = 0;
    	long sum = 0;
    	long modNum = 1000000007;
    	if(arr.length == 0)
    		return 0;
    	if(arr.length == 1)
    		return arr[0];
    	for(int i = 0; i < arr.length; i++) {
    		ret = (ret + ((arr[i] * sum)% modNum))% modNum;
    		sum = (sum + arr[i]) % modNum;
    	}
    	return (int)(ret % modNum);
    }
}
