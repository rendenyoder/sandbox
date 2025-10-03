package creational;

import static java.util.Objects.requireNonNull;

/**
 * The builder pattern separates the construction of a complex object from its representation, allowing the same
 * construction process to create different representations. This class demonstrates the pattern by providing
 * controlled, step-by-step construction of a simple object while hiding the complexity of the construction process
 * from clients.
 *
 * <h4>Pattern Components Demonstrated:</h4>
 * <ul>
 *     <li><strong>Product:</strong> The complex object being constructed</li>
 *     <li><strong>Builder Interface:</strong> Contract for construction</li>
 *     <li><strong>Concrete Builder:</strong> Specific construction implementation</li>
 *     <li><strong>Abstract Product:</strong> Common interface for all buildable objects</li>
 * </ul>
 *
 * <h4>Pattern Benefits:</h4>
 * <ul>
 *     <li><strong>Flexibility:</strong> Same construction process can create different objects</li>
 *     <li><strong>Readability:</strong> Fluent interface makes code more readable</li>
 *     <li><strong>Validation:</strong> Construction logic can validate state</li>
 *     <li><strong>Immutability:</strong> Products can be made immutable after construction</li>
 *     <li><strong>Parameter Independence:</strong> Method chaining allows flexible ordering</li>
 * </ul>
 *
 * <h4>Usage Example:</h4>
 * <pre>{@code
 * Product product = new Product.Builder()
 *     .setId("123")
 *     .setName("Sample Product")
 *     .build();
 *
 * // Method chaining allows flexible parameter ordering
 * Product another = new Product.Builder()
 *     .setName("Another Product")  // Order doesn't matter
 *     .setId("456")
 *     .build();
 * }</pre>
 *
 * <h4>When to Use:</h4>
 * <ul>
 *     <li>Creating objects with many optional parameters</li>
 *     <li>When object construction is complex or requires validation</li>
 *     <li>When you want to create immutable objects</li>
 *     <li>To improve code readability compared to telescoping constructors</li>
 * </ul>
 */
public class BuilderPattern {
    /**
     * Common interface for all model objects that can be constructed.
     * <p>
     *
     * This interface defines the essential contract that all products must implement, providing a standardized way to
     * access basic properties of constructed objects.
     *
     * @see ModelBuilder
     */
    interface Model {
        /**
         * Returns the identifier of this model.
         *
         * @return the identifier of the model
         */
        String getId();

        /**
         * Returns the name of this model.
         *
         * @return the name of the model
         */
        String getName();
    }

    /**
     * Builder interface that declares the construction method for {@link Model} objects.
     * <p>
     *
     * This interface represents the builder abstraction in the builder pattern, providing a common contract for all
     * concrete builders. It enables the creation of different implementations while maintaining a consistent
     * construction interface.
     *
     * @see Model
     */
    interface ModelBuilder {
        /**
         * Constructs and returns the final {@link Model} product.
         * <p>
         *
         * This method represents the culmination of the building process. It should be called after all necessary
         * construction steps have been performed through the builder's configuration methods. The method is
         * responsible for creating a new instance of the target model type and transferring all state.
         * <p>
         *
         * <strong>Implementation Guidelines:</strong>
         * <ul>
         *     <li>Should create a new instance on each call (not singleton behavior)</li>
         *     <li>Should validate the configured state before creating the product</li>
         *     <li>Should return an immutable or properly initialized object</li>
         *     <li>May throw exceptions for invalid configurations</li>
         * </ul>
         *
         * @return the constructed {@link Model} instance with all configured properties applied
         * @throws IllegalStateException if the builder is in an invalid state for construction
         */
        Model build() throws IllegalStateException;
    }

    /**
     * Concrete implementation of the builder pattern demonstrating product construction.
     * <p>
     *
     * This class serves as the concrete product in the Builder pattern, providing a practical example of how simple
     * objects can be constructed using the builder approach. The class maintains immutability after construction and
     * encapsulates its builder implementation.
     *
     * @see Model
     * @see ModelBuilder
     */
    static class Product implements Model {
        private String id;
        private String name;

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
         * Concrete Builder implementation for constructing {@link Product} instances.
         * <p>
         *
         * This static nested class implements the builder pattern's core construction logic, providing a fluent
         * interface for step-by-step object construction. The builder accumulates state through setter methods and
         * transfers that state to a new {@link Product} instance when {@link #build()} is called.
         *
         * @see Product
         * @see ModelBuilder
         */
        static class Builder implements ModelBuilder {
            private String id;
            private String name;

            /**
             * Sets the ID for the product being constructed.
             *
             * @param id the unique identifier for the product being constructed
             * @return this builder instance to enable method chaining
             */
            public Builder setId(String id) {
                this.id = id;
                return this;
            }

            /**
             * Sets the name for the product being constructed.
             *
             * @param name the name for the product being constructed
             * @return this builder instance to enable method chaining
             */
            public Builder setName(String name) {
                this.name = name;
                return this;
            }

            /**
             * Constructs and returns a new {@link Product} instance with all configured properties.
             * <p>
             *
             * This method creates a new {@link Product} instance, transfers all accumulated state from the builder to
             * the product, and returns the fully constructed, immutable result.
             *
             * @return a new, immutable Product instance with all configured properties
             * @throws IllegalStateException if the builder is in an invalid state for construction
             */
            @Override
            public Product build() throws IllegalStateException {
                Product instance = new Product();
                instance.id = requireNonNull(id, "id must not be null");
                instance.name = requireNonNull(name, "name must not be null");
                return instance;
            }
        }
    }
}
