#include "common.h"

class Solution {
public:

    void calculate(char sign, int num, stack<int>& st){
        if(sign == '+' || sign == '-'){
            st.push(((sign == '+') ? 1 : (-1)) * num);
        }
        else if(sign == '*'){
            int num2 = st.top();
            //printf("mul: num:%d %d\n", num2, num);
            st.pop();
            st.push(num2 * num);
        }
        else if(sign == '/'){
            int num2 = st.top();
            //printf("div:num:%d %d\n", num2, num);
            st.pop();
            st.push(num2 / num);
        }
    }
    int helper(string s, int &i){

        int ret = 0, num = 0;
        char sign = '+';
        stack<int> st;
        for(; i < s.length(); i++){ //1+(2*(4/2))

            if(isdigit(s[i])){
                num = num * 10 + (s[i] - '0');
            }
            if(!isdigit(s[i]) && s[i] != ' ' || i == s.length() - 1){
                if(s[i] == '('){
                    calculate(sign, helper(s, ++i), st);
                    //printf("=left end=\n");
                }
                else if(s[i] == ')'){
                    calculate(sign, num, st);
                    while(!st.empty()){
                        //printf("top:%d \n", st.top());
                        ret += st.top();
                        st.pop();
                    }
                    printf("=right end=\n");
                    return ret;
                }
                else{
                    calculate(sign, num, st);
                }
                sign = s[i];
                num = 0;
            }
        }
        
        while(!st.empty()){
            ret += st.top();
            st.pop();
        }
        return ret;
    }


    int calculate(string s) {
        int idx = 0;
        return helper(s, idx);
    }
};

int main(){
    Solution s;
    cout << "<Case:" << __FILE__ << ">\n";
    cout << "==Output==\n";
    cout << "result:" << s.calculate("1+(1-2*(4/2))") << endl;
    return 0;
}
