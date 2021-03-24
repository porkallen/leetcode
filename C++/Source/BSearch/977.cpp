#include "common.h"

class Solution {
public:
    vector<int> sortedSquares(vector<int>& nums) {
        
        sort(nums.begin(), nums.end(), [](int a, int b){
            return abs(a) <= abs(b);
        });

        for(int i = 0; i < nums.size(); i++){
            nums[i] = nums[i] * nums[i];
        }
        return nums;
    }
};

int main(){
    Solution s;
    cout << "<Case:" << __FILE__ << ">\n";
    cout << "==Output==\n";

    vector<int> v = {-100,-99,-98,-98,-97,-91,-90,-87,-87,-80,-74,-73,-71,-69,-66,-63,-63,-60,-60,-58,-57,-52,-49,-45,-41,-38,-32,-31,-30,-30,-29,-28,-27,-27,-24,-21,-21,-21,-18,-13,-12,-11,-10,-10,-9,-8,-5,-5,-5,-4,0,0,0,1,2,2,2,3,3,6,16,16,22,26,28,30,34,35,38,39,40,45,46,47,52,53,54,56,56,57,59,62,62,63,65,66,67,67,69,71,75,79,80,83,86,88,93,94,94,99};
    
    v = s.sortedSquares(v);

    for(auto it : v){
        cout << " " << it;
    }
    cout<< endl;

    return 0;
}