#include "common.h"

class Solution {
public:

    vector<int> helper(string s){

        vector<int> ret;
        for(int i = 0; i < s.length(); i++){
            if(!isdigit(s[i])){
                vector<int> v1 = helper(s.substr(0, i));
                vector<int> v2 = helper(s.substr(i+1));

                for(auto it : v1){
                    for(auto it2 : v2){
                        if(s[i] == '+'){
                            ret.push_back(it + it2);
                        }
                        else if(s[i] == '-'){
                            ret.push_back(it - it2);
                        }  
                        else if(s[i] == '*'){
                            ret.push_back(it * it2);
                        }
                        else{
                            ret.push_back(it / it2);
                        }
                    }
                }
            }
        }
        
        if(ret.size() == 0)
            ret.push_back(stoi(s));

        return ret;
    }
    vector<int> diffWaysToCompute(string input) {
        return helper(input);
    }
};


int main(){
    Solution s;

    
    cout << "<Case:" << __FILE__ << ">\n";
    cout << "==Output==\n";

    for(auto it : s.diffWaysToCompute("2*3-4*5")){
        cout << "ret" << it << endl;
    }
    return 0;
}