#include "common.h"

class Solution {
public:

    void dfs(TreeNode* node ,vector<vector<int>> ret, int n){
        if(!node)
            return;

        ret[n].push_back(node->val);
        dfs(node->left, ret, n + 1);
        dfs(node->right, ret, n + 1);
    }
    vector<vector<int>> levelOrder(TreeNode* root) {
        vector<vector<int>> ret;
        dfs(root, ret, 0);

        cout << "ret empty ?: " << ret.empty() << "\n";
        for (auto it : ret)
        {
            for(auto it2 : it){
                cout << "val:" << it2 << "\n";
            }
        }
        return ret;
    }
};

int main(){
    Solution s;
    TreeNode root(10);
    root.left = new TreeNode(5);
    root.right = new TreeNode(12);
    s.levelOrder(&root);
    return 0;
}
