#include "common.h"

class Solution {
public:
    int getXORSum(vector<int>& arr1, vector<int>& arr2) {
        
        int ret = 0;
        vector<int> bitmask(32, 0);
        
        for(auto it : arr2){
            for(int i = 0; i < 32; i++){
                if((it >> i) & 0x1)
                    bitmask[i]++;
            }
        }

        int andResult = 0;
        for(auto it : arr1){
            for(int i = 0; i < 32; i++){
                int l = (it >> i) & 0x1, r = bitmask[i] % 2;
                if(l + r == 2){ // And case
                    andResult += 1 << i;
                }
            }
        }
        ret ^= andResult;
        return 0;
    }
};

int main(){
    Solution s;
    cout << "<Case:" << __FILE__ << ">\n";
    cout << "==Output==\n";
    vector<int> arr1 = {1,2};
    vector<int> arr2 = {6,5};
    s.getXORSum(arr1, arr2);
    return 0;
}
