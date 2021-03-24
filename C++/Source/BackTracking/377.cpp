#include "common.h"

class Solution {
public:
    void helper(vector<int>& nums, int target, int max, vector<int>& collected, vector<vector<int>>& ret){
        if(target == 0){
            ret.push_back(collected);
            return;
        }
        if(target < 0)
            return;
        
        for(int i = 0; i < max; i ++){
            collected.push_back(nums[i]);
            helper(nums, target - nums[i], max, collected, ret);
            collected.pop_back();
        }
    }
    int combinationSum4(vector<int>& nums, int target) {
        sort(nums.begin(), nums.end());

        int max = nums.size();
        if(nums[0] != 0)
            max = nums.size() / nums[0];

        vector<int> collected;
        vector<vector<int>> ret;
        helper(nums, target, max, collected, ret);
        return ret.size();
    }
};

int main(){
    Solution s;
    cout << "<Case:" << __FILE__ << ">\n";
    cout << "==Output==\n";
    vector<int> candidates = {3,2,1};
    int target = 7;
    cout << s.combinationSum4(candidates, target);

    return 0;
}
