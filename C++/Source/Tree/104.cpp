#include "common.h"
#define ENABLE_TREE_TEST_CASE 1

class Solution {
public:
    int maxNum = 0;
    void dfs(TreeNode *node, int num)
    {
        if(!node){
            return;
        }
        if(!node->left && !node->right){
            maxNum = max(maxNum, num);
        }
        dfs(node->left, num + 1);
        dfs(node->right, num + 1);
    }
    int maxDepth(TreeNode* root) {

        dfs(root, 1);
        return maxNum;
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
    cout << s.maxDepth(&root);
    return 0;
}