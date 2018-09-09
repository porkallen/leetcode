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
		//Solution0908 s = new Solution0908();
		Stack<int[]> s = new Stack<int[]>();
		int[] a = new int[2];
		a[0] = 1;
		a[1] = 2;
		s.push(a);
		a = new int[2];
		a[0] = 2;
		a[1] = 3;
		s.push(a);
		int[] b = s.pop();
		System.out.printf("%d %d \n",b[0],s.lastElement()[0]);
		b = s.pop();
		System.out.printf("%d \n",b[0]);

		
		
    }
}