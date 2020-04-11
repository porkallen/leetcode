package leetcode;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.stream.Collectors;

import leetcode.ContainerPractice.SortbyKey;

public class Solution0812 {
	Solution0812(){
		//{{1,2},{2,3},{3,4},{4,5},{1,5}}
		//{1,2},{2,3},{3,4},{4,5},{1,5}
		//{{1,2},{1,3},{2,4}}
		int[][] ret = {{4,7},{4,8},{5,6},
				 {1,6},{3,7},{2,5},{5,8},{1,2},
				 {4,9},{6,10},{8,10},{3,6},{2,10},{9,10},
				 {3,9},{2,3},{1,9},{4,6},{5,7},{3,8},{1,8},{1,7},{2,4}};
		System.out.printf("ret:%s \n",possibleBipartition(10,ret));
	}

    public boolean possibleBipartition(int N, int[][] dislikes) {
    	boolean ret = false;
    	int color = 1;
    	int[] colorMap = new int[N + 1];
    	Arrays.fill(colorMap, 0);
    	
    	if(N > 0 && dislikes.length == 0)
    		return true;
    	
    	for(int i = 1; i <= N; i++) {
    		if(colorMap[i] != 0)
    			continue;
    		Queue<MyPair<Integer,Integer>> q = new LinkedList<MyPair<Integer,Integer>>();
    		q.add(new MyPair<Integer,Integer>(i,color));
    		colorMap[i] = color;
    		while(!q.isEmpty()) {
    			MyPair<Integer,Integer> curNode = q.poll();
    			int curColor = curNode.value;
    			//System.out.printf("curNode:%d \n", curNode.key);
        		for(int j = 0; j < dislikes.length; j++) {
        			int node = 0;
        			if(dislikes[j][0] == curNode.key) {
        				node = dislikes[j][1];
        				dislikes[j][0] = 0;
        				dislikes[j][1] = 0;

        			}
        			else if(dislikes[j][1] == curNode.key) {
        				node = dislikes[j][0];
        				dislikes[j][0] = 0;
        				dislikes[j][1] = 0;
        			}
        			if(node != 0) {
            			//System.out.printf("(%d)key: %d, dislike:%d color:%d (%d)\n",i,curNode.key,node,colorMap[node],curColor);
        				if(colorMap[node] == 0) {
            				q.add(new MyPair<Integer,Integer>(node,-curColor));
            				colorMap[node] = -curColor;
        				}
        				else {
        					if(colorMap[node] == curColor) {
        						return ret;
        					}
        				}
        			}
        		}
        		color = -color;
    		}
    	}
    	return true;
    }
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
    	int curCnt = 0;
    	int[][] retArrs;// R:x C:y
        //Enter data using BufferReader
    	//Scanner in = new Scanner(System.in);
    	ArrayList<ArrayList<Integer>> retList = new ArrayList<ArrayList<Integer>>();
    	int[][] moveMap = {{0,1},{1,0}};
    	int curX = r0,curY = c0;
    	int round = 1;
    	int negative = 1;
    	
    	if(R == 0 || C == 0)
    		return (int[][])retList.toArray();
    	
    	retArrs = new int[R * C][2];// R:x C:y
    	retArrs[curCnt][0] = r0;
    	retArrs[curCnt][1] = c0;
    	curCnt++;
		
    	while(curCnt < R*C) {
    		for(int i = 0; i < moveMap.length; i++) {
    			for(int j = 0; j < round; j++) {
        			curX = curX + moveMap[i][0] * negative;
        			curY = curY + moveMap[i][1] * negative;
        			//System.out.printf("x:%d y:%d \n",curX,curY);
        			//String name = in.nextLine();
        			if(curX >= 0 && curX < R && curY >=0 && curY < C) {
            			//System.out.printf("Hit: x:%d y:%d \n",curX,curY);
        		    	retArrs[curCnt][0] = curX;
        		    	retArrs[curCnt][1] = curY;
        		    	curCnt++;		
        			}	
    			}
    		}
    		negative = -negative;
    		round++;
    	}
    	return retArrs;
    }
    public String[] uncommonFromSentences(String A, String B) {
    	ArrayList<String> retList = new ArrayList<String>();
    	if(A.length() == 0 && B.length() != 0) {
    		retList.add(B);
    		return retList.toArray(new String[retList.size()]);
    	}
    	if(B.length() == 0 && A.length() != 0) {
    		retList.add(A);
    		return retList.toArray(new String[retList.size()]);
    	}
    	String[] aArrs = A.split(" "), bArrs = B.split(" ");
    	Set<String> aSet = new HashSet<String>(), bSet = new HashSet<String>(), cSet= new HashSet<String>();
    	for(String a : aArrs) {
    		if(cSet.contains(a)) {
    			cSet.remove(a);
    		}
    		else {
    			if(!aSet.contains(a)) {
    				aSet.add(a);
    				cSet.add(a);
    			}
    		}
    	}
    	for(String b : bArrs) {
    		if(cSet.contains(b)) {
    			cSet.remove(b);
    		}
    		else {
    			if(!aSet.contains(b) && !bSet.contains(b)) {
            		bSet.add(b);
            		cSet.add(b);
    			}
    		}
    	}
    	return cSet.toArray(new String[cSet.size()]);
    }
}
