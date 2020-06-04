#include "common.h"

class Solution {
public:

    bool preorder(TreeNode* p, TreeNode* q){

        if (!p && !q)
            return true;
        
        if((!p && q) || (p && !q) || (p->val != q->val))
            return false;

        return preorder(p->left, q->left) & preorder(p->right, q->right);
    }
    bool isSameTree(TreeNode* p, TreeNode* q) {
        return preorder(p,q);
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
