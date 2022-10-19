class Solution(object):
    def findNumbers(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        sum_even_numbers = 0
        for number in nums:
            chars = 0
            for _ in str(number):
                chars += 1

            if chars % 2 == 0:
                sum_even_numbers += 1

        return sum_even_numbers
