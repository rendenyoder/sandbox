package behavioral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * The iterator pattern is a behavioral design pattern that separates the algorithm for traversing a data structure
 * from the data structure itself. In this example, the standard {@link Collection} and {@link Iterator} interfaces are
 * used as this pattern is rather fundemental.
 *
 * <h4>Pattern Components:</h4>
 * <ul>
 *     <li><strong>Collection:</strong> Defines the interface for traversable data structures.</li>
 *     <li><strong>Concrete Collection:</strong> Implements the collection interface and contains the data.</li>
 *     <li><strong>Iterator:</strong> Defines the interface for traversing a collection.</li>
 *     <li><strong>Concrete Iterator:</strong> Implements the iterator interface and provides the traversal logic.</li>
 *     <li><strong>Client:</strong> Uses the collection to retrieve iterators.</li>
 * </ul>
 *
 * <h4>Key Benefits:</h4>
 * <ul>
 *     <li><strong>Parallel Iteration:</strong> iterators can be used in parallel without interference.</li>
 *     <li><strong>Single Responsibility:</strong> iterators only focus on iterating over the data structure.</li>
 *     <li><strong>Open/closed Principal:</strong> iterators can be added without modifying exsting iterators.</li>
 * </ul>
 *
 * <h4>Example Usage:</h4>
 * <pre>{@code
 * // construct a collection of whole numbers
 * final var numbers = new WholeNumbers(1, 2, 3, 4, 5, 6);
 *
 * // retrieve iterators for odd and even numbers
 * final var odds = numbers.odd();
 * final var evens = numbers.even();
 *
 * // print each odd and even number using the concrete iterators
 * while (odds.hasNext()) System.out.println(odds.next());   // 1 3 5
 * while (evens.hasNext()) System.out.println(evens.next()); // 2 4 6
 * }
 */
public class IteratorPattern {
    /**
     * The data structure containing whole numbers.
     */
    public class WholeNumbers extends ArrayList<Integer> {
        /**
         * @param numbers an array of whole numbers.
         */
        public WholeNumbers(Integer... numbers) {
            super(Arrays.asList(numbers));
        }

        /**
         * @return the concrete odd number iterator.
         */
        public Iterator<Integer> odd() {
            return new OddNumberIterator(this);
        }

        /**
         * @return the concrete even number iterator.
         */
        public Iterator<Integer> even() {
            return new EvenNumberIterator(this);
        }
    }

    /**
     * A concrete iterator for iterating over odd numbers.
     */
    public class OddNumberIterator implements Iterator<Integer> {

        private final Iterator<Integer> iterator;

        /**
         * @param numbers the collection of whole numbers.
         */
        public OddNumberIterator(ArrayList<Integer> numbers) {
            this.iterator = numbers.stream()
                    .filter(n -> n % 2 == 1)
                    .iterator();
        }

        /**
         * @return the next odd number in the collection.
         */
        @Override
        public Integer next() {
            return iterator.next();
        }

        /**
         * @return if the collection has more odd numbers.
         */
        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }
    }

    /**
     * A concrete iterator for iterating over even numbers.
     */
    public class EvenNumberIterator implements Iterator<Integer> {

        private final Iterator<Integer> iterator;

        /**
         * @param numbers the collection of whole numbers.
         */
        public EvenNumberIterator(ArrayList<Integer> numbers) {
            this.iterator = numbers.stream()
                    .filter(n -> n.doubleValue() % 2 == 0)
                    .iterator();
        }

        /**
         * @return the next even number in the collection.
         */
        @Override
        public Integer next() {
            return iterator.next();
        }

        /**
         * @return if the collection has more even numbers.
         */
        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }
    }
}
