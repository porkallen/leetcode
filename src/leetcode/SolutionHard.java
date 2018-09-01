package leetcode;

import java.util.*;

public class SolutionHard {
	SolutionHard(){
		int[] nums = {1,5,2,4,3};
		subsetWithTarget(nums,4);
		//subsets(nums);
	}
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Set<List<Integer>> retSet = new HashSet<List<Integer>>();
        List<List<Integer>> retList = new LinkedList<List<Integer>>();
    	if(nums.length <= 0)
    		return retList;

    	Arrays.sort(nums);
    	for(int i = 0; i < Math.pow(2,nums.length); i ++) {
    		List<Integer> l = new LinkedList<Integer>();
    		for(int j = 0; j < nums.length; j++) {
    			if((i & (1<<j)) > 0) {
    				l.add(nums[j]);
    			}
    		}
    		retSet.add(l);
    	}
    	retList = new LinkedList<List<Integer>>(retSet);
    	return retList;
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> retList = new LinkedList<List<Integer>>();
    	if(nums.length <= 0)
    		return retList;
    	
    	for(int i = 0; i < Math.pow(2,nums.length); i ++) {
    		List<Integer> l = new LinkedList<Integer>();
    		for(int j = 0; j < nums.length; j++) {
    			if((i & (1<<j)) > 0) {
    				l.add(nums[j]);
    			}
    		}
    		retList.add(l);
    	}
    	return retList;
    }
    public long subsetWithTarget(int[] nums, int target) {//818
        // Write you code here
    	long ret = 0;
    	if(nums.length == 0)
    		return ret;
    	
    	Arrays.sort(nums);
    	for(int i = 0; i < nums.length; i++) {
    		if(nums[i] < target) {
        		int right = nums.length - 1;
        		if(nums[i] + nums[i] < target)
        			ret += 1;
	    		while(right > i) {
	    			if(nums[i] + nums[right] < target) {
	    				System.out.printf("%d %d %d \n",right,i, (int)Math.pow(2, right - i) - 1);
	    				ret += Math.pow(2, right - i) - 1;
	    				break;
	    			}
	    			right--;
	    		}
				System.out.printf("ret %d \n",ret);
    		}
    	}
    	System.out.printf("%d \n",ret);
    	return ret;
    }
}
