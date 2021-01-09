#include "common.h"

class Solution {
public:
    int lowbound(vector<int> nums, int target) {
        int left = 0, right = nums.size();
        while(left < right) {
            int m = left + (right -left)/2;
            if(target > nums[m])
                left = m + 1;
            else 
                right = m;
        }
        return left;
    }
    int upperbound(vector<int> nums, int target) {
        int left = 0, right = nums.size();
        while(left < right) {
            int m = left + (right -left)/2;
            if(target >= nums[m])
                left = m + 1;
            else 
                right = m;
        }
        return left;
    }
    vector<int> searchRange(vector<int>& nums, int target) {
        
        int leftBound = lowbound(nums,target);
        int rightBound = upperbound(nums,target);
        vector<int> ret = {-1,-1};
        if(leftBound < nums.size() && nums[leftBound] == target){
            ret = {leftBound, rightBound - 1};
        }
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
