package structural;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

/**
 * The adapter pattern is a structural design pattern that converts the interface of a class into another interface to
 * make it compatible with additional clients.
 * 
 * <h4>Pattern Overview</h4>
 * The adapter pattern allows classes to work together that couldn't otherwise because of incompatible interfaces. It
 * acts as a bridge between two incompatible interfaces without modifying the existing code.
 * 
 * <h4>Components in this Implementation</h4>
 * <ul>
 *   <li><b>Target Interface:</b> The interface that the client code expects to work with</li>
 *   <li><b>Adaptee:</b> The existing third-party service with an incompatible interface</li>
 *   <li><b>Adapter:</b> Bridges the gap between target interface and adaptee</li>
 * </ul>
 * 
 * <h4>Real-World Scenario</h4>
 * Imagine an e-commerce application that needs to support multiple payment gateways. Each payment provider (Stripe,
 * PayPal, Square) has its own API with different method names and parameter types. The adapter pattern allows us to
 * create a unified interface for all payment processors while maintaining compatibility with each provider.
 * 
 * <h4>Benefits</h4>
 * <ul>
 *   <li><b>Single Responsibility Principle:</b> Each adapter is responsible for bridging one specific interface</li>
 *   <li><b>Open/Closed Principle:</b> New payment processors can be added without modifying existing code</li>
 *   <li><b>Loose Coupling:</b> Client code doesn't depend on specific payment processor implementations</li>
 * </ul>
 * 
 * <h4>Usage Example</h4>
 * <pre>{@code
 * StripeService stripeService = new StripeServiceImpl();
 * PaymentProcessor processor = new StripePaymentAdapter(stripeService);
 * processor.process("ORDER123", new BigDecimal("99.99"));
 * }</pre>
 */
public class AdapterPattern {
    /**
     * Represents the interface that our application's client code expects to work with. This represents the
     * standardized payment processing interface across all payment providers.
     * <p>
     *
     * From a client's perspective, all payment processors implement this interface regardless of the underlying
     * payment service being used.
     */
    interface PaymentProcessor {
        /**
         * Processes a payment transaction.
         * 
         * @param id The unique transaction identifier
         * @param amount The payment amount using BigDecimal for precision in financial calculations
         */
        void process(String id, BigDecimal amount);
    }

    /**
     * Represents the third-party payment service interface. This is the existing component that does not conform to
     * our {@link PaymentProcessor} interface.
     */
    interface StripeService {
        /**
         * Creates a payment charge using Stripe's API.
         * 
         * @param id The transaction identifier
         * @param total The payment amount as a double
         */
        void create(String id, double total);
    }

    /**
     * Represents the adapter class that bridges the gap between the {@link PaymentProcessor} and {@link StripeService}.
     * <p>
     *
     * This class implements the {@link PaymentProcessor} interface that our client code expects, while internally
     * delegating calls to the {@link StripeService}. It handles the necessary transformations:
     *
     * <ul>
     *   <li>Method name translation: process() → create()</li>
     *   <li>Parameter type conversion: BigDecimal → double</li>
     * </ul>
     * 
     * <h4>Key Responsibilities:</h4>
     * <ul>
     *   <li>Implements the target interface (PaymentProcessor)</li>
     *   <li>Holds a reference to the adaptee (StripeService)</li>
     *   <li>Translates method calls and converts data types as needed</li>
     * </ul>
     * 
     * <h4>Potential Considerations:</h4>
     * <ul>
     *   <li><b>Performance:</b> Adds a layer of indirection which may impact performance</li>
     *   <li><b>Error Handling:</b> May need additional logic to handle conversion errors or null values</li>
     * </ul>
     */
    class StripePaymentAdapter implements PaymentProcessor {
        private final StripeService service;

        /**
         * Constructor injection of the {@link StripeService} dependency.
         * 
         * @param service The {@link StripeService} implementation to adapt
         * @throws IllegalArgumentException if the provided {@link StripeService} is null
         */
        StripePaymentAdapter(StripeService service) {
            this.service = requireNonNull(service, "stripe service cannot be null");
        }

        /**
         * Adapts the {@link PaymentProcessor#process} call to {@link StripeService#create}.
         * 
         * @param id The unique transaction identifier
         * @param amount The payment amount - converted from BigDecimal to double
         */
        @Override
        public void process(String id, BigDecimal amount) {
            service.create(id, amount.doubleValue());
        }
    }
}
