#include "common.h"

#define ENABLE_TREE_TEST_CASE 1

class Solution {
public:

    TreeNode* helper(TreeNode* node, TreeNode* p, TreeNode* q){
        if(node == nullptr || node == p || node== q){
            return node;
        }
        TreeNode *left = helper(node->left, p, q);
        TreeNode *right = helper(node->right, p, q);

        cout << "node" << node->val << "\n";
        if(left){
            cout << "left node" << left->val << "\n";
        }
        if(right){
            cout << "right node" << right->val << "\n";
        }
        if(left && right)
            return node;
        else
            return left? left : right;
    }
    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
        return helper(root, p, q);
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

    TreeNode *node = s.lowestCommonAncestor(&root,root.left->right, root.left);
    return 0;
}
