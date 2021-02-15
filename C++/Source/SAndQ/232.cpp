#include <common.h>

class MyQueue {
public:
    /** Initialize your data structure here. */
    stack<int> s1;
    stack<int> s2;
    MyQueue() {
        
    }
    
    /** Push element x to the back of queue. */
    void push(int x) {
        s1.push(x);
    }
    
    int popOrReturnPeek(bool isPop){
        int ret = 0;
        if(s2.empty()){
            while(!s1.empty()){
                int tmp = s1.top();
                s1.pop();
                s2.push(tmp);
            }
        }
        ret = s2.top();
        if(isPop)
            s2.pop();
        return ret;
    }
    /** Removes the element from in front of queue and returns that element. */
    int pop() {
        return popOrReturnPeek(true);
    }
    
    /** Get the front element. */
    int peek() {
        return popOrReturnPeek(false);
    }
    
    /** Returns whether the queue is empty. */
    bool empty() {
        return s1.empty() && s2.empty();
    }
};