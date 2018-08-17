package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class SolutionMedium {
	SolutionMedium(){
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right = new TreeNode(5);
		root.right.right = new TreeNode(6);
		//flatten(root);
		System.out.printf("%d \n",numTrees(3));
		
	}
    public boolean wordBreak(String s, List<String> wordDict) {
        
    }
	public class BSTIterator {
		TreeNode treeRoot;
		ArrayList<TreeNode> sNodeList;
		int curSNodeIdx;
		public void inorder(TreeNode node) {
			if(node == null)
				return;
			inorder(node.left);
			sNodeList.add(node);
			inorder(node.right);
		}
	    public BSTIterator(TreeNode root) {
	    	curSNodeIdx = 0;
	    	sNodeList = new ArrayList<TreeNode>();
	    	inorder(root);
	    }
	    /** @return whether we have a next smallest number */
	    public boolean hasNext() {
	    	return (curSNodeIdx < sNodeList.size())? true : false;
	    }
	    /** @return the next smallest number */
	    public int next() {
	    	return sNodeList.get(curSNodeIdx++).val;
	    }
	}
	public int kthSmallestCnt(TreeNode node) {
		if(node == null)
			return 0;
		return 1 + kthSmallestCnt(node.left) + kthSmallestCnt(node.right);
		
	}
    public int kthSmallest(TreeNode root, int k) {//230
    	int leftCnt = kthSmallestCnt(root.left);
    	TreeNode retNode = root;
        int curCnt = k;
    	
    	while((leftCnt + 1) != curCnt) {
    		//System.out.printf("Cnt:%d node:%d \n",leftCnt,retNode.val);
        	if(leftCnt + 1 < curCnt) {
                curCnt -= (leftCnt + 1);
        		retNode = retNode.right;
        		leftCnt = kthSmallestCnt(retNode.left);
        	}
        	else {
        		retNode = retNode.left;
        		leftCnt = kthSmallestCnt(retNode.left);
        	}
    	}
    	return retNode.val;   	
    }
	public int numTreesDP(int[][] dp, int left,int right) {
		int ret = 0;
		if(left >= right)
			return 1;
		if(dp[left][right] != -1)
			return dp[left][right];
		for(int i = left; i <= right; i++) {
			ret += numTreesDP(dp,left,i - 1) * numTreesDP(dp,i + 1,right);
		}
		dp[left][right] = ret;
		return ret;
	}
    public int numTrees(int n) {//96
    	int[][] dp = new int[n + 1][n + 1];
    	for(int[] a : dp)
    		Arrays.fill(a, -1);
    			
    	if(n < 2)
    		return n;
    	return numTreesDP(dp, 1, n);
    }
	class MyCalendar {//729

		HashMap<Integer,Integer> hm;
	    public MyCalendar() {
	    	 hm = new HashMap<Integer,Integer>();
	    }
	    
	    public boolean book(int start, int end) {
	        if(start < 0 || end < 0)
	        	return false;
	        if(start >= end)
	        	return false;
	        for(Map.Entry<Integer,Integer> entry: hm.entrySet()) {
	        	int schStart = entry.getKey(), schEnd = entry.getValue();
	        	if(start >= schStart && start < schEnd)
	        		return false;
	        	if(schStart >= start && schStart < end)
	        		return false;
	        }
	        hm.put(start,end);
	        return true;
	    }
	}
	public TreeNode buildTree2Traverse(int[] inorder, int[] postorder, int left, int right, int postLeft, int postRight) {
		TreeNode ret = null;
		if(left > right){
			return null;
        }
		ret = new TreeNode(postorder[postRight]);
		int mid = 0;
		for(int i = left; i < right; i++) {
			if(inorder[i] == postorder[postRight]) {	
				System.out.printf("root: %d \n",inorder[i]);
				mid = i;
				break;
			}
		}
		ret.left = buildTree2Traverse(inorder,postorder,left, mid - 1, postLeft, mid - 1 - left + postLeft);
		ret.right = buildTree2Traverse(inorder,postorder,mid + 1, right, mid - left + postLeft, postRight - 1);
		return ret;
	}
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
    	if(inorder.length == 0 || postorder.length == 0)
    		return null;
    	TreeNode ret = buildTree2Traverse(inorder,postorder,0,inorder.length - 1,0, postorder.length - 1);
    	return ret;
        
    }
	public void flattenTraverse(LinkedList<Integer> l, TreeNode curNode) {
		if(curNode == null)
			return;
    	l.add(curNode.val);
    	flattenTraverse(l,curNode.left);
    	flattenTraverse(l,curNode.right);
	}
    public void flatten(TreeNode root) {//114
    	if(root == null)
    		return;
        LinkedList<Integer> l = new LinkedList<Integer>();
    	flattenTraverse(l,root);
    	root.left = null;
    	for(int i = 1; i < l.size(); i++) {
    		root.right = new TreeNode(l.get(i));
    		root = root.right;
    	}
    }
    public void setZeroes(int[][] matrix) {
    	Set<Integer> s = new HashSet<Integer>();
    	for(int i = 0; i < matrix.length; i++) {
        	boolean ind = false;
    		for(int j = 0; j < matrix[0].length; j++) {
    			if(matrix[i][j] == 0) {
    				ind = true;
    				s.add(j);
    			}
    		}
    		if(ind == true) {
    			Arrays.fill(matrix[i], 0);
    		}
    	}
    	for(int j : s) {
        	for(int i = 0; i < matrix.length; i++) {
        		matrix[i][j] = 0;
        	}
    	}
    }
	public TreeNode buildTreeRecursive(int[] preorder,int preIdx,int rootIdx,int[] inorder,int left, int right){
	    if (preIdx > preorder.length - 1 || left > right) {
	        return null;
	    }
	    TreeNode root = new TreeNode(preorder[preIdx]);
		for(int i = 0; i < preorder.length; i++) {
			if(preorder[preIdx] == inorder[i]) {
				rootIdx = i;
			}
		}
		
		root.left = buildTreeRecursive(preorder,preIdx+1,rootIdx,inorder,left, rootIdx-1);
		/*
		 * Move preIdx to the most left point of right nde set
		 * For example: [5(L),3(L),2(L),4(R),6(R),8(R)]
		 * 
		 * the number of L nodes is rootIdx - left +1. Therefore, the next preIdx for the right node set
		 * is preIdx + rootIdx - left +1;
		 * 
		 * */
		root.right = buildTreeRecursive(preorder,preIdx + rootIdx - left + 1,rootIdx,inorder,rootIdx+1, right);
		return root;
	}
    public TreeNode buildTree(int[] preorder, int[] inorder) {
    	TreeNode ret = null;
    	if(preorder.length == 0 || inorder.length == 0)
    		return ret;
    	if(preorder.length != inorder.length)
    		return ret;
    	return buildTreeRecursive(preorder,0,0,inorder,0,inorder.length - 1);
    }
}
