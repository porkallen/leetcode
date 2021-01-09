#include "common.h"

class Solution {
public:
    
    Node* connect(Node* root) {
        // [2,1,3,0,7,9,1,2,null,1,0,null,null,8,8,null,null,null,null,7]
        Node *head, *cur, *prev;
        head = cur = prev = nullptr;

        cur = root;
        while(cur != nullptr){

            //traverse the same level, record the head of next level (for next loop), and the previous node in next level.
            while(cur != nullptr){
                if(cur->left){
                    if(!prev){
                        prev = head = cur->left;
                    }
                    else{
                        prev->next = cur->left;
                        prev = prev->next;
                    }
                }

                if(cur->right){
                    if(!prev){
                        prev = head = cur->right;
                    }
                    else{
                        prev->next = cur->right;
                        prev = prev->next;
                    }
                }
                cur = cur->next;
            }
            cur = head;
            head = prev = nullptr;
        }
        return root;
    }
};