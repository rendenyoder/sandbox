package hashing

/**
 * Design an algorithm to encode a list of strings to a single string.
 *
 * The encoded string is then decoded back to the original list of strings.
 */
class EncodeDecodeString {
    /**
     * The solution encodes a list of strings simply by appending the length as a character at the beginning of each
     * string segment.
     *
     * This is the more compact version where the literal string length and a delimiter might be used. This more
     * compact solution works so long as string lengths are within the size representable by a UTF-8 character.
     */
    fun encode(strs: List<String>): String {
        return buildString {
            for (string in strs) {
                append(string.length.toChar())
                append(string)
            }
        }
    }

    /**
     * The solution decodes the string into a list of strings by reading the leading character, and subsequent
     * leading characters, for each substring where the leading character's code value represents the length
     * of the proceeding string.
     */
    fun decode(str: String): List<String> {
        return buildList {
            var start = 0
            var end = str.getOrNull(0)?.code

            while (end != null) {
                add(str.substring(start + 1 .. end))

                start = end + 1
                end = str.getOrNull(start)?.code?.plus(start)
            }
        }
    }
}
