import collections
import sys
from misc import TreeNode,ListNode 
from operator import itemgetter
import copy

class Solution_1110(object):
    def __init__(self):
        self.a = 0
        print self.distinctSubseqII("bebb")
    def distinctSubseqII(self, S):
        """
        :type S: str
        :rtype: int
        """
        MOD = 10 ** 9 + 7
        dp = [0] * (len(S) + 1)
        dp[0] = 0
        chNum = [0] * 26
        for i in range(1,len(S) + 1):
            dp[i] = 2 * dp[i - 1] + 1 - chNum[ord(S[i - 1]) - ord('a')]
            chNum[ord(S[i - 1]) - ord('a')] = dp[i- 1] + 1
        return dp[len(S)] % MOD
    def minAreaRect(self, points):
        """
        :type points: List[List[int]]
        :rtype: int
        """
        ret = 0x7fffffff
        dp = collections.defaultdict(list)
        for i in range(len(points)):
            dp[points[i][0]].append(points[i][1])
        
        for i in dp:
            dp[i].sort()

        for i in dp:      
            for j in dp:
                if i != j:
                    arrs = []
                    for k in dp[i]:
                        if k in dp[j]:
                            arrs.append(k);
                    yLen = 0x7ffffffff
                    for l in range(len(arrs) - 1):
                        yLen = min(yLen,arrs[l + 1] - arrs[l])
                    if yLen is not 0x7ffffffff:
                        ret = min(ret, abs(i - j) * yLen)
        return 0 if ret == 0x7fffffff else ret
            
    def reorderLogFiles(self, logs):
        """
        :type logs: List[str]
        :rtype: List[str]
        """
        if logs == None:
            return logs
        logs1,logs2 = [],[]
        for i in range(len(logs)):
            word = logs[i].split()
            if word[1].isalpha():
                logs1.append(logs[i])
            else:
                logs2.append(logs[i])
        logs1 = sorted(logs1,key = lambda x: (" ".join(x.split()[1:])))
        return logs1 + logs2

    def rsbTra(self,node,L,R):
        sum = 0
        if(node == None):
            return 0
        if(node.val <= R and node.val >= L):
            return node.val
        sum += self.rsbTra(node.left,L,R)
        sum += self.rsbTra(node.right,L,R)
        return sum
    def rangeSumBST(self, root, L, R):
        """
        :type root: TreeNode
        :type L: int
        :type R: int
        :rtype: int
        """
        if(root == None):
            return 0
        return self.rsbTra(root,L,R)




a = Solution_1110()