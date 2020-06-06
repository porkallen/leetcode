#include "common.h"

#define ENABLE_TREE_TEST_CASE 1

class Solution {
public:

    void preorder(TreeNode* node, TreeNode** retNode){
        if(!node)
            return;

        if(!*retNode)
            *retNode = new TreeNode(node->val);

        preorder(node->left, &((*retNode)->right));
        preorder(node->right, &((*retNode)->left));
    }
    TreeNode* invertTree(TreeNode* root) {
        TreeNode *retRoot = nullptr;
        if(!root)
            return retRoot;

        retRoot = new TreeNode(root->val);

        preorder(root, &retRoot);
        return retRoot;
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
#endif
    s.invertTree(&root);
    return 0;
}