package behavioral;

import java.util.List;
import java.util.function.Predicate;

/**
 * This class demonstrates the strategy behavioral design pattern, which defines a family of algorithms that can be
 * chosen at runtime.
 * 
 * <h4>Pattern Components:</h4>
 * <ul>
 *    <li><strong>Context:</strong> Delegates filtering behavior to strategy objects</li>
 *    <li><strong>Strategy Interface:</strong> Defines the contract for all filtering strategies</li>
 *    <li><strong>Concrete Strategies:</strong> Implement specific filtering algorithms</li>
 *    <li><strong>Client:</strong> Any code that creates concrete instances and passes them to the context</li>
 * </ul>
 *
 * <h4>Key Benefits Demonstrated:</h4>
 * <ul>
 *    <li><strong>Separation of Concerns:</strong> Filtering logic is separated from repository logic</li>
 *    <li><strong>Interchangeable Strategies:</strong> Can swap filtering behavior at runtime</li>
 *    <li><strong>Composition over Inheritance:</strong> Repository HAS-A filter rather than IS-A specific filter</li>
 *    <li><strong>Open/Closed Principle:</strong> Easy to add new filters without modifying existing code</li>
 * </ul>
 *
 * <h4>Example Usage</h4>
 * <pre>{@code
 * // Create repository with sample data
 * var products = List.of(
 *     new Product(1, "Laptop", "Gaming laptop"),
 *     new Product(2, "Mouse", "Wireless mouse"),
 *     new Product(3, "Keyboard", "Mechanical keyboard")
 * );
 * var repository = new ProductRepository(products);
 *
 * // Use different strategies at runtime
 * var laptops = repository.findProducts(new Filter.ByName("Laptop"));
 * var gamingProducts = repository.findProducts(new Filter.ByDescription("Gaming laptop"));
 * var specificProduct = repository.findProducts(new Filter.ById(2));
 * }</pre>
 */
public class StrategyPattern {
    /**
     * A domain model representing a product with basic attributes.
     */
    record Product(long id, String name, String description) {}

    /**
     * The strategy interface defining the filtering contract.
     * <p>
     *
     * This sealed interface extends {@link Predicate} interface to leverage standard functional programming
     * capabilities while maintaining type safety.
     */
    sealed interface Filter extends Predicate<Product> {
        /**
         * Represents a specific concrete filter strategy for matching products by ID.
         */
        record ById(long id) implements Filter {

            @Override
            public boolean test(Product product) {
                return product.id() == id;
            }
        }

        /**
         * Represents a specific concrete filter strategy for matching products by name.
         */
        record ByName(String name) implements Filter {

            @Override
            public boolean test(Product product) {
                return product.name.equals(name);
            }
        }

        /**
         * Represents a specific concrete filter strategy for matching products by description.
         */
        record ByDescription(String description) implements Filter {

            @Override
            public boolean test(Product product) {
                return product.description.equals(description);
            }
        }
    }

    /**
     * The class that delegates the filtering behavior to strategy objects.
     */
    class ProductRepository {
        private final List<Product> products;

        ProductRepository(List<Product> products) {
            this.products = products;
        }

        /**
         * Performs filtering using the provided strategy.
         * <p>
         *
         * This method demonstrates the core strategy pattern principle: the context delegates the algorithm execution
         * to the strategy object. The repository doesn't need to know which specific filtering algorithm is being used.
         * 
         * @param filter The filtering strategy to apply
         * @return List of products that match the filter criteria
         */
        public List<Product> findProducts(Filter filter) {
            return products.stream().filter(filter).toList();
        }
    }
}
