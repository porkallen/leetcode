#include "common.h"

class Solution {
public:

    bool preorder(TreeNode* left, TreeNode* right){
        if(!left && !right)
            return true;
        
        if((!left && right) || (left && !right) || (left->val != right->val))
            return false;

        return preorder(left->right, right->left) && preorder(left->left, right->right);
    }
    bool isSymmetric(TreeNode* root) {
        if(!root)
            return true;
        return preorder(root->left, root->right);
    }
};

int main(){
    Solution s;
    TreeNode root(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right->left = new TreeNode(15);
    root.right->right = new TreeNode(7);
    //s.levelOrder(&root);

    return 0;
}
