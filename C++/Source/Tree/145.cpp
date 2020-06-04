#include "common.h"

class Solution {
public:
    vector<int> postorderTraversal2(TreeNode* root) {
        stack<TreeNode *> s1,s2;
        vector<int> v;
        //TreeNode *node = root;
        s1.push(root);
        while (!s1.empty())
        {
            TreeNode *t = s1.top();
            s2.push(t);
            s1.pop();
            if (t->left)
                s1.push(t->left);
            if(t->right)
                s1.push(t->right);
        }
        while(!s2.empty()){
            v.push_back(s2.top()->val);
            s2.pop();
        }
        return v;
    }

    vector<int> postorderTraversal(TreeNode* root) {
        stack<TreeNode *> s;
        vector<int> v;
        TreeNode *preNode = nullptr, *node = root;
        
        while(!s.empty() || node){
            if(node){
                s.push(node);
                node = node->left;
            }
            else{
                TreeNode *t = s.top();
                if (t->right == nullptr || t->right == preNode)
                {
                    v.push_back(t->val);
                    s.pop();
                    preNode = t;
                    node = nullptr;
                }
                else{
                    node = t->right;
                }
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
    s.postorderTraversal(&root);
    return 0;
}
