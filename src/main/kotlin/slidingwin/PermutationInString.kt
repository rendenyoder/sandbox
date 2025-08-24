package slidingwin

/**
 * You are given two strings, return true if the second contains a permutation of the first, or false otherwise.
 *
 * Both strings only contain lowercase letters.
 *
 * Constraints:
 * - `1 <= s1.length, s2.length <= 10⁴`
 */
class PermutationInString {
    /**
     * This solution returns if the string [s2] contains a permutation of the string [s1] with a time complexity
     * of O(n) and a constant space complexity.
     *
     * The general approach follows that of a sliding window technique. First, the number of occurrences, or the count,
     * of each character contained within [s1] is recorded within an integer array. Next, a left pointer is initialized
     * at the start of the search string [s2], along with a value for the total number of matches encountered.
     *
     * The search string [s2] is then iterated over (i.e., the right pointer). For every character of [s2], the
     * corresponding index within the count integer array is determined. The count recorded for this character is
     * then decremented. If the decremented value is still greater than or equal to 0, the characters within the
     * current window are considered valid (i.e., could be a valid permutation or a segment of a valid permutation),
     * and the number of matches is incremented.
     *
     * If the decremented value is less than 0, however, the window is considered invalid. While this is the case, the
     * corresponding index within the count integer array is determined for the character at the left pointer. The left
     * pointer, and the number of occurrences for its character, are both incremented. If the number of occurrences
     * for the left character after being incremented is greater than 0, a previously encountered matching character
     * has left the "scope" of the window and the number of matches is decremented.
     *
     * If at any point, the number of matches is equal to the length of the target string, a permutation of [s1] does
     * exist within [s2] and a value of true is returned.
     *
     * Otherwise, if the right pointer reaches the end of the search string, no permutation exists and a value of
     * false is returned.
     */
    fun checkInclusion(s1: String, s2: String): Boolean {
        val counts = IntArray(26) { 0 }
        for (char in s1) counts[char - 'a']++

        var p1 = 0
        var matches = 0

        for (p2 in s2.indices) {
            val right = s2[p2] - 'a'
            if (--counts[right] >= 0) matches++

            while (counts[right] < 0) {
                val left = s2[p1++] - 'a'
                if (++counts[left] > 0) matches--
            }

            if (matches == s1.length) return true
        }

        return false
    }
}
