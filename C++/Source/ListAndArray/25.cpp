#include "common.h"

class Solution {
public:

    ListNode* reverseKGroup(ListNode* head, int k) {

        /*
        0 -> 1 -> 2 -> 3 -> 4 -> 5
        */
        int count = 0;
        ListNode tmpNode(-1);
        tmpNode.next = head;
        
        ListNode* pre = &tmpNode, *cur = &tmpNode, *next = &tmpNode;
        while(cur->next){
            cur = cur->next;
            count++;
        }

        while(count >= k){
            cur = pre->next;
            next = cur->next;
            for(int i = 0; i < k ; i++){
                cur->next = next->next;
                next->next = cur; 
                pre->next = next;
                next = cur->next;
            }
            pre = cur;
            count -= k;
        }
        return head;
    }
};

int main(){
    Solution s;
    cout << "<Case:" << __FILE__ << ">\n";
    cout << "==Output==\n";
#if ENABLE_TREE_TEST_CASE
    TreeNode root(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right->left = new TreeNode(15);
    root.right->right = new TreeNode(7);
    root.left->right = new TreeNode(2);
    /*     3 
          / \
        9    20
         \   / \
          2 15  7
    */
#endif
    return 0;
}
