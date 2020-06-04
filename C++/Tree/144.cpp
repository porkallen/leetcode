#include "common.h"

class Solution {
public:
    vector<int> preorderTraversal(TreeNode* root) {
        vector<int> v;
        stack<TreeNode *> s;
        
        if(root == nullptr)
            return v;
        
        s.push(root);
        while( !s.empty()){
            
            TreeNode *node = s.top();
            v.push_back(node->val);
            s.pop();
            if(node->right != nullptr){
                s.push(node->right);
            }
            if(node->left != nullptr){
                s.push(node->left);
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
    s.preorderTraversal(&root);
    return 0;
}
