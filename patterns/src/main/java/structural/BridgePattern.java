package structural;

/**
 * This class demonstrates the bridge structural design pattern, which separates an abstraction from implementation,
 * allowing both to vary independently.
 *
 * <h4>Pattern Components:</h4>
 * <ul>
 *     <li><strong>Abstraction:</strong> Defines the API for interacting with a database.</li>
 *     <li><strong>Refined Abstraction (optional):</strong> Extends the abstraction to include more functionality.</li>
 *     <li><strong>Interface:</strong> Defines the interface for database drivers.</li>
 *     <li><strong>Concrete Interface:</strong> Implements the database driver interface.</li>
 * </ul>
 *
 * <h4>Key Benefits:</h4>
 * <ul>
 *     <li><strong>Decoupling:</strong> Abstraction and implementation can evolve independently</li>
 *     <li><strong>Simplicity:</strong> Abstraction encapsulates complex behaviors of the concrete interfaces</li>
 *     <li><strong>Flexibility:</strong> Abstraction can be extended to selectively support database features</li>
 *     <li><strong>Open/Closed Principle:</strong> Abstraction is open for extension, but closed for modification</li>
 *     <li><strong>Runtime Flexibility:</strong> Interfaces and abstractions can be swapped at runtime</li>
 * </ul>
 *
 * <h4>Example Usage:</h4>
 * <pre>{@code
 * var driver = new MySQLDriver(); // could be any driver for any database
 * var database = new Database(driver); // consistent API for interacting with the database
 *
 * database.createTable("users");
 * database.dropTable("users");
 * }
 */
public class BridgePattern {
    /**
     * The abstraction providing a consistent API for interacting with a database.
     */
    class Database {
        private final DatabaseDriver driver;

        /**
         * Constructor for the database abstraction, wrapping the specified driver.
         *
         * @param driver the database driver to use for interacting with the database.
         */
        public Database(DatabaseDriver driver) {
            this.driver = driver;
        }

        /**
         * An API for creating and dropping tables using the underlying database driver.
         *
         * @param tableName the name of the table to create or drop.
         */
        public void createTable(String tableName) {
            driver.connect();
            driver.createTable(tableName);
            driver.disconnect();
        }

        /**
         * An API for dropping tables using the underlying database driver.
         *
         * @param tableName the name of the table to drop.
         */
        public void dropTable(String tableName) {
            driver.connect();
            driver.dropTable(tableName);
            driver.disconnect();
        }
    }

    /**
     * The interface for database drivers usable by the database abstraction.
     */
    interface DatabaseDriver {
        /**
         * Connects to the database.
         */
        void connect();

        /**
         * Disconnects from the database.
         */
        void disconnect();

        /**
         * Drops the specified table from the database.
         *
         * @param tableName the name of the table to drop.
         */
        void dropTable(String tableName);

        /**
         * Creates a new table in the database.
         *
         * @param tableName the name of the table to create.
         */
        void createTable(String tableName);
    }

    /**
     * A concrete MySQL implementation of the database interface.
     */
    class MySQLDriver implements DatabaseDriver {
        @Override
        public void connect() {
            System.out.println("Connecting to MySQL...");
        }

        @Override
        public void disconnect() {
            System.out.println("Disconnecting from MySQL...");
        }

        @Override
        public void dropTable(String tableName) {
            System.out.println("Dropping table " + tableName + " from MySQL...");
        }

        @Override
        public void createTable(String tableName) {
            System.out.println("Creating table " + tableName + "in MySQL...");
        }
    }

    /**
     * A concrete PostgreSQL implementation of the driver interface.
     */
    class PostgreSQLDriver implements DatabaseDriver {
        @Override
        public void connect() {
            System.out.println("Connecting to PostgreSQL...");
        }

        @Override
        public void disconnect() {
            System.out.println("Disconnecting from PostgreSQL...");
        }

        @Override
        public void dropTable(String tableName) {
            System.out.println("Dropping table " + tableName + " from PostgreSQL...");
        }

        @Override
        public void createTable(String tableName) {
            System.out.println("Creating table " + tableName + " in PostgreSQL...");
        }
    }

    /**
     * A concrete Oracle implementation of the driver interface.
     */
    class OracleDriver implements DatabaseDriver {
        @Override
        public void connect() {
            System.out.println("Connecting to Oracle...");
        }

        @Override
        public void disconnect() {
            System.out.println("Disconnecting from Oracle...");
        }

        @Override
        public void dropTable(String tableName) {
            System.out.println("Dropping table " + tableName + " from Oracle...");
        }

        @Override
        public void createTable(String tableName) {
            System.out.println("Creating table " + tableName + " in Oracle...");
        }
    }
}
