#include "common.h"
#define ENABLE_TREE_TEST_CASE 1
class Codec {
public:

    // Encodes a tree to a single string.
    string serialize(TreeNode* root) {
        string s = "[";
        deque<TreeNode *> dq;
        TreeNode dummyNode(INT_MIN);

        if (!root){
            s += "]";
            return s;
        }
        dq.push_back(root);

        int lastCharIdx = 1;
        while (dq.size() != 0)
        {
            int sz = dq.size();
            for (int i = 0; i < sz; i++){
                TreeNode *node = dq.front();
                dq.pop_front();
                
                if(node == &dummyNode){
                    s += "null,";
                }
                else{
                    s += to_string(node->val) + ",";
                    lastCharIdx = s.size() - 1;
                    if (node->left)
                    {
                        dq.push_back(node->left);
                    }
                    else{
                        dq.push_back(&dummyNode);
                    }

                    if(node->right){
                        dq.push_back(node->right);
                    }
                    else{
                        dq.push_back(&dummyNode);
                    }
                }
            }
        }
        s = s.substr(0, lastCharIdx);
        s += "]";
        return s;
    }

    // Decodes your encoded data to tree.
    TreeNode *deserialize(string data)
    {
        TreeNode *root = nullptr;
        deque<TreeNode *> dq;
        if (data.size() == 0 || data == "[]")
        {
            return root;
        }
        data = data.substr(1);
        int val = stoi(data.substr(0, data.find(",")));
        root = new TreeNode(val);

        if(data.find(",") == string::npos)
            return root;

        data = data.substr(data.find(",") + 1);
        dq.push_back(root);
        while (dq.size() > 0){
            int sz = dq.size();
            for (int i = 0; i < sz; i++){
                TreeNode *node = dq.front();
                dq.pop_front();

                string nodeValStr = data.substr(0, data.find(","));
                int tmpVal = 0;
                if (nodeValStr.size() != 0 && nodeValStr != "null")
                {
                    int tmpVal = stoi(nodeValStr);
                    node->left = new TreeNode(tmpVal);
                    dq.push_back(node->left);
                }

                if(data.find(",") == string::npos){
                    dq.clear();
                    break;
                }

                data = data.substr(data.find(",") + 1);
                nodeValStr = data.substr(0, data.find(","));
                if(nodeValStr.size() != 0 && nodeValStr != "null"){
                    tmpVal = stoi(nodeValStr);
                    node->right = new TreeNode(tmpVal);
                    dq.push_back(node->right);
                }
                printf("xnode:%d left:%d right:%d data:%c idx:%x\n",node->val, node->left? node->left->val: 0, node->right? node->right->val: 0, data[0], data.find(","));

                if (data.find(",") == string::npos){
                    dq.clear();
                    break;
                }
                else
                    data = data.substr(data.find(",") + 1);
            }
            
        }
            return root;
    }
};
int main(){
    Codec c;
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
    cout << "String:" << c.serialize(&root) << "\n";
    c.deserialize("[3,9,20,null,2,15,7]");
    return 0;
}
