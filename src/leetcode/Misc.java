package leetcode;

public class Misc {
	Misc(){
		
	}
	public int swap(int a, int b) {  // usage: y = swap(x, x=y);
		   return a;
	}
	public int lowbound(int[] nums, int target) {
		int left = 0, right = nums.length;
		while(left < right) {
			int m = left + (right -left)/2;
			if(target > nums[m])
				left = m + 1;
			else 
				right = m;
		}
		return nums[left];
	}
	public int upperbound(int[] nums, int target) {
		int left = 0, right = nums.length;
		while(left < right) {
			int m = left + (right -left)/2;
			if(target >= nums[m])
				left = m + 1;
			else 
				right = m;
		}
		return nums[left];
	}
}
