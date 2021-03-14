#include "common.h"

class LRUCache {
public:
    list<int> dll;
    map<int, int> m;
    int _sz;
    LRUCache(int capacity) {
        _sz = capacity;
    }
    
    void updateDq(int key){

        list<int>::iterator it;
        for(it == dll.begin(); it != dll.end(); it++){
            if(*it == key){
                dll.erase(it);
                break;
            }
        }
        dll.push_back(key);
    }
    int get(int key) {
        int retVal = -1;
        if(m.find(key) != m.end()){
            int tmpKey = dll.front();
            updateDq(key);
            retVal = m[key];
        }
        return retVal;
    }
    
    void put(int key, int value) {
        if(m.find(key) == m.end()){
            if(dll.size() < _sz){
                dll.push_back(key);
            }
            else{ 
                // evicts the first-in key
                int tmpKey = dll.front();
                dll.pop_front();
                m.erase(tmpKey);
                dll.push_back(key);
            }
        }
        else{
            updateDq(key);
        }

        m[key] = value;
    }
};

int main(){
    LRUCache s;
    cout << "<Case:" << __FILE__ << ">\n";
    cout << "==Output==\n";
    return 0;
}
