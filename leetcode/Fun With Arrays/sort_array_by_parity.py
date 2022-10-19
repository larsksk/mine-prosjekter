class Solution(object):
    def sortArrayByParity(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        i = 0
        length = len(nums)
        while i < length:
            if nums[i] % 2 != 0:
                nums.append(nums[i])
                nums.pop(i)
                length -= 1
            else:
                i += 1

        return nums
