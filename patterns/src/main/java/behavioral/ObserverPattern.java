package behavioral;

import java.util.LinkedList;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * The observer pattern is a behavioral design pattern that defines a one-to-many dependency between objects
 * so that when one object changes state, all its dependents are notified and updated automatically.
 *
 * <h4>Key Benefits</h4>
 * <ul>
 *   <li><strong>Loose Coupling:</strong> Subjects and observers interact with minimal knowledge of each other</li>
 *   <li><strong>Inversion of Control:</strong> Observers register for notifications instead of polling for changes</li>
 *   <li><strong>Open/Closed Principle:</strong> New observer types can be added without modifying the subject</li>
 *   <li><strong>Runtime Flexibility:</strong> Observers can subscribe and unsubscribe dynamically</li>
 * </ul>
 *
 * <h4>Pattern Structure</h4>
 * <ul>
 *   <li><strong>Subject:</strong> Interface defining subscription management and notification methods</li>
 *   <li><strong>ConcreteSubject:</strong> Maintains the observer list and notifies on state changes</li>
 *   <li><strong>Observer:</strong> Interface defining the update contract for notifications</li>
 *   <li><strong>ConcreteObserver:</strong> Implements specific response to subject notifications</li>
 * </ul>
 *
 * <h4>Usage Example</h4>
 * <pre>{@code
 * // Create event publisher
 * EventPublisher publisher = new EventPublisher();
 *
 * // Subscribe observers using lambda expressions
 * publisher.subscribe(event -> System.out.println("Observer 1: " + event.name()));
 * publisher.subscribe(event -> System.out.println("Observer 2: " + event.name()));
 *
 * // Publish event - all observers are notified
 * publisher.update(new Event(1, "System Started"));
 * }</pre>
 *
 * <h4>Common Use Cases</h4>
 * <ul>
 *   <li>Event-driven systems and user interface components</li>
 *   <li>Model-View architectures where views react to model changes</li>
 *   <li>Notification systems for stock updates, news feeds, or alerts</li>
 *   <li>Real-time monitoring and logging systems</li>
 * </ul>
 *
 * <h4>Limitations</h4>
 * <ul>
 *   <li>Memory leaks if observers are not properly unsubscribed</li>
 *   <li>Notification order is not guaranteed and may cause inconsistent states</li>
 *   <li>Performance overhead with large numbers of observers</li>
 *   <li>Debugging can be complex with cascading notifications</li>
 * </ul>
 */
public class ObserverPattern {
    /**
     * Subject interface that defines the contract for objects that can be observed.
     * <p>
     *
     * This interface provides the fundamental operations for managing observers and notifying them of changes.
     * Subjects maintain a collection of observers and are responsible for notifying them when relevant
     * state changes occur.
     *
     * @param <T> the type of data that will be sent to observers during notifications
     */
    interface Subject<T> {
        /**
         * Subscribes an observer to receive notifications from this subject.
         *
         * @param observer the observer to subscribe; must not be null
         */
        void subscribe(Observer<T> observer);

        /**
         * Unsubscribes an observer from receiving notifications.
         *
         * @param observer the observer to unsubscribe; null values are ignored
         */
        void unsubscribe(Observer<T> observer);

        /**
         * Notifies all subscribed observers with the provided value.
         *
         * @param value the data to send to all observers; null values are ignored
         */
        void update(T value);
    }

    /**
     * A Functional interface representing an observer in the observer pattern.
     * <p>
     *
     * This interface defines the contract for objects that want to be notified of changes in a subject. The functional
     * interface annotation enables the use of lambda expressions and method references for cleaner, more concise
     * observer implementations.
     *
     * @param <T> the type of data the observer expects to receive
     */
    @FunctionalInterface
    interface Observer<T> {
        /**
         * Called when the subject this observer is subscribed to has an update.
         *
         * @param value the updated value from the subject
         */
        void onupdate(T value);
    }

    /**
     * Immutable record representing an event with an ID and name.
     */
    record Event(long id, String name) {}

    /**
     * Concrete subject implementation of the interface for publishing events.
     */
    class EventPublisher implements Subject<Event> {
        private final List<Observer<Event>> observers = new LinkedList<>();

        /**
         * Subscribes an observer to receive event notifications.
         *
         * @param observer the observer to subscribe; must not be null
         */
        @Override
        public void subscribe(Observer<Event> observer) {
            requireNonNull(observer, "an observer cannot be null");

            observers.add(observer);
        }

        /**
         * Unsubscribes an observer from receiving event notifications.
         *
         * @param observer the observer to unsubscribe; null values are safely ignored
         */
        @Override
        public void unsubscribe(Observer<Event> observer) {
            if (observer == null) return;

            observers.remove(observer);
        }

        /**
         * Publishes an event to all subscribed observers.
         *
         * @param value the event to publish; null values are safely ignored
         */
        @Override
        public void update(Event value) {
            if (value == null) return;

            for (Observer<Event> observer : observers) {
                observer.onupdate(value);
            }
        }
    }
}
