#include "common.h"
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:

    vector<TreeNode*> helper(int idx, int n){

        if(idx < 1 || idx > n){
            return;
        }
        vector<TreeNode*> ret;


        for(int i = 0; i <= n; i++){
            TreeNode node(i);

            vector<TreeNode *> vLeft = helper(0, i);
            vector<TreeNode *> vRight = helper(i + 1, n);

            for(auto it : vLeft){
                for(auto it2 : vRight){
                    node.left = it;
                    node.right = it2;
                    ret.push_back(&node);
                }
            }
        }
        return ret;
    } 
    vector<TreeNode*> generateTrees(int n) {

        vector<TreeNode*> ret;
        if(n == 1){
            ret.push_back(&TreeNode(1));
            return ret;
        }
        return helper(1, n);
    }
};