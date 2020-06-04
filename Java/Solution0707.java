package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solution0707 {
	Solution0707(){
		TreeNode root,tmp;
		root = new TreeNode(3);
		root.left = new TreeNode(5);
		root.right = new TreeNode(1);
		tmp = root.left;
		tmp.left = new TreeNode(6);
		tmp.right = new TreeNode(2);
		tmp = tmp.right;
		tmp.left = new TreeNode(7);
		tmp.right = new TreeNode(4);
		tmp = root.right;
		tmp.left = new TreeNode(0);
		tmp.right = new TreeNode(8);
		subtreeWithAllDeepest(root);//9989900
		//System.out.printf("%d \n",primePalindrome(9015110));
	}
	boolean isPrime(int num) {
		if(num %2 == 0)
			return false;
		int sqrt = (int)Math.sqrt(num) + 1;
		for(int i = 3; i < sqrt; i+=2) {
			if(num % i == 0)
				return false;
		}
		return true;
	}
	boolean isPrime2(int num) {
		if (num < 0){
			return false;
		}
		if (num == 0 || num == 1){
			return false;
		}
		if (num == 2 || num == 3){
			return false;
		}
		if((num * num - 1) % 24 == 0){ 
			return true;
		}
		else{
			return false;
		}

	}
	boolean isPalindrome(int n) {
	    char[] cArr = Integer.toString(n).toCharArray();
	    int tailIdx = cArr.length -1;
	    int startIdx = 0;
	    while(startIdx < tailIdx) {
	    	if(cArr[startIdx++] != cArr[tailIdx--])
	    		return false;
	    }
	    return true;
	}
    public int primePalindrome(int N) {
    	if(N == 1)
    		return 2;
    	if(N <= 3)
    		return N;
    	for(int i = N; i < 100000000; i++) {
    		if(isPrime2(i) && isPalindrome(i) &&isPrime(i))
    			return i;
    	}
        return 0;
    }
	public int dfsMine(TreeNode node,Map<TreeNode,List<TreeNode>> m,List<TreeNode> l,int depth) {
		if(node == null)
			return depth-1;
		
			List<TreeNode> newList = new LinkedList<TreeNode>();
			newList.addAll(l);
			newList.add(node);
			m.put(node, newList);
			/*for(TreeNode t : newList)
		    	System.out.printf("val:%d (visited:%d)dep:%d\n",node.val,t.val,depth);*/

		return Math.max(dfsMine(node.left,m,newList,depth+1),dfsMine(node.right,m,newList,depth+1));

	}
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
    	Map<TreeNode,List<TreeNode>> m = new HashMap<TreeNode,List<TreeNode>>();
    	List<TreeNode> l = new LinkedList<TreeNode>();
    	List<List<TreeNode>> arrList = new ArrayList<List<TreeNode>>();
    	TreeNode ret = null;
    	if(root == null)
    		return root;
    	if(root.left == null && root.right == null)
    		return root;
    	int deepestDep = dfsMine(root,m,l,1);
    	//System.out.println("Depth:"+deepestDep);
    	for (Map.Entry<TreeNode,List<TreeNode>> entry : m.entrySet()) {
            if(entry.getValue().size() == deepestDep) {
            	arrList.add(entry.getValue());
            }
    	}
    	/*Find LCA*/
    	List<TreeNode> a = arrList.get(0);
    	if(arrList.size() == 1)
    		return a.get(a.size() - 1);
        int lcaDepth = Integer.MAX_VALUE;
    	for(int i = 1; i < arrList.size(); i++) {
    		List<TreeNode> b = arrList.get(i);
    		for(int j = a.size() - 1; j >= 0; j--) {
                //System.out.printf("A:%d \n B:%d \n",a.get(j).val,b.get(j).val);
    			if(a.get(j).equals(b.get(j))) {
                    //System.out.printf("EQ:%d dep:%d\n",a.get(j).val,j);
                    if(lcaDepth > j){
                        lcaDepth = j;
    				    ret = a.get(j);
                    }
                    break;
    			}
    		}
    	}
    	return ret;
    }   
    public int[][] transpose(int[][] A) {
        int[][] ret = new int[A[0].length][A.length];
        
        if(A.length == 0 || A[0].length == 0)
        	return A;
        for(int i = 0; i < A.length; i++) {
        	for(int j = 0; j < A[0].length; j++) {
        		ret[j][i] = A[i][j];
        	}
        }
        return ret;
                                
    }

}
