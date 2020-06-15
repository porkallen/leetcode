#include "common.h"

#define ENABLE_TREE_TEST_CASE 1

class Solution {
public:

    bool ret = true;
    int dfs(TreeNode *node){
        if(!node)
            return 0;
        return max(dfs(node->left), dfs(node->right)) + 1;
    }
    bool tdHelper(TreeNode *node)
    {
        if(!node)
            return true;

        stack<TreeNode *> s;
        s.push(node);
        while(!s.empty()){
            TreeNode *t = s.top();
            s.pop();

            if(abs(dfs(t->left) - dfs(t->right)) > 1)
                return false;

            if (t->left)
                s.push(t->left);
            if (t->right)
                s.push(t->right);
        }
        return true;
    }

    int buHelper(TreeNode *node){
        if(!node)
            return 0;
        
        int left = buHelper(node->left);
        int right = buHelper(node->right);

        if(node->val == 2)
            cout << "left:" << left << "right:" << right << "\n";

        if (abs(left - right) > 1)
            ret = false;

        return max(left, right) + 1;
    }
    bool isBalanced(TreeNode* root) {
        buHelper(root);
        return ret;
    }
};

int main(){
    Solution s;
    cout << "<Case:" << __FILE__ << ">\n";
    cout << "==Output==\n";
#if ENABLE_TREE_TEST_CASE
    TreeNode root(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left->left = new TreeNode(4);
    root.right->right = new TreeNode(5);
    root.left->left->left = new TreeNode(6);
     root.right->right->right = new TreeNode(6);
    /*     3 
          / \
        9    20
         \   / \
          2 15  7
    */
#endif
    cout << s.isBalanced(&root);
    return 0;
}
