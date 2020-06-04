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
import java.util.Stack;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Solution0818 {
	Solution0818(){
		int[] pre = {1,2,4,5,3,6,7};
		int[] post = {4,5,2,6,7,3,1};
		constructFromPrePost(pre,post);
		
	}
	public TreeNode constructFromPrePostRecursive(int[] pre, int[] post,
			int preLeft, int preRight, int postLeft, int postRight) {
	    TreeNode ret = null;
	    if(preLeft > preRight || postLeft > postRight){
	        return null;
	    }
	    ret = new TreeNode(pre[preLeft++]);
	    int postMid = -1;
	    if(preLeft > preRight)
	    	return ret;
	    
	    for(int i = postLeft; i <= postRight; i++) {
	    	if(post[i] == pre[preLeft]) {
	    		postMid = i;
	    		break;
	    	}
	    }
	    if(postMid == -1)
	    	return ret;

	    int sz = postMid - postLeft + 1;
	    ret.left = constructFromPrePostRecursive(pre,post,preLeft, 
	    		preLeft + sz - 1,postLeft,postMid);
	    ret.right = constructFromPrePostRecursive(pre,post,preLeft + sz, 
	    		preRight,  postMid + 1,postRight);
	    return ret;
	}
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
    	TreeNode ret = null;
    	if(pre.length == 0 || post.length == 0)
    		return ret;
    	if(pre.length != post.length)
    		return ret;
    	if(pre.length == 1) {
    		ret = new TreeNode(pre[0]);
    		return ret;
    	}
    	return constructFromPrePostRecursive(pre, post,0, pre.length - 1, 0,post.length - 1);
    }
    public List<String> findAndReplacePattern(String[] words, String pattern) {
    	List<String> ret = new LinkedList<String>();
        if(words.length == 0 || pattern.length() == 0)
        	return ret;
        
        for(String word : words) {
            HashMap<Character,Character> hm = new HashMap<Character,Character>();
            boolean isMatch = true;
            if(pattern.length() != word.length())
            	continue;
            for(int i = 0; i < pattern.length(); i++) {
            	if(hm.containsKey(pattern.charAt(i))) {
            		if(!hm.get(pattern.charAt(i)).equals(word.charAt(i))) {
            			isMatch = false;
            			break;
            		}
            	}
            	else {
            		if(hm.containsValue(word.charAt(i))) {
            			isMatch = false;
            			break;
            		}
            		hm.put(pattern.charAt(i), word.charAt(i));
            	}
            }
        	if(isMatch) {
        		ret.add(word);
        	}
        }
        return ret;
        
    }
    public int[] fairCandySwap(int[] A, int[] B) {
    	int[] ret = new int[2];
    	int ASum = 0, BSum = 0;
        if(A.length == 0 || B.length == 0)
        	return ret;
        for(int i : A)
        	ASum += i;
        for(int i : B)
        	BSum += i;
        
        for(int i = 0; i < A.length; i++) {
        	for(int j = 0; j < B.length; j++) {
        		if(ASum - A[i] + B[j] == BSum - B[j] + A[i]) {
        			ret[0] = A[i];
        			ret[1] = B[j];
        			return ret;
        		}
        	}
        }
        return ret;
    }
}
