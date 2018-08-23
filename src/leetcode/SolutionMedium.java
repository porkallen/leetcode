package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class SolutionMedium {
	SolutionMedium(){
	}
    public int threeSumClosest(int[] nums, int target) {
    	int ret = 0, minDiff = Integer.MAX_VALUE;
    	if(nums.length <= 0)
    		return 0;
    	Arrays.sort(nums);
    	for(int i = 0; i < nums.length; i++) {
    		int left = i + 1, right = nums.length - 1;
    		while(left < right) {
    			int sum = nums[i] + nums[left] + nums[right];
    			if(minDiff > Math.abs(target - sum)) {
    				ret = sum;
    				minDiff = Math.abs(target - sum);
    			}
    			if(sum < target) {
    				left++;
    			}
    			else {
    				right--;
    			}
    		}
    	}
    	return ret;
    }
    public int maxArea(int[] height) {
    	int ret = Integer.MIN_VALUE, l = 0, r = height.length - 1;
        if(height.length <= 1)
        	return ret;
        while(l < r) {
        	int val = Math.min(height[l], height[r]) * Math.abs(l - r);
        	ret = Math.max(ret, val);
        	if(height[l] < height[r]) {
        		l += 1;
        	}
        	else
        		r -=1;
        }
        return ret;
    }
    public String intToRoman(int num) {
    	StringBuilder sb = new StringBuilder();
    	int[] a = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    	String[] b = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        for(int i = 0; i < a.length; i++) {
        	int tmp = num / a[i];
        	for(int j = 0; j < tmp; j++) {
        		sb.append(b[i]);
        	}
        	num -= tmp * a[i];
        }
        return sb.toString();
    }
    public List<List<Integer>> threeSum(int[] nums) {
    	Set<List<Integer>> hs = new HashSet<List<Integer>>();
    	List<List<Integer>> retList = new LinkedList<List<Integer>>();
    	HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
    	if(nums.length == 0)
    		return retList;

    	Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++) {
        	for(int j = i + 1; j < nums.length; j++) {
            	int thirdNum = -(nums[i] + nums[j]);
            	if(hm.get(thirdNum) != null) {
            		int v = hm.get(thirdNum);
            		List<Integer> l = new LinkedList<Integer>();
            		l.add(nums[i]);
            		l.add(nums[j]);
            		l.add(thirdNum);
            		hs.add(l);
            		hm.put(thirdNum,v);
            	}
        	}
        	int val = hm.getOrDefault(nums[i], 0);
        	hm.put(nums[i], ++val);
        }
        retList = new LinkedList<List<Integer>>(hs); 
        return retList;
    }
    public int[] tree(int[] x, int[] y, int[] a, int[] b) {
        // Write your code here
        int[] retArrs = new int[0];
        HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
        MyGraph mg = new MyGraph(100000);
        if(x.length * y.length * a.length * b.length == 0)
            return retArrs;
        
        for(int i = 0; i < x.length; i++) {
        	mg.add(x[i], y[i]);
        	mg.add(y[i], x[i]);
        }
        Queue<MyPair<Integer,Integer>> q = new LinkedList<MyPair<Integer,Integer>>();
        q.add(new MyPair<Integer,Integer>(1,-1));
        while(!q.isEmpty()){
            MyPair<Integer,Integer> curNode = q.poll();
            hm.put(curNode.getKey(),curNode.getValue());
            for(int num : mg.adj[curNode.getKey()]){
                if(hm.get(num) == null)
                    q.add(new MyPair<Integer,Integer>(num,curNode.getKey()));
            }
        }
        retArrs = new int[a.length];
        int retArrsIdx = 0;
        for(int i = 0; i < a.length; i++){
            int p1 = hm.get(a[i]), p2 = hm.get(b[i]);
            int val = 0;
            if(p1 == p2)
                val = 1;
            else if(a[i] == p2 || p1 == b[i])
                val = 2;
            retArrs[retArrsIdx++] = val;
        }
        return retArrs;
    }
	class LRUCache {
		private class Node {
			int key;
			int val;
			Node prev,next;
		}
		HashMap<Integer,Node> hm;
		Node head,tail;
		int curSz,sz;
	    public LRUCache(int capacity) {
	    	this.hm = new HashMap<Integer,Node>();
	    	this.curSz = 0;
	    	this.sz = capacity;
	    	this.head = new Node();
	    	this.head.prev = null;
	    	this.tail = new Node();
	    	this.tail.next = null;
	    	head.next = tail;
	    	tail.prev = head;
	    }
	    public int get(int key) {
	    	int retVal = (-1);
	        if(this.hm.containsKey(key)) {
	        	Node n = this.hm.get(key);
	        	remove(n);
	        	insert(n);
	        	retVal = this.hm.get(key).val;
	        }
	        System.out.printf("====Get Key:%d Val:%d====\n",key,retVal);
        	statusPrintf();
        	return retVal;
	    }
	    public void put(int key, int value) {
	    	Node node = hm.get(key);
	    	if(node == null) {
	    		node = new Node();
	    		node.key = key;
	    		node.val = value;
	    		hm.put(key, node);
	    		this.insert(node);
	    		if(this.isQFull()) {
	    			Node n = popTail();
	    			hm.remove(n.key);
	    		}
	    	}
	    	else {
	    		node.val = value;
	    		this.remove(node);
	    		this.insert(node);
	    	}
	        System.out.printf("====Put Key:%d Val:%d====\n",key,value);
        	statusPrintf();
	    }    
	    public boolean isQFull() {
	    	return (this.curSz > this.sz);
	    }
	    public void insert(Node n) {//always insert after head
	    	n.prev = this.head;
	    	n.next = this.head.next;
	    	if(this.head.next != null) {
		    	this.head.next.prev = n;
	    	}
	    	this.head.next = n;
    		this.curSz++;
	    }
	    private Node popTail(){
	    	Node n = tail.prev;
	    	this.remove(n);
	    	return n;
	    }
	    public void remove(Node n) {
	    	if(n.prev != null)
	    		n.prev.next = n.next;
	    	if(n.next != null)
	    		n.next.prev = n.prev;
	    	this.curSz--;
	    }
	    public void statusPrintf() {
	    	System.out.printf("Queue Status \n");
	    	System.out.printf("Queue cur Sz:%d (cap:%d) \n",curSz,sz);
	    	System.out.printf("Queue Content \n");
	    	Node t = head.next;
	    	while(t != null && t!= tail) {
	    		System.out.printf("(K:%d,V:%d) ",t.key,t.val);
	    		t = t.next;
	    	}
    		System.out.printf("\n");
	    	System.out.printf("Hash Status \n");
	    	for(Map.Entry<Integer, Node> entry: hm.entrySet()) {
	    		System.out.printf("K:%d,V:%d ",entry.getKey(),entry.getValue().val);
	    	}
    		System.out.printf("\n");
	    }
	}
    public int getBestRoad(int[][] grid) {//lintcode 1446,notes
        // Write your code here
     	int retVal = Integer.MAX_VALUE;
     	int START_IDX = 0, END_IDX = 1, rowLen = grid.length, colLen = grid[0].length;
     	
     	ArrayList<MyPair<Integer,Integer>> blockList = new ArrayList<MyPair<Integer,Integer>>();
     	for(int i = 0; i < rowLen; i++) {
     		for(int j = 0; j < colLen; j++) {
     			if(grid[i][j] == 1)
     				blockList.add(new MyPair<Integer,Integer>(i,j));
     		}
     	}
     	
     	ArrayList<MyPair<Integer,Integer>> movCordSet = new ArrayList<MyPair<Integer,Integer>>();
     	movCordSet.add(new MyPair<Integer,Integer>(0,1));
     	movCordSet.add(new MyPair<Integer,Integer>(0,-1));
     	movCordSet.add(new MyPair<Integer,Integer>(1,0));
     	movCordSet.add(new MyPair<Integer,Integer>(-1,0));
     	
     	int[][][] maps = new int[2][rowLen][colLen];
     	for(int [][] m1 : maps)
     		for(int[] m2 : m1)
     			Arrays.fill(m2, Integer.MAX_VALUE);
     	
     	MyPair<Integer,Integer> start = new MyPair<Integer,Integer>(0,0);
     	MyPair<Integer,Integer> end = new MyPair<Integer,Integer>(rowLen - 1,colLen - 1);
     	
     	for(int i = START_IDX; i <= END_IDX; i++) {
         	Queue<MyPair<Integer,Integer>> q = new LinkedList<MyPair<Integer,Integer>>(); 

     		if(i == START_IDX) {
     			maps[i][0][0] = 0;
     			q.add(start);
     		}
     		else {
     			maps[i][rowLen - 1][colLen - 1] = 0;
     			q.add(end);
     		}
     		
         	while(!q.isEmpty()) {
         		MyPair<Integer,Integer> cur = q.poll();
         		if(maps[i][0][0] == 1 || maps[i][rowLen - 1][colLen - 1] == 1)
                     return -1;
         		for(MyPair<Integer,Integer> p : movCordSet) {
         			int nextR = cur.getKey().intValue() + p.getKey().intValue();
         			int nextC = cur.getValue().intValue() + p.getValue().intValue();
         			if((nextR >= 0 && nextR < rowLen) && (nextC >= 0 && nextC < colLen)
         					&& grid[nextR][nextC] != 1) {
         				int curSteps = maps[i][cur.getKey().intValue()][cur.getValue().intValue()];
         				if((curSteps + 1) < maps[i][nextR][nextC]) {
         	    		    q.add(new MyPair<Integer,Integer>(nextR,nextC));
             			    maps[i][nextR][nextC] = curSteps + 1;
         				}
         			}
         		}
         	}
     	}
     	
     	for(MyPair<Integer,Integer> bNode : blockList) {
     		int bRow = bNode.getKey(), bCol = bNode.getValue();
     		/*Left v.s Right and */
     		if(bRow - 1 >= 0) {
     			int num1 = Integer.MAX_VALUE,num2 = Integer.MAX_VALUE;
     			if(bRow + 1 < rowLen && maps[START_IDX][bRow - 1][bCol] != Integer.MAX_VALUE
     					&& maps[END_IDX][bRow + 1][bCol] != Integer.MAX_VALUE) {
     				num1 = maps[START_IDX][bRow - 1][bCol] + maps[END_IDX][bRow + 1][bCol] + 2;
     			}
     			if(bCol + 1 < colLen && maps[START_IDX][bRow - 1][bCol] != Integer.MAX_VALUE
     					&& maps[END_IDX][bRow][bCol + 1] != Integer.MAX_VALUE) {
     				num2 = maps[START_IDX][bRow - 1][bCol] + maps[END_IDX][bRow][bCol + 1] + 2;
     			}
     			retVal = Math.min(retVal, Math.min(num1,num2));
     		}
     		if(bCol - 1 >= 0) {
     			int num1 = Integer.MAX_VALUE,num2 = Integer.MAX_VALUE;
     			if(bRow + 1 < rowLen && maps[START_IDX][bRow][bCol - 1] != Integer.MAX_VALUE
     					&& maps[END_IDX][bRow + 1][bCol] != Integer.MAX_VALUE) {
     				num1 = maps[START_IDX][bRow][bCol - 1] + maps[END_IDX][bRow + 1][bCol] + 2;
     			}
     			if(bCol + 1 < colLen && maps[START_IDX][bRow][bCol - 1] != Integer.MAX_VALUE
     					&& maps[END_IDX][bRow][bCol + 1] != Integer.MAX_VALUE) {
     				num2 = maps[START_IDX][bRow][bCol - 1] + maps[END_IDX][bRow][bCol + 1] + 2;
     			}
     			retVal = Math.min(retVal, Math.min(num1,num2));
     		}
     	}
     	return retVal == Integer.MAX_VALUE? -1 : retVal;
    }
	class UndirectedGraphNode {
		int label;
		List<UndirectedGraphNode> neighbors;
		UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
	};
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
        HashMap<UndirectedGraphNode,UndirectedGraphNode> hm = 
        		new HashMap<UndirectedGraphNode,UndirectedGraphNode>();
        if(node == null)
        	return null;
        
        q.add(node);      
        while(!q.isEmpty()) {
        	UndirectedGraphNode oriNode = q.poll();
        	if(hm.get(oriNode) != null) continue;
        	hm.put(oriNode,new UndirectedGraphNode(oriNode.label));
        	for(UndirectedGraphNode n : oriNode.neighbors) {
        		q.add(n);
        	}
        }
        for(Map.Entry<UndirectedGraphNode, UndirectedGraphNode> entry : hm.entrySet()) {
        	UndirectedGraphNode oriNode = entry.getKey();
        	UndirectedGraphNode newNode = entry.getValue();
        	for(UndirectedGraphNode n : oriNode.neighbors) {
            	UndirectedGraphNode nbNode = hm.get(n);
            	newNode.neighbors.add(nbNode);
        	}
        }       
        return hm.get(node);
    }
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        if(nums.length <= 0)
        	return -1;
        
        while(left < right) {
        	int mid = left + (right - left)/2 + 1;
        	
        	int adjLeft = (mid - 1) < 0 ? Integer.MIN_VALUE : nums[mid - 1];
        	int adjRight = (mid + 1) >= nums.length ? Integer.MIN_VALUE : nums[mid + 1];
        	if(nums[mid] > adjRight && nums[mid] > adjLeft)
        		return mid;
        	if(nums[mid] > nums[left]){
        		right = mid - 1;
        	}
        	if(nums[mid] < nums[right]) {
        		left = mid + 1;
        	}
        	if(right >= nums.length)
        		right = Integer.MIN_VALUE;
        	if(left <= -1)
        		left = Integer.MAX_VALUE;
        }
        if(left == right) {
        	int adjLeft = (left - 1) < 0 ? Integer.MIN_VALUE : nums[left - 1];
        	int adjRight = (left + 1) >= nums.length ? Integer.MIN_VALUE : nums[left + 1];
        	if(nums[left] > adjRight && nums[left] > adjLeft)
        		return left;
        }
        return -1;
    }
	public int minSubArrayLen(int s, int[] nums) {
	    int minLen = Integer.MAX_VALUE;
	    int sum = 0;
	    int left = 0, right = 0;
	    if(nums.length == 0 || s <= 0)
	    return 0;
	    while(right < nums.length) {
	    sum += nums[right];
	    while(sum >= s && left < right) {
	        minLen = Math.min(minLen, right - left + 1);
	        sum -= nums[left++];
	    }
	    right++;
	    }
	    if(minLen == Integer.MAX_VALUE)
	    return 0;

	    return minLen;
	}
	public boolean wordBreakDFS(String s, HashMap<String,Integer> hm, int curIdx, boolean[] dp) {
		boolean ret = false;
		if(curIdx == s.length()) {
			return true;
		}
			
		for(int i = 1; curIdx + i <= s.length(); i++) {
			String tmpS = s.substring(curIdx, curIdx + i);
			if(hm.get(tmpS) != null && !dp[curIdx]) {
				if(wordBreakDFS(s,hm,curIdx+i,dp))
					return true;
			}
		}
		dp[curIdx] = true;
		return false;
	}
    public boolean wordBreak(String s, List<String> wordDict) {
    	boolean[] dp = new boolean[s.length() + 1];
    	HashMap<String,Integer> hm = new HashMap<String,Integer>();
    	if(s.length() == 0 || wordDict.isEmpty())
    		return false;
    	for(String tmpS : wordDict) {
    		hm.put(tmpS, 1);
    	}
    	Arrays.fill(dp, false);
    	return wordBreakDFS(s, hm, 0, dp);    	
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
