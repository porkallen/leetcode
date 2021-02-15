#include "common.h"

class Solution {
public:
    int evalRPN(vector<string>& tokens) {
        stack<string> s;
        
        for(auto it : tokens){

            if(it == "+" || it == "-" || it == "*" || it == "/"){
                int a = stoi(s.top());
                s.pop();
                int b = stoi(s.top());
                s.pop();

                int ret = 0;
                switch(it.c_str()[0]){
                case '+':
                    ret = b + a;
                    break;
                case '-':
                    ret = b - a;
                    break;
                case '*':
                    ret = b * a;
                    break;
                case '/':
                    ret = b / a;
                    break;
                }
                s.push(to_string(ret));
            }
            else{
                s.push(it);
            }
        }
        return stoi(s.top());
    }
};

int main(){
    Solution s;
    cout << "<Case:" << __FILE__ << ">\n";
    cout << "==Output==\n";
#if ENABLE_TREE_TEST_CASE
    TreeNode root(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right->left = new TreeNode(15);
    root.right->right = new TreeNode(7);
    root.left->right = new TreeNode(2);
    /*     3 
          / \
        9    20
         \   / \
          2 15  7
    */
#endif
    return 0;
}