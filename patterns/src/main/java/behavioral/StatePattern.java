package behavioral;

/**
 * The state pattern is a behavioral design pattern that allows an object to alter its behavior based on its internal
 * state.
 *
 * <h4>Key Benefits</h4>
 * <ul>
 *   <li><strong>Eliminates Complex Conditionals:</strong> Replaces if/else blocks and switch statements with clean object-oriented design</li>
 *   <li><strong>Single Responsibility Principle:</strong> Each state class handles only its specific behavior and transitions</li>
 *   <li><strong>Open/Closed Principle:</strong> New states can be added without modifying existing code</li>
 *   <li><strong>Enhanced Maintainability:</strong> State-specific logic is organized into dedicated, cohesive classes</li>
 * </ul>
 *
 * <h4>Pattern Structure</h4>
 * <ul>
 *   <li><strong>State Interface:</strong> Defines common methods that all concrete states must implement</li>
 *   <li><strong>ConcreteState:</strong> Encapsulates behavior specific to a particular state and handles transitions</li>
 *   <li><strong>Context:</strong> Maintains reference to current state and delegates state-specific behavior</li>
 * </ul>
 *
 * <h4>Usage Example</h4>
 * <pre>{@code
 * // Create a document workflow
 * Document document = new Document();
 * 
 * // Progress through document states
 * document.next();              // Draft → InProgress
 * document.setReviewed(true);   // Mark as reviewed
 * document.next();              // InProgress → Complete
 * }</pre>
 *
 * <h4>Common Use Cases</h4>
 * <ul>
 *   <li>Document workflow systems with approval stages</li>
 *   <li>Media player states (play, pause, stop, rewind)</li>
 *   <li>Traffic light systems and state machines</li>
 *   <li>Online gaming state management (lobby, playing, paused)</li>
 * </ul>
 *
 * <h4>Limitations</h4>
 * <ul>
 *   <li>Class explosion: Each state requires a separate class implementation</li>
 *   <li>Potential tight coupling between states and context if not carefully designed</li>
 *   <li>Circular dependencies possible when states directly reference context</li>
 *   <li>Overkill for simple state machines with minimal behavior differences</li>
 * </ul>
 */
public class StatePattern {
    /**
     * Represents the state interface that defines the contract for all document states.
     * <p>
     *
     * This sealed interface ensures compile-time safety by restricting implementations. Each state implementation
     * encapsulates the behavior and transition logic specific to that particular state in the document workflow.
     */
    sealed interface State {
        /**
         * Defines state transition behavior that all concrete states must implement.
         *
         * @param document the context object that maintains the current state
         */
        void next(Document document);
    }

    /**
     * Concrete state representing the initial draft phase of a document.
     */
    final class Draft implements State {
        /**
         * Transitions from {@link Draft} to {@link InProgress} state unconditionally.
         *
         * @param document the context object whose state will be changed
         */
        @Override
        public void next(Document document) {
            document.state = new InProgress();
        }
    }

    /**
     * Concrete state representing active document development phase.
     */
    final class InProgress implements State {
        /**
         * Conditionally transitions to {@link Complete} state based on review status. Only reviewed documents can
         * progress to completion.
         *
         * @param document the context object whose state will be changed
         */
        @Override
        public void next(Document document) {
            if (!document.reviewed) return;

            document.state = new Complete();
        }
    }

    /**
     * Concrete state representing the completed document phase.
     */
    final class Complete implements State {
        /**
         * Conditionally transitions back to {@link InProgress} state when review status is lost.
         *
         * @param document the context object whose state will be changed
         */
        @Override
        public void next(Document document) {
            if (document.reviewed) return;

            document.state = new InProgress();
        }
    }

    /**
     * Context class representing a document in the workflow management system.
     * <p>
     *
     * This class maintains a reference to the current {@link State} and delegates all state-specific behavior to the
     * appropriate implementation. This separation allows the context instance to dynamically change its behavior as it
     * progresses through different workflow stages.
     */
    class Document {
        private boolean reviewed = false;
        private State state = new Draft();

        /**
         * Updates the document's review status.
         *
         * @param reviewed true if the document has been reviewed, false otherwise
         */
        public void setReviewed(boolean reviewed) {
            this.reviewed = reviewed;
        }

        /**
         * Triggers state transition based on current state and document conditions.
         * <p>
         *
         * This method delegates transition logic to the current state implementation, allowing each state to determine
         * its own progression rules and target states.
         */
        public void next() {
            state.next(this);
        }
    }
}
