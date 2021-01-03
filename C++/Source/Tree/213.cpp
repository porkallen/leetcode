#include "common.h"

class Solution {
public:
    int topDownhelper(vector<int>& nums, map<int,int>& m, int startIdx, int endIdx){
        if(endIdx < 0 || endIdx > nums.size() - 1 || endIdx < startIdx){
            return 0;
        }

        if(m.find(endIdx) == m.end()){
        
            m[endIdx] = max(nums[endIdx] + topDownhelper(nums, m, startIdx , endIdx - 2), topDownhelper(nums,m, startIdx , endIdx - 1));
        }

        return m[endIdx];
    }
    int rob(vector<int>& nums) {
        int ret = 0;
        if(nums.size() == 0)
            return 0;
        if(nums.size() == 1)
            return nums[0];

        map<int,int> m, m1;
        
        return max(topDownhelper(nums,m,0, nums.size() - 2), topDownhelper(nums,m1,1, nums.size() - 1));
    }
};

int main(){
    Solution s;
    cout << "<Case:" << __FILE__ << ">\n";
    cout << "==Output==\n";
    
    vector<int> v = {2,3,2};
    cout << "nums:" << s.rob(v) << "\n";
    return 0;
}
