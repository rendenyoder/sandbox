package hashing

/**
 * Given two strings, return true if t is an anagram of s, and false otherwise.
 *
 * Constraints:
 *
 * - `1 <= s.length`, `t.length <= 5 * 104`
 * - `s` and `t` consist of lowercase English letters.
 */
class ValidAnagram {
    /**
     * This solution determines if two strings are anagrams in O(n) time and space complexity.
     *
     * First, the two string lengths are compared. If they are not equal, false is returned as they cannot be anagrams.
     *
     * Next, an array of integers is created representing all lower case english letters. The strings are iterated
     * over, and index of the current character's integer value, minus the integer value of 'a', is incremented for
     * [s] and decremented for [t].
     *
     * If the integer array only contains zero, true is returned. Otherwise, false is returned as the strings differ
     * by at least a single character.
     */
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val count = IntArray(26)
        s.indices.forEach { i ->
            count[s[i] - 'a']++
            count[t[i] - 'a']--
        }

        return count.all { it == 0 }
    }
}
