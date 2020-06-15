#include "common.h"

class Solution {
public:

    void dfsHelper(TreeNode* node, vector<int> v, vector<vector<int>> &ret, int acc, int sum){
        
        if(!node)
            return;

        v.push_back(node->val);
        if (!node->left && !node->right && (acc + node->val) == sum)
        {
            ret.push_back(v);
            return;
        }

        dfsHelper(node->left, v, ret, acc + node->val, sum);
        dfsHelper(node->right, v ,ret, acc + node->val, sum);
    }
    vector<vector<int>> pathSum(TreeNode* root, int sum) {
        vector<vector<int>> ret;
        vector<int> v;
        dfsHelper(root, v, ret , 0, sum);
        return ret;
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
    return 0;
}
