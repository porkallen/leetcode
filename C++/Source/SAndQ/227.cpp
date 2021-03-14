
#include "common.h"

class Solution {
public:
    int calculate(string s) {
        stack<int> st;
        stack<char> stOp;
        int num1 = 0;

        stOp.push('+');
        s += '\n';
        for(int i = 0; i < s.length(); i++){
            
            if(s[i] == ' '){
                continue;
            }
            if(isdigit(s[i])){
                num1 = num1 * 10 + (s[i] - '0');    
            }
            else{
                
                //12 * 23, 1+2-4*3, 4/2-4*3+4
                cout << "ch:" << s[i] << "sz:" <<stOp.size() << endl;
                if(!stOp.empty()){
                
                    if(stOp.top() == '*'){
                        int num2 = st.top();
                        st.pop();
                        stOp.pop();
                        num1 *= num2;
                    }
                    else if(stOp.top() == '/'){
                        int num2 = st.top();
                        st.pop();
                        stOp.pop();
                        num1 = num2 / num1;
    
                    }
                    if(s[i] != '\n'){
                        //cout << "2ops" << s[i] << endl;
                        stOp.push(s[i]);
                    }
                    st.push(num1);
                }
                else{
                   // if(num1 != 0)
                   //     st.push(num1);

                    if(s[i] != '\n'){
                        //cout << "1ops" << s[i] << endl;
                        stOp.push(s[i]);
                    }
                }
                num1 = 0;
            } // if(isdigit(s[i]))
        } // for(int i = 0; i < s.length(); i++)

        int retNum = 0;

        while(!st.empty() && !stOp.empty()){
        
            int num1 = st.top();
            st.pop();
            char ch = stOp.top();
            stOp.pop();
            cout << "num" << num1 << " op" << ch << " sz" << st.size() << " sz" << stOp.size() <<endl;

            if(ch == '+'){
                retNum += num1;
            }
            else{
                retNum -=num1;
            }    
        }
        return retNum;
    }
};

int main(){
    Solution s;

    
    cout << "<Case:" << __FILE__ << ">\n";
    cout << "==Output==\n";
    cout << "result:" << s.calculate("3/2") << endl;
    return 0;
}