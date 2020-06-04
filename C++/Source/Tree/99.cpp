#include "common.h"

class Solution {
public:
    
    TreeNode *prevNode, *firstNode, *secondNode;
    void inorder(TreeNode *node){
        
        if(!node)
            return;
        
        inorder(node->left);
        
        if(!firstNode && prevNode && prevNode->val >= node->val)
            firstNode = prevNode;
        
        if(firstNode && prevNode && prevNode->val >= node->val)
            secondNode = node;
        
        prevNode = node;
        inorder(node->right);
    }
    void recoverTree(TreeNode* root) {
        
        prevNode = firstNode = secondNode = nullptr;
        inorder(root);
        
        if(firstNode && secondNode)
            swap(firstNode->val, secondNode->val);
    }
};

int main(){
    Solution s;
    TreeNode root(10);
    root.left = new TreeNode(5);
    root.right = new TreeNode(12);
    s.recoverTree(&root);
    return 0;
}