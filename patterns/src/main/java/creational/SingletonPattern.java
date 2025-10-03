package creational;

/**
 * The singleton is a creational pattern that ensures a class has at most one instance and provides a global point of
 * access to this instance.
 * <p>
 *
 * <h4>Motivation:</strong>
 * This pattern is particularly useful when you need to ensure that only one instance of a class exists throughout the
 * application lifecycle. Common use cases include:
 *
 * <ul>
 *     <li>Configuration managers that need consistent settings across the application</li>
 *     <li>Connection pools that manage database or network connections</li>
 *     <li>Logging services that coordinate output destinations and formats</li>
 *     <li>Hardware interface access (like printer services) that prevent conflicts</li>
 * </ul>
 *
 * <h4>Design Components:</h4>
 * <ul>
 *     <li>Private constructor: Prevents external instantiation</li>
 *     <li>Private static instance: Holds the single instance of the class</li>
 *     <li>Public static getInstance() method: Provides global access point</li>
 * </ul>
 *
 * <h4>Implementation Details:</h4>
 * This implementation uses eager initialization, where the instance is created at class loading time. This approach is
 * thread-safe by default and ensures the instance is ready when first accessed, following the principle of fail-fast
 * initialization.
 *
 * <h4>Benefits:</h4>
 * <ul>
 *     <li>Reduced memory usage: Only one instance exists throughout the application</li>
 *     <li>Guaranteed single instance: Ensures consistency across all consumers</li>
 *     <li>Global access point: Provides centralized access to the instance</li>
 *     <li>Thread safety: Eager initialization is inherently thread-safe</li>
 * </ul>
 *
 * <h4>Potential Limitations:</h4>
 * <ul>
 *     <li>Global state: Can make it difficult to track state changes</li>
 *     <li>Testing challenges: Persistent state can affect unit test isolation</li>
 * </ul>
 *
 * <strong>Usage Example:</strong>
 * <pre>{@code
 * Singleton instance1 = Singleton.getInstance();
 * Singleton instance2 = Singleton.getInstance();
 *
 * instance1.setValue("Configuration Data");
 * System.out.println(instance2.getValue()); // Prints: Configuration Data
 *
 * System.out.println(instance1 == instance2); // Prints: true
 * }
 * </pre>
 */
public class SingletonPattern {
    /**
     * The single instance of the {@link SingletonPattern} class.
     * <p>
     *
     * This static final field ensures that only one instance of the {@link SingletonPattern} exists throughout the
     * application lifecycle.
     */
    private static final SingletonPattern instance = new SingletonPattern();

    /**
     * The value stored in this singleton instance.
     * <p>
     *
     * This field demonstrates how the singleton maintains state that is consistent across all access points in the
     * application. Any changes to this value will be visible to all consumers of the singleton.
     */
    private String value;

    /**
     * Private constructor to prevent external instantiation.
     * <p>
     *
     * By making the constructor private, we ensure that the {@link SingletonPattern} class cannot be instantiated from
     * outside the class. This is the cornerstone of the singleton pattern's "one instance" guarantee.
     */
    private SingletonPattern() {}

    /**
     * Returns the value stored in this singleton instance.
     *
     * @return the current value stored in the singleton, or null if not set
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value in this singleton instance.
     *
     * @param value the new value to store in the singleton
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Returns the single instance of the {@link SingletonPattern} class.
     * <p>
     *
     * This is the global access point to the singleton instance. It provides controlled access to the single instance
     * and ensures that the same object is returned on every call.
     *
     * @return the single instance of the {@link SingletonPattern} class
     */
    public static SingletonPattern getInstance() {
        return instance;
    }
}
