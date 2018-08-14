package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Main {
	static void arrayFormat(String s) {
		String ret = s.replace("[", "{");
		ret = ret.replaceAll("]","}");
		System.out.printf("%s\n",ret);
	}
	public static void main(String[] args) {
		Solution0727 s = new Solution0727();
		//arrayFormat("[\"w\",\"wo\",\"wor\",\"worl\",\"world\"]");
    }
}