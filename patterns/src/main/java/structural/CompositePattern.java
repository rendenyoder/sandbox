package structural;

import java.util.Arrays;

/**
 * This class demonstrates the composite structural design pattern, which delegates operations to the leaf nodes of
 * a tree, or tree-like, data structure.
 *
 * <h4>Pattern Components:</h4>
 * <ul>
 *     <li><strong>Component:</strong> Defines the interface for all nodes in the composite tree.</li>
 *     <li><strong>Leaf:</strong> Implements the component interface and defines the behavior for leaf nodes.</li>
 *     <li><strong>Composite:</strong> Implements the component interface and delegates operations to child nodes.</li>
 * </ul>
 *
 * <h4>Key Benefits:</h4>
 * <ul>
 *     <li><strong>Flexibility:</strong> Component types can be added without modifying existing code.</li>
 *     <li><strong>Decoupling:</strong> Leaf node and composite implementations can evolve independently.</li>
 *     <li><strong>Simplicity:</strong> Recursive actions are performed across the hierarchy using a single object.</li>
 * </ul>
 *
 * <h4>Example Usage:</h4>
 * <pre>{@code
 * final var root = new Directory("root", new Node[]{
 *     new Directory("dir1", new Node[]{new File("file1.txt", 2048)}),
 *     new Directory("dir2", new Node[]{new File("file2.txt", 2048)}),
 *     new File("file3.txt", 4096)
 * });
 *
 * final var total = root.getSize(); // total = 8192
 * }
 */
public class CompositePattern {
    /**
     * The component interface defining the contract for all nodes in the composite tree.
     */
    interface Node {
        /**
         * @return the total size of the node and its descendants.
         */
        long getSize();
    }

    /**
     * The leaf node implementation for a file system (i.e., a file).
     *
     * @param name the name of the file.
     * @param size the size of the file, in bytes for this example.
     */
    record File(String name, int size) implements Node {
        /**
         * @return the size of the file.
         */
        @Override
        public long getSize() {
            return size;
        }
    }

    /**
     * The composite node implementation for a file system (i.e., a directory).
     *
     * @param name the name of the directory.
     * @param children the children of the directory.
     */
    record Directory(String name, Node[] children) implements Node {
        /**
         * @return the total file size of all children by delegating the calculation to each leaf node.
         */
        @Override
        public long getSize() {
            return Arrays.stream(children)
                    .mapToLong(Node::getSize)
                    .sum();
        }
    }
}
