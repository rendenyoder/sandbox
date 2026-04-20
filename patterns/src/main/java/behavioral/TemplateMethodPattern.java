package behavioral;

import java.util.HashMap;
import java.util.Map;

/**
 * The template method pattern is a behavioral design pattern that defines a skeleton of an algorithm using an abstract
 * implementation, leaving the specifics of the algorithm to subclasses. This pattern allows algorithms to be re-used
 * and modified without modifying the algorithm's structure.
 *
 * <h4>Pattern Components:</h4>
 * <ul>
 *     <li><strong>Template Method Abstraction:</strong> Defines the skeleton of an algorithm.</li>
 *     <li><strong>Concrete Implementation:</strong> Implements the specifics of the algorithm steps.</li>
 * </ul>
 *
 * <h4>Key Benefits:</h4>
 * <ul>
 *     <li><strong>Code Reusability:</strong> Implementations can share logic through the abstract class.</li>
 *     <li><strong>Open/Closed Principle:</strong> New algorithms can be added without modifying the existing code.</li>
 * </ul>
 *
 * <pre>{@code
 * final var csv = new CSVReader();
 * final var tsv = new TSVReader();
 * final var psv = new PSVReader();
 *
 * csv.read("key1,value1,key2,value2");
 * tsv.read("key1\tvalue1\tkey2\tvalue2");
 * psv.read("key1|value1|key2|value2");
 *
 * System.out.println(csv); // {key1=value1, key2=value2}
 * System.out.println(tsv); // {key1=value1, key2=value2}
 * System.out.println(psv); // {key1=value1, key2=value2}
 * }</pre>
 */
public class TemplateMethodPattern {
    /**
     * An abstract template method abstraction defining text parsing behavior.
     */
    abstract static class KeyValueReader {

        protected Map<String, String> fields = new HashMap<>();

        /**
         * A template method for decoding an input string into a key-value pair array.
         *
         * @param input the raw input string to be decoded.
         * @return the decoded key-value pair as an array of strings.
         */
        protected abstract String[] decode(String input);

        /**
         * A template method for validating decoded input.
         *
         * @param decoded the decoded key-value pair as an array of strings.
         */
        protected void validate(String[] decoded) {
            if (decoded.length == 0) throw new IllegalArgumentException("Empty input");
            if (decoded.length % 2 != 0) throw new IllegalArgumentException("Invalid number of fields");
        }

        /**
         * A template method for processing decoded input by adding key-value pairs to the map.
         *
         * @param decoded the decoded key-value pair as an array of strings.
         */
        protected void process(String[] decoded) {
            for (int i = 0; i < decoded.length; i += 2) {
                fields.put(decoded[i], decoded[i + 1]);
            }
        }

        /**
         * The main template method that orchestrates the reading process.
         *
         * @param input the raw input string to be parsed.
         */
        final void read(String input) {
            final var decoded = decode(input);
            validate(decoded);
            process(decoded);
        }

        /**
         * @return the map of parsed key-value pairs as a string.
         */
        @Override
        public String toString() {
            return fields.toString();
        }
    }

    /**
     * A concrete implementation for parsing comma-separated content.
     */
    static class CSVReader extends KeyValueReader {

        @Override
        protected String[] decode(String input) {
            return input.split(",");
        }
    }

    /**
     * A concrete implementation for parsing tab-separated content.
     */
    static class TSVReader extends KeyValueReader {

        @Override
        protected String[] decode(String input) {
            return input.split("\t");
        }
    }

    /**
     * A concrete implementation for parsing pipe-separated content.
     */
    static class PSVReader extends KeyValueReader {

        @Override
        protected String[] decode(String input) {
            return input.split("\\|");
        }
    }
}
