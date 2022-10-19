class Solution(object):
    def moveZeroes(self, nums):
        """
        :type nums: List[int]
        :rtype: None Do not return anything, modify nums in-place instead.
        """

        pop_list = []
        for i in range(len(nums)):
            if nums[i] == 0:
                nums.append(0)
                pop_list.append(i)

        pop_list.sort(reverse=True)
        for pop_index in pop_list:
            nums.pop(pop_index)
