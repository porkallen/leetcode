#include "common.h"
#define ENABLE_TREE_TEST_CASE 1
class Solution {
public:
    int maxVal = INT32_MIN;
    int helper(TreeNode* node){
        int ret = 0;
        if(!node)
            return 0;
        
        int left = helper(node->left);
        int right = helper(node->right);

        ret = max(node->val, node->val + left);
        ret = max(ret, node->val + right);
        //ret = max(ret, node->val + left + right);

        maxVal = max(maxVal, ret);
        maxVal = max(maxVal, node->val + left + right);

        return ret;
    }
    void postOrderHelper(TreeNode *node, map<TreeNode *, pair<int, int>>& m)
    {
        if(!node)
            return;
    
        postOrderHelper(node->left, m);
        postOrderHelper(node->right, m);

        pair<int, int> p;
        p.first = node->left ? max(node->left->val, node->left->val + max(m[node->left].first, m[node->left].second)) : 0;
        p.second = node->right ? max(node->right->val, node->right->val + max(m[node->right].first, m[node->right].second)): 0;
        m[node] = p;

        printf("node:%d left:%d right:%d \n", node->val, p.first, p.second);
        maxVal = max(maxVal, node->val + (p.first > 0 ? p.first : 0) + (p.second > 0 ? p.second : 0));
    }
    int maxPathSum(TreeNode* root) {
        map<TreeNode *, pair<int, int>> m;
        if (!root->left && !root->right)
            return root->val;
        //postOrderHelper(root, m);

        helper(root);
        return maxVal;
    }
};

int main(){
    Solution s;
    cout << "<Case:" << __FILE__ << ">\n";
    cout << "==Output==\n";
#if ENABLE_TREE_TEST_CASE
    TreeNode root(-3);
    root.left = new TreeNode(-9);
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
    printf("val:%d \n",s.maxPathSum(&root));
    return 0;
}
