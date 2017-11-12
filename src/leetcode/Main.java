package leetcode;

public class Main {
    public static void main(String[] args) {
    	int[] nums = {0,1,2,3,4};
    	
    	//int[] nums = {4,5,6,3,6};
    	
    	int k = 3;
    	ListNode root = new ListNode(0);
    	ListNode traverse = root;
    	
    	for(int i = 1 ; i < 5 ; i++) {
    		traverse.next = new ListNode(i);
    		traverse = traverse.next;
    	}
    	ListNode[] test = Solution.splitListToParts(root,k);
    	
    	for(int i = 0 ; i < k ;i++) {
    		System.out.println("hi:"+test[i].val);
    	}
    	
    	//System.out.println(Solution.pivotIndex(nums));
        System.out.println("Func: "+Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}