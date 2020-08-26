#include "common.h"

class Solution {
public:

    void builder(TreeNode** node, vector<int>& nums, int left, int right){
        int middle = left + (right - left) / 2;
        if(left >= right)
            return;

        if (!*node){
            *node = new TreeNode(nums[middle]);
        }

        builder(&((*node)->left), nums, left, middle);
        builder(&((*node)->right), nums, middle + 1, right);
    }
    TreeNode* sortedArrayToBST(vector<int>& nums) {
        TreeNode *root = nullptr;
        if(nums.size() == 0)
            return root;

        root = new TreeNode(nums[nums.size() / 2]);
        builder(&root, nums, 0, nums.size());
        return root;
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
