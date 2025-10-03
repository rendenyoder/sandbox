package creational;

/**
 * The factory pattern is a creational design pattern that provides an interface for creating objects without
 * specifying their concrete classes.
 * <p>
 *
 * The factory method pattern promotes loose coupling by eliminating the need for clients to know the specific classes
 * they're instantiating. Instead, clients work with factory interfaces and product abstractions, making the system
 * more maintainable and extensible.
 *
 * <h4>Pattern Structure:</h4>
 * <ul>
 *     <li><strong>Product:</strong> Defines the interface for objects the factory creates</li>
 *     <li><strong>Concrete Products:</strong> Implement the product interface</li>
 *     <li><strong>Creator:</strong> Declares the factory method</li>
 *     <li><strong>Concrete Creators:</strong> Override the factory method</li>
 * </ul>
 *
 * <h4>Design Principles Supported:</h4>
 * <ul>
 *     <li><strong>Encapsulate what varies:</strong> Vehicle types may change or be added</li>
 *     <li><strong>Program to an interface:</strong> Clients use model and factory interfaces</li>
 *     <li><strong>Dependency Inversion:</strong> High-level modules depend on abstractions</li>
 *     <li><strong>Open/Closed Principle:</strong> New vehicle types can be easily added</li>
 *     <li><strong>Single Responsibility:</strong> Each factory is responsible on concrete type</li>
 * </ul>
 *
 * <h4>Pattern Benefits:</h4>
 * <ul>
 *     <li><strong>Loose Coupling:</strong> Clients interact with interfaces</li>
 *     <li><strong>Extensibility:</strong> New vehicle types only require new concrete factories</li>
 *     <li><strong>Localized Knowledge:</strong> Object creation is contained within factories</li>
 *     <li><strong>Runtime Flexibility:</strong> The object created can be determined at runtime</li>
 * </ul>
 *
 * <h4>Usage Example:</h4>
 * <pre>{@code
 * VehicleFactory carFactory = new CarFactory();
 * Vehicle vehicle = carFactory.createVehicle(); // Returns a Car instance
 * System.out.println("Created: " + vehicle.getType());
 * 
 * // Easy to switch factory types
 * VehicleFactory bikeFactory = new BikeFactory();
 * Vehicle anotherVehicle = bikeFactory.createVehicle(); // Returns a Bike instance
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
 *     <li>Each concrete factory should create exactly one type of product</li>
 *     <li>Products should implement a common interface</li>
 *     <li>Creation logic should be self-contained within factories</li>
 *     <li>Avoid external dependencies in object creation</li>
 * </ul>
 */
public class FactoryPattern {
    /**
     * Product interface defining the contract for all vehicles created by the factory.
     * 
     * @see VehicleFactory
     */
    interface Vehicle {
        /**
         * Returns the type identifier for this vehicle.
         *
         * @return a string representing the vehicle type
         */
        String getType();
    }

    /**
     * Creator interface that declares the factory method for creating vehicles.
     * 
     * @see Vehicle
     */
    interface VehicleFactory {
        /**
         * Factory method for creating vehicle objects.
         * <p>
         *
         * Concrete implementations must override this method to return specific vehicle instances.
         *
         * @return a new instance of a concrete {@link Vehicle} implementation
         */
        Vehicle createVehicle();
    }

    /**
     * Concrete vehicle implementation representing a car.
     */
    class Car implements Vehicle {
        @Override
        public String getType() {
            return "Car";
        }
    }

    /**
     * Concrete vehicle implementation representing a bike.
     */
    class Bike implements Vehicle {
        @Override
        public String getType() {
            return "Bike";
        }
    }

    /**
     * Concrete vehicle implementation representing a truck.
     */
    class Truck implements Vehicle {
        @Override
        public String getType() {
            return "Truck";
        }
    }

    /**
     * Factory implementation for creating car objects.
     */
    class CarFactory implements VehicleFactory {
        @Override
        public Car createVehicle() {
            return new Car();
        }
    }

    /**
     * Factory implementation for creating bike objects.
     */
    class BikeFactory implements VehicleFactory {
        @Override
        public Bike createVehicle() {
            return new Bike();
        }
    }

    /**
     * Factory implementation for creating truck objects.
     */
    class TruckFactory implements VehicleFactory {
        @Override
        public Truck createVehicle() {
            return new Truck();
        }
    }
}
