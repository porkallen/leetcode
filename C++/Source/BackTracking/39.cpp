#include "common.h"

class Solution {
public:

    void helper(vector<int>& candidates, int target, vector<int>& chosenCandidates, int idx, vector<vector<int>>& result){
        
        if(target == 0){
            result.push_back(chosenCandidates);
            return;
        }
        if(target < 0){
            return;
        }

        for(int i = idx; i < candidates.size(); i++){
            chosenCandidates.push_back(candidates[i]);
            helper(candidates, target - candidates[i], chosenCandidates, i, result);
            chosenCandidates.pop_back();
        }
    }
    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
        vector<int> chosenCandidates;
        vector<vector<int>> result;
        helper(candidates, target, chosenCandidates, 0 ,result);
        return result;
    }
};

int main(){
    Solution s;
    cout << "<Case:" << __FILE__ << ">\n";
    cout << "==Output==\n";
    vector<int> candidates = {7,2,3,6};
    int target = 7;
    vector<vector<int>> result = s.combinationSum(candidates, target);
    cout << result.size();
    return 0;
}
