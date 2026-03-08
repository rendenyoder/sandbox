package behavioral;

import java.util.ArrayDeque;
import java.util.List;

/**
 * The command pattern is a behavioral design pattern that encapsulates a request as an object. This pattern allows
 * for the enqueueing and dispatching of requests, which can be executed at any time.
 *
 * <h4>Pattern Components:</h4>
 * <ul>
 *     <li><strong>Command Interface:</strong> Defines the contract for all commands.</li>
 *     <li><strong>Concrete Command:</strong> Defines the concrete behavior of a command.</li>
 *     <li><strong>Receiver:</strong> The object that often performs the backing behavior of a command.</li>
 *     <li><strong>Sender:</strong> The object that configures and executes commands.</li>
 *     <li><strong>Client:</strong> The object that interacts with the sender.</li>
 * </ul>
 *
 * <h4>Key Benefits:</h4>
 * <ul>
 *     <li><strong>Defer Execution:</strong> Commands can be queued and executed at a later time.</li>
 *     <li><strong>Open/Closed Principal:</strong> New commands can be added without modifying existing code.</li>
 *     <li><strong>Single Responsibility:</strong> Commands are by nature focused on performing a single action.</li>
 * </ul>
 *
 * <h4>Example Usage:</h4>
 * <pre>{@code
 * final var notifier = new ApplicationNotifier();
 * final var contacts = List.of(
 *         new Contact("johndoe@email.com", "555-555-5555"),
 *         new Contact("janedoe@email.com", "555-555-5555")
 * );
 *
 * contacts.forEach(contact -> notifier.queue(contact, "Hello, this is a test message."));
 * notifier.dispatch();
 * }
 */
public class CommandPattern {
    /**
     * The interface defining the contract for all commands.
     */
    interface Command {
        /**
         * Executes the command.
         *
         * @throws Exception if the command execution fails.
         */
        void execute() throws Exception;
    }

    /**
     * A command that retries a task upon failure.
     *
     * @param count the number of retries attempted.
     * @param command the command to retry.
     */
    record RetryCommand(int count, Command command) implements Command {
        /**
         * Executes the given command.
         * @throws Exception if the command execution fails.
         */
        @Override
        public void execute() throws Exception {
            command.execute();
        }
    }

    /**
     * A command that sends an SMS to a contact.
     *
     * @param sender the message dispatcher that will send the message.
     * @param contact the contact to send the message to.
     * @param message the body of the message.
     */
    record SendSmsCommand(MessageDispatcher sender, Contact contact, String message) implements Command {
        /**
         * Sends the SMS to the contact.
         *
         * @throws Exception if the message sending fails.
         */
        @Override
        public void execute() throws Exception {
            sender.dispatchSMS(contact, message);
        }
    }

    /**
     * A command that sends an SMS or email to a contact.
     *
     * @param sender the message dispatcher that will send the message.
     * @param contact the contact to send the message to.
     * @param message the body of the message.
     */
    record SendEmailCommand(MessageDispatcher sender, Contact contact, String message) implements Command {
        /**
         * Sends the email to the contact.
         *
         * @throws Exception if the message sending fails.
         */
        @Override
        public void execute() throws Exception {
            sender.dispatchEmail(contact, message);
        }
    }

    /**
     * A contact with an email and phone number.
     *
     * @param email the email address of the contact.
     * @param phone the phone number of the contact.
     */
    record Contact(String email, String phone) {}

    /**
     * A message dispatcher that functions as the receiver of the command behavior.
     */
    class MessageDispatcher {
        /**
         * Sends an SMS to the contact.
         *
         * @param contact the contact to send the SMS to.
         * @param message the body of the SMS.
         * @throws Exception if the SMS sending fails.
         */
        public void dispatchSMS(Contact contact, String message) throws Exception {
            System.out.printf("Sent SMS to %s: %s%n", contact.email, message);
        }

        /**
         * Sends an email to the contact.
         *
         * @param contact the contact to send the email to.
         * @param message the body of the email.
         * @throws Exception if the email sending fails.
         */
        public void dispatchEmail(Contact contact, String message) throws Exception{
            System.out.printf("Sent email to %s: %s%n", contact.email, message);
        }
    }

    /**
     * A queued application notifier functioning as the sender of the commands.
     */
    class ApplicationNotifier {

        private final MessageDispatcher sender = new MessageDispatcher();
        private final ArrayDeque<Command> queue = new ArrayDeque<>();

        /**
         * Queues a set of commands to send an SMS and an email message to the contact.
         *
         * @param contact the contact to send the message to.
         * @param message the message to include in the message.
         */
        public void queue(Contact contact, String message) {
            queue.add(new SendSmsCommand(sender, contact, message));
            queue.add(new SendEmailCommand(sender, contact, message));
        }

        /**
         * Dispatches all queued commands, retrying failed commands up to 3 times.
         */
        public void dispatch() {
            while (!queue.isEmpty()) {
                final var command = queue.poll();

                try {
                    command.execute();
                } catch (Exception ex) {
                    if (command instanceof RetryCommand retry && retry.count >= 3) {
                        System.out.printf("Command failed after 3 attempts: %s%n", command);
                        continue;
                    }

                    System.out.printf("Retrying command: %s%n", command);
                    if (command instanceof RetryCommand(int count, Command task)) {
                        queue.add(new RetryCommand(count + 1, task));
                    } else {
                        queue.add(new RetryCommand(1, command));
                    }
                }
            }
        }
    }
}
