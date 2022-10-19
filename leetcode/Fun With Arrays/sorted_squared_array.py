class Solution(object):
    def sortedSquares(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        squared = []
        for number in nums:
            squared.append(number*number)

        squared.sort()
        return squared


nums = [-4,-1,0,3,10]
s = Solution()
print(s.sortedSquares(nums))