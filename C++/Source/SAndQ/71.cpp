#include "common.h"

class Solution {
public:
    string simplifyPath(string path) {
        
        string ret, tok;
        vector<string> toks;
        if(path.length() == 0)
            return path;
        stringstream ss(path);

        while(getline(ss, tok, '/')){
            if(tok == "" || tok == "."){
                continue;
            }
            else if(tok == ".."){
                if(!toks.empty())
                    toks.pop_back();
            }
            else{
                toks.push_back(tok);
            }
        }
        ret = "/";
        for(auto it : toks){
            ret += it + "/";
        }
        return ret;
    }
};

int main(){
    Solution s;
    cout << "<Case:" << __FILE__ << ">\n";
    cout << "==Output==\n";

    cout << "c path:" << s.simplifyPath("/home/") << endl;
    return 0;
}
