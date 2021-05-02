#include "common.h"

#pragma Sparse Matrix
/*
设计一个数据结构能存储稀疏矩阵，并且实现以下方法：
set(x, y, val): 给定坐标，赋值
get(x,y)：返回给定坐标的值
add(matrix)：两个稀疏矩阵相加
我是用哈希表存储非零的值key是坐标，value是非零值。
实现add的时候要注意两个数相加可能为零，这种情况要从哈希表里把这个位置删除。
*/
template<typename T>
class SolutionSparseMatrix{
private:
    map<int, map<int, T>> dict;
    
public:
    void dump(void){
        printf("===================\n");
        for(auto it : dict){ // col 
            for(auto it2 : it.second){ // row
                printf("(%d,%d) = %d \n", it.first, it2.first, it2.second);
            }
        }
    }
    void set(int col, int row, T val){
        dict[col][row] += val;

        if(dict[col][row] == 0){
            dict[col].erase(row);
            dict.erase(col);
        }
    }
    T get(int col, int row){
        if(dict.find(col) != dict.end() && dict[col].find(row) != dict.end()){
            return dict[col][row];
        }
        else
            return (-1);
    }
    bool add(const map<int, map<int, T>>& m){
        if(m.size() != dict.size() || m.begin()->second.size() != dict.begin()->second.size())
            return false;
        
        for(auto it : m){
            for(auto it2 : it.second){
            
                dict[it.first][it2.first] += it2.second;
                if(dict[it.first][it2.first] == 0){
                    dict[it.first].erase(it2.first);
                    dict.erase(it.first);
                }
            }
        }
        return true;
    }
    const map<int, map<int, T>>& getData(void){
        return dict;
    }
    void setData(map<int, map<int, T>>& m){
        dict = m;
    }
    SolutionSparseMatrix multipulate(const map<int, map<int, T>>& m){
        // 2 x 3 | 3 x 2

        map<int, map<int, T>> ret;
        SolutionSparseMatrix s;
        if(dict.begin()->second.size() != m.begin()->second.size())
            return s;
        
        for(auto it : dict){ // col
            for(auto it2 : it.second){ // row
                if(m.find(it2.first) != m.end()){ // if dict's row == m's col
                    for(auto it3 : m.find(it2.first)->second){
                        ret[it.first][it3.first] += dict[it.first][it2.first] * m.at(it2.first).at(it3.first);
                    }
                }
            }
        }
        s.setData(ret);
        return s;
    };

};
#pragma TODO: Find the smallest comment element in n sorted arrays 

class SolutionFindSmallestCommentElement{
public:
    int findSmallestCommonElements(vector<vector<int>> sortedArrays){
    
        if(sortedArrays.size() == 1)
            return sortedArrays[0][0];
        
        for(auto it : sortedArrays[0]){ // Use the first array
            bool found = true;
            for(int i = 1; i < sortedArrays.size(); i++){
                if(find(sortedArrays[i].begin(), sortedArrays[i].end(), it) == sortedArrays[i].end()){
                    found = false;
                    break;
                }
            }
            if(found)
                return it;
        }
        return -1;
    }
};
#pragma TODO: Shuffle a card deck
//https://www.cplusplus.com/reference/cstdlib/rand/
class MyDeck{
    struct Card{
        int num;
        int type;
    };
};

#pragma Queue Implementation
template<typename T>
class MyQueue{
private:
    vector<T> _v;
    int _sz;
    int _front, _back;
public:
    MyQueue(int sz){
        _sz = sz;
        _v.resize(sz);
        _front = _back = 0;
    };
    T front(void){
        return _v[_front];
    }
    bool isFull(void){
        return (_front + 1) % _sz == _back;
    }

    bool isEmpty(void){
        return _front == _back;
    }

    bool push(T val){
        if(isFull())
            return false;    
        
        _front = (_front + 1) % _sz; // Start filling element since idx 1, so one memory block is sacraficed. 
        _v[_front] = val;
        return true;
    }
    bool pop(){
        if(isEmpty())
            return false;
        _back = (_back + 1) % _sz;
        return true;
    }
};
#pragma Thread with Condition Variable
struct ThreadPool{
    mutex m;
    condition_variable cv;
    bool ready = false;

    void thread1(void){
        unique_lock<mutex> u(m);
        cv.wait(u, [this](){ return ready;});
        cout << endl << "t1:" << ready << endl;
    }

    void thread2(void){
        unique_lock<mutex> u(m);
        ready = true;
        cout << endl << "t2" << endl;
        cv.notify_one();

        std::unique_lock<std::mutex> lck; 
    }
};

class CondVariablePractice{

    ThreadPool tp;
    
    thread t1, t2;

public:
    CondVariablePractice(): t1(&ThreadPool::thread1, &tp), t2(&ThreadPool::thread2, &tp){
        cout << "Constructor" << endl;
    }
    #if 1
    ~CondVariablePractice(){
        cout << "Deconstructor" << endl;
        if(t1.joinable())
            t1.join();
        if(t2.joinable())
            t2.join();
    }
    #endif

};
#pragma C level Linked List

struct MyNode{
public:
    int val;
    MyNode* next;
};

MyNode* nodeInit(int sz){
    MyNode* head = new MyNode;
    MyNode* iter = head;
    head->val = 0;

    for(int i = 1; i < sz; i++){
        iter->next = new MyNode;
        iter->next->val = i;
        iter = iter->next;
    }
    return head;
}
void allNodesDump(MyNode* head){

    while(head != nullptr){
        printf("%d ",head->val);
        head = head->next;
    }
    printf("\n");
}

MyNode* reverse(MyNode* head){
    MyNode* dummy = new MyNode;
    MyNode *pre, *cur, *tail;
    dummy->next = head;
    pre = dummy; cur = pre->next; tail = cur->next;

    while(tail != nullptr){
        cur->next = tail->next;
        tail->next = pre->next;
        pre->next = tail;
        tail = cur->next;
    }
    return dummy->next;
}

MyNode* removeElements(MyNode* head, int val) {
    MyNode* dummy = new MyNode;
    MyNode *pre, *cur, *nex;
    dummy->next = head;
    pre = dummy; cur = head; nex = cur->next;

    while(cur != nullptr){
        if(cur->val == val){
            pre->next = cur->next;
            delete cur;
        }
        else{
            pre = cur;
        }
        cur = nex;
        nex = nex ? nex->next : nullptr;
    }
    return dummy->next;
}

#pragma Shared Ptr
template<typename T>
class SharedPtr{
private: 
    
    mutex m;
    T* _ptr;
    unsigned int* refCnt;
public:
    
    SharedPtr(T val){
        _ptr = static_cast<T *>(malloc(sizeof(T)));
        refCnt = malloc(sizeof(unsigned int));
        *static_cast<T *>(_ptr) = val;
        *refCnt = 0;
    }
    SharedPtr(SharedPtr<T>& rhs){
        refCnt = rhs.getRefcountPtr();
        _ptr = rhs.get();
        *refCnt++;
    }
    ~SharedPtr() // destructor
	{
        cout << "Destructor" << std::endl;
        *refCnt--;
        if (_ptr != nullptr && *refCnt == 0){
            cout << "Destructor -- Free" << std::endl;
			free(_ptr);
            free(refCnt);
        }
	}
#if 0
    //For unique_ptr
    // copy constructor is deleted
    SharedPtr(const SharedPtr& obj) = delete; 
    // copy assignment is deleted
    SharedPtr& operator=(const SharedPtr& obj) = delete; 
#endif
    SharedPtr<T>& operator= (const SharedPtr<T>& rhs){
        *refCnt++;
        _ptr = rhs.get();
        return *this;
    }
    SharedPtr<T>* operator->() // deferencing arrow operator
	{
		return _ptr;
	}
    SharedPtr<T>& makeShared (T val){
        void* p = malloc(sizeof(SharedPtr) + sizeof(T));
        *static_cast<SharedPtr *>(p)->_ptr = val;
        refCnt = static_cast<int *>(p) + sizeof(T);
        return *this;
    }
    T* get(void){
        return _ptr;
    }
    int* getRefcountPtr(void){
        return refCnt;
    }
};
#pragma Memory Pool
class MemPool {
private:
    int _minBlockSz = sizeof(int);
    int _blockNum = 0;
    static constexpr int MAX_SIZE = 10;
    void *headP, *currP;
    struct MemBlock{
        MemBlock* next;
    };
public:
    MemPool();
    MemPool(int blockNum);
    void* memAlloc();
    void memFree(void *p);
    ~MemPool();
};

MemPool::MemPool(int blockNum){

    _blockNum = blockNum;
    currP = headP = new int[blockNum * _minBlockSz];

    MemBlock* blockP = static_cast<MemBlock *>(headP);
    for(int i = 1; i < blockNum; i++){
        blockP->next = blockP + _minBlockSz;
        blockP = blockP->next; 
    }
}
void* MemPool::memAlloc(){
    void* retP = currP;
    currP = static_cast<MemBlock *>(currP)->next;
    return retP;
    
}
void MemPool::memFree(void *p){
    static_cast<MemBlock *>(p)->next = static_cast<MemBlock *>(currP);
    currP = p;
}

#pragma BitMask
class SolutionBM {
public:
    int getXORSum(vector<int>& arr1, vector<int>& arr2) {
        
        vector<int> bitmask(32, 0);
        
        for(auto it : arr2){
            for(int i = 0; i < 32; i++){
                if((it >> i) & 0x1)
                    bitmask[i]++;
            }
        }
        for(auto it : bitmask)
            cout << " " << it;
        cout << endl;
        return 0;
    }
    vector<int> getSubSum(vector<int> v, int n){
    
        vector<int> allSum;
        for(int i = 0; i < (1 << n); i++){
        
            int sum = 0;
            for(int j = 0; j < n; j++){
                if(i & (1 << j)){
                    sum += v[j];
                }
            }
            allSum.push_back(sum);
        }
        return allSum;
    }
};

#pragma Priority Queue with comparator
// TODO
class myComparator
{
public:
    int operator() (const list<int> list1, const list<int> list2){
        return list1.front() > list2.front();
    }
};
class SolutionPQ{

    void pqPractice(list<int> list1, list<int> list2){
        priority_queue<int, list<int>, myComparator> pq;
    }


};
#pragma HashMap (Unordered Map with key pair)
class SolutionHM {
public:
    struct Comparator {
        int operator()(const vector<int> &V) const {
            long hash = V.size();
            for(auto &i : V) {
                hash ^= i + 0x9e37 + (hash << 6) + (hash >> 2);
            }
            return hash;
        }
    };
    vector<vector<int>> tasks;
    void foo(){
        tasks = {{6,10},{7,10},{7,12},{7,5},{7,4},{7,2}};

        unordered_map<vector<int>, int, Comparator> hashMap;
        
        for(int i = 0; i < tasks.size(); i++){
            hashMap[tasks[i]] = i;
        }
        sort(tasks.begin(), tasks.end(),[](vector<int>a, vector<int>b){
        
            return a[0] <= b[0] && a[1] <= b[1];
        });

        for(auto it : tasks)
            printf("%d,%d \n", it[0], it[1]);
    }
};

#pragma Main
int main(void){

#ifdef C_LEVEL_LINKED_LIST
    MyNode* head = nodeInit(10);
    head = removeElements(head, 9);
    allNodesDump(head);
    head = reverse(head);
    allNodesDump(head);
#endif

    SolutionSparseMatrix<int> s;
    s.set(0,0,1);
    s.set(1,0,-1);
    s.set(1,2,3);
    s.dump();

    SolutionSparseMatrix<int> s1;
    s1.set(0,0,7);
    s1.set(2,2,1);
    s1.dump();

    auto ret = s.multipulate(s1.getData());
    ret.dump();
    cout << (long)1e9 << endl;

    std::set<int> myset;
    std::set<int>::iterator itlow,itup;

    for (int i=1; i<10; i++) myset.insert(i*10); // 10 20 30 40 50 60 70 80 90

    itlow=myset.lower_bound (25);                //       ^
    itup=myset.upper_bound (60);                 //                   ^

    myset.erase(itlow,itup);                     // 10 20 70 80 90

    std::cout << "myset contains:";
    for (std::set<int>::iterator it=myset.begin(); it!=myset.end(); ++it)
        std::cout << ' ' << *it;
    std::cout << '\n';

    vector<vector<int>> v = {{1,4},{2,4},{3,6},{4,4}};
    sort(v.begin(), v.end());

    for(auto it : v)
        printf(" (%d,%d) \n",it[0], it[1]);
    return 0;
}
