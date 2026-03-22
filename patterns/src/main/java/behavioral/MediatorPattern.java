package behavioral;

import static java.util.Objects.requireNonNull;

/**
 * The mediator pattern is a behavioral pattern that acts as a middleman between a set of components. It decouples
 * components from one another and forces them to only interact with each other through a mediator object.
 *
 * <h4>Pattern Components:</h4>
 * <ul>
 *     <li><strong>Component:</strong> An object that delegate interaction to the mediator.</li>
 *     <li><strong>Mediator:</strong> Defines the interface for how the mediator interacts with the components.</li>
 *     <li><strong>Concrete Mediator:</strong> A concrete implementation of the mediator interface.</li>
 * </ul>
 *
 * <h4>Key Benefits:</h4>
 * <ul>
 *     <li><strong>Single Responsibility:</strong> components only focus on their own behavior.</li>
 *     <li><strong>Open/closed Principal:</strong> components and mediators can be added without modifying existing code.</li>
 *     <li><strong>Decoupling:</strong> components are reusable and can be developed independently of the mediator.</li>
 * </ul>
 *
 * <h4>Example Usage:</h4>
 * <pre>{@code
 * // silly example but you could imagine this connected to some GUI
 * final var dialog = new ContactDialog();
 *
 * // fill in contact information
 * dialog.getName().setValue("John Doe");
 * dialog.getPhone().setValue("555-555-5555");
 * dialog.getEmail().setValue("johndoe@email.com");
 *
 * // agree to terms and conditions
 * dialog.getTerms().click();
 *
 * // submit the dialog form
 * dialog.submit.click(); // "Submitting contact details..."
 * }
 */
public class MediatorPattern {
    /**
     * The mediator interface defining the contract for the mediator.
     */
    interface Mediator {
        /**
         * Notifies the mediator of a component event.
         *
         * @param component the component that generated an event.
         * @param event     the event that occurred.
         */
        void notify(Object component, Event event);
    }

    /**
     * A complimentary sealed type for component events.
     */
    sealed interface Event {
        record Click() implements Event { }
        record Change(String value) implements Event { }
    }

    /**
     * A concrete component implementation for a button.
     */
    class Button {

        private final Mediator mediator;

        /**
         * @param mediator the mediator to notify when the button is clicked.
         */
        public Button(Mediator mediator) {
            this.mediator = mediator;
        }

        /**
         * Notifies the mediator that the button was clicked.
         */
        public void click() {
            mediator.notify(this, new Event.Click());
        }
    }

    /**
     * A concrete component implementation for a checkbox.
     */
    class Checkbox {

        private boolean checked;
        private final Mediator mediator;

        /**
         * @param mediator the mediator to notify when the checkbox is checked or unchecked.
         */
        public Checkbox(Mediator mediator) {
            this.checked = false;
            this.mediator = mediator;
        }

        /**
         * @return the current checked state of the checkbox.
         */
        public boolean isChecked() {
            return checked;
        }

        /**
         * Toggles the checked state of the checkbox and notifies the mediator.
         */
        public void click() {
            checked = !checked;

            mediator.notify(this, new Event.Click());
        }
    }

    /**
     * A concrete component implementation for a textbox.
     */
    class Textbox {

        private String value;
        private final Mediator mediator;

        /**
         * @param mediator the mediator to notify when the textbox value changes.
         */
        public Textbox(Mediator mediator) {
            this.value = "";
            this.mediator = mediator;
        }

        /**
         * @return the current value of the textbox.
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the textbox and notifies the mediator.
         *
         * @param value the new value of the textbox.
         */
        public void setValue(String value) {
            this.value = value;

            mediator.notify(this, new Event.Change(value));
        }
    }

    /**
     * A concrete mediator implementation for a contact dialog that handles contains a set of components and acts
     * as the middleman between them to coordinate their interactions.
     */
    class ContactDialog implements Mediator {

        private final Textbox name = new Textbox(this);
        private final Textbox email = new Textbox(this);
        private final Textbox phone = new Textbox(this);
        private final Checkbox terms = new Checkbox(this);
        private final Button submit = new Button(this);

        /**
         * @return the name textbox.
         */
        public Textbox getName() {
            return name;
        }

        /**
         * @return the email address textbox.
         */
        public Textbox getEmail() {
            return email;
        }

        /**
         * @return the phone textbox.
         */
        public Textbox getPhone() {
            return phone;
        }

        /**
         * @return the terms and conditions checkbox.
         */
        public Checkbox getTerms() {
            return terms;
        }

        /**
         * @return the submit button.
         */
        public Button getSubmit() {
            return submit;
        }

        /**
         * Notifies the mediator of a component event in order to submit contact information.
         *
         * @param component the component that generated an event.
         * @param event     the event that occurred.
         */
        @Override
        public void notify(Object component, Event event) {
            if (component == submit && event instanceof Event.Click) {
                requireNonNull(name.getValue(), "Name is required");
                requireNonNull(email.getValue(), "Email address is required");
                requireNonNull(phone.getValue(), "Phone number is required");
                if (!terms.checked) throw new IllegalStateException("Terms and conditions must be accepted");

                System.out.println("Submitting contact details...");
            }
        }
    }
}
