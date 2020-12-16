#include "common.h"

class Solution {
public:

    unordered_set<TreeNode*> nodeSet; 
    void helper(TreeNode* node){
        if(!node)
            return;
        
        if(nodeSet.size() == 0){
            nodeSet.insert(node);
        }
        else{
            bool isContigous = false;
            for(auto it : nodeSet){
                if(it == node || it->left = node || it->right == node)
                {
                    isContigous = true;
                    break;
                }
            }
            if(!isContigous)
            {
                nodeSet.insert(node);
            }
        }
        helper();
        
    
    }
    int rob(TreeNode* root) {
        
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
