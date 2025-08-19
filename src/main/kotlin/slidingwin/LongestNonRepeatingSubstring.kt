package slidingwin

/**
 * Given a string, find the length of the longest substring without duplicate characters.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 * Constraints:
 * - `0 <= s.length <= 5 * 104`
 * - s consists of English letters, digits, symbols and spaces.
 */
class LongestNonRepeatingSubstring {
    /**
     * This solution returns the longest substring without repeating characters with a time complexity of O(n) and a
     * space complexity of O(m), where m is the number of unique characters.
     *
     * The solution follows a sliding window approach. A left pointer is initialized at the start of the string. For
     * every character of the string, it is determined where it has been seen before by checking against a set of
     * previously encountered characters. If so, while the character of the right pointer is contained within the set,
     * we remove the character of the left pointer and increment it.
     *
     * For each iteration, the current character is added to our set and the running maximum sequence length is
     * determined. Once complete, the maximum length encountered is returned.
     */
    fun lengthOfLongestSubstring(s: String): Int {
        var p1 = 0
        var max = 0
        val chars = hashSetOf<Char>()

        for (p2 in s.indices) {
            while (s[p2] in chars) {
                chars.remove(s[p1++])
            }

            chars.add(s[p2])
            max = maxOf(max, chars.size)
        }

        return max
    }
}
