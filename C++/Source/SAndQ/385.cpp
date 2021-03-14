
#include "common.h"

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
class NestedInteger {
    public:
        // Constructor initializes an empty nested list.
        NestedInteger();

        // Constructor initializes a single integer.
        NestedInteger(int value);

        // Return true if this NestedInteger holds a single integer, rather than a nested list.
        bool isInteger() const { return true;}

        // Return the single integer that this NestedInteger holds, if it holds a single integer
        // The result is undefined if this NestedInteger holds a nested list
        int getInteger() const { return 0;}

        // Set this NestedInteger to hold a single integer.
        void setInteger(int value) {}

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        void add(const NestedInteger &ni);

        // Return the nested list that this NestedInteger holds, if it holds a nested list
        // The result is undefined if this NestedInteger holds a single integer
        const vector<NestedInteger> &getList() const;
 };
 
class Solution {
public:

    void helper(string s, int idx, NestedInteger& ret){

        int num = 0;
        for(int i = idx; i < s.length(); i++){
        
            if(isdigit(s[i])){
                num = num * 10 + (s[i] - '0');
            }
            else{
                if(s[i] == '['){
                    ret.setInteger(num);
                    helper(s, i + 1, ret);
                }
                else if(s[i] == ']'){
                    NestedInteger ni(num);
                    ret.add(ni);
                }
            }
        }
    }
    NestedInteger deserialize(string s) {
        NestedInteger ret;
        helper(s, 0, ret);
        return ret;
    }
};

int main(){
    Solution s;

    s.deserialize("[123,[456,[789]]]");
    cout << "<Case:" << __FILE__ << ">\n";
    cout << "==Output==\n";
    return 0;
}