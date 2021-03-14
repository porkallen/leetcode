#include "common.h"

class Solution {
public:
    string helper(string s, int& idx) {

        int num = 0;
        string str = ""; //3[a]2[bc]
        for(;idx < s.length(); idx++){
            
            if(isdigit(s[idx])){
                num = num * 10 + (s[idx] - '0');
            }
            else{
                if(s[idx] == '['){ // ch == '['
                    string tmpStr = helper(s, ++idx);
                    for(int j = 0; j < num; j++){
                        str += tmpStr;
                    }
                    num = 0;
                }
                else if(s[idx] == ']'){ // ch == ']'
                    return str;
                }
                else{ // normal char
                    str += s[idx];
                }
            }
        }
        return str;
    }
    string decodeString(string s) {
        int idx = 0;
        return helper(s, idx);
    }
    string decodeString2(string s) {
        stack<char> s1;
        string retStr;
        for(int i = 0; i < s.length(); i++){
            
            while(s[i] != ']'){
                s1.push(s[i]);
                i++;
            }

            string curStr = "";
            while(s1.top() != '['){ // collect alphabets
                curStr = s1.top() + curStr;
                s1.pop();   
            }

            s1.pop(); // remove [
            
            int curNum = 0;
            while(!s1.empty() && isdigit(s1.top())){ //collect digits
                curNum += curNum * 10 + s1.top() - '0';
                s1.pop();
            }
            while(curNum != 0){
                for(int j = 0; j < curStr.length(); j++){
                    s1.push(curStr[j]);
                }
                curNum--;
            }
        }

        while(!s1.empty()){
            retStr = s1.top() + retStr;
            s1.pop();
        }
        return retStr;
    }
};

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack* obj = new MyStack();
 * obj->push(x);
 * int param_2 = obj->pop();
 * int param_3 = obj->top();
 * bool param_4 = obj->empty();
 */

int main(){
    Solution s;
    cout << "<Case:" << __FILE__ << ">\n";
    cout << "==Output==\n"; // 2[3[a]2[bc3[a]]]
    cout << "String:" << s.decodeString2("2[3[a]2[bc3[a]]]");
    return 0;
}
