package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class SolutionArray {
	public int[] result;
	HashSet<List<Integer>> hs = new HashSet<List<Integer>>();
	SolutionArray(){
		int[] nums1 = {-1, 0, 1, 2, -1, -4};
		threeSum(nums1);
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
