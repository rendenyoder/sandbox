package behavioral;

import java.util.ArrayDeque;

/**
 * The memento pattern is a behavioral design pattern that allows an originator to be restored to a previous state
 * using a snapshot of its internal state which does not expose its internal structure. A snapshot is an object that
 * contains enough information to restore the originator to a previous state. Once a snapshot is created, it can be
 * stored and later used to restore an originator to a previous state by a caretaker.
 *
 * <h4>Pattern Components:</h4>
 * <ul>
 *     <li><strong>Originator:</strong> The object that creates snapshots of its state.</li>
 *     <li><strong>Caretaker:</strong> The object that manages the history of snapshots.</li>
 *     <li><strong>Memento:</strong> A snapshot of the state of the originator at a specific point in time.</li>
 * </ul>
 *
 * <h4>Key Benefits:</h4>
 * <ul>
 *     <li><strong>Single Responsibility:</strong> Caretaker only focuses on managing the history of snapshots.</li>
 *     <li><strong>Encapsulation:</strong> Originator state is encapsulated within mementos without exposing directly.</li>
 * </ul>
 *
 * <h4>Example Usage:</h4>
 * <pre>{@code
 * final var editor = new Editor();
 * final var history = new History(editor);
 *
 * editor.setContent("Hello, world!");
 * editor.setCursor(10, 20);
 * history.snapshot();
 *
 * editor.setContent("Goodbye, world!");
 * editor.setCursor(5, 15);
 * history.snapshot();
 *
 * editor.setContent("Hello again!");
 * editor.setCursor(15, 30);
 *
 * history.undo(); // content = "Goodbye, world!", x, y = (5, 15)
 * history.undo(); // content = "Hello, world!", x, y = (10, 20)
 * }</pre>
 */
public class MementoPattern {
    /**
     * An editor representing an originator of a memento which can be used to restore the editor to a previous state.
     */
    public final class Editor {

        private int cursorX = 0;
        private int cursorY = 0;
        private String content = "";

        /**
         * Sets the content of the editor.
         *
         * @param content the new content of the editor.
         */
        public void setContent(String content) {
            this.content = content;
        }

        /**
         * Sets the cursor position.
         *
         * @param cursorX the new cursor position for the x-axis.
         * @param cursorY the new cursor position for the y-axis.
         */
        public void setCursor(int cursorX, int cursorY) {
            this.cursorX = cursorX;
            this.cursorY = cursorY;
        }

        /**
         * Creates a snapshot of the current state of the editor.
         *
         * @return a snapshot of the current state of the editor.
         */
        public Snapshot snapshot() {
            return new Snapshot(content, cursorX, cursorY);
        }

        /**
         * Restores the editor to a previous state.
         *
         * @param snapshot the snapshot to restore the editor to.
         */
        public void restore(Snapshot snapshot) {
            this.content = snapshot.content;
            this.cursorX = snapshot.cursorX;
            this.cursorY = snapshot.cursorY;
        }

        /**
         * A memento representing the state of the editor at a specific point in time.
         */
        public static final class Snapshot {

            private final int cursorX;
            private final int cursorY;
            private final String content;

            private Snapshot(String content, int cursorX, int cursorY) {
                this.content = content;
                this.cursorX = cursorX;
                this.cursorY = cursorY;
            }
        }
    }

    /**
     * The caretaker in the memento pattern, responsible for managing the history of editor states.
     */
    public final class History {

        private final Editor editor;
        private final ArrayDeque<Editor.Snapshot> snapshots;

        /**
         * Constructs a history object for the given editor.
         *
         * @param editor the editor to manage history for.
         */
        public History(Editor editor) {
            this.editor = editor;
            this.snapshots = new ArrayDeque<>();
        }

        /**
         * Reverts the editor to the last snapshot.
         */
        public void undo() {
            if (snapshots.isEmpty()) return;

            editor.restore(snapshots.pop());
        }

        /**
         * Adds a snapshot of the editor to the history stack.
         */
        public void snapshot() {
            snapshots.addFirst(editor.snapshot());
        }
    }
}
