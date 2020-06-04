package leetcode;
import java.util.*;
import javafx.util.*;


public class Solution0825 {
	Solution0825(){
		String[] a = {"abc","acb","bac","bca","cab","cba"};
		//numSpecialEquivGroups(a);
		int[][] grid = {{2}};//{{1,2},{3,4}};
		//surfaceArea(grid);
		allPossibleFBT(7);
	}
	List<TreeNode> build(int n) {
		List<TreeNode> ret = new ArrayList<TreeNode>();
		if(n == 0)
			return ret;
		if(n == 1) {
			ret.add(new TreeNode(0));
			return ret;
		}
		for(int i = 1; i <= n - 2; i += 2) {
			List<TreeNode> lList = build(i);
			List<TreeNode> rList = build(n - 1 - i);
			for(TreeNode left : lList) {
				for(TreeNode right : rList) {
					TreeNode root = new TreeNode(0);
					root.left = left;
					root.right = right;
					ret.add(root);
				}
			}
		}
		return ret;
	}
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> l = new ArrayList<TreeNode>();
        l = build(N);
       // System.out.printf("%d \n",l.size());
        return l;
    }
    public int surfaceArea(int[][] grid) {
        int extfour = 0, topDown = 0, innerArea = 0;
        int rowLen = grid.length, colLen = grid[0].length;
        int[][] cord = {{0,1},{0,-1},{1,0},{-1,0}};
        for(int i = 0; i < rowLen; i++) {
        	extfour += grid[i][0] + grid[i][colLen - 1];
        }
        for(int j = 0; j < colLen; j++) {
        	extfour += grid[0][j] + grid[rowLen - 1][j];
        }
        for(int i = 0; i < rowLen; i++) {
        	for(int j = 0; j < colLen; j++) {
        		if(grid[i][j] >= 1) {
            		for(int k = 0; k < cord.length; k++) {
            			int curR = i + cord[k][0];
            			int curC = j + cord[k][1];
            			if(curR >= 0 && curR < rowLen && curC >=0 && curC < colLen) {
                    		if(grid[i][j] > grid[curR][curC]) {
                    			innerArea += grid[i][j] - grid[curR][curC];
                    		}
            			}		
            		}
            		System.out.printf("%d %d \n",i,j);
        			topDown++;
        		}
        	}
        }
        topDown *= 2;
        System.out.printf("%d %d %d \n",topDown,extfour,innerArea);
        return topDown + extfour + innerArea;
    }
    public int numSpecialEquivGroups(String[] A) {
        HashMap<Pair<String,String>, List<Integer>> hm =
        		new HashMap<Pair<String,String>, List<Integer>>();
        for(int i = 0; i < A.length; i++) {
        	StringBuilder s1 = new StringBuilder();
        	StringBuilder s2 = new StringBuilder();
        	for(int j = 0; j < A[i].length(); j++) {
        		if(j%2 == 0) {
        			s1.append(A[i].charAt(j));
        		}
        		else {
        			s2.append(A[i].charAt(j));
        		}
        	}
        	char[] c1 = s1.toString().toCharArray();
        	char[] c2 = s2.toString().toCharArray();
        	Arrays.sort(c1);
        	Arrays.sort(c2);
        	//System.out.printf("%s / %s \n",String.valueOf(c1),String.valueOf(c2));
        	Pair<String,String> p = new Pair<String,String>(String.valueOf(c1),String.valueOf(c2));
        	List<Integer> l = hm.getOrDefault(p, new ArrayList<Integer>());
        	l.add(i);
        	hm.put(p, l);
        }
        //System.out.printf("%d \n",hm.size());
        return hm.size();
    }
}
