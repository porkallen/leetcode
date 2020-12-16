#include "common.h"

class Solution {
public:
    pair<int, TreeNode* > dfsHelper(TreeNode* node){
        if(!node)
            return {0, nullptr};

        auto left = dfsHelper(node->left);
        auto right = dfsHelper(node->right);

        if(left.first > right.first){
            return {left.first + 1, left.second};
        }
        else if(left.first == right.first)
            return {left.first + 1, node};
        else{
            return {right.first + 1, right.second};
        }
    }

    TreeNode* subtreeWithAllDeepest(TreeNode* root) {
        return dfsHelper(root).second;
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
