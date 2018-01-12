package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class SolutionTree{
	static int rnd = 0;
	SolutionTree(){
		List<TreeNode> tList = generateTrees(3);
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
