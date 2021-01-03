#include "common.h"

class Solution {
public:

    map<TreeNode*, int> m;

    array<int, 2> bottomUpHelper(TreeNode* node){

        if(!node)
            return {0, 0};
        
        array<int, 2> left = bottomUpHelper(node->left);
        array<int, 2> right = bottomUpHelper(node->right);

        array<int,2> ret;
        ret[0] = max(left[0], left[1]) + max(right[0], right[1]); // ret[0] is the sum of node was not robbed.
        ret[1] = node->val + left[0] + right[0]; // ret[1] is the sum of node was robbeed
        return ret;
    }
    int topDownHelper(TreeNode* node){
        if(!node)
            return 0;
        
        if(m.find(node) == m.end()){
            m[node] = max(node->val + (node->left ? topDownHelper(node->left->left) : 0) + (node->left ? topDownHelper(node->left->right) : 0) + (node->right ? topDownHelper(node->right->left) : 0) +(node->right ? topDownHelper(node->right->right) : 0), topDownHelper(node->left) + topDownHelper(node->right));
        }
        return m[node]; 
    }
    int rob(TreeNode* root) {
        //return topDownHelper(root);
        return max(bottomUpHelper(root)[0], bottomUpHelper(root)[1]);
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
