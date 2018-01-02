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
	public static void main(String[] args) {
    		//SolutionArray s = new SolutionArray();
		Graph g = new Graph(5);
		g.add(0,1);
		g.add(0,4);
		g.add(1,0);
		g.add(2,1);
		g.add(3,2);
		g.add(3,4);
		g.add(4,3);
		g.bfs(0);
		g.dfs(0);

    }
}