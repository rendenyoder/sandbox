package behavioral;

import static java.lang.String.join;
import static java.util.Arrays.stream;

/**
 * The visitor pattern is a behavioral design pattern that separates the algorithm from the elements on which it
 * operates. It allows for operations to be added to an element without substantially modifying the element's class.
 *
 * <h4>Pattern Components:</h4>
 * <ul>
 *     <li><strong>Element:</strong> Defines the interface for elements that can be visited.</li>
 *     <li><strong>Concrete Element:</strong> Implements the element interface with further implementation details.</li>
 *     <li><strong>Visitor:</strong> Defines the interface for operations that can be performed on elements.</li>
 *     <li><strong>Concrete Visitor:</strong> Implements the visitor interface and performs the operations.</li>
 * </ul>
 *
 * <h4>Key Benefits:</h4>
 * <ul>
 *     <li><strong>Open/Closed Principal:</strong> New visitors can be added without modifying existing code.</li>
 *     <li><strong>Separation of Concerns:</strong> Operations are encapsulated in visitors, keeping elements clean.</li>
 *     <li><strong>Extensibility:</strong> Visitors can be extended to support new operations without altering elements.</li>
 * </ul>
 *
 * <pre>{@code
 * final var csvExporter = new CSVExporter();
 * final var tsvExporter = new TSVExporter();
 *
 * final var datasets = List.of(
 *         new ArrayDataset<>(new Integer[]{1, 2, 3}),
 *         new MatrixDataset<>(new Integer[][]{{1, 2}, {3, 4}})
 * );
 *
 * // Prints:
 * // 1,2,3
 * // 1,2
 * // 3,4
 * for (final var dataset : datasets) {
 *     System.out.println(dataset.export(csvExporter));
 * }
 *
 * // Prints:
 * // 1    2   3
 * // 1	2
 * // 3	4
 * for (final var dataset : datasets) {
 *     System.out.println(dataset.export(tsvExporter));
 * }
 * }</pre>
 */
public class VisitorPattern {
    /**
     * The visitor interface defining the contract for all exporters.
     */
    interface Exporter {
        /**
         * @return the exported array dataset as a string
         */
        String export(ArrayDataset<?> dataset);

        /**
         * @return the exported matrix dataset as a string
         */
        String export(MatrixDataset<?> dataset);
    }

    /**
     * The element interface defining the contract for all datasets.
     */
    interface Dataset {
        /**
         * The visitor method for accepting a visitor instance.
         *
         * @param exporter the visitor instance.
         * @return the exported element as a string.
         */
        String export(Exporter exporter);
    }

    /**
     * A concrete array dataset implementation.
     */
    record ArrayDataset<T>(T[] data) implements Dataset {
        /**
         * The visitor method implementation with the array dataset as the receiver.
         *
         * @param exporter the visitor instance.
         * @return the exported array dataset as a string.
         */
        @Override
        public String export(Exporter exporter) {
            return exporter.export(this);
        }
    }

    /**
     * A concrete matrix dataset implementation.
     */
    record MatrixDataset<T>(T[][] data) implements Dataset {
        /**
         * The visitor method implementation with the matrix dataset as the receiver.
         *
         * @param exporter the visitor instance.
         * @return the exported matrix dataset as a string.
         */
        @Override
        public String export(Exporter exporter) {
            return exporter.export(this);
        }
    }

    /**
     * A concrete exporter implementation for CSV format.
     */
    class CSVExporter implements Exporter {
        /**
         * @return the exported array dataset as a comma-separated string.
         */
        @Override
        public String export(ArrayDataset<?> dataset) {
            return join(",", stream(dataset.data).map(Object::toString).toList());
        }

        /**
         * @return the exported matrix dataset as a newline-separated, comma-separated string.
         */
        @Override
        public String export(MatrixDataset<?> dataset) {
            return join("\n", stream(dataset.data)
                    .map(value -> join(",", stream(value).map(Object::toString).toList()))
                    .toList());
        }
    }

    /**
     * A concrete exporter implementation for TSV format.
     */
    class TSVExporter implements Exporter {
        /**
         * @return the exported array dataset as a tab-separated string.
         */
        @Override
        public String export(ArrayDataset<?> dataset) {
            return join("\t", stream(dataset.data).map(Object::toString).toList());
        }

        /**
         * @return the exported matrix dataset as a newline-separated, tab-separated string.
         */
        @Override
        public String export(MatrixDataset<?> dataset) {
            return join("\n", stream(dataset.data)
                    .map(value -> join("\t", stream(value).map(Object::toString).toList()))
                    .toList());
        }
    }
}
