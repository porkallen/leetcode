#include "common.h"

class Solution {
public:

    bool isAtBoulder(int boundX, int boundY, int x, int y){
        return x == boundX - 1 || y == boundY - 1; 
    }
    void solve(vector<vector<char>>& board) {

        deque<pair<int, int>> dq;

        if(board.size() == 0)
            return;
        
        for(int i = 0; i < board.size(); i++){
            for(int j = 0; j < board[0].size(); j++){
                if(board[i][j] == 'O' && !isAtBoulder(board.size(), board[0].size(), i , j)){
                    dq.push_back({i,j});
                    while(!dq.empty()){
                        auto curNode = dq.front();
                        dq.pop_front();

                        if()
                    }
                
                    
                
                }
            
            }
        
        }
        
    }
};