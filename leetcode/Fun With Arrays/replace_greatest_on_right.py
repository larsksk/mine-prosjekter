class Solution(object):
    def replaceElements(self, arr):
        """
        :type arr: List[int]
        :rtype: List[int]
        """
        for i in range(len(arr) - 1):
            right_max = 0
            for j in range(i + 1, len(arr)):
                if arr[j] > right_max:
                    right_max = arr[j]
            arr[i] = right_max

        arr[-1] = -1

        return arr
