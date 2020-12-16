#include "common.h"

class Solution {
public:
    vector<int> rightSideView(TreeNode* root) {
        
        vector<int> ret;
        if(!root)
            return ret;
        
        deque<TreeNode *> dq;
        dq.push_back(root);

        while(!dq.empty()){
            int size = dq.size();
            
            TreeNode *node = nullptr;
            for(int i = 0; i < size; i++){
                node = dq.front();
                dq.pop_front();

                if(node->left)
                    dq.push_back(node->left);     
                if(node->right)
                    dq.push_back(node->right); 
            }
            ret.push_back(node->val);
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
