package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SolutionString {
	SolutionString(){
		System.out.println("result: "+myAtoi("1234566770"));
	}
    public int myAtoi(String str) {
        char chrArr[] = str.toCharArray();
        int ret = 0;
        int digit = 1;
        for(int i = str.length()-1; i >= 0; i--) {
    			System.out.printf("result: %d \n",str.charAt(i) - '0');
        		ret += (str.charAt(i) - '0')*digit;
        		digit*=10;
        }
        return ret;
    }
    public String convert(String s, int numRows) {
        //6
    		char chrArr[] = s.toCharArray();
    		char retChrArr[] = new char[s.length()+1];
    		int retChrIdx = 0;
    		Arrays.fill(retChrArr, '\0');
    		List<List<Character>> tmpDict = new ArrayList<List<Character>>();
    		if(numRows == 0)
    			return "";
    		if(numRows == 1)
    			return s;
    		for(int i = 0 ; i < numRows; i++) {
				List<Character> tmpList = new ArrayList<Character>();
				tmpDict.add(tmpList);
    		}
    		for(int i = 0; i < s.length();) {
    			for(int j = 0; j < numRows && i < s.length(); j++) {
    				List<Character> tmpList = tmpDict.get(j);
    				if(tmpList == null) {
    					return "";
    				}
    				tmpList.add(chrArr[i++]);
    			}
    			for(int j = numRows - 2; j >= 1 && i < s.length(); j--) {
    				List<Character> tmpList = tmpDict.get(j);
    				if(tmpList == null) {
    					return "";
    				}
    				tmpList.add(chrArr[i++]);
    			}
    		}
    		for(int i = 0; i < numRows; i++) {
    			List<Character> tmpList = tmpDict.get(i);
    			for(Iterator<Character> l = tmpList.iterator();l.hasNext();) {
    				char c = l.next();
    				if(c != ' ') {
    					retChrArr[retChrIdx++] = c;
    					System.out.printf("idx:%d char:%c \n",i,c);
    				}
    				
    			}
    		}
    		return String.valueOf(retChrArr); 		
    }
}
