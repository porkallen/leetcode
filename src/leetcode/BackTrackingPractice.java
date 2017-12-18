package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BackTrackingPractice {
	public static final int MAX_NUM = 5;
	public static int[] result = new int[MAX_NUM];
	public static char[] strResult = new char[MAX_NUM];
	public static int[] checked;
	public int resultCnt = 0;
	
	BackTrackingPractice() {
		//numSelect(10);
		String str = "ABBA";
		int partialNum = 4;
		combBackTracking(0,0);
		//strSelect(str.toCharArray(),partialNum);
		//System.out.println("num of result:"+this.resultCnt);
	}
	public void readOneLine() {
		Scanner reader = new Scanner(System.in);
		reader.nextLine();
	}
	public void numSelect(int num) {
		checked = new int[num];
		numSelectBackTracking(num,0);
	}
	public void strSelect(char[] str, int partialNum) {
		
		checked = new int[str.length];
		strSelectBackTracking(str,str.length,0,partialNum);
	}
	public void numSelectBackTracking(int num,int count) {
		
		if(count == MAX_NUM) {
			System.out.println("result:"+Arrays.toString(result));
			return;
		}
		for(int i = 1 ; i < num; i++) {
			/*No repeated number in one set*/
			if(checked[i] == 0) {
				checked[i] = 1;
				result[count] = i;
				numSelectBackTracking(num,count+1);
				checked[i] = 0;
			}

		}
	}
	public void strSelectBackTracking(char[] str,int len,int count,int charNum) {
		
		if(count == charNum) {
			System.out.println("result: "+Arrays.toString(strResult));
			readOneLine();
			resultCnt++;
			return;
		}
		char last_one = '\0';
		for(int i = 0 ; i < len; i++) {
			/*No repeated number in one set*/
			//System.out.format("idx:%d cnt:%d char:%c last: %c \n",i,count,str[i],last_one);
			//readOneLine();
			if(checked[i] == 0) {
				if(last_one != str[i]) {
					checked[i] = 1;
					last_one = str[i];
					strResult[count] = str[i];
					strSelectBackTracking(str,len,count+1,charNum);
					checked[i] = 0;
				}
			}
		}
	}
	public void combBackTracking(int num, int sz) {
		
		if(num == 5) {
			System.out.println("result: "+Arrays.toString(result));
			//readOneLine();
			//resultCnt++;
			return;
		}

		result[sz] = num;
		combBackTracking(num+1,sz+1);
		combBackTracking(num+1,sz);
	}
}
