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

public class Solution0804 {
	Solution0804(){
		int[] people = {3,2,3,2,2};
		int limit = 6;
		//System.out.printf("ret:%d \n",numRescueBoats(people,limit));
		int[][] arrs = {{1,0},{0,2}};
		//System.out.printf("ret:%d \n",projectionArea(arrs));
		decodeAtIndex("leet2code",10);
	}
	public String decodeAtIndex(String S, int K) {
		int curLen = 0;
		HashMap<Character, ArrayList<Integer>> hm = new HashMap<Character, ArrayList<Integer>>();
        for(int i = 0; i < S.length(); i++) {
        	if(S.charAt(i) - '\0' >= 65) { //char
        		//System.out.printf("%c %d \n",S.charAt(i),S.charAt(i) - '\0');
        		ArrayList<Integer> tmpList;
        		if(hm.get(S.charAt(i)) != null) {
        			tmpList = hm.get(S.charAt(i));
        			tmpList.add(curLen);
        		}
        		else {
        			tmpList = new ArrayList<Integer>();
        			tmpList.add(curLen);
        		}
        		hm.put(S.charAt(i), tmpList);
        		curLen++;
        	}
        	else { //number
        		//System.out.printf("11%c %d \n",S.charAt(i),S.charAt(i) - '\0');
        		int rep = S.charAt(i) - '\0' - 48;
        		//System.out.printf("curret %s rep:%d \n",ret,rep);
        		for (Map.Entry<Character, ArrayList<Integer>> entry : hm.entrySet()) {
        			Character key = entry.getKey();
        			ArrayList<Integer> tmpList = entry.getValue();
        			ArrayList<Integer> tmpList1 = new ArrayList<Integer>(tmpList);
        			for(int j : tmpList) {
        				for(int k = 1; k < rep; k++) {
        					tmpList1.add(j + curLen * k);
        				}
        			}
        			hm.put(key, tmpList1);
        		}
        		curLen *= rep;
        	}
        }
		for (Map.Entry<Character, ArrayList<Integer>> entry : hm.entrySet()) {
			Character key = entry.getKey();
			ArrayList<Integer> tmpList = entry.getValue();
			for(int j : tmpList) {
				if(j == K - 1)
					return Character.toString(key);
			}
		}
		//System.out.printf("rep(%s) %s \n",ret,Character.toString(ret.charAt(K - 1)));
        return null;
	}
    public String decodeAtIndex1(String S, int K) {
    	String ret = "";
        for(int i = 0; i < S.length(); i++) {
        	if(S.charAt(i) - '\0' >= 65) { //char
        		//System.out.printf("%c %d \n",S.charAt(i),S.charAt(i) - '\0');
        		ret += S.charAt(i);
        	}
        	else { //number
        		//System.out.printf("11%c %d \n",S.charAt(i),S.charAt(i) - '\0');
        		int rep = S.charAt(i) - '\0' - 48;
        		//System.out.printf("curret %s rep:%d \n",ret,rep);
        		String copyStr = ret;
        		for(int j = 0; j < rep - 1; j++) {
        			ret += copyStr;
            		//System.out.printf("curret %s \n",ret);
        		}
        	}
        }
		System.out.printf("rep(%s) %s \n",ret,Character.toString(ret.charAt(K - 1)));
        return Character.toString(ret.charAt(K - 1));
    }
    public int projectionArea(int[][] grid) {
        int xArc = 0, yArc = 0, zArc = 0;
        for(int a[] : grid) {
        	for(int b : a) {
        		if(b != 0)
        			zArc++;
        	}
        }
        for(int i = 0 ; i < grid.length; i++) {
        	int maxX = 0;
        	for(int j = 0; j < grid[i].length; j++) {
        		maxX = Math.max(maxX, grid[i][j]);
        	}
        	xArc += maxX;
        }
        for(int j = 0 ; j < grid[0].length; j++) {
        	int maxY = 0;
        	for(int i = 0; i < grid.length; i++) {
        		maxY = Math.max(maxY, grid[i][j]);
        	}
        	yArc += maxY;
        }
        System.out.printf("%d %d %d \n",xArc,yArc,zArc);
        return xArc + yArc + zArc;
    }
    public int numRescueBoats(int[] people, int limit) {
    	int ret = 0;
    	int curWeight = 0;
    	HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
    	if(people.length == 0)
    		return 0;
    	if(people.length == 1 && people[0] > limit)
    		return 0;
    	if(people.length == 1 && people[0] < limit)
    		return 1;
        Arrays.sort(people);
        List<Integer> aList = Arrays.stream(people).boxed().collect(Collectors.toList());
        int lIdx = 0, rIdx = people.length - 1;
        
        while(lIdx < rIdx) {
        	if((people[lIdx] + people[rIdx]) <= limit) {
        		ret++;
        		people[lIdx ++] = 0;
        		people[rIdx --] = 0;
        	}
        	else if((people[lIdx] + people[rIdx]) > limit) {
        		rIdx --;
        	}
        }
        for(int i : people) {
        	if(i != 0)
        		ret++;
        }
        	
        return ret;
    }
	
}
