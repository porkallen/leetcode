#include "common.h"

class Solution {
public:
    map<int, int> m;

    int bottomUpHelper(vector<int>& nums){
        vector<int> dp(nums.size() + 1, 0);
        dp[0] = 0;
        dp[1] = nums[0];

        for(int i = 1; i < nums.size(); i++){
            dp[i + 1] = max(nums[i] + dp[i - 1], dp[i]);
        }
        return dp[nums.size()];

    }
    int topDownHelper(vector<int>& nums, int idx){
        if(idx < 0 || idx >= nums.size())
            return 0;
        if(m.find(idx) == m.end()){
            m[idx] = max(nums[idx] + topDownHelper(nums, idx - 2), topDownHelper(nums, idx - 1));
        }
        return m[idx];
    }
    int rob(vector<int>& nums) {
        if(nums.size() == 0)
            return 0;
        if(nums.size() == 1)
            return nums[0];

        //return topDownHelper(nums, nums.size() - 1);
        return bottomUpHelper(nums);
    }
};

int main(){
    Solution s;
    cout << "<Case:" << __FILE__ << ">\n";
    cout << "==Output==\n";
    vector<int> nums = {1,2,3,1};
    cout << "nums:" << s.rob(nums);
    return 0;
}
