package leetcode;

import java.util.Arrays;
import java.util.List;


public class Solution1202 {
	public static int strDFS(int row,int col, int[][] arr,int rowLen,int colLen, int nowLen) {
		int sum = nowLen;
		System.out.printf("sum :%d \n",sum);
		if(row< rowLen && col< colLen) {
			if(arr[row][col] == 1) {
				sum++;
				System.out.printf("sum :%d \n",sum);
				sum = strDFS(row+1,col+1,arr,rowLen,colLen,sum);
			}
		}
		return sum;
	}
	public static int longestStringCompare(String s1,String s2) {
		int[][] idxArr = new int[s1.length()][s2.length()];
		int maxlen = 0;
		int tmplen = 0;
		/*O(n^3)*/
		for(int i = 0 ; i< s1.length(); i++) {
			for(int j = i+1 ; j < s1.length();j++) {
				if(s2.contains(s1.substring(i, j))) {
					if(maxlen < (j - i)) {
						maxlen = (j - i);
					}
				}
			}
		}
		//O(n^2)
		tmplen = maxlen = 0;
		for(int i = 0 ; i < s1.length();i++) {
			tmplen = strDFS(i,0,idxArr,s1.length(),s2.length(),0);
			if(maxlen < tmplen)
				maxlen = tmplen;
		}

		return maxlen;
	}
    public static int pivotIndex(int[] nums) {
    	
    	int sum = 0;
    	int tmpSum = 0;
    	//O(n)
    	for(int i = 0;i < nums.length; i++) {
    		sum += nums[i];
    	}
    	//O(n)
    	for(int i = 0;i < nums.length ; i++) {
    		tmpSum += nums[i];
    		if(tmpSum == (sum - tmpSum - nums[i+1])) {
    			return i+1;
    		}
    	}
    	return (-1);
    }
    public static ListNode[] splitListToParts(ListNode root, int k) {
    	int sum = 0;
    	int rest = 0;
    	int div = 0;
    	ListNode[] list = new ListNode[k];
    	ListNode visit = root;

    	for(sum = 0; visit != null; visit = visit.next ) {
    		sum++;
    		
    	}
    	visit = root;
    	for(int i = 0; i < k ; i++) {
    		int num = 0;
        	rest = sum%(k - i);
        	div = sum/(k - i);
        	if(rest > 0) {
        		num = div+1;
        	}
        	else {
        		num = div;
        	}
        	if((sum > 0 ) && (i == (k -1)))
        		num = sum;
        	sum -= num;
    		System.out.println(i+" num is "+num+" rest: "+rest+ " div: "+div + " sum: "+ sum);
    		/*First Element*/
    		ListNode visit2 = null;
    		if(visit != null) {
    			visit2 = new ListNode(visit.val);
    			visit = visit.next;
    			num --;
    		}
    		/*Start to insert next element*/
    		ListNode visit3 = visit2;
    		while(num > 0) {
    			if(visit != null) {
        			visit3.next = new ListNode(visit.val);
        			visit = visit.next;
        			visit3 = visit3.next;
        		}
    			num--;
    		}
    		list[i] = visit2;
    	}
    	return list;
    }
    private int findOp(String string){
    	char[] validOp = {'(',')'};
        int index = -1;
        for(int i = validOp.length-1; i >= 0; i--)
        {
            index = string.indexOf(validOp[i]);
            if(index >= 0)
             return index;
        }
        return index;
    }
    public String countOfAtoms(String formula) {
    	/*Not completed yet*/
    	int len = formula.length();
    	int head,tail,upperCaseIdx;
    	head = tail = upperCaseIdx = 0;
    	char[] charArr = formula.toCharArray();
    	String tmpStr = formula;
    	List<String> cList = Arrays.asList();
    	
    	tail = head + 1;
    	for(int i = 0; i< len; i++) {
			//case 1: number padding
			//case 2: lower case
			//case 3: nothing
			//cList.add(Character.toString(formula.charAt(i)));
    		if(Character.isUpperCase(tmpStr.charAt(i))) {
    			int j = i+1;
    			for(; j < len ; j++) {
        			if(Character.isDigit(tmpStr.charAt(j))) {
        				cList.add(Character.toString(formula.charAt(i)));
        				cList.add(Character.toString(formula.charAt(j)));
        				break;
        			}
        			if(Character.isLowerCase(tmpStr.charAt(j))) {
        				cList.add(formula.substring(i, j));
        				break;
        			}
        			if(tmpStr.charAt(j)== '(' || tmpStr.charAt(j)== ')') {
        				cList.add(Character.toString(formula.charAt(i)));
        				break;
        			}
    			}
    			i = j;
    		}
    		if(tmpStr.charAt(i)== '(') {
    			int j = i+1;
    			for(; j < len ; j++) {
    				if(Character.isUpperCase(tmpStr.charAt(j))) {
    					
    				}
    			}
    		}
    	}
    	return null;   
    }
}
