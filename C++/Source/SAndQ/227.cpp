
#include "common.h"

class Solution {
public:
    int calculate(string s) {

        stack<int> st;
        int num = 0, ret = 0;
        char sign = '+';
        for(int i = 0; i < s.size(); i++){
        
            if(isdigit(s[i])){
                num = num * 10 + (s[i] - '0');
            }
            else{
            
                if(sign == '+'){
                    st.push(num);
                
                }
                else if(sign == '-'){
                    st.push(num);
                }
                else if(sign == '*'){ // post handling
                    int tmp = st.top() * num;
                    st.pop();
                    st.push(tmp);    
                }
                else{
                    int tmp = st.top() / num;
                    st.pop();
                    st.push(tmp); 
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
};

int main(){
    Solution s;

    
    cout << "<Case:" << __FILE__ << ">\n";
    cout << "==Output==\n";
    cout << "result:" << s.calculate("3/2") << endl;
    return 0;
}