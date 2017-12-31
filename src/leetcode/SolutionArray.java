package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.xml.stream.events.Characters;

class ListNode1 {
	int val;
	ListNode1 next;
	ListNode1(int x) { val = x; }
}

public class SolutionArray {
	public int[] result;
	HashSet<List<Integer>> hs = new HashSet<List<Integer>>();
	SolutionArray(){//abcabcbb
		int[] cost = {0,0,0,1};
		System.out.println("result: "+ minCostClimbingStairs(cost));
	}
    public int minCostClimbingStairs(int[] cost) {
    		int sumStep[] = new int[cost.length+1];
    		sumStep[0] = cost[0];
    		sumStep[1] = cost[1];
    		for(int i = 2; i < cost.length; i++) {
    			sumStep[i] += Math.min(sumStep[i-1]+cost[i], sumStep[i-2]+cost[i]);
    			System.out.printf("i:%d /%d\n",i,sumStep[i]);
    		}
    		return sumStep[cost.length-1]<=sumStep[cost.length-2]?sumStep[cost.length-1]:sumStep[cost.length-2];
    }
    public int lengthOfLongestSubstring(String s) {
    		if(s.length() == 0)
    			return 0;
    		Map<Character, Integer> hMap = new HashMap<Character, Integer>();
    		int j = 0,max = 0;
    		for(int i = 0; i < s.length(); i++) {
    			if(hMap.containsKey(s.charAt(i))) {
    				j = Math.max(j, hMap.get(s.charAt(i))+1);
    				System.out.printf("char:%c idx:%d j:%d max:%d\n",s.charAt(i),i,j,max);
    			}
    			max = Math.max(max, i-j+1);
    			hMap.put(s.charAt(i), i);
    		}
    		return max;
    		
    }
    public String longestPalindrome(String s) {
    		//#5
    		int headIdx,tailIdx;
    		char strArr[] = s.toCharArray();
    		String retStr = "";
    		if(s.length() == 1)
    			return s;
    		for(int i = 0; i < s.length(); i++) {
    			headIdx = tailIdx = i;
    			while(headIdx > 0) {
    				if(strArr[headIdx-1] == s.charAt(i)) {
    					headIdx --;
    				}
    				else {
    					break;
    				}
    			}
    			while(tailIdx < s.length()-1) {
    				if(strArr[tailIdx+1] == s.charAt(i)) {
    					tailIdx ++;
    				}
    				else {
    					break;
    				}
    			}
    			while(headIdx > 0 && tailIdx < s.length()-1) {
    				if(strArr[headIdx-1] == strArr[tailIdx+1]) {
    					headIdx --;
    					tailIdx ++;
        				//System.out.printf("(%d)char:%c h:%d L:%d\n",i,s.charAt(i),headIdx,tailIdx);
    				}
    				else {
    					break;
    				}
    			}
    			/*the tailidx of substring should add 1*/
    			if(Math.abs(headIdx - tailIdx)+1 > retStr.length()) {
    				if(headIdx < 0)
    					headIdx = 0;
    				if(tailIdx >= s.length())
    					tailIdx = s.length()-1;
    				retStr = s.substring(headIdx, tailIdx+1);
    				//System.out.printf("h:%d L:%d xret:%s \n",headIdx,tailIdx,retStr);
    			}
    		}
    		if(retStr.length() == 0) {
    			retStr = Character.toString(s.charAt(0));
    		}
        return retStr;
    }
	public ListNode1 addTwoNumbers(ListNode1 l1, ListNode1 l2) {
		//#2
		List<Integer> list1 = new ArrayList();
		List<Integer> list2 = new ArrayList();
		int idx1,idx2,len1,len2,val1,val2,carry,digit;
		ListNode1 tmpNode,headNode;
		tmpNode = l1;
		while(tmpNode != null) {
			list1.add(tmpNode.val);
			tmpNode = tmpNode.next;
		}
		tmpNode = l2;
		while(tmpNode != null) {
			list2.add(tmpNode.val);
			tmpNode = tmpNode.next;
		}
		len1 = list1.size() - 1;
		len2 = list2.size() - 1;
		if(len1 < 0 && len2 < 0)
			return null;
		idx1 = idx2 = digit = carry =0;
		if(len1 < 0) {
			val1 = 0;
		}
		else {
			val1 = list1.get(idx1);
		}
		if(len1 < 0) {
			val2 = 0;
		}
		else {
			val2 = list2.get(idx2);
		}
		digit = (val1 + val2 + carry)%10;
		carry = (val1 + val2 + carry)/10;
		tmpNode = headNode = new ListNode1(digit);
		idx1++;
		idx2++;
		len1--;
		len2--;
		while(len1 >= 0 || len2 >= 0) {
			if(len1 < 0) {
				val1 = 0;
			}
			else {
				val1 = list1.get(idx1);
			}
			if(len2 < 0) {
				val2 = 0;
			}
			else {
				val2 = list2.get(idx2);
			}
			digit = (val1 + val2 + carry)%10;
			carry = (val1 + val2 + carry)/10;
			tmpNode.next = new ListNode1(digit);
			tmpNode = tmpNode.next;
			idx1++;
			idx2++;
			len1--;
			len2--;
		}
		if(carry != 0) {
			tmpNode.next = new ListNode1(carry);
		}
		return headNode;
	}
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Double> totalNums = new ArrayList<Double>();
        for(int i = 0 ; i < nums1.length; i++) {
        	totalNums.add((double)nums1[i]);
        }
        for(int i = 0 ; i < nums2.length; i++) {
        	totalNums.add((double)nums2[i]);
        }
        Collections.sort(totalNums);
        int quo = totalNums.size()/2;
        int rem = totalNums.size()%2;
        if(rem == 1) {
        	return totalNums.get(quo);
        }
        else {
        	return (totalNums.get(quo) + totalNums.get(quo-1)) / 2;
        }
    }
    public void threeSumBackTracking(int[] nums,int idx,int sum, int cnt) {
    	if(cnt == 3) {
    		if(sum == 0) {
    			List<Integer> list = new ArrayList<Integer>();
    			for(int i = 0 ; i < nums.length; i++) {
    				if(result[i] == 1) {
    	    			System.out.printf("idx:%d num:%d \n",i,nums[i]);
    					list.add(nums[i]);
    				}
    			}
    			hs.add(list);
    			
    			System.out.println("===============");
    			
    		}
    		return;
    	}
    	for(int i = idx; i < nums.length; i++) {
    		if(result[i] == 0) {
    			result[i] = 1;
    			sum += nums[i];
    			threeSumBackTracking(nums,i+1,sum,cnt+1);
    			sum -= nums[i];
            	result[i] = 0;
    		}
    	}
    	
    }
    public List<List<Integer>> threeSum(int[] nums) {
    	result = new int[nums.length];
    	Arrays.sort(nums);
    	threeSumBackTracking(nums,0,0,0);
    	List<List<Integer>> retList = new ArrayList(hs);
    	return retList;
    }
}
