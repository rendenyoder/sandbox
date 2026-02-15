package structural;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.hash;

/**
 * The flyweight structural design pattern reduces memory usage or computational cost by sharing as much data as
 * possible with other similar objects. This pattern is primarily used with large data sets where the same data is
 * represented multiple times.
 *
 * <h4>Pattern Components:</h4>
 * <ul>
 *     <li><strong>Flyweight:</strong> The object that holds the intrinsic state.</li>
 *     <li><strong>Context:</strong> The object that holds the extrinsic state and a reference to a flyweight.</li>
 *     <li><strong>Factory:</strong> A factory for controlling the creation of flyweight and context objects.</li>
 *     <li><strong>Client:</strong> The object that interacts with the factory to get context objects.</li>
 * </ul>
 *
 * <h4>Key Benefits:</h4>
 * <ul>
 *     <li><strong>Memory</strong> Reduces the number of objects needed to represent the same data.</li>
 *     <li><strong>Performance</strong> Reduces the number of times a flyweight object needs to be created.</li>
 * </ul>
 *
 * <h4>Example Usage:</h4>
 * <pre>{@code
 * final var plot = new ScatterPlot();
 *
 * // create a million points (intrinic state is created once for all points)
 * for (int i = 0; i < 1_000_000; i++) {
 *     plot.add(i, i, 1, Color.RED);
 * }
 *
 * plot.draw();
 * }
 */
public class FlyweightPattern {
    /**
     * The context object, containing the extrinsic state of a point as well as the intrinsic state, via the
     * {@link PointType} flyweight object.
     *
     * @param x     the x-coordinate of the point (an extrinsic property).
     * @param y     the y-coordinate of the point (an extrinsic property).
     * @param type  a set of common characteristics describing the point (i.e., intrinsic reference).
     */
    record Point(int x, int y, PointType type) { }

    /**
     * The flyweight object, containing the intrinsic state of a point.
     *
     * @param size   the size of the point (an intrinsic property).
     * @param color  the color of the point (an intrinsic property).
     */
    record PointType(int size, Color color) { }

    /**
     * A factory that creates context objects and their flyweight references based on the intrinsic state provided.
     */
    class PointFactory {

        private final Map<Integer, PointType> flyweights = new HashMap<>();

        /**
         * Returns a context object, conditionally creating a flyweight based on the provided intrinsic state. It
         * should be noted that a set could be used in this specific scenario. However, a map is used here to
         * highlight that a new flyweight is only created for a unique set of intrinsic state.
         *
         * @param x      the x-coordinate of the point.
         * @param y      the y-coordinate of the point.
         * @param size   the size of the point.
         * @param color  the color of the point.
         * @return the context object containing the extrinsic and intrinsic state of the point.
         */
        public Point create(int x, int y, int size, Color color) {
            final var key = hash(size, color);
            final var type = flyweights.computeIfAbsent(key, __ -> new PointType(size, color));

            return new Point(x, y, type);
        }
    }

    /**
     * The client of the flyweight pattern, conceptually plotting millions of points in a scatter plot.
     */
    class ScatterPlot {

        private final PointFactory factory = new PointFactory();
        private final List<Point> points = new ArrayList<>(1_000_000);

        /**
         * A stub to represent the drawing of points to the scatter plot.
         */
        public void draw() {
            // draws each point...
        }

        /**
         * Adds a point to the scatter plot, abstracting the intrinsic and extrinsic state of a point away from
         * the client by providing a simple API for plotting points.
         *
         * @param x     the x-coordinate of the point.
         * @param y     the y-coordinate of the point.
         * @param size  the size of the point.
         * @param color the color of the point.
         */
        public void add(int x, int y, int size, Color color) {
            points.add(factory.create(x, y, size, color));
        }
    }
}
