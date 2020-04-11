package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution0728 {
	Solution0728(){
		int[] piles = {18,3,8,1,9,7,11,13,18,11,17,20,14,2,17,20,11,14,8,7};
		//int[] piles = {5,3,4,5};
		//System.out.printf("%s \n",stoneGame(piles));1000000000,40000,40000
		System.out.printf("%d \n",nthMagicalNumber(3,6,4));

	}
    public int getGcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return getGcd(b, a % b);
        }
    }
    public int nthMagicalNumber(int N, int A, int B) {
    	int ret = 0;
    	long l = 1,r = N;
		long curUpbound = 0;
		long curCnt = 0;
		int lcm = A * B/getGcd(A,B);
		boolean isLcmEq = false;
		if(N == 1)
			return Math.min(A, B);
		if(lcm == Math.max(A, B))
			isLcmEq = true;
    	while(l < r) {
    		long cur = l + (r - l)/2;
    		
    		if(isLcmEq == true) {
    			curUpbound = Math.min(A, B) * cur;
    			curCnt = cur;
    		}
    		else {
        		curUpbound = Math.max(A, B) * cur;
        		curCnt = cur + curUpbound/Math.min(A, B) - curUpbound/(lcm);
    		}
    		System.out.printf("l:%d r:%d idx:%d up:%d rem:%d cupb:%d cnt:%d \n",l,r,cur,
    				curUpbound/Math.min(A, B),curUpbound/lcm,curUpbound,curCnt);
    		if(curCnt < N) {
    			l = cur + 1;
    		}
    		else if(curCnt > N) {
    			r = cur - 1;
    		}
    		else
    			return (int) (curUpbound%((int)Math.pow(10, 9) + 7));	
    	}
    	if(curCnt > N) {
    	   	for(long i = curUpbound - 1; i >= curUpbound/2; i--) {
        		if(isLcmEq == true) {
        			if(i % Math.min(A, B) == 0)
        				curCnt = (int)(i/Math.min(A, B));
        		}
        		else {
        			if(i%A == 0 || i%B == 0)
        				curCnt = (int)(i/A + i/B - i/(lcm));
        		}
        		System.out.printf("i:%d Bound:%d cnt:%d \n",i,curUpbound,curCnt);
        		if(curCnt == N)
        			return (int)(i%((int)Math.pow(10, 9) + 7));
        	}
    	}
    	else {
    	   	for(long i = (curUpbound + 1); i <= curUpbound * 2 + 1; i++) {
        		if(isLcmEq == true) {
        			if(i % Math.min(A, B) == 0)
        				curCnt = (i/Math.min(A, B));
        		}
        		else {
        			if(i%A == 0 || i%B == 0)
        				curCnt = (i/A + i/B - i/(lcm));
        		}
        		System.out.printf("i:%d Bound:%d cnt:%d \n",i,curUpbound*2,curCnt);
        		if(curCnt == N)
        			return (int)(i%((int)Math.pow(10, 9) + 7));
        	}
    	}
 
        return ret;
    }
	public int stoneGameDFS(int[] Piles,int curLeft, int curRight,int alexDiff,int[][] memo) {
		if(curLeft >= curRight) {
			return alexDiff;
		}
		if(memo[curLeft][curRight] != -1)
			return memo[curLeft][curRight];
		
		memo[curLeft][curRight] = alexDiff;

		System.out.printf("left:%d right:%d alex:%d\n",curLeft,curRight,alexDiff);
		return Math.max(stoneGameDFS(Piles,curLeft + 1, curRight, alexDiff + Piles[curLeft], memo),
				stoneGameDFS(Piles,curLeft, curRight - 1, alexDiff + Piles[curRight], memo));
	}
    public boolean stoneGame(int[] piles) {
    	if(piles.length <= 2)
    		return true;
    	int[][] memo = new int[piles.length][piles.length];
    	for (int[] row : memo) {
    		Arrays.fill(row, -1);
    	}
    	return (stoneGameDFS(piles,0,piles.length-1,0,memo) > 0);
        
    }
    public ListNode middleNode(ListNode head) {
    	ListNode ret = null, tmp = null;
    	int len = 0;
    	if(head == null)
    		return ret;
    	if(head.next == null)
    		return head;
    	if(head.next.next == null)
    		return head.next;
    	if(head.next.next.next == null)
    		return head.next;
    	if(head.next.next.next.next == null)
    		return head.next.next;
    	tmp = head;
    	while(tmp != null) {
    		len++;
    		tmp = tmp.next;
    	}
    	ret = head;
    	System.out.printf("%d \n",len);
    	if(len/2 == 0)
    		len = len/2 + 1; 
    	else
    		len = len/2;
    	for(int i = 0; i <= len/2; i++)
    		ret = ret.next;
    	return ret;
    }
}
