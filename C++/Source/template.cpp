#include "common.h"

class Solution {
public:
};

int main(){
    Solution s;
#if ENABLE_TREE_TEST_CASE
    TreeNode root(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right->left = new TreeNode(15);
    root.right->right = new TreeNode(7);
#endif
    return 0;
}
