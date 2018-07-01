package leetcode;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Solution0623 {
	Solution0623(){
		System.out.printf("ret:%d \n",scoreOfParentheses("()()()"));
	}
    public int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack<Integer>();
        int ret = 0;
        for(int idx = 0; idx < S.length(); idx++) {
        	if(S.charAt(idx) == '(') {
        		stack.push(-1);
        	}
        	else {
        		int sum = 0;
        		while(!stack.isEmpty()) {
        			int num = stack.pop();
        			if(num != -1)
        				sum += num;	
        			else
        				break;
        		}
        		if(sum == 0)
        			sum = 1;
        		else
        			sum = sum * 2;
        		System.out.printf("sum:%d\n",sum);
        		stack.push(sum);
        	}
        }
        while(!stack.isEmpty()) {
        	ret += stack.pop();
        }
        return ret;
    }
    public boolean buddyStrings(String A, String B) {
    	boolean ret = false;
    	int ASum,BSum,diffCnt;
    	ASum = BSum = diffCnt = 0;
    	String diffStr = new String();
    	if(A.length() == 0 && B.length() == 0)
    		return false;
    	if(A.length() == 0 && B.length() != 0)
    		return false;
    	if(A.length() != B.length())
    		return false;
    	for(int i = 0; i< A.length(); i++) {
    		if(i == 0) {
    			diffStr+=A.charAt(0);
    		}
    		else {
    			if(diffStr.indexOf(A.charAt(i)) == -1)
    				diffStr+=A.charAt(i);
    		}
    		ASum += A.charAt(i) - '\0';
    		BSum += B.charAt(i) - '\0';
    		if(A.charAt(i) != B.charAt(i))
    			diffCnt ++;
    	}
		//System.out.printf("%d %d %s\n",diffCnt,diffStr.length(),diffStr);
    	if(diffCnt == 0 && diffStr.length() == 1)//(aa,aa)
    		return true;
    	if(diffCnt == 0 && 2*diffStr.length() == A.length() && A.length() != 2)//(abab,abab)
    		return true;
    	if(diffCnt == 2 && (ASum == BSum))//(ab,ca)
    		ret = true;
    	return ret;
    }
}
