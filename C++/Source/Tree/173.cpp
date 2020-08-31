#include "common.h"

class BSTIterator {
public:
    stack<TreeNode *> s;

    BSTIterator(TreeNode *root)
    {
        TreeNode *tmpNode = root;
        while(tmpNode){
            s.push(tmpNode);
            tmpNode = tmpNode->left;
        }
    }

    /** @return the next smallest number */
    int next() {
        TreeNode *tmpNode = s.top();
        int retVal = tmpNode->val;
        
        s.pop();
        tmpNode = tmpNode->right;
        while(tmpNode){
            s.push(tmpNode);
            tmpNode = tmpNode->left;
        }
        return retVal;
    }
    
    /** @return whether we have a next smallest number */
    bool hasNext() {
        return s.size() != 0;
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
