import collections
import sys
from misc import TreeNode,ListNode 
import copy

class Solution1006(object):
    def __init__(self):
        self.a = 0
        print self.numMusicPlaylists(2,3,1)
    def kad(self,a): 
        n = len(a) 
        maxSum = -40000
        accSum = 0
        for i in range(n): 
            accSum = max(accSum + a[i] ,a[i])
            maxSum = max(maxSum,accSum)
        return maxSum
    def maxSubarraySumCircular(self,A):
        a = A[:]
        n = len(A)
        total = 0
        for num in A:
            total += num
        # Case 1 (w/o wrapping): get the maximum sum 
        num1 = self.kad(A)
        # Case 2 (w/ wrapping): get the min sum of rest items
        for i in range(len(A)):
            a[i] = A[i] * (-1)
        num2 = self.kad(a[1:-1])
        return max(num1,total + num2)
        

    class CBTInserter(object):
        def __init__(self, root):
            """
            :type root: TreeNode
            """
            self.root = root
        def insert(self, v):
            """
            :type v: int
            :rtype: int
            """
            if self.root is None:
                self.root = TreeNode(v)
                return None
            q = collections.deque()
            q.append(self.root)
            retNode = None
            while len(q) > 0:
                retNode = node = q.popleft()
                if node.left is not None:
                    node.left = TreeNode(v)
                    break
                elif node.right is not None:
                    node.right = TreeNode(v)
                    break
                else:
                    q.append(node.left)
                    q.append(node.right)
            return retNode

        def get_root(self):
            """
            :rtype: TreeNode
            """
            return self.root
    def reverseOnlyLetters(self, S):
        """
        :type S: str
        :rtype: str
        """
        s = list(S)
        retS = list(S)
        sIdx,retSIdx = 0,len(S) - 1
        while sIdx < len(s) and retSIdx >=0:
            while sIdx < len(s) and s[sIdx].isalpha() is not True:
                sIdx += 1
            while retSIdx >=0 and retS[retSIdx].isalpha() is not True:
                retSIdx -= 1
            if sIdx < len(s) and retSIdx >= 0:
                retS[retSIdx] = s[sIdx]
                retSIdx -= 1
                sIdx += 1
        return ''.join(retS)
a = Solution1006()
                
                
