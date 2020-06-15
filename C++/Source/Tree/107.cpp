#include "common.h"

class Solution {
public:

    vector<vector<int>> levelOrderBottom(TreeNode* root) {
        vector<vector<int>> ret;
        deque<TreeNode *> q;
        if(!root)
            return ret;
        q.push_back(root);

        int level = 0;
        while (!q.empty())
        {
            int sz = q.size();
            ret.push_back({});
            for (int i = 0; i < sz; i++)
            {
                TreeNode *node = q.front();
                q.pop_front();
                ret[level].push_back(node->val);
                if(node->left)
                    q.push_back(node->left);
                if(node->right)
                    q.push_back(node->right);
            }
            level++;
        }
        return vector<vector<int> > (ret.rbegin(), ret.rend());;
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
