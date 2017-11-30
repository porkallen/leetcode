package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BackTrackingPractice {
	public static final int MAX_NUM = 5;
	public static int[] result = new int[MAX_NUM];
	public static int[] checked;
	public static void numSelect(int num) {
		checked = new int[num];
		numSelectBackTracking(num,0);
	}
	public static void numSelectBackTracking(int num,int count) {
		
		if(count == MAX_NUM) {
			System.out.println("result:"+Arrays.toString(result));
			Scanner reader = new Scanner(System.in);
			reader.nextLine();
			return;
		}
		for(int i = 1 ; i < num; i++) {
			if(checked[i] == 0) {
				checked[i] = 1;
				result[count] = i;
				numSelectBackTracking(num,count+1);
				checked[i] = 0;
			}

		}
	}
}
