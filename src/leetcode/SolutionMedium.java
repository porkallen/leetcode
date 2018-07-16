package leetcode;

import java.util.Stack;

public class SolutionMedium {
	SolutionMedium(){
		int[] preOrder = {3,9,20,15,7};
		int[] inOrder = {9,3,15,20,7};
		TreeNode ret = buildTree(preOrder,inOrder);
		System.out.printf("%d %d %d \n",ret.val,ret.left.val,ret.right.val);
		
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
