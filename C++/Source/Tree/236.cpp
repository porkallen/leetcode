#include "common.h"
#define ENABLE_TREE_TEST_CASE 1
class Solution {
public:
    bool helper(TreeNode* node, TreeNode* target, vector<TreeNode *>& v){
        if (!node || !target)
            return false;

        v.push_back(node);

        if(node == target)
            return true;

        if(helper(node->left, target, v) || helper(node->right, target, v))
            return true;
        
        v.pop_back();
        return false;
    }
    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
        vector<TreeNode *> v1, v2;
        helper(root, p, v1);
        helper(root, q, v2);
        int i = 0;
        for (i = 0; i < min(v1.size(), v2.size()); i++)
        {
            if(v1[i] != v2[i])
                break;
        }
        return i == 0 ? nullptr : v1[i - 1];
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
    s.lowestCommonAncestor(&root);
    for(auto it : s.m){
        cout << "node:" << it.first->val << "\n";
        for(auto it2 : it.second){
            cout << it2->val << "\n";
        }
        cout << " ==== \n";
    }
    return 0;
}
