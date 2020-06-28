#include "common.h"
#define ENABLE_TREE_TEST_CASE 1

class Solution {
public:
    void dfsHelper(TreeNode* node, vector<int> &v, int & sum){

        if(!node)
            return;

        
        if (!node->left && !node->right)
        {
            int tmpSum = 0, digit = 0;

            while (digit < v.size())
            {
                tmpSum += v[digit++];
                tmpSum *= 10;
            }
            sum += tmpSum + node->val;
            return;
        }
        v.push_back(node->val);
        dfsHelper(node->left, v, sum);
        dfsHelper(node->right, v, sum);
        v.pop_back();
    }
    int sumNumbers(TreeNode* root) {
        vector<int> v;
        int ret = 0;
        dfsHelper(root, v, ret);
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
    s.sumNumbers(&root);
    return 0;
}
