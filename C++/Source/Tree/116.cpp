#include "common.h"

#define ENABLE_TREE_TEST_CASE 1

class Solution {
public:

    void iterHelper(Node* root){

        Node *node = root;
        while(node->left){
        
            while(node->next){
                node->right->next = node->next->left;
            }
            node->left->next = node->right;
            node = node->left;
        }

    }
    void recursiveHelper(Node* node){
        if(!node || !node->left)
            return;
        
        if(node->next){
            node->right->next = node->next->left;
        }
        node->left->next = node->right;
        helper(node->left);
        helper(node->right);
    }

    void recursiveHelper(Node* node){
        if(!node || !node->left)
            return;
        
        if(node->next){
            node->right->next = node->next->left;
        }
        node->left->next = node->right;
        recursiveHelper(node->next);
        recursiveHelper(node->left);
    }
    Node* connect(Node* root) {
        if(!root || (!root->left && root->right))
            return root;
        recursiveHelper(root);
        return root;
    }
};

int main(){
    Solution s;
    cout << "<Case:" << __FILE__ << ">\n";
    cout << "==Output==\n";
#if ENABLE_TREE_TEST_CASE
    Node root(3);
    root.left = new Node(9);
    root.right = new Node(20);
    root.right->left = new Node(15);
    root.right->right = new Node(7);
    root.left->left = new Node(8);
    root.left->right = new Node(2);
    /*     3 
          / \
        9    20
         \   / \
          2 15  7
    */
#endif
    s.connect(&root);

    cout<< "final value:" << root.left->right->next->val;
    return 0;
}
