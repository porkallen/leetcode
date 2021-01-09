#include "common.h"

class Solution {
public:
    int findMin(vector<int>& nums) {
        int left = 0, right = nums.size() - 1;

        while(left < right){
            int m = left + (right - left)/2;
            
            if(nums[left] == nums[m] && nums[right] == nums[m]){
                left ++;
                right --;
                continue;
            }
            if(nums[m] <= nums[right]){
                right = m;
            }
            else{
                left = m + 1;
            }
        }
        return nums[left];
    }
};