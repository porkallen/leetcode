#include "common.h"

class Solution {
public:

    void helper(vector<int>& candidates, int target, int idx, vector<int> collected, vector<vector<int>>& ret){
        if(target == 0){
            cout << endl;
            ret.push_back(collected);
        }
        if(target < 0){
            return;
        }
        for(int i = idx; i < candidates.size(); i++){
            
            //Prevent duplicated cases
            if(i > idx && candidates[i] == candidates[i - 1]) 
                continue;
            collected.push_back(candidates[i]);
            helper(candidates, target - candidates[i], i + 1, collected, ret);
            collected.pop_back();
        }
    }
    vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
        vector<int> collected;
        vector<vector<int>> ret;

        sort(candidates.begin(), candidates.end());
        helper(candidates, target, 0, collected, ret);
        return ret;
    }
};

int main(){
    Solution s;
    cout << "<Case:" << __FILE__ << ">\n";
    cout << "==Output==\n";
    vector<int> candidates = {10,1,2,7,6,1,5};
    int target = 8;
    vector<vector<int>> result = s.combinationSum2(candidates, target);
    for(auto it : result){
        for(auto it2 : it){
            cout << " " << it2;
        }
        cout << endl;
    }
    return 0;
}
