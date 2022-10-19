class Solution(object):
    def findMaxConsecutiveOnes(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        max_consecutive = 0
        current_consecutive = 0
        for number in nums:
            if number != 1:
                current_consecutive = 0
            else:
                current_consecutive += 1

            if current_consecutive > max_consecutive:
                max_consecutive = current_consecutive

        return max_consecutive
