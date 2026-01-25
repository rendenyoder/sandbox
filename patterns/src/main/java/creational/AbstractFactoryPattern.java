package creational;

/**
 * The abstract factory pattern is a creational design pattern for creating families of related or dependent objects.
 * <p>
 *
 * This pattern promotes loose coupling by eliminating the need for clients to know the specific classes and groups of
 * classes they're instantiating. Instead, clients work with abstract factory interfaces and product abstractions,
 * making the system more maintainable and extensible.
 *
 * <h4>Pattern Structure:</h4>
 * <ul>
 *     <li><strong>Product:</strong> Defines the interface for the objects the factories create</li>
 *     <li><strong>Concrete Products:</strong> Implements the product interface</li>
 *     <li><strong>Abstract Creator:</strong> Defines the interface and factory methods</li>
 *     <li><strong>Concrete Creators:</strong> Overrides the factory method</li>
 * </ul>
 *
 * <h4>Design Principles:</h4>
 * <ul>
 *     <li><strong>Encapsulate what varies:</strong> Element and operating system types may change or be added</li>
 *     <li><strong>Program to an interface:</strong> Clients use model and factory interfaces</li>
 *     <li><strong>Dependency Inversion:</strong> High-level modules depend on abstractions</li>
 *     <li><strong>Open/Closed Principle:</strong> New element types can be easily added</li>
 *     <li><strong>Single Responsibility:</strong> Each factory is responsible for a group of concrete types</li>
 * </ul>
 *
 * <h4>Pattern Benefits:</h4>
 * <ul>
 *     <li><strong>Loose Coupling:</strong> Clients interact with interfaces</li>
 *     <li><strong>Extensibility:</strong> New element types only require new concrete factory methods</li>
 *     <li><strong>Localized Knowledge:</strong> Object creation is contained within factories</li>
 *     <li><strong>Runtime Flexibility:</strong> The object created can be determined at runtime</li>
 * </ul>
 *
 * <h4>Usage Example:</h4>
 * <pre>{@code
 * var operatingSystem = System.getProperty("os.name").toLowerCase();
 *
 * ElementFactory factory;
 * if (operatingSystem.contains("mac")) {
 *     factory = new MacUIFactory();
 * } else if (operatingSystem.contains("win")) {
 *     factory = new WinUIFactory();
 * } else {
 *     throw new IllegalStateException("Unsupported operating system: " + operatingSystem);
 * }
 *
 * // Returns a Button instance based on the operating system
 * var button = factory.createButton();
 * ...
 * }</pre>
 *
 * <h4>When to Use This Pattern:</h4>
 * <ul>
 *     <li>The exact type of object to create is determined at runtime</li>
 *     <li>You want to localize the knowledge of which classes to create</li>
 *     <li>You want to provide a library of products and only reveal their interfaces</li>
 *     <li>The system needs to be independent of how its products are created</li>
 *     <li>You need to delegate object creation to subclasses</li>
 * </ul>
 *
 * <h4>Implementation Guidelines:</h4>
 * <ul>
 *     <li>Each concrete factory should create exactly one grouping of product</li>
 *     <li>Products should implement a common interface</li>
 *     <li>Creation logic should be self-contained within each factory</li>
 *     <li>Avoid external dependencies in object creation</li>
 * </ul>
 */
public class AbstractFactoryPattern {
    /**
     * A product interface for all UI elements.
     *
     * @see ElementFactory
     */
    interface Element {
    }

    /**
     * An abstract factory interface for creating UI elements.
     *
     * @see MacElementFactory
     * @see WinElementFactory
     */
    interface ElementFactory {
        Element createButton();
        Element createMenuButton();
        Element createRadioButton();
    }

    /**
     * A group of concrete product UI elements for macOS.
     *
     * @see MacElementFactory
     */
    sealed interface MacElement extends Element {
        record Button() implements MacElement { }
        record MenuButton() implements MacElement { }
        record RadioButton() implements MacElement { }
    }

    /**
     * A group of concrete product UI elements for Windows.
     *
     * @see WinElementFactory
     */
    sealed interface WinElement extends Element {
        record Button() implements WinElement { }
        record MenuButton() implements WinElement { }
        record RadioButton() implements WinElement { }
    }

    /**
     * A concrete factory for creating UI elements for macOS.
     */
    class MacElementFactory implements ElementFactory {
        @Override
        public MacElement.Button createButton() {
            return new MacElement.Button();
        }

        @Override
        public MacElement.MenuButton createMenuButton() {
            return new MacElement.MenuButton();
        }

        @Override
        public MacElement.RadioButton createRadioButton() {
            return new MacElement.RadioButton();
        }
    }

    /**
     * A concrete factory for creating UI elements for windows.
     */
    class WinElementFactory implements ElementFactory {
        @Override
        public WinElement.Button createButton() {
            return new WinElement.Button();
        }

        @Override
        public WinElement.MenuButton createMenuButton() {
            return new WinElement.MenuButton();
        }

        @Override
        public WinElement.RadioButton createRadioButton() {
            return new WinElement.RadioButton();
        }
    }
}
