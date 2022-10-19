class Solution(object):
    def removeDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        new_length = len(nums)
        current_value = ""
        for i in range(len(nums)):
            if current_value == nums[i]:
                nums[i] = "_"
                new_length -= 1
            else:
                current_value = nums[i]
        nums.sort()

        return new_length
