package hashing

import kotlin.text.iterator

/**
 * Given an array of strings strs, group the anagrams together.
 *
 * You can return the answer in any order.
 */
class GroupAnagrams {
    /**
     * This solution builds a map of character frequency mapped to each string. The frequency of each character is
     * used as order does not matter for determining if strings are anagrams of one another.
     *
     * For each character frequency, we check if there is an existing anagram list or create an empty list. We then
     * append the given string to the list.
     *
     * The resulting values of the map are then returned as the solution.
     */
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val frequencies = buildMap {
            for (string in strs) {
                val frequency = mutableMapOf<Char, Int>()

                for (char in string) {
                    frequency[char] = frequency.getOrDefault(char, 0) + 1
                }

                val anagrams: MutableList<String> = getOrDefault(frequency, mutableListOf())
                set(frequency, anagrams.apply { add(string) })
            }
        }

        return frequencies.values.toList()
    }
}
