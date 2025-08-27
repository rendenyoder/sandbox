package slidingwin

/**
 * Given two strings, return the shortest substring of the first such that every character of the second, including
 * duplicates, is present in the substring.
 *
 * If such a substring does not exist, return an empty string "".
 *
 * You may assume that the correct output is always unique. In addition, both strings consist only of uppercase
 * and lowercase English letters.
 *
 * Constraints:
 * - `1 <= s.length <= 1000`
 * - `1 <= t.length <= 1000`
 */
class MinimumWindowSubstring {
    /**
     * This solution returns the shortest substring of [s] that contains every character of [t] with a time
     * complexity of O(n) and a space complexity of O(m), where m is the number of unique characters in [t].
     *
     * The approach follows a sliding window technique. First, a "counts" hashmap is initialized to record the
     * number of times a character appears in the target string [t]. Next, a left pointer is initialized at the
     * starting index of the search string. Minimum left and right indices are also initialized for when a valid
     * substring is encountered, along with a variable to track the total number of matching characters as to
     * determine when a substring is valid during an iteration.
     *
     * For each character from the search string [s], the algorithm checks if it is in the count hashmap. If it is,
     * the counter for that character is decremented, and if the decremented value if non-negative, the total number
     * of matching characters is incremented.
     *
     * During each iteration, the algorithm also checks if the number of matches is equal to the length of the target
     * string to determine if the substring formed by the window is considered valid. If this is true, the minimum
     * right and left indices are updated if the current window is smaller than the smallest previously seen window.
     * In addition, we remove the leftmost character, updating the character counter and matching characters. This
     * process repeats until our current window no longer represents a valid substring (i.e., not enough matches).
     *
     * Finally, if our minimum right pointer was assigned a value, the algorithm returns the substring between the
     * minimum left and right pointers as the shortest substring. Otherwise, an empty string is returned.
     */
    fun minWindow(s: String, t: String): String {
        val counts = hashMapOf<Char, Int>()
        for (char in t) counts[char] = (counts[char] ?: 0) + 1

        var l = 0
        var minL = 0
        var minR = Int.MAX_VALUE
        var matches = 0

        for (r in s.indices) {
            if (s[r] in counts) {
                counts[s[r]] = counts[s[r]]!! - 1

                if (counts[s[r]]!! >= 0) matches++
            }

            while (matches == t.length) {
                if (r - l < minR - minL) {
                    minL = l
                    minR = r
                }

                val removed = s[l++]
                if (removed in counts) {
                    counts[removed] = counts[removed]!! + 1

                    if (counts[removed]!! > 0) matches--
                }
            }
        }

        return if (minR != Int.MAX_VALUE) s.substring(minL, minR + 1) else ""
    }
}
