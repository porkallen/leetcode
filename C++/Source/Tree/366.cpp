#include "common.h"

#define ENABLE_TREE_TEST_CASE 1

class Solution {
public:
    unordered_set<TreeNode *> s;
    pair<TreeNode*, int> helper(TreeNode* node, vector<vector<int>> &vectorPool){
        if(!node)
            return {nullptr, 0};
        
        auto left = helper(node->left, vectorPool);
        auto right = helper(node->right, vectorPool);

        int depth = (left.first && right.first) ? (max(left.second, right.second) + 1) : left.first ? (left.second + 1) : (right.second + 1); 
        if(vectorPool.size() < depth){
            vector<int> v;
            v.push_back(node->val);
            vectorPool.push_back(v);
        }
        else{
            vector<int> v = vectorPool[depth - 1];
            v.push_back(node->val);
            vectorPool[depth - 1] = v;
        }
        return {node, depth};
    }

    vector<vector<int>> findLeaves(TreeNode* root) {
        vector<vector<int>> ret;
        helper(root, ret);
        return ret;
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
    vector<vector<int>> v = s.findLeaves(&root);

    int idx = 0;
    for(auto it : v){
        cout<< "level: " << ++idx << "\n";
        for(auto it2 : it){
            cout << it2 << " ";
        }
        cout << "\n";
    }
    return 0;
}
