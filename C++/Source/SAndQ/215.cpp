#include "common.h"

class Solution {
public:
    int findKthLargest(vector<int>& nums, int k) {
        priority_queue<int, vector<int>> pq(nums.begin(), nums.end()); 

        while(k > 1){
            pq.pop();
            k--;
        }
        return pq.top();
    }
};


int main(){
    Solution s;

    
    cout << "<Case:" << __FILE__ << ">\n";
    cout << "==Output==\n";

    return 0;
}