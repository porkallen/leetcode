package leetcode;
import java.lang.reflect.Array;
import java.util.*;

public class Solution0824 {
	Solution0824(){
		//int[] a = {1,4,1,2,2,1};
		//getAnswer(a);
		//int a = askingForTheLongest01Substring("11100111110010000001110001101000001101010111001101110000100010101010101011000111");
		//int a = askingForTheLongest01Substring("0101");
		//System.out.printf("%d \n",a);
		getAns(3,"AABACCDCD");
	}
    public int[] getAns(int k, String S) {
    	int[] ret = new int[S.length()];
    	HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
    	//StringBuilder sb = new StringBuilder();
    	int sbLen = 0;
    	int accSlashLen = 0;
    	int curIdx = 0;
        // Write your code here.
    	while(curIdx < S.length()) {
    		if(!hm.containsKey(S.charAt(curIdx))) {
    			//sb.append(S.charAt(curIdx));
    			sbLen++;
    			//hm.put(S.charAt(curIdx),sb.length() - 1);
    			hm.put(S.charAt(curIdx),sbLen - 1);

    			ret[curIdx] = 0;
    		}
    		else {
    			int preIdx = hm.get(S.charAt(curIdx));
    			int len = curIdx - preIdx + accSlashLen; 
    			//System.out.printf("%c preIdx:%d len:%d\n",S.charAt(curIdx),preIdx,len);
    			if(len < k) {
    				/*for(int i = len; i < k; i++) {
    					//sb.append("_");
    					sbLen++;
    					accSlashLen++;
    				}*/
    				sbLen += (k - len);
    				accSlashLen += (k - len);
    				ret[curIdx] = k - len;
    			}
    			else {
    				ret[curIdx] = 0;
    			}
    			//sb.append(S.charAt(curIdx));
    			sbLen++;
    			//System.out.printf("%s \n",sb.toString());
    			//hm.put(S.charAt(curIdx), sb.length() - 1);
    			hm.put(S.charAt(curIdx), sbLen - 1);
    		}
    		curIdx++;
    	}
		//System.out.printf("%s \n",sb.toString());
		//for(int i : ret)
		//System.out.printf("%s ",i);
		
    	return ret;
    }
    public int askingForTheLongest01Substring(String str) {
        // Write your code here
    	int ret = Integer.MIN_VALUE;
    	List<Integer> l = new LinkedList<Integer>();
    	for(int i = 0; i < str.length(); i++) {
    		if(Integer.parseInt(String.valueOf(str.charAt(i))) == 1) {
    			l.add(i);
    		}
    	}
    	for(int i : l) {
			int len = 0;
	    	//System.out.printf("i: %d \n",i);
	    	int tmp = 0;
    		if(i + 1 < str.length() && (str.charAt(i + 1) - '0' == 0)) {
    			if(i + 2 < str.length() && (str.charAt(i + 2) - '0' == 1)) {
        			++len;
        			if(i + 3 >= str.length() || (i + 3 < str.length() && (str.charAt(i + 3) - '0' == 0))) {
            			--len;
        			}
    			}

    			tmp = str.charAt(i + 1) - '0';
    			++len;
    			boolean isCons = true; 
        		for(int j = i; j >= 0; j--) {
        			int tmp1 = str.charAt(j) - '0';
        			if(tmp != tmp1) {
        				++len;
        				tmp = tmp1;
        			}
        			else {
        				isCons = false;
        				break;
        			}
        		}
    	    	System.out.printf("tmp:%d \n",tmp);
        		if(isCons) {
            		for(int j = str.length() - 1; j > i + 1; j --) {
            			int tmp1 = str.charAt(j) - '0';
            	    	//System.out.printf("j: %d tmp1:%d \n",j,tmp1);
            			if(tmp != tmp1) {
            				++len;
            				tmp = tmp1;
            			}
            			else {
            				isCons = false;
            				break;
            			}
            		}
        		}
    		}
	    	//System.out.printf("len: %d \n",len);
    		ret = Math.max(ret, len);
    	}
    	return ret != 0? ret: 1;
    }
    public int getAnswer(int[] a) {
        // Write your code here
    	int ret = 0;
    	int k = 4;
    	if(a.length <= 0)
    		return 0;
    	
    	Arrays.sort(a);
    	HashMap<Integer,List<Integer>> hm = new HashMap<Integer,List<Integer>>();
    	
    	for(int i = 0 ; i < a.length; i++) {
    		boolean isValid = false;
    		if((k - a[i]) == 0) {
    			ret ++;
    			continue;
    		}
    		else {
        		if(hm.get(k - a[i]) != null) {
        			List<Integer> l = hm.get(k - a[i]);
        			if(l.size() > 0) {
        				ret++;
        				l.remove(0);
        				isValid = true;
        			}
        		}
        		if(!isValid) {
        			List<Integer> l = hm.getOrDefault(a[i], new LinkedList<Integer>());
        			l.add(i);
        			hm.put(a[i],l);
        		}
    		}
    	}
    	//System.out.printf("%d \n",ret);
    	int finalSum = 0;
    	List<Integer> l = hm.get(3);
    	if(l != null && l.size() > 0)
    		ret += l.size();
    	
    	l = hm.get(2);
    	if(l != null && l.size() > 0)
    		finalSum += l.size() * 2;
    	l = hm.get(1);
    	if(l != null && l.size() > 0)
    		finalSum += l.size();
    	
    	int remain = (finalSum%4 > 0)? 1: 0;
    	//System.out.printf("%d %d \n",hm.get(2).size(),ret + (finalSum/4) + remain);

    	return ret + (finalSum/4) + remain;
    }
}
