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
	public static void main(String[] args) {
		Solution0831 s = new Solution0831();
		//int a = 0;
		//arrayFormat("[[36,77],[5,54],[5,42],[31,37],[10,36],[15,66],[58,68]]");
    }
}