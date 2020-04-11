import collections
import sys
from misc import TreeNode,ListNode 
import copy

class SolutionMid(object):
    def __init__(self):
        self.a = 0
    def insertionSortList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if head is None:
            return head
        retTail = retHead = ListNode(head.val)
        node = head.next
        while node:
            retNode = retHead
            while retNode.next and node.val > retNode.next.val:
                retNode = retNode.next

            if retNode == retHead and node.val < retNode.val: # head replacement
                tmpNode = retHead
                retHead = ListNode(node.val)
                retHead.next = tmpNode
            else:
                if retNode == retTail: # tail replacement
                    tmpNode = retTail
                    retTail = ListNode(node.val)
                    tmpNode.next = retTail
                else: # middle element replacement
                    tmpNode = retNode.next
                    retNode.next = ListNode(node.val)
                    retNode.next.next = tmpNode
            node = node.next
        return retHead
    class TreeLinkNode:
        def __init__(self, x):
            self.val = x
            self.left = None
            self.right = None
            self.next = None
    def connecthelper(self, node):
        if(node == None):
            return
        
        if node.left != None:
            if node.right != None:
                node.left.next = node.right
            else:
                node.left.next = None
        if node.next != None and node.right is not None:
            if node.next.left != None:
                node.right.next = node.next.left
            else:
                node.right.next = None
        self.connecthelper(node.left)
        self.connecthelper(node.right)


    def connect(self, root):
        # @param root, a tree link node
        # @return nothing
        if(root == None):
            return
        
        root.next = None
        if(root.left == None and root.right == None):
            return
        self.connecthelper(root)
        self.connecthelper(root)

    def grayCode(self, n):
        '''
        from up to down, then left to right
    
        0   1   11  110
                10  111
                    101
                    100
                
        start:      [0]
        i = 0:      [0, 1]
        i = 1:      [0, 1, 3, 2]
        i = 2:      [0, 1, 3, 2, 6, 7, 5, 4]
        '''
        results = [0]
        for i in range(n):
            ret = list(reversed(results))
            print ret
            results += [x + pow(2, i) for x in reversed(results)]
        return results

    def levelOrder(self, root):
        """
        :type root: TreeNode
        :rtype: List[List[int]]
        """
        retList = []
        q = collections.deque()
        q.append([0,root])

        while len(q) != 0:
            depth,tmpNode = q.popleft()
            if(len(retList) < depth + 1):
                retList.append([])
            retList[depth].append(tmpNode.val)
            if(tmpNode.left != None):
                q.append([depth + 1, tmpNode.left])
            if(tmpNode.right != None):
                q.append([depth + 1, tmpNode.right])

        return retList

    def sltHelper(self, list, left, right):
        retRoot = None
        if(left >= right):
            return retRoot
        m = left + (right - left) / 2
        retRoot = TreeNode(list[m])
        retRoot.left = self.sltHelper(list , left, m - 1)
        retRoot.right = self.sltHelper(list , m + 1, right)
        return retRoot

    def sortedListToBST(self, head):
        """
        :type head: ListNode
        :rtype: TreeNode
        """
        list = []
        while head != None :
            list.append(head.val)
            head = head.next
        if(len(list) == 0):
            return None
        return self.sltHelper(list,0,len(list) - 1)        

    def search(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: bool
        """
        if len(nums) == 0:
            return False
        if len(nums) == 1:
            if nums[0] == target:
                return True
            else:
                return False

        left,right = 0, len(nums) - 1
        while(left < right):
            print left,right
            m = left + (right - left) / 2
            if nums[m] == target:
                return True
            while(left < right and nums[right] == nums[m]):
                right -= 1
            if nums[right] <= target:
                if(nums[m + 1] <= target and target <= nums[right]): # in second ordered one
                    left = m + 1
                else:
                    right = m - 1
                
            else:
                if(nums[left] <= target and target <= nums[m]): # in first ordered one
                    right = m - 1
                else:
                    left = m + 1

        if(left == right):
            if nums[left] == target:
                return True
        return False
    def numDecodings(self, s):
        """
        :type s: str
        :rtype: int
        """
        if(len(s) == 0 or s[0] == '0'):
            return 0
        dp = [0] * (len(s) + 1)
        dp[0] = 1 # record the next one
        for i in range(1,len(s)):
            #print i, dp[i], dp[i - 1], dp[i - 2]
            if s[i] != '0' and int(s[i : i + 1]) > 0 and int(s[i: i + 1]) < 27:
                dp[i] += dp[i - 1]
            if s[i - 1] != '0' and int(s[i - 1: i + 1]) > 0 and int(s[i - 1: i + 1]) < 27:
                dp[i] += dp[i - 2] if i > 1 else dp[i - 1]
        # 2,2,6 ; 2,26 ; 22, 6
        return dp[len(s) - 1]

s = SolutionMid()