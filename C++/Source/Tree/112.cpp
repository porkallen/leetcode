#include "common.h"

class Solution {
public:

    bool dfsHelper2(TreeNode* node, int acc, int sum){
        if(!node)
            return false;

        acc += node->val;
        if(!node->left && !node->right && acc == sum){
            return true;
        }

        if(dfsHelper2(node->left, acc, sum) || dfsHelper2(node->right, acc, sum))
            return true;

        acc -= node->val;
        return false;
    }
    void dfsHelper(TreeNode* node, int acc, int sum, bool &ret){
        if(!node || ret)
            return;

        acc += node->val;
        if(!node->left && !node->right && acc == sum){
            ret = true;
        }

        dfsHelper(node->left, acc, sum, ret);
        dfsHelper(node->right, acc, sum, ret);
    }
    bool hasPathSum(TreeNode* root, int sum) {
        bool ret = false;
        dfsHelper(root, 0, sum, ret);
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
