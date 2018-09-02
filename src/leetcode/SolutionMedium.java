package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Node;

import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class SolutionMedium {
	boolean[] checked;

	SolutionMedium(){
		//generateParenthesis(1);
		//divide(Integer.MIN_VALUE,2);
		//restoreIpAddresses("25525511135");
		int[] a = {2,0,1};
		//sortColors(a);
		int[] a1 = {10,5,2,6};
		numSubarrayProductLessThanK(a1,100);
	}
    public int search(int[] nums, int target) {
    	int ret = -1;
    	if(nums.length <= 0)
    		return ret;
    	int left = 0, right = nums.length - 1;
    	while(left < right) {
    		int mid = left + (right - left)/2;
    		if(nums[mid] < nums[right]) {
    			right = mid; 
    		}
    		else {
    			left = mid + 1;
            }
        }
    	int sIdx = left;
    	if(target < nums[sIdx])
    		return ret;
    	
    	
    	int leftTarget = Misc.lowerbound(nums, 0, sIdx, target);
    	int rightTarget = Misc.lowerbound(nums, sIdx , nums.length, target);
    	
        //System.out.printf("%d %d \n", leftTarget, rightTarget);
        if(leftTarget != sIdx && nums[leftTarget] == target)
            return leftTarget;
        if(rightTarget != nums.length && nums[rightTarget] == target)
            return rightTarget;
    	return ret;  
    }
    public int[] searchRange(int[] nums, int target) {
    	int[] retArrs = {-1,-1}; 
        if(nums.length <= 0 || target < 0) {
        	return retArrs;
        }
        int left = 0, right = nums.length - 1;
        while(left < right) {
        	int mid = left + (right - left)/2 + 1;
        	if(nums[mid] == target) {
        		int[] ret = {mid -1, mid};
        		return ret;
        	}
        	else if(nums[mid] < target) {
        		left = mid + 1;
        	}
        	else {
        		right = mid - 1;
        	}
        }
        if(left == right) {
        	int[] ret = {left,left + 1};
        	return ret;
        }
    	return retArrs;
    }
	public void permuteDFS(int[] nums,List<Integer> l,List<List<Integer>> retList) {
		if(l.size() == nums.length) {
			retList.add(l);
		}
		for(int i = 0; i < nums.length; i++) {
			if(!checked[i]) {
				checked[i] = true;
				List<Integer> l1 = new LinkedList<Integer>();
				l1.add(nums[i]);
				permuteDFS(nums, l1, retList);
				checked[i] = false;
			}
		}
	}
    public List<List<Integer>> permute(int[] nums) {
    	List<List<Integer>> retList = new LinkedList<List<Integer>>();
    	List<Integer> l = new LinkedList<Integer>();
        if(nums.length == 0)
        	return retList;
        checked = new boolean[nums.length + 1];
        permuteDFS(nums, l, retList);
        return retList;
    }
    public int numSubarrayProductLessThanK(int[] nums, int k) {
    	int ret = 0, pd= 1, left = 0;
        if(k == 0)
        	return ret;
        for(int i = 0; i < nums.length; i++) {
        	
        	pd *= nums[i];
        	while(left < i && pd >= k) {
        		pd /= nums[left++];
        	}
        	ret += (i - left) + 1;  
        	System.out.printf("%d %d\n",pd,ret);
        }
        return ret;
    }
    public String findLongestWord(String s, List<String> d) {
        int dictLeft, dictRight, left,right;
        StringBuffer sb = new StringBuffer();
        if(d.size() <= 0 || s.length() == 0)
        	return sb.toString();
        
        for(String dict : d) {
        	left = 0;
        	right = s.length() - 1;
        	dictLeft = 0;
        	dictRight = dict.length() - 1;
        	while((left < right) && (dictLeft < dictRight)) {
        		if(dict.charAt(dictLeft) == s.charAt(left)) {
        			dictLeft++;
        		}
        		else
        			left++;
        		
        		if(dict.charAt(dictRight) == s.charAt(right)) {
        			dictRight--;
        		}
        		else
        			right--;
        	}
        	if(dictLeft == dictRight && dict.length() > sb.length()) {
        		if((sb.length() != 0) && (dict.length() == sb.length()) && (dict.compareTo(sb.toString()) > 0)) {
        			continue;
        		}
        		sb = new StringBuffer(dict);
        	}
        }
        return sb.toString();
    }
	
	int sortColorsSwap(int a, int b) {  // usage: y = swap(x, x=y);
		   return a;
	}
    public void sortColors(int[] nums) {
    	int leftIdx = 0, curIdx = 0, rightIdx = nums.length - 1;
    	if(nums.length <= 0)
    		return;
    	
    	while(curIdx <= rightIdx) {
    		if(nums[curIdx] == 0 && curIdx != leftIdx) {
    			nums[leftIdx] = sortColorsSwap(nums[curIdx], nums[curIdx] = nums[leftIdx]);
    			leftIdx ++;
    		}
    		else if(nums[curIdx] == 2 && curIdx != rightIdx) {
    			nums[rightIdx] = sortColorsSwap(nums[curIdx], nums[curIdx] = nums[rightIdx]);
    			rightIdx --;
    		}
    		else
    			curIdx++;
        	System.out.printf("%s (%d %d %d )\n",Arrays.toString(nums),leftIdx,curIdx,rightIdx);
    	}
    }
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head,slow = head;
        ListNode ret = null;
        boolean isHit = false;
        if(head == null || head.next == null || head.next.next == null)
        	return ret;
       
        while(fast.next != null && slow.next != null) {
        	slow = slow.next;
        	fast = fast.next.next;
            if(fast == null)
                return ret;
        	if(slow.equals(fast)) {
                fast = head;
                while(fast != null && slow != null) {

                    if(fast.equals(slow)) {
                        return fast;
                    }
                    fast = fast.next;
                    slow = slow.next;
                }
        	}
        }
        return ret;
    }
	public int isBlanacedDFS(TreeNode node, int depth) {
		int lHeight = 0, rHeight = 0;
		if(node == null)
			return depth - 1;
		
		if(node.left != null)
			lHeight = isBlanacedDFS(node.left, depth + 1);
		if(node.right != null)
			rHeight = isBlanacedDFS(node.right, depth + 1);
		
		return Math.abs(lHeight - rHeight) > 1? -1 : Math.max(lHeight,rHeight);
	}
    public boolean isBalanced(TreeNode root) {
    	int l = 0,r = 0;
    	
    	if(root == null || (root.left == null && root.right == null))
    		return true;

    	l = isBlanacedDFS(root.left,1);
    	r = isBlanacedDFS(root.right,1);
    	
    	return (Math.abs(l - r) > 1);
        
    }
	public void restoreIpAddressesDFS(String s, int rnd, int curIdx, StringBuilder curSb, List<String> l) {
		if(rnd < 4 && curIdx >= s.length())
			return;
		if(rnd == 4 && curIdx >= s.length()) {
			//System.out.printf("%s \n",curSb.delete(curSb.length() - 1, curSb.length()));
			curSb.delete(curSb.length() - 1, curSb.length());
			l.add(curSb.toString());
			return;
		}
		for(int i = 1; i <= 3; i++) {
			if(curIdx + i <= s.length()) {
				String tmp = s.substring(curIdx, curIdx + i);
				if(Integer.valueOf(tmp) >= 0 && Integer.valueOf(tmp) <= 255) {
                    if(tmp.length() > 1 && tmp.charAt(0) == '0')
                        continue;

                    StringBuilder tmpSb = new StringBuilder(curSb);
					tmpSb.append(tmp);
					tmpSb.append(".");
					restoreIpAddressesDFS(s,rnd + 1, curIdx + i, tmpSb, l);
				}
			}
		}
	}
    public List<String> restoreIpAddresses(String s) {
    	List<String> retList = new LinkedList<String>();
    	StringBuilder sb = new StringBuilder();
    	if(s.length() < 4 || s.length() > 12)
    		return retList;
    	restoreIpAddressesDFS(s,0,0,sb,retList);
    	return retList;
    }
	public int uniquePathsDFS(int m, int n, int x, int y,int[][] map) {
		int ret = 0;
		if(x == (m - 1) && y == (n - 1)) {
			return 1;
		}
		if(x >= m || y >= n)
			return 0;
        if(map[x][y] != 0)
            return map[x][y];
        
		ret += uniquePathsDFS(m,n,x + 1, y,map) + uniquePathsDFS(m,n,x, y + 1,map);
        map[x][y] = ret;
		return ret;
	}
    public int uniquePaths(int m, int n) {
        if(m == 0 && n == 0)
        	return 0;
        int[][] map = new int[m][n];
        return uniquePathsDFS(m,n,0,0,map);
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    	int rowLen = obstacleGrid.length, colLen = obstacleGrid[0].length;
        int[][] arrs = new int[rowLen + 1][colLen + 1];
        arrs[1][1] = 1;
        for(int i = 1; i < rowLen; i++) {
        	for(int j = 1; j < colLen; j++) {
        		if(obstacleGrid[i][j] == 1) {
        			arrs[i][j] = 0;
        		}
        		else {
            		arrs[i][j] = arrs[i - 1][j] + arrs[i][j - 1];
        		}
        	}
        }
        return arrs[rowLen][colLen];
    }
    public int kSimilarity(String A, String B) {
    	int retK = 0, diff = 0;
        if(A.length() * B.length() == 0)
        	return retK;
        if(A.length() != B.length())
        	return retK;
        
        char[] aChars = A.toCharArray(), bChars = B.toCharArray();
        Arrays.sort(aChars);
        Arrays.sort(bChars);
        if(!aChars.equals(bChars))
        	return retK;
        
        for(int i = 0; i < A.length(); i++) {
        	System.out.printf("%c %c \n",A.charAt(i),B.charAt(i));
        	if(A.charAt(i) != B.charAt(i))
        		diff++;
        }
        return diff/2; 
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val > p.val && root.val > q.val){
            return lowestCommonAncestor(root.left, p, q);
        }else if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right, p, q);
        }else{
            return root;
        }
    }
    public int divide(int dividend, int divisor) {
    	int OVERFLOW = Integer.MAX_VALUE;
    	int sign = 1;
    	int q = 0;
    	if(divisor == 0)
    		return OVERFLOW;
    	if((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0))
    		sign = -1;
    	long num = 0;
    	long div = Math.abs(divisor);
    	if(dividend == Integer.MIN_VALUE) {
    		num = Integer.MAX_VALUE;
    		num += 1;
    	}
        else
            num = new Long((Math.abs(dividend)));
    	    	
    	int cnt = 0;
    	while(num >= div) {
    		long temp = div << cnt;
    		if(temp > num) {
    			cnt = 0;
    			temp = div << cnt;
    		}
    		num -= temp;
    		q += (1 << cnt);
        	//System.out.printf("num:%d tmp:%d q:%d\n",num,temp,q);
    		cnt++;
    	}	
    	//System.out.printf("%d \n",q);
    	return sign * q; 
    }
	public void widthOfBinaryTreeDFS(TreeNode node, int depth,int cnt, HashMap<Integer,ArrayList<Integer>> hm) {
		if(node == null) {
			return;
		}
		ArrayList<Integer> l = hm.getOrDefault(depth + 1, new ArrayList<Integer>());
		if(node.left != null) {
			l.add(cnt * 2);
			hm.put(depth + 1, l);
			widthOfBinaryTreeDFS(node.left,depth + 1 , cnt * 2,hm);
		}
		if(node.right != null) {
			l.add(cnt * 2 + 1);
			hm.put(depth + 1, l);
			widthOfBinaryTreeDFS(node.right,depth + 1, cnt * 2 + 1,hm);
		}
	}
    public int widthOfBinaryTree(TreeNode root) {
    	int ret = 0, sum = 0;;
    	ArrayList<Integer> dList = new ArrayList<Integer>();
    	HashMap<Integer,ArrayList<Integer>> hm = new HashMap<Integer, ArrayList<Integer>>();
    	if(root == null)
    		return 0;
    	if(root.left == null && root.right == null)
    		return 1;
    	dList.add(1);
    	widthOfBinaryTreeDFS(root,0,1,hm);
    	hm.entrySet()
    	.stream()
    	.sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
    	.forEachOrdered(x -> hm.put(x.getKey(), x.getValue()));
    	
    	for(Map.Entry<Integer,ArrayList<Integer>> entry : hm.entrySet()) {
    		int depth = entry.getKey();
    		ArrayList<Integer> l = entry.getValue();
    		if(l.size() > 1) {
    			//System.out.printf("D:%d ret:%s \n",depth,l.toString());
                ret = Math.max(ret,l.get(l.size() - 1) - l.get(0) + 1);
    			
    		}
    	}
    	return ret;
    }
    public ListNode swapPairs(ListNode head) {
    	ListNode pNode, cNode;
    	if(head.next == null)
    		return head;
    	
    	pNode = head;
    	cNode = head.next;
    	if(head.next.next == null) {
    		cNode.next = pNode;
    		pNode = cNode;
    		return head;
    	}
    	while(cNode != null) {
    		pNode.next = cNode.next;
    		cNode.next = pNode;
    		if(pNode.next == null)
    			break;
    		if(pNode.next.next == null)
    			break;
    		pNode = pNode.next;
    		cNode = pNode.next.next;
    	}
    	return head;
    }
	public void permuteUniqueR(int[] nums, int acc, List<Integer> tmpList, Set<List<Integer>> hs) {
		if(acc == nums.length) {
			hs.add(tmpList);
			return;
		}
		for(int i = 0; i < nums.length; i++) {
			if(!checked[i]) {
				checked[i] = true;
				List<Integer> tmpList1 = new LinkedList<Integer>(tmpList);
				tmpList1.add(nums[i]);
				permuteUniqueR(nums,acc + 1, tmpList1, hs);
				checked[i] = false;
			}
		}
	}
    public List<List<Integer>> permuteUnique(int[] nums) {
    	Set<List<Integer>> retSet = new HashSet<List<Integer>>();
    	List<List<Integer>> retList = new LinkedList<List<Integer>>(retSet);

    	List<Integer> tmpList = new LinkedList<Integer>();
    	if(nums.length == 0)
    		return retList;
    	
    	checked = new boolean[nums.length];
    	permuteUniqueR(nums,0,tmpList,retSet);
    	retList = new LinkedList<List<Integer>>(retSet);
    	return retList;
    }
	public void generateParenthesisR(int n, int acc, int lcnt, int rcnt, StringBuilder sb, List<String> l) {
		char[] pArrs = {'(',')'};
		//System.out.printf("L:%d R:%d %s \n",lcnt,rcnt, sb.toString());
		if((lcnt + rcnt) == n * 2 && acc == 0) {
			l.add(sb.toString());
			return;
		}
		for(int i = 0; i < pArrs.length; i++) {
			StringBuilder sb1 = new StringBuilder(sb);
			if(i == 0 && acc <= n && lcnt <= n) {
				sb1.append(pArrs[i]);
				generateParenthesisR(n, acc + 1, lcnt + 1, rcnt, sb1, l);
			}
			else if(i == 1 && acc > 0 && rcnt <= n){
				sb1.append(pArrs[i]);
				generateParenthesisR(n, acc - 1, lcnt, rcnt + 1, sb1, l);
			}
		}
	}
    public List<String> generateParenthesis(int n) {
    	List<String> retList = new LinkedList<String>();
    	if(n == 0)
    		return retList;
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append('(');
    	generateParenthesisR(n, 1, 1, 0, sb, retList);
    	for(String s: retList)
    		System.out.printf("%s \n",s);
    	return retList; 
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
    	HashMap<Integer,ListNode> hm = new HashMap<Integer,ListNode>();
    	int sz = 0;
    	if(head == null || n < 0)
    		return head;
    	
    	ListNode tNode = head;
    	for(int i = 1; tNode != null; i++) {
    		hm.put(i, tNode);
    		tNode = tNode.next;
    		sz++;
    	}
    	if(n > sz)
    		return head;
    	
    	tNode = hm.get(sz - n + 1);
    	if(tNode == head) {
    		head = tNode.next;
    	}
    	else {
    		ListNode preNode = hm.get(sz - n);
    		if(preNode != null)
    			preNode.next = tNode.next;
    	}
    	return head;
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
    	Set<List<Integer>> retSet = new HashSet<List<Integer>>();
    	HashMap<Integer, Integer> hm = new HashMap<Integer,Integer>();
    	
    	Arrays.sort(nums);
    	for(int i = 0 ; i < nums.length; i++) {
    		for(int j = i + 1; j < nums.length; j++) {
    			for(int k = j + 1; k < nums.length; k++) {
    				int fourthNum = target - nums[i] - nums[j] - nums[k];
    				if(hm.containsKey(fourthNum)) {
    					List<Integer> l = new LinkedList<Integer>();
    					l.add(nums[i]);
    					l.add(nums[j]);
    					l.add(nums[k]);
    					l.add(fourthNum);
    					retSet.add(l);
    				}
    			}
    		}
    		hm.put(nums[i], 1);
    	}
    	List<List<Integer>> retList = new LinkedList<List<Integer>>(retSet);
    	return retList;
    }
	public void letterCombinationsDFS(String digits, StringBuilder sb, List<String> sList, HashMap<Integer,String> hm){
		if(sb.length() == digits.length()) {
			sList.add(sb.toString());
			return;
		}
		
		char curDigit = digits.charAt(sb.length());
		String s = hm.get((int)(curDigit - '0'));
		for(int i = 0; i < s.length(); i++) {
				StringBuilder sb1 = new StringBuilder(sb);
				sb1.append(s.charAt(i));
				letterCombinationsDFS(digits,sb1,sList,hm);
		}
	}
    public List<String> letterCombinations(String digits) {
    	HashMap<Integer,String> hm = new HashMap<Integer,String>();
    	StringBuilder sb1 = new StringBuilder();
    	List<String> sList = new LinkedList<String>();
		char c = 'a';
		if(digits.length() <= 0)
			return sList;
		
    	for(int i = 2; i <= 9 ; i++) {
    		StringBuilder sb = new StringBuilder();
    		int len = 3;
    		if(i == 7 || i == 9)
    			len = 4;
    		for(int j = 0; j < len; j++) {
    			sb.append(c);
    			c++;
    		}
    		hm.put(i,sb.toString());
    	}
    	letterCombinationsDFS(digits,sb1,sList,hm);
    	//for(String s : sList)
    	//	System.out.printf("%s ",s);
    	return sList;
    }
	public void combinationSum(int[] c, int target, int sum, List<Integer> l, Set<List<Integer>> hs) {
		if(sum > target)
			return;
		if(sum == target){
            Collections.sort(l);
			hs.add(l);
        }
		for(int i = 0; i < c.length; i++) {
			List<Integer> tmpList = new LinkedList<Integer>(l);
			tmpList.add(c[i]);
			combinationSum(c, target, sum + c[i], tmpList, hs);
		}
		
	}
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
    	Set<List<Integer>> hs = new HashSet<List<Integer>>();
    	List<List<Integer>> retList = new LinkedList<List<Integer>>(hs);
        if(candidates.length <= 0)
        	return retList;
        
        List<Integer> l = new LinkedList<Integer>();
        combinationSum(candidates, target,0,l,hs);
        
    	retList = new LinkedList<List<Integer>>(hs);
        return retList;
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
        	return 0;
        
        while(left < right) {
        	int mid = left + (right - left)/2 + 1;
        	
        	int adjLeft = (mid - 1) < 0 ? Integer.MIN_VALUE : nums[mid - 1];
        	int adjRight = (mid + 1) >= nums.length ? Integer.MIN_VALUE : nums[mid + 1];
        	if(nums[mid] > adjRight && nums[mid] > adjLeft)
        		return mid;
        	if(nums[mid] < adjLeft){
        		right = mid - 1;
        	}
        	else{
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
        return 0;
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
