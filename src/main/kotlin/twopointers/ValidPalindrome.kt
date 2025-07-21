package twopointers

/**
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all
 * non-alphanumeric characters, it reads the same forward and backward.
 *
 * Alphanumeric characters include letters and numbers.
 *
 * Given a string s, return true if it is a palindrome, or false otherwise.
 *
 * Constraints:
 *
 * - `1 <= s.length <= 2 * 105`
 * - `s` consists only of printable ASCII characters.
 */
class ValidPalindrome {
    /**
     * This solution returns if a string is a palindrome with a time and space complexity of O(n) and O(1) respectively.
     *
     * Two index pointers are initialized for the start and end of the string. The start pointer is incremented while
     * the end point is decremented in order to compare the characters at each pointer index.
     *
     * If a character at either pointer index for a comparison is non-alphanumeric, the given pointer will move to
     * the next position until an alphanumeric character is found or the two pointers cross.
     *
     * If at any point, the lowercase value of the characters being compared is not equal, false will be returned.
     *
     * Finally, if no differences were found, true will be returned as the string is a palindrome.
     */
    fun isPalindrome(s: String): Boolean {
        var p1 = 0
        var p2 = s.length - 1

        while (p1 < p2) {
            while (p1 < p2 && !s[p1].isLetterOrDigit()) p1++
            while (p1 < p2 && !s[p2].isLetterOrDigit()) p2--

            if (s[p1].lowercaseChar() != s[p2].lowercaseChar()) return false

            p1++
            p2--
        }

        return true
    }
}
