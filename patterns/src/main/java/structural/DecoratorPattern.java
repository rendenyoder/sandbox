package structural;

import static java.util.Objects.requireNonNull;

/**
 * The decorator pattern is a structural design pattern that allows behavior to be added to objects dynamically without
 * altering their structure. It provides a flexible alternative to subclassing for extending functionality.
 *
 * <h4>Key Benefits</h4>
 * <ul>
 *   <li><strong>Open/Closed Principle:</strong> Classes are open for extension but closed for modification</li>
 *   <li><strong>Composition over Inheritance:</strong> Uses HAS-A relationship instead of IS-A</li>
 *   <li><strong>Single Responsibility:</strong> Each decorator handles one specific enhancement</li>
 *   <li><strong>Runtime Flexibility:</strong> Decorators can be added/removed dynamically</li>
 * </ul>
 *
 * <h4>Pattern Structure</h4>
 * <ul>
 *   <li><strong>Component:</strong> Interface defining operations for objects that can be decorated</li>
 *   <li><strong>ConcreteComponent:</strong> Base objects to which decorators are added</li>
 *   <li><strong>Decorator:</strong> Base class maintaining reference to Component</li>
 *   <li><strong>ConcreteDecorator:</strong> Add specific functionality</li>
 * </ul>
 *
 * <h4>Usage Example</h4>
 * <pre>{@code
 * // Create a base beverage
 * Beverage coffee = new LightRoastCoffee();
 *
 * // Add decorators
 * coffee = new IcedBeverageDecorator(coffee);
 * coffee = new ExtraShotBeverageDecorator(coffee, 2);
 *
 * // Get final price and description
 * System.out.println(coffee.getDescription()); // "Light Roast Coffee, Iced, x2 Extra Shots"
 * System.out.println(coffee.getPrice()); // 3.25
 * }</pre>
 *
 * <h4>Common Use Cases</h4>
 * <ul>
 *   <li>Point of Sale systems with add-ons and discounts</li>
 *   <li>GUI components with dynamic visual enhancements</li>
 *   <li>Web middleware for request/response processing</li>
 * </ul>
 *
 * <h4>Limitations</h4>
 * <ul>
 *   <li>Increases complexity with multiple decorator layers</li>
 *   <li>Can make debugging more difficult with nested decorators</li>
 *   <li>May violate the "Interface Segregation Principle" if the interface is too broad</li>
 * </ul>
 */
public class DecoratorPattern {
    /**
     * Component interface that defines the contract for all beverage objects.
     * <p>
     *
     * This interface ensures that both concrete components and decorators can be used interchangeably, enabling the
     * decorator pattern's flexibility. All beverages must be able to provide their price and description, which allows
     * decorators to enhance these properties while maintaining the same interface contract.
     */
    interface Beverage {
        /**
         * Gets the total price of the beverage including any decorations.
         *
         * @return the price in dollars as a double value
         */
        double getPrice();

        /**
         * Gets the complete description of the beverage, including any decorations.
         *
         * @return a string description of the beverage and its enhancements
         */
        String getDescription();
    }

    /**
     * Concrete component representing a light roast coffee.
     */
    static class LightRoastCoffee implements Beverage {
        /**
         * Returns the base price for light roast coffee.
         *
         * @return 2.25 as the standard price for light roast coffee
         */
        @Override
        public double getPrice() {
            return 2.25;
        }

        /**
         * Returns the base description for light roast coffee.
         *
         * @return "Light Roast Coffee" as the base description
         */
        @Override
        public String getDescription() {
            return "Light Roast Coffee";
        }
    }

    /**
     * Concrete component representing a dark roast coffee.
     */
    static class DarkRoastCoffee implements Beverage {
        /**
         * Returns the base price for dark roast coffee.
         *
         * @return 2.00 as the standard price for dark roast coffee
         */
        @Override
        public double getPrice() {
            return 2.00;
        }

        /**
         * Returns the base description for dark roast coffee.
         *
         * @return "Dark Roast Coffee" as the base description
         */
        @Override
        public String getDescription() {
            return "Dark Roast Coffee";
        }
    }

    /**
     * Abstract base decorator class that maintains a reference to a {@link Beverage} component.
     * <p>
     *
     * This abstract class serves as the foundation for all concrete decorators. It implements the {@link Beverage}
     * interface and maintains a reference to another {@link Beverage} object, enabling the decoration chain.
     * <p>
     *
     * By implementing the same interface as the components it decorates, decorators can be chained together and used
     * interchangeably with base components, providing the flexibility that makes the decorator pattern powerful.
     */
    static abstract class BeverageDecorator implements Beverage {
        protected Beverage decorated;

        /**
         * Constructs a new decorator wrapping the specified beverage.
         *
         * @param decorated the beverage to be decorated; must not be null
         */
        public BeverageDecorator(Beverage decorated) {
            this.decorated = requireNonNull(decorated, "decorated beverage cannot be null");
        }
    }

    /**
     * Concrete decorator that adds ice to a beverage.
     */
    static class IcedBeverageDecorator extends BeverageDecorator {
        /**
         * Constructs an iced beverage decorator.
         *
         * @param decorated the beverage to make iced; must not be null
         */
        public IcedBeverageDecorator(Beverage decorated) {
            super(decorated);
        }

        /**
         * Returns the price of the decorated beverage plus the ice surcharge.
         *
         * @return the total price including the $0.50 ice surcharge
         */
        @Override
        public double getPrice() {
            return decorated.getPrice() + 0.50;
        }

        /**
         * Returns the description with ice added.
         *
         * @return the enhanced description including the ice indicator
         */
        @Override
        public String getDescription() {
            return "%s, Iced".formatted(decorated.getDescription());
        }
    }

    /**
     * Concrete decorator that adds extra espresso shots to a beverage.
     */
    static class ExtraShotBeverageDecorator extends BeverageDecorator {
        private final int shots;

        /**
         * Constructs an extra shot decorator with the specified number of shots.
         *
         * @param decorated the beverage to enhance with extra shots; must not be null
         * @param shots the number of extra shots to add; must be positive
         */
        public ExtraShotBeverageDecorator(Beverage decorated, int shots) {
            super(decorated);

            if (shots <= 0) {
                throw new IllegalArgumentException("Number of shots must be positive");
            }

            this.shots = shots;
        }

        /**
         * Returns the price with extra shot charges added.
         *
         * @return the total price including extra shot charges
         */
        @Override
        public double getPrice() {
            return decorated.getPrice() + 0.50 * shots;
        }

        /**
         * Returns the description indicating the number of extra shots.
         *
         * @return the enhanced description including the shot count
         */
        @Override
        public String getDescription() {
            return "%s, x%d Extra Shots".formatted(decorated.getDescription(), shots);
        }
    }
}
