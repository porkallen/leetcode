package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SolutionEasy {
	SolutionEasy(){
		/*
		ListNode temp;
		ListNode l1;
		l1 = temp = new ListNode(1);
		temp.next = new ListNode(2); 
		temp = temp.next;
		temp.next = new ListNode(4); 
		ListNode l2;
		l2 = temp = new ListNode(1);
		temp.next = new ListNode(3); 
		temp = temp.next;
		temp.next = new ListNode(4); 
		mergeTwoLists(l1,l2);
		*/
		int[] num = {1,2,2,3,3,3,4,4};
		//System.out.printf("%d \n",removeDuplicates(num));
		//System.out.printf("%d \n",removeElement(num,2));
		//System.out.printf("result:%d \n",romanToInt("MCDLXXVI"));
	}
	public boolean dfs(TreeNode cur, int curSum, int sum) {
		if(cur == null) {
			return false;
		}
		System.out.printf("val:%d cursum:%d \n",cur.val,curSum);
		if((curSum + cur.val) == sum && cur.left == null && cur.right == null) {
			return true;
		}
		return dfs(cur.left, curSum+cur.val, sum) | dfs(cur.right, curSum+cur.val, sum);
	}
	public void dfs2(TreeNode cur, int curSum, List<List<Integer>> ret, List<Integer> curSet, int sum) {
		List<Integer> tmpSet = new LinkedList<Integer>(curSet);
		if(cur == null) {
			return;
		}
		tmpSet.add(cur.val);
		if((curSum + cur.val) == sum && cur.left == null && cur.right == null) {
			ret.add(tmpSet);
			return;
		}
		dfs2(cur.left,curSum+cur.val,ret,tmpSet,sum);
		dfs2(cur.right, curSum+cur.val,ret,tmpSet,sum);
	}
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
    	List<List<Integer>> ret = new LinkedList<List<Integer>>();
    	List<Integer> curSet = new LinkedList<Integer>();
    	if(root == null)
    		return ret;
    	if(root.left == null && root.right == null) {
    		if(root.val == sum) {
    			curSet.add(root.val);
    			ret.add(curSet);
    		}
    		return ret;
    	}
    	dfs2(root,0,ret,curSet,sum);
    	return ret;
    }
    public boolean hasPathSum(TreeNode root, int sum) {
    	boolean ret = false;
    	if(root == null)
    		return false;
    	if(root.left == null && root.right == null) {
    		return (root.val == sum)? true : false;
    	}
    	return ret | dfs(root,0,sum);
    }
    public int searchInsert(int[] nums, int target) {//35
    	int idx = 0;
    	if(nums.length == 0)
    		return 0;
    	for(int i = 0; i < nums.length; i++) {
    		if(nums[i] < target)
    			idx = i + 1;
    		if(target == nums[i])
    			return i;
    	}
    	return idx;
        
    }
    public int romanToInt(String s) {//13
    	int ret = 0;
        for(int i = 0; i< s.length(); i++) {
        	char c = s.charAt(i);
        	System.out.printf("ret:%d c:%c\n",ret,c);
        	if((i+1) < s.length()) {
        		String tmp = (String)s.subSequence(i, i+2);
        		System.out.printf("(%d)tmp:%s\n",i,tmp);
        		switch(tmp) {
        		case "IV":
        			ret += 4;
            		i += 1;
            		continue;
        		case "IX":
        			ret += 9;
            		i += 1;
            		continue;
        		case "XL":
        			ret += 40;
            		i += 1;
            		continue;
        		case "XC":
        			ret += 90;
            		i += 1;
            		continue;
        		case "CD":
        			ret += 400;
            		i += 1;
            		continue;
        		case "CM":
        			ret += 900;
            		i += 1;
            		continue;
            	default:
            		break;
        		}
        	}
        	switch(c) {
        	case 'I':
        		ret += 1;
        		break;
        	case 'V':
        		ret += 5;
        		break;
        	case 'X':
        		ret += 10;
        		break;
        	case 'L':
        		ret += 50;
        		break;
        	case 'C':
        		ret += 100;
        		break;
        	case 'D':
        		ret += 500;
        		break;
        	case 'M':
        		ret += 1000;
        		break;
        	default:
        		break;
        	}
        }
        return ret;
    }
    public int removeElement(int[] nums, int val) {//#27
    	int idx = 0;
    	if(nums.length == 0)
    		return 0;
    	for(int i:nums) {
    		if(i != val) {
    			nums[idx++] = i;
    		}
    	}
        return idx;
    }
    public int removeDuplicates(int[] nums) {//#26
    	Map<Integer,Integer> m = new HashMap<Integer,Integer>();
    	int idx = 0;
    	if(nums.length == 0)
    		return 0;
    	for(int i:nums) {
    		if(m.put(i,i) == null) {
    			nums[idx++] = i;
    		}
    	}
    	for (int i = 0; i < m.size(); i++) {
    		System.out.printf("%d,",nums[i]);
    	}
    	System.out.printf("\n");
    	return m.size();
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode retList,traversal;
        retList = traversal = new ListNode(0);
        while(l1 != null || l2 != null) {
        	ListNode pin;
        	if(l1 == null && l2 != null) {
        		pin = l2;
        		l2 = l2.next;
        	}
        	else if(l2 == null && l1 != null) {
        		pin = l1;
        		l1 = l1.next;
        	}
        	else {
        		if(l1.val > l2.val) {
        			pin = l2;
        			l2 = l2.next;
        		}
        		else {
        			pin = l1;
        			l1 = l1.next;
        		}
        	}
        	traversal.next = pin;
        	traversal = traversal.next;
        	System.out.printf("val:%d\n", traversal.val);
        }
        return retList.next;
    }
    public boolean isPalindrome(int x) {
    	int p = 0;
    	int temp = x;
    	int pow = 0;
    	if(x < 0)
    		return false;
    	ArrayList<Integer> l = new ArrayList<Integer>();
        while(temp != 0) {
        	l.add(temp%10);
        	temp /=10;
        }
        pow = l.size() - 1;
        for(int i = 0 ; i < l.size(); i++) {
    		//System.out.printf("%d %d %d\n",p,l.get(i),(int)Math.pow(10,pow));
        	p += l.get(i) * (int)Math.pow(10,pow--);
        	if(p < 0)//overflow
        		return false;
        }
        return (p==x);
    }
}
