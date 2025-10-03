package creational;

/**
 * The prototype design pattern is a creational design approach that streamlines the process of creating complex
 * objects by enabling clients to produce clones of existing objects rather than constructing new instances from the
 * ground up.
 *
 * <h4>Pattern Overview</h4>
 * The prototype pattern reduces both the cost and complexity associated with object creation, making it particularly
 * useful in scenarios where there is a frequent need for similar objects. This approach can be more efficient than
 * using the builder pattern for duplication scenarios as it eliminates the need to reconstruct objects from scratch.
 *
 * <h4>Key Benefits</h4>
 * <ul>
 *   <li><strong>Efficient Object Creation:</strong> Clones existing objects rather than instantiating from scratch</li>
 *   <li><strong>Abstracted Cloning Logic:</strong> Encapsulates cloning responsibility within objects themselves</li>
 *   <li><strong>Interface-based Design:</strong> Follows the "Program to an Interface" principle</li>
 *   <li><strong>Flexible Implementation:</strong> Allows seamless swapping of concrete implementations</li>
 * </ul>
 *
 * <h4>Pattern Components</h4>
 * <ul>
 *   <li><strong>Prototype Interface:</strong> Declares the cloning method</li>
 *   <li><strong>Concrete Prototype:</strong> Implements cloning logic</li>
 *   <li><strong>Client:</strong> Uses the prototype interface to clone objects</li>
 * </ul>
 * 
 * <h4>Considerations</h4>
 * <ul>
 *   <li>Can be resource-intensive for deeply nested object structures</li>
 *   <li>Most beneficial when objects require significant setup after instantiation</li>
 *   <li>Deep vs. shallow copying must be carefully considered based on object complexity</li>
 * </ul>
 */
public class PrototypePattern {
    /**
     * The interface that represents the clonable object.
     * <p>
     *
     * All concrete prototype classes must implement this interface, providing their own copy logic.
     */
    interface Prototype {
        /**
         * Returns the unique identifier of this prototype instance.
         *
         * @return the unique identifier of the prototype
         */
        String getId();

        /**
         * Returns the name of this prototype instance.
         *
         * @return the name of the prototype
         */
        String getName();

        /**
         * Creates and returns a copy of this prototype instance.
         * <p>
         *
         * This is the core method of the pattern that enables object cloning. Implementations should create a new
         * instance of the same class by copying the properties from the original object, eliminating the need for
         * clients to instantiate objects from scratch.
         * <p>
         *
         * The cloning can be either shallow (reusing property references) or deep (creating new instances of all
         * properties) depending on the implementation requirements. For simple objects like this example, a shallow
         * copy is enough.
         * 
         * @return a new {@link Prototype} instance that is a copy of this object
         */
        Prototype copy();
    }

    /**
     * Concrete implementation of the {@link Prototype} interface that represents a product entity.
     * <p>
     *
     * This concrete prototype encapsulates the cloning responsibility within itself, allowing clients to create copies
     * without knowing the internal structure or construction process of the {@link Product} class.
     */
    class Product implements Prototype {
        private final String id;
        private final String name;

        /**
         * Constructs a new {@link Product} with the specified identifier and name.
         * 
         * @param id the unique identifier for the product
         * @param name the name of the product
         */
        Product(String id, String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getId() {
            return id;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getName() {
            return name;
        }

        /**
         * Creates and returns a copy of this {@link Product} instance.
         * <p>
         *
         * This implementation creates a new {@link Product} object with the same id and name as this instance. Since
         * strings are immutable in Java, this shallow copy approach ensures that the cloned product is completely
         * independent of the original.
         * 
         * @return a new {@link Product} instance that is a copy of this product
         */
        @Override
        public Prototype copy() {
            return new Product(id, name);
        }
    }
}
