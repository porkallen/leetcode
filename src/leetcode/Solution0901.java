package leetcode;

import java.util.*;

public class Solution0901 {
	Solution0901(){
		int[] a = {1,2,4};
		subarrayBitwiseORs(a);
	}
    public int subarrayBitwiseORs(int[] A) {
    	Set<Integer> s = new HashSet<Integer>();
    	Set<Integer> t = new HashSet<Integer>();
    	
    	for(int i : A) {
    		Set<Integer> s1 = new HashSet<Integer>();
    		s1.add(i);
    		for(int j : t) {//only set generated last time
    			//System.out.printf("%d %d %d \n",i,j,i|j);
    			s1.add(i|j);
    		}
    		t = s1;
    		//s.addAll(t);//???
    		for(int j : t)
    			s.add(j);
    	}
    	//System.out.printf("==%d \n",s.size());
    	return s.size();
    }
    public String orderlyQueue(String S, int K) {
    	TreeMap<String,Integer> tm = new TreeMap<String,Integer>();
    	tm.put(S, 1);
    	List<Integer> headList = new ArrayList<Integer>();
    	if(K >= 2) {
    		char[] retArrs = S.toCharArray();
    		Arrays.sort(retArrs);
    		//System.out.printf("%s \n",String.valueOf(retArrs));
    		return String.valueOf(retArrs);
    	}
    	int minChar = Integer.MAX_VALUE;
    	for(int i = 0; i < S.length(); i++) {
    		minChar = Math.min(minChar,S.charAt(i) - 'a');
    	}
    	for(int i = 0; i < S.length(); i++) {
    		if(S.charAt(i) == (char)(minChar + 'a')) {
    			headList.add(i);
    		}
    	}
    	//System.out.printf("%s \n",(char)(minChar + 'a'));
    	for(int i : headList){
    		//System.out.printf("%s--%d \n",(char)(minChar + 'a'),i);
    		StringBuilder sb = new StringBuilder();
    		sb.append(S.substring(i, S.length()));
    		sb.append(S.substring(0,i));
    		tm.put(sb.toString(), 1);
    		//System.out.printf("XX%s \n",sb.toString());
    	}
    	//System.out.printf("%s \n",tm.firstKey());
    	return tm.firstKey();
    }
	public void increasingBSTIn(TreeNode node, List<TreeNode> retList) {
		if(node == null)
			return;
		increasingBSTIn(node.left,retList);
		retList.add(node);
		increasingBSTIn(node.right,retList);
	}
    public TreeNode increasingBST(TreeNode root) {
    	List<TreeNode> retList = new ArrayList<TreeNode>();
    	TreeNode retNode = new TreeNode(root.val), retNode1;
    	retNode1 = retNode;
    	if(root.left == null && root.right == null)
    		return root;
    	increasingBSTIn(root,retList);
    	for(TreeNode node : retList) {
    		retNode.right = new TreeNode(node.val);
    		retNode = retNode.right;
    	}
    	return retNode1.right;
    }
    public boolean isMonotonic(int[] A) {
    	int isAesc = 2;
    	if(A.length == 1)
    		return true;
    	for(int i = 0; i < A.length - 1; i++) {
    		if(A[i] == A[i + 1]) {
    			continue;
    		}
    		else if(A[i] < A[i + 1]) {
        		isAesc = 1;
        		break;
        	}
        	else {
        		isAesc = 0;
        		break;
        	}
    	}
    	if(isAesc == 2)
    		return true;
    	//System.out.printf("%d \n",isAesc);
    	for(int i = 0; i < A.length - 1; i++) {
    		if(isAesc == 1) {
    			if(A[i] > A[i + 1])
    				return false;
    		}
    		else {
    			if(A[i] < A[i + 1])
    				return false;
    		}
    	}
    	return true;
    }
}
