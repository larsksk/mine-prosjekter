class Solution(object):
    def removeElement(self, nums, val):
        """
        :type nums: List[int]
        :type val: int
        :rtype: int
        """
        new_length = len(nums)
        for i in range(len(nums)):
            if nums[i] == val:
                nums[i] = "_"
                new_length -= 1
        nums.sort()

        return new_length
