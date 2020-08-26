#include "common.h"
#define ENABLE_TREE_TEST_CASE 1

class Solution {
public:
    void helper(vector<int> &v, TreeNode **root, int left, int right)
    {
        int mid = left + (right - left) / 2;
        if(left >= right)
            return;
        
        if(!*root)
        {
            *root = new TreeNode(v[mid]);
        }

        helper(v, &(*root)->left, left, mid);
        helper(v, &(*root)->right, mid + 1, right);
    }
    TreeNode *sortedListToBST(ListNode *head)
    {
        vector<int> v;
        TreeNode *root;
        if(!head)
            return root;
        
        ListNode *node = head;
        while(node != nullptr)
        {
            v.push_back(node->val);
            node = node->next;
        }
        root = new TreeNode(v[v.size()/2]);
        helper(v, &root, 0, v.size());
        return root;
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
