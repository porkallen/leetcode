package leetcode;

import java.util.ArrayList;

public class SolutionEasy {
	SolutionEasy(){
		ListNode temp;
		ListNode l1;
		l1 = temp = new ListNode(1);
		temp.next = new ListNode(2); 
		temp = temp.next;
		temp.next = new ListNode(4); 
		ListNode l2;
		l2 = temp = new ListNode(1);
		temp.next = new ListNode(3); 
		temp = temp.next;
		temp.next = new ListNode(4); 
		mergeTwoLists(l1,l2);
	}
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode retList,traversal;
        retList = traversal = new ListNode(0);
        while(l1 != null || l2 != null) {
        	ListNode pin;
        	if(l1 == null && l2 != null) {
        		pin = l2;
        		l2 = l2.next;
        	}
        	else if(l2 == null && l1 != null) {
        		pin = l1;
        		l1 = l1.next;
        	}
        	else {
        		if(l1.val > l2.val) {
        			pin = l2;
        			l2 = l2.next;
        		}
        		else {
        			pin = l1;
        			l1 = l1.next;
        		}
        	}
        	traversal.next = pin;
        	traversal = traversal.next;
        	System.out.printf("val:%d\n", traversal.val);
        }
        return retList.next;
    }
	public int romanToInt(String s) {
		for(char c: s.toCharArray())
			System.out.printf("%c ", c);
		return 0;
	}
    public boolean isPalindrome(int x) {
    	int p = 0;
    	int temp = x;
    	int pow = 0;
    	if(x < 0)
    		return false;
    	ArrayList<Integer> l = new ArrayList<Integer>();
        while(temp != 0) {
        	l.add(temp%10);
        	temp /=10;
        }
        pow = l.size() - 1;
        for(int i = 0 ; i < l.size(); i++) {
    		//System.out.printf("%d %d %d\n",p,l.get(i),(int)Math.pow(10,pow));
        	p += l.get(i) * (int)Math.pow(10,pow--);
        	if(p < 0)//overflow
        		return false;
        }
        return (p==x);
    }
}
