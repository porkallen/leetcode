#include "common.h"

class Solution {
public:
    
    
    int helper(vector<vector<char>>& grid, vector<vector<bool>>& visited, int x, int y){
        int ret = 0;
        if((grid[x][y] - '0' == 0) || visited[x][y])
            return 0;
        
        vector<pair<int, int>> dir = {{0,1}, {1,0}, {-1,0}, {0,-1}};
        visited[x][y] = true;
        
        ret = 1;
        for(int i = 0; i < dir.size(); i++){
            if((x + dir[i].first) < grid.size() && (y + dir[i].second) < grid[0].size())
                ret += helper(grid, visited, x + dir[i].first, y + dir[i].second);
        }
        return ret;
    }
    int numIslands(vector<vector<char>>& grid) {
        int ret = 0;
        if(grid.size() == 0)
            return 0;
        vector<vector<bool>> visited(grid.size(), vector<bool> (grid[0].size(), false));
        
        //printf("%d %d %d \n", visited.size(), visited[0].size(), visited[0][0]);
        
        for(int i = 0; i < grid.size(); i++){
            for(int j = 0; j < grid[0].size(); j++){
                //printf("i:%d j:%d %d %d\n", i, j, grid[i][j] - '0', visited[i][j]);
                if((grid[i][j] - '0' == 1) && !visited[i][j]){
                    ret += (helper(grid, visited, i, j) > 0 ? 1: 0);
                }
            }
        }
        
        return ret;
        
    }
};