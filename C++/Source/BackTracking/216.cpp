#include "common.h"

class Solution {
public:
    void helper(int k, int target, int next, vector<int>& collected, vector<vector<int>>& ret){
        if(target == 0 && k == 0){
            ret.push_back(collected);
            return;
        }
        if(target < 0){
            return;
        }

        for(int i = next; i <= 9; i++){
            collected.push_back(i);
            helper(k - 1, target - i, i + 1, collected, ret);
            collected.pop_back();
        }
    }
    vector<vector<int>> combinationSum3(int k, int n) {
        vector<int> collected;
        vector<vector<int>> ret;
        helper(k, n, 1, collected, ret);
        return ret;
    }
};

int main(){
    Solution s;
    cout << "<Case:" << __FILE__ << ">\n";
    cout << "==Output==\n";
    int k = 3;
    int target = 7;
    vector<vector<int>> result = s.combinationSum3(k, target);

    for(auto it : result){
        for(auto it2 : it){
            cout << " " << it2;
        }
        cout << endl;
    }
    return 0;
}
