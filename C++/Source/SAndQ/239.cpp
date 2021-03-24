#include "common.h"

class MonoQueue{
public:
    deque<int> dq;

    void push(int val){
        while(!dq.empty() && dq.back() < val){
            dq.pop_back();
        }
        dq.push_back(val);
    }
    void pop(int val){ // If the one to give up is the largest one.
        if(val == dq.front())
            dq.pop_front();
    }
    int peek(){
        return dq.front();
    }
};
class Solution {
public:
    vector<int> maxSlidingWindow(vector<int>& nums, int k) {
        
        vector<int> ret;
        MonoQueue mq;
        for(int i = 0; i < nums.size(); i++){
            
            if(i < k -1){
                mq.push(nums[i]);
            }
            else{
                mq.push(nums[i]);
                ret.push_back(mq.peek());
                mq.pop(nums[i-k+1]);
            }
        }
        return ret;
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
