package leetcode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution0731 {
	Solution0731(){
		System.out.printf("%s \n",nextTime("23:59"));
	}
    public static boolean isInteger(String input){  
        Matcher mer = Pattern.compile("^[+-]?[0-9]+$").matcher(input);  
        return mer.find();  
    }
    public int[][] flippedByte(int[][] Byte) {
    	if(Byte.length == 0)
    		return Byte;
    	
    	for(int i = 0 ; i < Byte.length; i++) {
    		for(int j = 0, k = Byte[i].length - 1 ; j < Byte[i].length && k >= 0; j++,k--) {
    			if(j >= k)
    				break;
    			//int temp = ((Byte[i][j] >= 1)?1:0);
    			int temp;
    			if(Byte[i][j] >= 1)
    				temp = 1;
    			else
    				temp = Byte[i][j];
    			//Byte[i][j] = ((Byte[i][k] >= 1)?1:0);
    			if(Byte[i][k] >= 1)
    				Byte[i][j] = 1;
    			else
    				Byte[i][j] = Byte[i][k];
    			Byte[i][k] = temp;
        		//System.out.printf("%d row, j:%d k:%d head:%d tail:%d\n",i,j,k,Byte[i][j],Byte[i][k]);
    		}
    		//System.out.printf("%s \n",Arrays.toString(Byte[i]));
    		for(int j = 0; j < Byte[i].length; j++) {
    			Byte[i][j] ^= 1;
    		}
    	}
    	return Byte;
    }
    public String nextTime(String time) {
    	String s = "-1";
    	int num = 0;
		if(time.length() == 0)
			return s;
		if(time.length() != 5)
			return s;
		String[] timeDiv = time.split(":");
    	for(int i = 0; i < 2; i++) {
    		if(i == 0) {
    			if(isInteger(timeDiv[i]) == false || Integer.parseInt(timeDiv[i]) > 23) {
    				return s;
    			}
    			else {
    				num +=  Integer.parseInt(timeDiv[i])*100;
    			}
    		}
    		else {
    			if(isInteger(timeDiv[i]) == false || Integer.parseInt(timeDiv[i]) > 59) {
    				return s;
    			}
    			else {
    				num += Integer.parseInt(timeDiv[i]);
    			}
    		}
    	}
    	for(int i = (num + 1)%2360; i != num; i = (i + 1)%2360) {
    		String tmp = Integer.toString(i);
    		boolean isdiff = true;
    		if(tmp.length() < 3)
    			continue;
    		for(int j = 0; j < tmp.length(); j++) {
    			if(tmp.length() == 3 && tmp.charAt(j) == '0') {
    				isdiff = false;
    				break;
    			}
    			for(int k = j + 1; k < tmp.length(); k++) {
    				if(tmp.charAt(j) == tmp.charAt(k)) {
    					isdiff = false;
    					break;
    				}
    			}
    		}
    		if(isdiff) {
    			System.out.printf("%s\n",tmp);		
    			Integer tmpHour = 0;
    			Integer tmpMin = 0;
    			if(Integer.parseInt(tmp) < 9) {
    				tmpHour = 0;
    				tmpMin = Integer.parseInt(tmp.substring(0, 1));
    			}
    			else if(Integer.parseInt(tmp) < 99) {
    				tmpHour = 0;
    				tmpMin = Integer.parseInt(tmp.substring(0, 2));
    			}
    			else if(Integer.parseInt(tmp) < 99) {
    				tmpHour = 0;
    				tmpMin = Integer.parseInt(tmp.substring(0, 2));
    			}
    			else if(Integer.parseInt(tmp) < 999) {
    				tmpHour = Integer.parseInt(tmp.substring(0, 1));
    				tmpMin = Integer.parseInt(tmp.substring(1, 3));
    			}
    			else {
    				tmpHour = Integer.parseInt(tmp.substring(0, 2));
    				tmpMin = Integer.parseInt(tmp.substring(2, 4));
    			}
    			if(tmpHour > 23 || tmpMin > 59)
    				continue;
    	    	String ret1 = (tmpHour < 10)?"0"+tmpHour.toString():tmpHour.toString();
    	    	String ret2 = (tmpMin < 10)?"0"+tmpMin.toString():tmpMin.toString();
    			return ret1+":"+ret2;    	
    		}
    	}
    	return s;
    }
    public String lastTime(String time) {
    	String s = "-1";
    	Integer curHour = 0, curMin = 0;
		Integer retHour = 0,retMin = 0;
		if(time.length() == 0)
			return s;
		if(time.length() != 5)
			return s;
		
    	String[] timeDiv = time.split(":");
    	for(int i = 0; i < 2; i++) {
    		if(i == 0) {
    			if(isInteger(timeDiv[i]) == false || Integer.parseInt(timeDiv[i]) > 23) {
    				return s;
    			}
    		}
    		else {
    			if(isInteger(timeDiv[i]) == false || Integer.parseInt(timeDiv[i]) > 59) {
    				return s;
    			}
    		}
    	}
    	for(int i = 0; i < 2; i++) {
    		System.out.printf("%s curHour:%d\n",timeDiv[i],retHour);
    		if(i == 0) {
    			if(timeDiv[i].equals("00"))
    				curHour = 24;
    			else
        			curHour = Integer.parseInt(timeDiv[i]);
    		}
    		else{
    			if(timeDiv[i].equals("00")) {
    				curMin = 60;  
    				retHour = curHour - 1;
    			}
    			else {
    				curMin = Integer.parseInt(timeDiv[i]);
    				retHour = curHour;
    			}	  
        		retMin = curMin - 1;
    		}
    	}
		//System.out.printf("curHour:%d\n",retHour);
    	String ret1 = (retHour < 10)?"0"+retHour.toString():retHour.toString();
    	String ret2 = (retMin < 10)?"0"+retHour.toString():retMin.toString();
		return ret1+":"+ret2;    	
    }
    boolean checked[];
    public boolean cardGameDFS(int[] cost, int[] damage, int curMoney, int totalDamage, int curDamage, HashMap<Integer,Integer> hm) {
    	boolean ret = false;
    	if(curMoney < 0)
    		return false;
    	if(curDamage >= totalDamage)
    		return true;
    	if(hm.get(curMoney) != null)
    		return false;
    	hm.put(curMoney,curDamage);
    	for(int i = 0; i < cost.length; i++) {
    		if(checked[i] == false) {
    		    checked[i] = true;
    			ret |= cardGameDFS(cost, damage, curMoney - cost[i], totalDamage, curDamage + damage[i], hm);
    			checked[i] = false;
    		}
    	}
    	return ret;
    }
    public boolean cardGame(int[] cost, int[] damage, int totalMoney, int totalDamage) {
    	checked = new boolean[cost.length];
    	HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
    	if(cost.length == 0 || damage.length == 0)
    		return false;
    	if(cost.length != damage.length)
    		return false;
    	Arrays.fill(checked,false);
    	return cardGameDFS(cost,damage,totalMoney,totalDamage,0,hm);
    }
    public boolean canConvert(String s, String t) {
    	if(s == null && t == null)
    		return true;
    	if(s == null || t == null)
    		return false;
        if(s.length() < t.length())
        	return false;
        if(s.contains(t))
        	return true;
        int[] map = new int[128];
        
        for(char c : t.toCharArray())
        	map[c - '\0'] ++;
        
        for(char cc : s.toCharArray())
        	map[cc - '\0'] --;
        
        for(int i = 0 ; i < map.length; i++)
        	if(map[i] > 0)
        		return false;
        
        return true;
    }
}
