#include "common.h"

class Solution {
public:
    vector<vector<int>> ret;
    void helper(vector<int>& nums, vector<int> collected, int idx){

        ret.push_back(collected);
        for(int i = idx; i < nums.size(); i++){
            if(i == idx || nums[i] != nums[i-1]){
                collected.push_back(nums[i]);
                helper(nums, collected, i + 1);
                collected.pop_back();
            }
        }
    }
    vector<vector<int>> subsetsWithDup(vector<int>& nums) {
        vector<int> collected;
        sort(nums.begin(),nums.end());
        helper(nums, collected, 0);
        return ret;
    }

    vector<vector<int>> subsetsWithDup2(vector<int>& nums) {
        vector<vector<int>> ret2;
        sort(nums.begin(),nums.end());
        ret2.push_back({});

        for(int i = 0; i < nums.size();){
        
            //Calculate the dup
            int dup = 0;
            while((i + dup) < nums.size() && nums[i] == nums[i + dup]) dup++;

            int sz = ret2.size();
            for(int j = 0; j < sz; j++){
                vector<int> v = ret2[j];
                for(int k = 0; k < dup; k++){
                    v.push_back(nums[i]);
                    ret2.push_back(v);
                }
            }
            i += dup;
        }
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
