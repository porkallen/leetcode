package leetcode;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution0630 {
	Solution0630(){
		TreeNode root = new TreeNode(0);
		TreeNode travel = root;
		TreeNode target;
		/*
		root.left = new TreeNode(5);
		root.right = new TreeNode(1);
		target = root.left;
		travel = root.left;
		travel.left = new TreeNode(6);
		travel.right = new TreeNode(2);
		travel = travel.right;
		travel.left = new TreeNode(7);
		travel.right = new TreeNode(4);
		travel = root.right;
		travel.left = new TreeNode(0);
		travel.right = new TreeNode(8);
		travel = new TreeNode(5);
		*/
		root.left = new TreeNode(2);
		root.right = new TreeNode(1);
		travel = root.right;
		travel.left = new TreeNode(3);
		target = travel.left;
		
		//distanceK(root,target,3);
		//int[] arr = {48,99,37,4,-31};
		//System.out.printf("ret %d\n",shortestSubarray(arr,140));
		int[][] A = {{0},{1}};
		System.out.printf("ret:%d \n", matrixScore(A));
	}
    public int shortestSubarray(int[] A, int K) {
    	int minLen = Integer.MAX_VALUE;
    	int[] prefixSum = new int[A.length + 1];
    	if(A.length == 1) {
    		if(A[0] != K)
    			return (-1);
    		return 1;
    	}
    	prefixSum[0] = 0; 
    	for(int i = 1; i < A.length + 1; i++) {
    		prefixSum[i] = prefixSum[i-1] + A[i-1]; 
    	}
    	for(int i = A.length; i >= 1; i--) {
    		for(int j = i - 1; j >= 0; j--) {
    			int temp = prefixSum[i] - prefixSum[j];
    			if(temp >= K && minLen > (i-j)) {
    				minLen = i - j;
    				//System.out.printf("i:%d,j%d\n",i,j);
    			}
    		}
    	}
    	return minLen == Integer.MAX_VALUE?(-1):minLen;
    }
    public int[][] toggleOne(int[][] a,int x,int y,boolean isCol) {
    	if(isCol) {
        	for(int i = 0; i < a.length; i++) {
        		if(a[i][y] == 1)
        			a[i][y] = 0;
        		else
        			a[i][y] = 1;
        	}
    	}
    	else {
        	for(int i = 0; i < a[x].length; i++) {
        		if(a[x][i] == 1)
        			a[x][i] = 0;
        		else
        			a[x][i] = 1;
        	}
    	}
    	return a;
    }
    public boolean isOneMore(int[][] a,int x,int y,boolean isCol) {
    	int oneCount = 0;
    	int zeroCount = 0;
    	if(isCol) {
        	for(int i = 0; i < a.length; i++) {
        		//System.out.printf("idx:%d y:%d len:%d \n",i,y, a.length);
        		if(a[i][y] == 1)
        			oneCount++;
        	}
        	zeroCount = a.length - oneCount;
    	}
    	else {
    		/*
        	for(int i = 0; i < a[x].length; i++) {
        		if(a[x][i] == 1)
        			oneCount++;
        	}
        	zeroCount = a[x].length - oneCount;
        	if(oneCount > zeroCount) {
        		if(a[x][0] == 1)
        			return false;
        	}*/
    		if(a[x][0] == 0)
    			return false;
    		else
    			return true;
    	}
    	return (oneCount >= zeroCount);
    }
    public int matrixScore(int[][] A) {
        int ret = 0;
        int m = A.length;
        int n = A[0].length;
        if(m == 0 && n == 0)
        	return ret;
        /*for(int i = 0; i < m; i++) {
    		System.out.printf("[");
        	for(int j = 0; j < n; j++) {
        		System.out.printf("%d,",A[i][j]);
        	}
    		System.out.printf("]\n");
        }
        System.out.printf("====================\n");*/
        for(int i = 0; i < m; i++) {
        	for(int j = 0; j < n; j++) {
        		if(!isOneMore(A,i,j,true)) {
        			A = toggleOne(A,i,j,true);
        		}
        		else {
            		if(!isOneMore(A,i,j,false)) {
            			A = toggleOne(A,i,j,false);
            		}
        		}
            	/*System.out.printf("======(%d,%d)======\n",i,j);
                for(int a = 0; a < m; a++) {
            		System.out.printf("[");
                	for(int b = 0; b < n; b++) {
                		System.out.printf("%d,",A[a][b]);
                	}
            		System.out.printf("]\n");
                }*/
        	}
        }
        for(int i = 0; i < m; i++) {
        	int dim = n - 1;
        	for(int j = 0; j < n; j++) {
        		ret += Math.pow(2,dim--) * A[i][j];
        	}
        }
        return ret;
    }
	public void distanceKDFS(TreeNode target,int distance,Map<TreeNode,Boolean> m,List<Integer> ret){
		if(target == null)
			return;
		if(!m.isEmpty() && m.containsKey(target) && m.get(target) == true) {
			return;
		}
		if(distance == 0) {
			System.out.printf("val:%d \n",target.val);
			ret.add(target.val);
			return;
		}
		distanceKDFS(target.left,distance - 1,m,ret);
		distanceKDFS(target.right,distance - 1,m,ret);
	}
	public Map<TreeNode,TreeNode> treeMapBuild(TreeNode root){
		Map<TreeNode,TreeNode> m = new HashMap<TreeNode,TreeNode>();
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		while(!q.isEmpty()) {
			TreeNode node= q.poll();
			if(node.left != null) {
				m.put(node.left,node);
				q.add(node.left);
			}
			if(node.right != null) {
				m.put(node.right,node);
				q.add(node.right);
			}
		}
		return m;
	}
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
    	List<Integer> ret = new LinkedList<Integer>();
    	Map<TreeNode, TreeNode> treeMap =  new HashMap<TreeNode,TreeNode>();
    	Map<TreeNode, Boolean> treeMapVisited =  new HashMap<TreeNode,Boolean>();
    	if(root == null)
    		return ret;
    	if(root.val == target.val && K == 1) {
    		if(root.left != null)
    			ret.add(root.left.val);
    		if(root.right != null)
    			ret.add(root.right.val);
    		return ret;
    	}
    	treeMap = treeMapBuild(root);
    	if(treeMap == null)
    		return ret;
    	// search down
    	distanceKDFS(target, K,treeMapVisited,ret);
    	// backedge
    	treeMapVisited.put(target,true);
    	TreeNode node = treeMap.get(target);
    	while(node != null) {
			//System.out.printf("node parent:%d Dis:%d\n",node.val,--K);
    		distanceKDFS(node, --K,treeMapVisited,ret);
        	treeMapVisited.put(node,true);
    		node = treeMap.get(node);
    	}
    	return ret;
    }
	//5,10,20
    public boolean lemonadeChange(int[] bills) {
    	boolean ret = false;
    	int[] depos = new int[3];
    	if(bills.length == 0)
    		return ret;
    	if(bills[0] != 5)
    		return ret;
    	for(int i = 0; i< bills.length; i++) {
    		if(bills[i] == 5) {
    			depos[0]++;
    		}
    		else if(bills[i] == 10) {
    			if(depos[0] != 0) {
    				depos[0]--;
    				depos[1]++;
    			}
    			else {
    		    	System.out.printf("1\n");
    				return ret;
    			}
    		}
    		else {
    			if(depos[0] != 0 && depos[1] != 0) {
    					depos[0]--;
    					depos[1]--;
    					depos[2]++;
    			}
    			else if(depos[0] >= 3) {
    				depos[0] -=3;
    			}
    			else {
    		    	System.out.printf("2\n");
    				return ret;
    			}
    		}
    	}
    	return true;
    }
}
