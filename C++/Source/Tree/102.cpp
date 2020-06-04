#include "common.h"

class Solution {
public:

    void bfs(TreeNode* node ,vector<vector<int>> &ret){
        deque<TreeNode *> dq;
        int level = 0;
        if(!node)
            return;

        dq.push_back(node);
        while (!dq.empty())
        {
            ret.push_back({});
            int num = dq.size();
            for (int i = 0; i < num; i++)
            {
                TreeNode *t = dq.front();
                dq.pop_front();
                ret[level].push_back(t->val);
                if (t->left)
                    dq.push_back(t->left);
                if(t->right)
                    dq.push_back(t->right);
            }
            level++;
        }
    }

    void dfs(TreeNode* node ,vector<vector<int>> &ret, int n){
        if(!node)
            return;
        if(ret.size() <= n)
            ret.push_back({});
        ret[n].push_back(node->val);
        dfs(node->left, ret, n + 1);
        dfs(node->right, ret, n + 1);
    }
    vector<vector<int>> levelOrder(TreeNode* root) {
        vector<vector<int>> ret;
        //dfs(root, ret, 0);
        bfs(root, ret);

        for (int i = 0; i < ret.size(); i++){
            cout << "ith:" << i << "\n";
            for (auto it : ret[i])
            {
                cout << it << " ";
            }
            cout << "\n";
        }
            return ret;
    }
};

int main(){
    Solution s;
    TreeNode root(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right->left = new TreeNode(15);
    root.right->right = new TreeNode(7);
    s.levelOrder(&root);

    return 0;
}
