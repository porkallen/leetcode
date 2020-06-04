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
import java.util.Scanner;
import java.util.Set;

import javafx.util.Pair;

public class Solution0721 {
	Solution0721(){
		int[] piles = {3,6,7,11};
		minEatingSpeed(piles,8);
	}
    public int minEatingSpeed(int[] piles, int H) {
    	int retK = Integer.MAX_VALUE;
    	Arrays.sort(piles);
    	int maxK = piles[piles.length - 1];
    	int minK = 1;
        // Using Scanner for Getting Input from User
        //Scanner in = new Scanner(System.in);
        while(minK <= maxK) {
    		int curH = 0;
    		int curK = minK + (maxK-minK)/2;
    		//System.out.printf("min:%d max:%d cur:%d \n",minK,maxK,curK);
    		//String s = in.nextLine();
    		for(int i = 0; i < piles.length; i++) {
    			int r = (piles[i]%curK == 0)?0:1;
    			curH += piles[i]/curK + r;
    		}
    		if(curH <= H) {
        		//System.out.printf("retK:%d \n",curK);
    			retK = Math.min(retK, curK);
    			maxK = curK - 1;
    		}
    		else {
    			minK = curK + 1;
    		}
    	}
        return retK;
    }
    public int robotSim(int[] commands, int[][] obstacles) {
    	int ret = Integer.MIN_VALUE;
    	int cur_direction = 0; // 0: North 1:South 2: East 3: West
    	ArrayList<Pair<Integer,Integer>> obList = new ArrayList<Pair<Integer,Integer>>();
    	Pair<Integer,Integer> curLoc = new Pair<Integer,Integer>(0,0);
    	
    	for(int i = 0; i < obstacles.length; i++) {
    			Pair<Integer,Integer> p = new Pair<Integer,Integer>(obstacles[i][0],obstacles[i][1]);
    			obList.add(p);
    	}
    	for(int cmd : commands) {
    		if(cmd == -2) {
    			switch(cur_direction) {
    			case 0://North
    				cur_direction = 3;
    				break;
    			case 1://South
    				cur_direction = 2;
    				break;
    			case 2://East
    				cur_direction = 0;
	   				break;
    			case 3://West    				
    				cur_direction = 1;
    				break;
    			default:
    				break;
    			}
    			
    		}
    		else if(cmd == -1) {
    			switch(cur_direction) {
    			case 0://North
    				cur_direction = 2;
    				break;
    			case 1://South
    				cur_direction = 3;
    				break;
    			case 2://East
    				cur_direction = 1;
	   				break;
    			case 3://West    				
    				cur_direction = 0;
    				break;
    			default:
    				break;
    			}
    		}
    		else if(cmd >= 1 && cmd <= 9){
    			int curX = (int)curLoc.getKey();
    			int curY = (int)curLoc.getValue();
    			int cmd1 = cmd;
    			while(cmd1 > 0) {
    				Pair<Integer,Integer> p = null;
        			switch(cur_direction) {
        			case 0://North
        				curY ++;
        				p = new Pair<Integer,Integer>(curX,curY);
        				if(obList.contains(p)) {
        					curY --;
        				}
        				break;
        			case 1://South
        				curY --;
        				p = new Pair<Integer,Integer>(curX,curY);
        				if(obList.contains(p)) {
        					curY ++;
        				}
        				break;
        			case 2://East
        				curX ++;
        				p = new Pair<Integer,Integer>(curX,curY);
        				if(obList.contains(p)) {
        					curX --;
        				}
        				break;
        			case 3://West    				
        				curX --;
        				p = new Pair<Integer,Integer>(curX,curY);
        				if(obList.contains(p)) {
        					curX ++;
        				}
        				break;
        			default:
        				break;
        			}
        			p = new Pair<Integer,Integer>(-2,-77);
    				if(obList.contains(p)) {
    	    			int aX = (int)p.getKey();
    	    			int aY = (int)p.getValue();
    	    	    	System.out.printf("Cos:%d,%d\n",aX,aY);
    					break;
    				}
    				cmd1--;
    			}
    			ret = Math.max(ret, curX*curX + curY * curY);
    			curLoc = new Pair<Integer,Integer>(curX,curY);
    		}
    	}
    	return ret;
    }
	void preOrder(TreeNode node,ArrayList<Integer> l) {
        if(node == null)
            return;
		if(node.left == null && node.right == null) {
			l.add(node.val);
			return;
		}
		preOrder(node.left,l);
		preOrder(node.right,l);
	}
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
    	ArrayList<Integer> l1 = new ArrayList<Integer>();
    	ArrayList<Integer> l2 = new ArrayList<Integer>();
    	if(root1 == null && root2 == null)
    		return true;
    	if(root1 == null || root2 == null)
    		return false;
    	preOrder(root1,l1);
    	preOrder(root2,l2);
    	
    	System.out.printf("L1: %s \n",l1.toString());
    	System.out.printf("L2: %s \n",l2.toString());

    	return l1.equals(l2)?true:false;
    }
}
