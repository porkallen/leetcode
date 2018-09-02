package leetcode;

public class Misc {
	Misc(){
		
	}
	public static int swap(int a, int b) {  // usage: y = swap(x, x=y);
		   return a;
	}
    public static void nextPermutation(int[] nums) {
        
    }
	public static int lowerbound(int[] nums,int leftBoud, int rightBound, int target) {
		int left = leftBoud, right = rightBound;
		while(left < right) {
			int m = left + (right -left)/2;
			if(target > nums[m])
				left = m + 1;
			else 
				right = m;
		}
		return left;
	}
	public static int upperbound(int[] nums, int leftBoud, int rightBound, int target) {
		int left = leftBoud, right = rightBound;
		while(left < right) {
			int m = left + (right -left)/2;
			if(target >= nums[m])
				left = m + 1;
			else 
				right = m;
		}
		return left;
	}
}
