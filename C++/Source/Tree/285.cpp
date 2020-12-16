#include "common.h"

class Solution {
public:

    bool isHit = false;
    TreeNode *ret = nullptr;
    void helper(TreeNode* node, TreeNode* p){
        if(!node || isHit)
            return;
        
        helper(node->left, p);

        if(isHit == true && !ret)
            ret = node;
        if(node == p)
            isHit = true;
        helper(node->right, p);
    }
    TreeNode* inorderSuccessor(TreeNode* root, TreeNode* p) {
        helper(root, p);
        return ret;
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
