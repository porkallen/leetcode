#include "common.h"

class Solution {
public:

    bool isTransformable(string str1, string str2){

        if(str1.length() != str2.length())
            return false;
        
        int diff = 0;
        for(int i = 0; i < str1.length(); i++){
            if(str1[i] != str2[i])
                diff++;
        }
        return (diff <= 1) ? true : false;
    }
    int ladderLength(string beginWord, string endWord, vector<string>& wordList) {

        deque<string> dq;
        int ret = 0;
        unordered_set<string> us(wordList.begin(), wordList.end());
        dq.push_back(beginWord);

        while(!dq.empty()){
        
            int sz = dq.size();
            for(int i = 0; i < sz; i ++){
                string str = dq.front();
                dq.pop_front();

                if(str == endWord){
                    break;
                }
                us.erase(str);
                for(auto it : us){
                    if(isTransformable(str, it)){
                        dq.push_back(it);
                    }
                }
                ret++;
            }
        }
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
    return 0;
}
