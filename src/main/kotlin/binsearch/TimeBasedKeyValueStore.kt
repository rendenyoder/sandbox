package binsearch

/**
 * Design a time-based key-value data structure that can store multiple values for the same key at different timestamps
 * and retrieve the key's value at a certain timestamp. Retrieved values should be the closest value to a provided
 * timestamp while still be greater than or equal to the given timestamp.
 *
 * Constraints:
 * - `1 <= timestamp <= 107`
 * - `1 <= key.length, value.length <= 100`
 * - All set timestamps are strictly increasing.
 * - At most 2 * 105 calls will be made to [set] and [get].
 * - Keys and values consist of lowercase English letters and digits.
 */
class TimeBasedKeyValueStore {

    private val store = mutableMapOf<String, MutableList<Pair<Int, String>>>()

    /**
     * Stores the key with the value at the given time timestamp.
     */
    fun set(key: String, value: String, timestamp: Int) {
        val entries = store.getOrPut(key) { mutableListOf() }
        entries.add(timestamp to value)
    }

    /**
     * Returns a value, with the previous timestamp less than or equal to the provided [timestamp].
     *
     * If no values are present, an empty string is immediately returned.
     *
     * The general approach follows a binary search algorithm. First, left, right, and middle pointers are initialized
     * along with a result, initially an empty string. Duringt the search, if a middle timestamp value is less than or
     * equal to the provided [timestamp], the result is assigned to the stored middle value and values to the right of
     * the middle are search. Otherwise, values to the left are searched **but no assignment occurs** due to the
     * requirement that the returned value must have a timestamp greater than or equal to the stored timestamp.
     *
     * After the pointers have met, the result is returned.
     *
     * It should be noted that there are some optimizations that were omitted for simplicity's sake. For example, there
     * is no need to search the entries if the first entry has a timestamp that is smaller than the target [timestamp].
     *
     * In addition, there exists an alternate approach of returning the result whereby the right index can be used,
     * after the search has completed, to get the leftmost entry.
     */
    fun get(key: String, timestamp: Int): String {
        val entries = store[key] ?: return ""

        var p1 = 0
        var p2 = entries.size - 1
        var result = ""

        while(p1 <= p2) {
            val mid = p1 + (p2 - p1) / 2
            val (midTimestamp) = entries[mid]

            when {
                midTimestamp <= timestamp -> {
                    result = entries[mid].second
                    p1 = mid + 1
                }
                else -> p2 = mid - 1
            }
        }

        return result
    }
}
