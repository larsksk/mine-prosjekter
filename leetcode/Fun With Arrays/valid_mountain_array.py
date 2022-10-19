class Solution(object):
    def validMountainArray(self, arr):
        """
        :type arr: List[int]
        :rtype: bool
        """
        increasing = True
        for i in range(1, len(arr)):
            if arr[i] == arr[i - 1] or not increasing and arr[i] > arr[i - 1]:
                return False

            if increasing and arr[i] < arr[i - 1]:
                if arr[i - 1] == arr[0]:
                    return False
                increasing = False

        return not increasing
