import collections
import sys
from misc import TreeNode,ListNode 
import copy

class SolutionEasy(object):
    def __init__(self):
        self.a = 0
        print self.search([2,5,6,0,0,1,2],2)

 

    def deleteDuplicates(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if head == None or head.next == None:
            return head
        dummy = ListNode(-1)
        dummy.next = head
        prev, node = dummy, head.next
        target = head.val
        isDup = False
        while node != None:
            if(target == node.val):
                prev.next = node.next
                isDup = True
            else:
                if isDup is not True:
                    prev = prev.next
                else:
                    isDup = False
            target = node.val
            node = node.next
        return dummy.next


    class TwoSum(object):
        def __init__(self):
            """
            Initialize your data structure here.
            """
            self.hm = collections.defaultdict(int)
            
        def add(self, number):
            """
            Add the number to an internal data structure..
            :type number: int
            :rtype: void
            """
            self.hm[number] += 1
            
        def find(self, value):
            """
            Find if there exists any pair of numbers which sum is equal to the value.
            :type value: int
            :rtype: bool
            """
            for k,v in self.hm.iteritems():
                if value == k * 2 :
                    if self.hm.get(value - k) >= 2:
                        return True
                else:
                    if self.hm.get(value - k) > 0:
                        return True
            return False


    class MinStack(object):
        
        def __init__(self):
            """
            initialize your data structure here.
            """
            self.stack = []
            self.curMin = sys.maxsize

        def push(self, x):
            """
            :type x: int
            :rtype: void
            """
            if x < self.curMin:
                self.curMin = x 
            self.stack.append([x,self.curMin])

        def pop(self):
            """
            :rtype: void
            """
            self.stack.pop()            

        def top(self):
            """
            :rtype: int
            """
            return None if len(self.stack) == 0 else self.stack[len(self.stack) - 1][0]
            
        def getMin(self):
            """
            :rtype: int
            """
            return None if len(self.stack) == 0 else self.stack[len(self.stack) - 1][1]


    def getIntersectionNode(self, headA, headB):
        """
        :type headA, headB: ListNode
        :rtype: ListNode
        """
        if(headA == None or headB == None):
            return None
        countA,countB = 0,0
        nodeA,nodeB = headA,headB
        while nodeA != None or nodeB != None:
            if nodeA != None:
                countA += 1
            if nodeB != None:
                countB += 1
        largeList,smallList,large,small = None,None,0,0
        if countB > countA:
            largeList,smallList = headB,headA
            large,small = countB, countA
        else:
            largeList,smallList = headA,headB
            large,small = countA,countB

        while largeList != None and large != 0:
            if(large <= small):
                if largeList == smallList:
                    return largeList
                smallList = smallList.next
                small -= 1

            largeList = largeList.next
            large -= 1

        return None

    def maxProfit2(self, prices):
        """
        :type prices: List[int]
        :rtype: int
        """
        if(len(prices) == 0):
            return 0
        retVal = 0
        target = prices[0]
        diff = 0
        for i in range(1,len(prices)):
            if target < prices[i]:
                diff += prices[i] - target
            else:
                if diff > 0:
                    retVal += diff
                    diff = 0
            target = prices[i]
        if diff > 0:
            retVal += diff
        return retVal
    def maxProfit(self, prices):
        """
        :type prices: List[int]
        :rtype: int
        """
        target = prices[0]
        maxdiff = 0
        for i in range(1,len(prices)):
            diff = prices[i] - target 
            maxdiff = max(maxdiff,diff)
            if(diff < 0):
                target = prices[i]
        return maxdiff

            
    def trailingZeroes(self, n):
        """
        :type n: int
        :rtype: int
        """
        retNum = 0
        if n < 5:
            return 0
        while(n != 0):
            retNum += n/5
            n /= 5
        return retNum

    def majorityElement(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if(len(nums) == 0):
            return 0
        
        ret = nums[0]
        rep_cnt = 1
        for i in range(1,len(nums)):
            if(ret == nums[i]):
                rep_cnt += 1
            else:
                rep_cnt -= 1
            if(rep_cnt <= 0):
                ret = nums[i]
                rep_cnt = 1
        
        return ret



    def singleNumber(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if(len(nums) == 0):
            return 0

        ret = 0
        for i in range(len(nums)):
            ret = ret ^ nums[i]
        return ret


    def isPalindrome2(self, s):
        """
        :type s: str
        :rtype: bool
        """
        left,right = 0,len(s) - 1
        while(left < right):
            while left < right and (s[left].isalpha() is False and s[left].isdigit() is False):
                left += 1
            while left < right and (s[right].isalpha() is False and s[right].isdigit() is False):
                right -= 1
            if(s[left].lower() == s[right].lower()):
                left+=1
                right-=1
            else:
                return False
        return True
            
    def getRow(self, rowIndex):
        row = [1]
        for i in range(1, rowIndex + 1):
            row = [1] + [ row[x] + row[x - 1] for x in range(1, i)] + [1]
        return row
    def generate(self, numRows):
        """
        :type numRows: int
        :rtype: List[List[int]]
        """
        retList = list()
        if(numRows == 0):
            return retList
        retList.append([1])
        if(numRows == 1):
            return retList
        retList.append([1,1])
        if(numRows == 2):
            return retList

        for i in range(2,numRows):
            retList.append([1])
            for j in range(len(retList[i - 1]) - 1):
                retList[i].append(retList[i - 1][j] + retList[i - 1][j + 1])
            retList[i].append(1)
        return retList


    def sortedArrayToBST(self, nums):
        """
        :type nums: List[int]
        :rtype: TreeNode
        """
        retNode = None
        if(len(nums) == 0):
            return retNode
        if(len(nums) <= 0):
            return None
        
        left,right = 0,len(nums)
        m = (left + right)/2
        retNode = TreeNode(nums[m])
        retNode.left = self.sortedArrayToBST(nums[left:m])
        retNode.right = self.sortedArrayToBST(nums[m+1:right])
        return retNode


    def longestCommonPrefix(self, strs):
        """
        :type strs: List[str]
        :rtype: str
        """
        retLen = 0 
        if(len(strs) <= 0):
            return ""
        if(len(strs) == 1):
            return strs[0]

        for i in range(min(len(strs[0]),len(strs[1]))):
            if(strs[0][i] == strs[1][i]):
                retLen+=1
        if(retLen == 0):
                return ""

        for i in range(2,len(strs)) :
            while(retLen > 0):
                if(strs[i][0:retLen] != strs[0][0:retLen]):
                    retLen -= 1
                else:
                    break
                
            if(retLen == 0):
                return ""

        return strs[0][0:retLen]

    def maxDepth(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        if(root is None):
            return 0
        return 1 + max(self.maxDepth(root.left),self.maxDepth(root.right))
        
    def isPalindrome(self, x):
        """
        :type x: int
        :rtype: bool
        """
        s = str(x)
        left, right = 0, len(s) - 1
        while(left < right):
            if(s[left] == s[right]):
                left+=1
                right-=1
            else:
                return False
        return True

    def reverse(self, x):
        """
        :type x: int
        :rtype: int
        """
        sign = 1
        idx = 0
        ret = 0
        s = str(x)
        if(len(s) == 0):
            return 0
        if(len(s) == 1):
            return x
        if(s[0] == '-'):
            sign = (-1)
        s = s[::-1]
        while(idx < len(s) and s[idx] == '0'):
            idx += 1
        for i in range(idx,len(s)):
            if(ord(s[i]) - ord('0') >= 0 and ord(s[i]) - ord('0') <= 9):
                ret = 10 * ret + (ord(s[i]) - ord('0'))
        if(ret > (2 ** 31 - 1) and sign == 1):
            return 0
        if(ret > (2 ** 31) and sign == (-1)):
            return 0
        return sign * ret

    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        if len(nums) <= 0:
            return []

        dict = collections.defaultdict(lambda: -1)

        for i in range(len(nums)):
            print(dict)
            if dict[target - nums[i]] != (-1) :
                return [i, dict[target - nums[i]]]
            dict[nums[i]] = i

        return []

s = SolutionEasy()
