#include "common.h"

class Solution {
public:
    int calculate(string s) {

        stack<int> st, signSt;
        int num = 0;
        int result = 0;
        int sign = 1;
        for(int i = 0; i < s.length(); i++){
            
            if(s[i] == ' ')
                continue;

            if(isdigit(s[i])){
                num = num * 10 + (s[i] - '0');
            }
            else{

                result += sign * num;
                num = 0;
                if(s[i] == '+' || s[i] == '-'){
                    s[i] == '+'? sign = 1 : sign = -1;
                }
                else if(s[i] == '('){
                    st.push(result);
                    signSt.push(sign);
                    result = 0;
                    sign = 1;
                }
                else if(s[i] == ')'){ // s[i] == ')'
                    result = signSt.top() * result + st.top();
                    signSt.pop(); st.pop();
                    
                }
                else{ // space
                    continue;
                }
            }
        } // for(int i = 0; i < s.length(); i++)

        result += sign * num;
        return result;

    }//int calculate(string s)
};

int main(){
    Solution s;
    cout << "<Case:" << __FILE__ << ">\n";
    cout << "==Output==\n";

    cout << "ret: " << s.calculate(" 3- (1 - 2) ");
    return 0;
}
