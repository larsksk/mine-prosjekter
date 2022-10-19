class Solution(object):
    def thirdMax(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        nums_list = []
        for num in nums:
            if num not in nums_list:
                nums_list.append(num)

        max_list = [0, 0, 0]

        for number in nums_list:
            for i in range(3):
                if number > max_list[i]:
                    for j in range(2, 1, -1):
                        max_list[j] = max_list[j - 1]
                    max_list[i] = number
                    break
            print(max_list)
        if max_list[2] == 0:
            return max_list[0]
        else:
            return max_list[2]
