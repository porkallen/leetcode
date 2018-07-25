package leetcode;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.event.ListSelectionEvent;

public class Main {
	static void arrayFormat(String s) {
		String ret = s.replace("[", "{");
		ret = ret.replaceAll("]","}");
		System.out.printf("%s\n",ret);
	}
	public static void main(String[] args) {
		//SolutionDFS s = new SolutionDFS();
		Solution0723 s = new Solution0723();
		//Solution0630 s = new Solution0630();
		//arrayFormat("[[0,0],[0,1],[0,2],[0,7],[1,2],[1,3],[1,9],[2,3],[2,5],[2,7],[2,8],[3,2],[3,5],[3,7],[4,2],[4,3],[4,5],[4,7],[5,1],[5,4],[5,8],[5,9],[7,2],[7,5],[7,7],[7,8],[8,5],[8,8],[9,0],[9,1],[9,2],[9,8]]");
		//Solution0113 s = new Solution0113();
    }
}