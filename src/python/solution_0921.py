import collections
import sys
from misc import TreeNode,ListNode 
import copy

class Solution_0921(object):
    def __init__(self):
        self.a = 0
        t = TreeNode(1)
        t.left = TreeNode(2)
        t.right = TreeNode(3)
        t.left.right = TreeNode(4)
        print self.isNStraightHand([1,2,3,4,5],3)

    def isNStraightHand(self, hand, W):
        """
        :type hand: List[int]
        :type W: int
        :rtype: bool
        """
        if len(hand)%W != 0:
            return False
        if W == 1:
            return True

        while len(hand) != 0:
            hand = sorted(hand)
            head = hand[0]
            w = 0
            num = head
            while w < W:
                print num
                try:
                    hand.remove(num)
                    w += 1
                    num += 1
                except ValueError:
                    return False
        return True

    def longestMountain(self, A):
        """
        :type A: List[int]
        :rtype: int
        """
        i = 1
        retLen = 0
        while i < len(A):
            m,n = i,i
            length = 0
            while m >= 1 and n <= len(A) - 2:
                if(A[m] > A[m - 1] and A[n] > A[n + 1]):
                    m -= 1
                    n += 1
                    length += 2
                else:
                    break
            #print A[i],A[n],length, n,len(A) - 2
            if length > 0:
                while m >= 1:
                    if m >= 1:
                        if A[m] > A[m - 1]:  
                            length += 1
                            m -= 1
                        else:
                            break
                while n <= len(A) - 2:
                    if n <= len(A) - 2:
                        if A[n] > A[n + 1]:
                            length += 1
                            n += 1
                        else:
                            break
            retLen = max(retLen,length + 1)
            i += max(1,n - i + 1)
        return 0 if retLen == 1 else retLen
            


    def backspaceCompare(self, S, T):
        """
        :type S: str
        :type T: str
        :rtype: bool
        """
        s,t = '',''
        for i in range(len(S)):
            if S[i] == '#':
                if len(S) > 0:
                    s = s[:len(s) - 1]
            else:
                s += S[i]
        for i in range(len(T)):
            if T[i] == '#':
                if len(T) > 0:
                    t = t[:len(t) - 1]
            else:
                t += T[i]
        if s == t:
            return True
        return False
                
            
    def tree2strPre(self,t,s):
        if t == None:
            return
        if t.left == None and t.right == None:
            s += '(' + str(t.val) + ')'
            return
        s += '(' + str(t.val)
        if t.left == None and t.right != None:
            s += '()'
        self.tree2strPre(t.left,s)
        self.tree2strPre(t.right,s)
        s += ')'

    def tree2str(self, t):
        """
        :type t: TreeNode
        :rtype: str
        """
        s = []
        retStr = ''
        self.tree2strPre(t,s)
        for i in range(1,len(s) - 1):
            retStr += s[i]
        print retStr
    
    
    def findDuplicate(self, paths):
        """
        :type paths: List[str]
        :rtype: List[List[str]]
        """
        d = collections.defaultdict(list)
        for i in range(len(paths)):
            x = paths[i].split()
            path = x[0]
            for j in range(1,len(x)):
                z = x[j].split("(")
                d[z[1]] += [str(x[0] + '/' + z[0])]
        retList = []
        for key in d:
            if len(d[key]) > 1:
                retList.append(d[key])
        print retList
        return retList
    def canPlaceFlowers(self, flowerbed, n):
        """
        :type flowerbed: List[int]
        :type n: int
        :rtype: bool
        """
        if n == 0:
            return True
        if len(flowerbed) == 1:
            if n == 0 or n == 1:
                if flowerbed[0] == 0:
                    return True
                else:
                    return False
            else:
                return False
                
        num = 0
        for i in range(0,len(flowerbed)):
            if i == 0:
                if flowerbed[i] != 1 and flowerbed[i + 1] != 1:
                    flowerbed[i] = 1
                    num += 1
            elif i == len(flowerbed) - 1:
                if flowerbed[i] != 1 and flowerbed[i - 1] != 1:
                    flowerbed[i] = 1
                    num += 1
            elif flowerbed[i] != 1 and flowerbed[i - 1] != 1 and flowerbed[i + 1] != 1:
                flowerbed[i] = 1
                num += 1
        if num >= n:
            return True
            
        return False

a = Solution_0921()