package leetcode;

import java.util.*;

public class Main {
	public static void printf(Object... args) {
		  System.out.println(args[0]);
		  // ...
	}
	static void arrayFormat(String s) {
		String ret = s.replace("[", "{");
		ret = ret.replaceAll("]","}");
		System.out.printf("%s\n",ret);
	}
	public static void helper1(int[] nums, int idx, List<Integer> l) {
		if(idx == nums.length) {
			for(int i : l)
				System.out.printf("%d ",i);
			System.out.printf("\n");
			return;
		}
		for(int i = idx; i < nums.length; i++) {
			l.add(nums[i]);
			helper1(nums,i + 1,l);
			l.remove(l.size() - 1);
		}
	}
	public static void main(String[] args) {
		SolutionMedium s = new SolutionMedium();
    }
}