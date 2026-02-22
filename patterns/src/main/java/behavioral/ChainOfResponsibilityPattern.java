package behavioral;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * The chain of responsibility pattern is a behavioral design pattern that enables a request to be processed by
 * a chain of request handers. A handler can decide whether to process the request or pass it to the next handler
 * in the chain.
 *
 * <h4>Pattern Components:</h4>
 * <ul>
 *     <li><strong>Handler:</strong> An object that processes the request.</li>
 *     <li><strong>Request:</strong> An object that represents a request to be processed.</li>
 *     <li><strong>Client:</strong> An object that composes a chain of handlers to process a request.</li>
 * </ul>
 *
 * <h4>Key Benefits:</h4>
 * <ul>
 *     <li><strong>Runtime/Order Flexibility:</strong> Handlers can be swapped or reordered at runtime.</li>
 *     <li><strong>Open/Closed Principal:</strong> New handlers can be added without modifying existing code.</li>
 *     <li><strong>Single Responsibility:</strong> Handlers can focus on performing one action within the chain.</li>
 * </ul>
 *
 * <h4>Example Usage:</h4>
 * <pre>{@code
 * // for some given user
 * final var user = new User("johndoe", List.of("READ"));
 *
 * // create a set of authorization handlers
 * final var handlers = List.<AuthorizationHandler>of(
 *         new AdminHandler(),
 *         new BlacklistHandler(List.of("banned_user")),
 *         new PermissionHandler(Set.of("READ", "WRITE"))
 * );
 *
 * // use a chain to determine authorization
 * final var chain = new Chain(handlers);
 * if (chain.authorize(user)) {
 *     System.out.println("User is authorized!");
 * } else {
 *     System.out.println("User is not authorized.");
 * }
 *
 * // Output: User is authorized!
 * }
 */
public class ChainOfResponsibilityPattern {
    /**
     * The handler interface for authorizing a user.
     */
    interface AuthorizationHandler {
        /**
         * A default authorization implementation that passes the request to the next handler in the chain.
         *
         * @param user     the user to authorize.
         * @param iterator the iterator of remaining handlers in the chain.
         * @return the result of the authorization check.
         */
        default boolean authorize(User user, Iterator<AuthorizationHandler> iterator) {
            return iterator.hasNext() && iterator.next().authorize(user, iterator);
        }
    }

    /**
     * A concrete handler implementation that checks if a user is blacklisted.
     *
     * @param blacklist a list of blacklisted principals.
     */
    record BlacklistHandler(List<String> blacklist) implements AuthorizationHandler {
        /**
         * Checks if the user is not blacklisted.
         *
         * @param user     the user to authorize.
         * @param iterator the iterator of remaining handlers in the chain.
         * @return the result of the blacklist check and further checks.
         */
        @Override
        public boolean authorize(User user, Iterator<AuthorizationHandler> iterator) {
            return !blacklist.contains(user.principal()) && AuthorizationHandler.super.authorize(user, iterator);
        }
    }

    /**
     * A concrete handler implementation that checks if a user has the required permissions.
     *
     * @param permissions a set of required permissions.
     */
    record PermissionHandler(Set<String> permissions) implements AuthorizationHandler {
        /**
         * Checks if the user has all required permissions.
         *
         * @param user     the user to authorize.
         * @param iterator the iterator of remaining handlers in the chain.
         * @return the result of the permission check or further checks.
         */
        @Override
        public boolean authorize(User user, Iterator<AuthorizationHandler> iterator) {
            return permissions.containsAll(user.permissions()) || AuthorizationHandler.super.authorize(user, iterator);
        }
    }

    /**
     * A concrete handler implementation that checks if a user is an admin.
     */
    record AdminHandler() implements AuthorizationHandler {
        /**
         * Checks if the user is an admin.
         *
         * @param user     the user to authorize.
         * @param iterator the iterator of remaining handlers in the chain.
         * @return the result of the admin check or further checks.
         */
        @Override
        public boolean authorize(User user, Iterator<AuthorizationHandler> iterator) {
            return user.principal().equals("admin") || AuthorizationHandler.super.authorize(user, iterator);
        }
    }

    /**
     * The request object a handler can accept as input.
     *
     * @param principal   a principal string that uniquely identifies a user.
     * @param permissions a set of permissions associated with the principal.
     */
    record User(String principal, List<String> permissions) {}

    /**
     * The client object that holds the chain of handlers.
     *
     * @param handlers the list of handlers in the chain.
     */
    record Chain(List<AuthorizationHandler> handlers) {
        /**
         * Begins the chain of authorization checks.
         *
         * @param user the user to authorize.
         * @return the result of the chain of authorization checks.
         */
        public boolean authorize(User user) {
            Iterator<AuthorizationHandler> iterator = handlers.iterator();

            return iterator.hasNext() && iterator.next().authorize(user, iterator);
        }
    }
}
