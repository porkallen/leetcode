package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SolutionString {
	SolutionString(){
		String s = "25525511135";
		restoreIpAddresses(s);
	}
	
	public void restoreIpAddressesBackTracking(String src,String out, List<String> retStrList,int cnt, int idx){
		if(cnt == 4 && idx == src.length()) {
			System.out.printf("result:%s\n",out);
			retStrList.add(out);
		}
		if(cnt > 4 )
			return;
		for(int i = 1; i< 4; i++) {
			if (idx + i > src.length()) break;
			String s = src.substring(idx, idx+i);
			//System.out.printf("s:%s (%d)\n",s,cnt);
	        if ((s.startsWith("0") && s.length()>1) || (i==3 && Integer.parseInt(s) >= 256)) continue;
			restoreIpAddressesBackTracking(src,out+s+(cnt==3?"":'.'),retStrList,cnt+1,idx+i);
		}
		
	}
    public List<String> restoreIpAddresses(String s) {//93
    	List<String> retStrList = new ArrayList<String>();
    	if(s == null)
    		return retStrList;
    	restoreIpAddressesBackTracking(s,"",retStrList,0,0);
    	return retStrList;
    	
        
    }
    public String addBinary(String a, String b) {//67
    	if(a == null)
    		return b;
    	if(b == null)
    		return a;
    	if(a == null && b == null)
    		return null;
    	StringBuilder s = new StringBuilder();
    	int i = a.length() - 1;
    	int j = b.length() - 1;
    	int sum = 0;
    	while(i >= 0 || j >= 0){
    		if(i >= 0 ) sum += a.charAt(i--) - '0';
    		if(j >= 0 ) sum += b.charAt(j--) - '0';
    		System.out.printf("num of sum:%d \n",sum);
    		s.insert(0,Integer.toString(sum%2));
    		System.out.printf("-->%s \n",s);

    		sum = sum/2;
    	}
    	if(sum != 0)
    		s.insert(0,"1");
    	return s.toString();
    }
	public int lengthOfLastWord(String s) { // 58
		if(s == null)
			return 0;
		if(s.length() == 0)
			return 0;
		String tmp = s;
		String retStrArr[] = tmp.split(" ");
		if(retStrArr.length == 0)
			return 0;
		return retStrArr[retStrArr.length-1].length();
	}
    public String longestCommonPrefix(String[] strs) {//14
    		if(strs.length == 0 || strs == null)
    			return "";
    		String prefixStr = strs[0];
    		int prefixLen = prefixStr.length();
    		for(int i = 1; i < strs.length; i++) {
    			while(strs[i].indexOf(prefixStr) != 0) {
    				prefixStr = prefixStr.substring(0, --prefixLen);
    			}
    		}
    		if(prefixStr.length() == 0)
    			return "";
        return prefixStr;
    }
    public int myAtoi(String str) {//8
        long ret = 0;//key point
        int idx = 0;
        int sign = 1;
        int err = -1;
        
        if(str.length() == 0)
        		return 0;
        for(int i = 0; i < str.length(); i++) {
        		if(str.charAt(idx) == ' ')
        			idx++;
        }
        if(err > 0)
        		return 0;
        if(str.charAt(idx) =='+') {
        		sign = 1;
        		idx++;
        }
        else if(str.charAt(idx) =='-') {
        		sign = -1;
        		idx++;
        }
        
        for(int i = idx; i < str.length(); i++) {
        		int digit = str.charAt(i) - '0';
    			if(digit < 10 && digit >=0) {
    				ret = ret*10+digit;
        			System.out.printf("digit: %d ret:%d(%s) %d %d\n",digit,ret,Long.toBinaryString(ret),Integer.MAX_VALUE,Integer.MIN_VALUE);
        			if (sign == -1 && (-1) * ret < Integer.MIN_VALUE)
        				return Integer.MIN_VALUE;
        			if (sign == 1 && ret > Integer.MAX_VALUE)
        				return Integer.MAX_VALUE;
    			}
    			else {
    				return (int)ret*sign;
    			}
        }
        return (int)ret*sign;
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
