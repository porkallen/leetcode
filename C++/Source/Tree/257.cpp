#include "common.h"
#define ENABLE_TREE_TEST_CASE 1

class Solution {
public:
    vector<string> ret;
    void dfs(TreeNode *node, string str)
    {
        // For cases which the node with only either left or right child
        if(!node)
            return;

        // stop condition: The leaf
        if (node && !node->left && !node->right){
            str += to_string(node->val);
            ret.push_back(str);
            return;
        }

        str += to_string(node->val) + "->";
        dfs(node->left, str);
        dfs(node->right, str);
        return;
    }
    vector<string> binaryTreePaths(TreeNode* root) {
        string str;
        if(!root)
            return ret;

        dfs(root, str);
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
    root.right = new TreeNode(2);
    root.right->left = new TreeNode(5);
    root.right->right = new TreeNode(7);
#endif
    s.binaryTreePaths(&root);
    return 0;
}
