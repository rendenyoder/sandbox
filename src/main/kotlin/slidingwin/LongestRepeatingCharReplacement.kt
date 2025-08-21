package slidingwin

/**
 * You are given a string and an integer "k".
 *
 * You can choose any character of the string and change it to any other uppercase English character up to "k" times.
 *
 * After performing at most "k" replacements, return the length of the longest single character substring.
 *
 * Constraints:
 * - `0 <= k <= s.length`
 * - `1 <= s.length <= 1000`
 */
class LongestRepeatingCharReplacement {
    /**
     * The solution returns the length of the longest single character substring, after at most "k" replacements,
     * with a time complexity of O(n) and a space complexity of O(m), where m is the number of unique characters.
     *
     * The general approach follows a sliding window technique. A left pointer is initialized at the start of the
     * string, as well as a frequency map and a max frequency integer.
     *
     * For each iteration, the frequency of the character from our right pointer is incremented and the largest
     * frequency encountered is conditionally updated. While the size of our window (right - left + 1) minus the
     * most frequent character in our window is larger than the "k" replacements, the window is too large, the left
     * pointer is incremented, and frequencies updated. Afterward, given the window size is guaranteed to be valid,
     * we can compare it to the current running max window size (i.e., the max substring length).
     *
     * Finally, the running max window length is returned as the longest possible single character substring after
     * at most "k" replacements.
     */
    fun characterReplacement(s: String, k: Int): Int {
        val frequencies = hashMapOf<Char, Int>()

        var p1 = 0
        var max = 0
        var maxFreq = 0

        for (p2 in s.indices) {
            frequencies[s[p2]] = frequencies.getOrDefault(s[p2], 0) + 1
            maxFreq = maxOf(maxFreq, frequencies[s[p2]]!!)

            while ((p2 - p1 + 1) - maxFreq > k) {
                frequencies[s[p1]] = frequencies.getOrDefault(s[p1], 0) - 1
                p1++
            }

            max = maxOf(max, (p2 - p1) + 1)
        }

        return max
    }
}
