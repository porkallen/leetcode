#include "common.h"

class Solution {
public:
    int kthSmallest(TreeNode* root, int k) {
        int retVal = 0;
        stack<TreeNode *> s;

        TreeNode *node = root;
        while(node){
            s.push(node);
            node = node->left;
        }
        
        while(k >= 1){ 
            node = s.top();
            retVal = node->val;
            s.pop();
            node = node->right;
            while(node){
                s.push(node);
                node = node->left;
            }
            k--;
        }
        return retVal;
    }
};

int main(){
    //Solution s;
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
