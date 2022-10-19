class Solution(object):
    def heightChecker(self, heights):
        """
        :type heights: List[int]
        :rtype: int
        """
        expected = heights[:]
        expected.sort()

        unexpected = 0
        for i in range(len(heights)):
            if heights[i] != expected[i]:
                unexpected += 1

        return unexpected
