#include <iostream>
#include <future>
#include <map>
#include <vector>
#include <stack>
#include <queue>
#include <deque>
#include <set> 
#include <string> 
#include <unordered_set>
#include <list> 
#include <iterator>
#include <algorithm> 
#include <utility> 
#include <tuple>
using namespace std;

 struct TreeNode {
     int val;
     TreeNode *left;
     TreeNode *right;
     TreeNode() : val(0), left(nullptr), right(nullptr) {}
     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};