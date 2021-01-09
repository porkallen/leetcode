#include "common.h"
class Solution {
public:
    vector<int> intersection(vector<int>& nums1, vector<int>& nums2) {
        vector<int> ret;
        unordered_set<int> s;
        if(nums1.size() == 0 || nums2.size() == 0)
            return ret;
        
        sort(nums1.begin(), nums1.end());
        sort(nums2.begin(), nums2.end());

        for(auto it: nums1){
            int left = 0, right = nums2.size();
            while(left < right){
                int m = left + (right - left)/2;
                if(nums2[m] > it){
                    right = m;
                }
                else if(nums2[m] == it){
                    s.insert(it);
                    break;
                }
                else{
                    left = m + 1;
                }
            }
        }
        ret.insert(ret.end(), s.begin(), s.end());
        return ret;
    }
};

int main(){
    Solution s;
    cout << "<Case:" << __FILE__ << ">\n";
    cout << "==Output==\n";
    vector<int> nums1 = {1,2,2,1}, nums2 = {2,2};
    s.intersection(nums1,nums2);
    return 0;
}