package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SolutionDFS {
	SolutionDFS(){
		int[] nums = {8,2,1};
		//List<List<Integer>> l = findAllCombination(nums);
		/*for(List<Integer> i : l) {
			System.out.printf("xx%s \n",i.toString());
		}*/
		TreeNode root = new TreeNode(5);
		TreeNode temp = root;
		root.left = new TreeNode(3);
		root.right = new TreeNode(6);
		temp = root.left;
		temp.left = new TreeNode(1);
		temp.right = new TreeNode(2);
		temp = root.right;
		temp.left = new TreeNode(7);
		calculateTreeHeight(root);
	}
	public int calculateTreeHeightDFS(TreeNode node) {
		int lHeight,rHeight;
		lHeight = rHeight = 0;
		if(node == null) {
			return 0;
		}
		lHeight = calculateTreeHeightDFS(node.left);
		rHeight = calculateTreeHeightDFS(node.right);
		System.out.printf("val:%d L:%d R:%d\n",node.val,lHeight,rHeight);
		return (lHeight > rHeight)? (lHeight+1):(rHeight+1);
	}
	public int calculateTreeHeight(TreeNode root) {
		int ret = calculateTreeHeightDFS(root);
		System.out.printf("ret:%d \n",ret);
		return ret;
	}
	public void findAllCombinationDFS(List<List<Integer>> l,int[] nums, int len, List<Integer> subSet) {
		if(len >= nums.length) {
			System.out.printf("--%s \n",subSet.toString());
			l.add(new ArrayList<Integer>(subSet));
			return;
		}
		for(int i = 0; i < nums.length; i++) {
			if(!subSet.contains(nums[i])) {
				subSet.add(nums[i]);
				findAllCombinationDFS(l,nums,len + 1,subSet);
				subSet.remove(subSet.size()-1);
			}
		}
		return;
	}
	public List<List<Integer>> findAllCombination(int[] nums){
		List<List<Integer>> l = new ArrayList<List<Integer>>();
		List<Integer> subSet = new ArrayList<Integer>();
		
		findAllCombinationDFS(l,nums,0,subSet);
		return l;
	}
}
