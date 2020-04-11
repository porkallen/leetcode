package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class SolutionTree{
	static int rnd = 0;
	SolutionTree(){
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right = new TreeNode(3);
		minDepth(root);
	}
    public int minDepthCal(TreeNode root) {
    	int lDep,rDep;
    	lDep = rDep = 0;
    	if(root == null)
    		return 0;
    	lDep = minDepthCal(root.left);
    	rDep = minDepthCal(root.right);
    	System.out.printf("l:%d r:%d val:%d\n",lDep,rDep,root.val);
    	return (lDep == 0 || rDep == 0) ? lDep + rDep + 1:Math.min(lDep, rDep)+1;
    }
    public int minDepth(TreeNode root) {//111
    	if(root == null)
    		return 0;
    	if(root.left == null && root.right == null)
    		return 1;
        return minDepthCal(root);
    }
	public int tDepth(TreeNode root) {
		int leftH,rightH;
		leftH = rightH = 0;
		if(root == null)
			return 0;
		leftH = tDepth(root.left);		if(leftH == -1)
			return (-1);
		rightH = tDepth(root.right);
		if(rightH == -1)
			return (-1);
		System.out.printf("l:%d r:%d\n",leftH,rightH);

		if(Math.abs(leftH-rightH) > 1)
			return (-1);
		return Math.max(leftH,rightH)+1;
	}
    public boolean isBalanced(TreeNode root) {//110
    	if(tDepth(root) <0)
    		return false;
    	return true;
        
    }
	public void zigzagbfs(TreeNode root,List<List<Integer>> retList) {
		boolean zigzag = false;
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		while(!q.isEmpty()) {
			List<Integer> tmpList = new ArrayList();
			int qz = q.size();
			for(int i = 0; i < qz; i++) {
				TreeNode temp = q.poll();
				if(!zigzag)
					tmpList.add(temp.val);
				else
					tmpList.add(0,temp.val);
				if(temp.left != null) {
					q.add(temp.left);
				}
				if(temp.right != null) {
					q.add(temp.right);
				}
			}
			retList.add(tmpList);
			zigzag = !zigzag;
		}
	}
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {//103
    	List<List<Integer>> retList = new ArrayList();
    	if(root == null)
    		return retList;
    	zigzagbfs(root,retList);
    	return retList;
    }
	public void inorderTIteration(TreeNode root,List<Integer> retList) {
		Stack<TreeNode> s = new Stack<TreeNode>();
		TreeNode temp = root;
		
		while(temp != null || !s.isEmpty()) {
			while(temp != null) {
				s.push(temp);
				temp = temp.left;
			}
			temp = s.pop();
			retList.add(temp.val);
			temp = temp.right;
		}
		
	}
	public void inorderT(TreeNode root,List<Integer> retList) {
		if(root == null)
			return;
		inorderT(root.left,retList);
		retList.add(root.val);
		inorderT(root.right,retList);
		
	}
    public List<Integer> inorderTraversal(TreeNode root) {//94
    	
    	List<Integer> retList = new LinkedList<Integer>();
    	if(root == null)
    		return retList;
    	inorderT(root,retList);
    	return retList;

    }
	public List<TreeNode> tClone(int start, int end) {
		List<TreeNode> retList = new ArrayList();
		
		if(start>end){ 
			retList.add(null); 
			return retList;
		}
		if(start == end) {
			retList.add(new TreeNode(start));
			return retList;
		}
		List<TreeNode> left,right;
		for(int i = start; i <= end; i++) {//start:1 i=1
			left = tClone(start,i-1);
			right = tClone(i+1,end);
			for(int j = 0; j< left.size();j++) {
				for(int k = 0; k< right.size();k++) {
					TreeNode root = new TreeNode(i);
					root.left = left.get(j);
					root.right = right.get(k);
					retList.add(root);
				}
			}
		}
		return retList;
	}
    public List<TreeNode> generateTrees(int n) {
    		List<TreeNode> retList;
    		if(n == 0) {
    	        retList = new ArrayList<TreeNode>();
    	    		//retList.add(new TreeNode());
    		}
    		else
    			retList = tClone(1,n);
    		    	
        return retList;
    }
}
