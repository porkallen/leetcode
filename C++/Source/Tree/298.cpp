#include "common.h"

#define ENABLE_TREE_TEST_CASE 1

class Solution {
public:

    int maxLen = 0;
    void helper(TreeNode* node, TreeNode* preNode, int len){
        if(!node)
            return;
        
        if(!preNode){
            preNode = node;
            maxLen = len = 1;
        }
        else{
            if((preNode->val + 1) == node->val){
                if(++len >= maxLen)
                    maxLen = len;
            }
            else
            {
                len = 1;
            }
        }
        helper(node->left, node, len);
        helper(node->right, node, len);
    }
};

int main(){
    Solution s;
    cout << "<Case:" << __FILE__ << ">\n";
    cout << "==Output==\n";
#if ENABLE_TREE_TEST_CASE
    TreeNode root(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(2);
    root.right->left = new TreeNode(15);
    //root.right->right = new TreeNode(3);
    /*     3 
          / \
        9    20
         \   / \
          2 15  7
    */
#endif
    cout << s.longestConsecutive(&root);
    return 0;
}
