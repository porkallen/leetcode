#include "common.h"

class Solution {
public:
    string simplifyPath(string path) {
        stack<char> s;
        string retStr;

        if(path.length() == 0){
            return path;
        }

        s.push(path[0]);

        for(auto c:path){
         

        }


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
