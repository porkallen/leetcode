#include "common.h"

class MinStack {
public:
    /** initialize your data structure here. */
    vector<int> v;
    int minVal = INT_MAX;
    MinStack() {        
    }
    
    void push(int x) {
        v.push_back(x);
    }
    
    void pop() {
        v.pop_back();
    }
    
    int top() {
        return v.back();
    }
    
    int getMin() {
        for(auto it : v){
            if(minVal >= it){]
                minVal = it;
            }
        }
        return minVal;
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
