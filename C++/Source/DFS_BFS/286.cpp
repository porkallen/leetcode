#include "common.h"
class Solution {
public:

    bool isValid(int boundX, int boundY, int x, int y){
        return x < boundX && x >= 0 && y < boundY && y >= 0;
    }
    void helper(vector<vector<int>> & rooms, int x, int y, int step){
        
        if(!isValid(rooms.size(), rooms[0].size(), x ,y))
            return;

        if(room[x][y] == (-1))
            return;

        vector<pair<int, int>> dir = {{1,0}, {0,1}, {-1,0}, {0,-1}};

        for(int i = 0; i < rooms.size(); i++){
            int nextX = x + dir[i].first, nextY = y + dir[i].second;
            if(nextX < rooms.size() && nextY < rooms[0].size()){
                if(rooms[nextX][nextY] == INT_MAX || step < rooms[nextX][nextY]){
                    rooms[nextX][nextY] = step;
                    helper(rooms, nextX, nextY, step + 1);
                }   
            }
        }
    }
    void wallsAndGates(vector<vector<int>>& rooms) {
        //-1 A wall or an obstacle.
        // 0 A gate.
        // INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
        for(int i = 0; i < rooms.size(); i++){
            for(int j = 0; j < rooms[0].size(); j++){
                if(rooms[i][j] == 0){
                    helper(rooms, i, j, 0);
                }
            }
        }
    }
};