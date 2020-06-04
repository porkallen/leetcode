#include "common.h"

class Solution {
public:
    
    vector<int> inorderTraversal(TreeNode* root) {
        TreeNode *node = root;
        stack<TreeNode *> s;
        vector<int> v;
        if(!node)
            return v;

        while (!s.empty() || node)
        {
            if(node)
            {
                s.push(node);
                node = node->left;
                
            }
            else
            {
                TreeNode *t = s.top();
                v.push_back(t->val);
                s.pop();
                node = t->right;
            }
        }
        return v;
    }
};

int main(){
    Solution s;
    TreeNode root(10);
    root.left = new TreeNode(5);
    root.right = new TreeNode(12);
    s.inorderTraversal(&root);
    return 0;
}
