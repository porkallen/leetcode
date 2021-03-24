#include "common.h"

class Solution {
public:

    void helper(vector<string>& words, int chosenIdx, int startIdx, string& conStr, vector<string>& ret){
        
        if(words[chosenIdx] == conStr){
            ret.push_back(words[chosenIdx]);
            return;
        }
        if(conStr.length() > words[chosenIdx].length())
            return;

        
        for(int i = 0; i < words.size(); i++){
            if(i != chosenIdx){
                conStr += words[i];
                helper(words, i, conStr, ret);
                //cout << ":::" << conStr << " :::" << words[i]<<endl; 

                size_t pos = conStr.find(words[i]);
                if(pos != std::string::npos)
                    conStr.erase(pos, words[i].length());
            }
        }
    }
    vector<string> findAllConcatenatedWordsInADict(vector<string>& words) {
        string conStr = "";
        vector<string> ret;
        helper(words, 0, 0, conStr, ret);
        return ret;
    }
};

int main(){
    vector<string> words = {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat", "catsdogcats","dogcatsdog","ratcatdogcat"};
    Solution s;
    vector<string> ret = s.findAllConcatenatedWordsInADict(words);

    for(auto it : ret){
        cout << " " << it << endl;
    }
    return 0;
}