#include "common.h"

class Solution {
public:
    vector<vector<int>> zigzagLevelOrder(TreeNode* root) {
        deque<TreeNode *> dq;
        vector<vector<int>> retVector;
        if (!root)
            return retVector;

        dq.push_back(root);

        bool isLeftFirst = false;
        while (dq.size() != 0)
        {
            int sz = dq.size();
            vector<int> v;
            isLeftFirst = !isLeftFirst;
            for (int i = 0; i < sz; i++)
            {
                TreeNode *node = dq.front();
                dq.pop_front();
                v.push_back(node->val);
                if (node->left)
                    dq.push_back(node->left);
                if(node->right)
                    dq.push_back(node->right);
            }
            if(!isLeftFirst)
                reverse(v.begin(),v.end());

            retVector.push_back(v);
        }
        return retVector;
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
