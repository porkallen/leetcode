#include "common.h"

class Solution {
public:
    vector<int> asteroidCollision(vector<int>& asteroids) {

        vector<int> ret;
        if(asteroids.size() == 0)
            return ret;

        ret.push_back(asteroids[0]);
        for(int i = 1; i < asteroids.size(); i++){

            int isSelected = true;
            while(ret.size()){
                if(ret[ret.size() - 1] * asteroids[i] < 0 && ret[ret.size() - 1] > 0) { // different direction
                    if(ret[ret.size() - 1] < (-asteroids[i])){ // the coming asteroid is larger than the last element
                        ret.pop_back();
                    }
                    else if(ret[ret.size() - 1] == (-asteroids[i])){
                        ret.pop_back();
                        isSelected = false;
                        break;
                    }
                    else{ // the coming element is smaller than the last one. Do nothing and skip.
                        isSelected = false;
                        break;
                    }
                }
                else{
                    break;
                }
            }
                            
            if(isSelected){
                ret.push_back(asteroids[i]);
            }
        }
        return ret;
    }
};
