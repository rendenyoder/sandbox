package stacks

/**
 * There are "n" cars traveling to the same destination on a one-lane highway.
 *
 * You are given two arrays of integers position and speed, both of the same lengths. Each index of position is the
 * position of the car (in miles), while each index of speed is the speed of the car (in miles per hour).
 *
 * The destination is at the given target, in miles.
 *
 * A car cannot pass another car ahead of it. It can only catch up to another car and then drive at the same speed
 * as the car ahead of it. A car fleet is a non-empty set of cars driving at the same position and same speed. A single
 * car is also considered a car fleet.
 *
 * If a car catches up to a car fleet the moment the fleet reaches the destination, then the car is considered
 * to be part of the fleet.
 *
 * Return the number of different car fleets that will arrive at the destination.
 *
 * Constraints:
 * - All the values of position are unique.
 * - `n == position.length == speed.length`
 * - `1 <= n <= 105`
 * - `0 < target <= 106`
 * - `0 <= position[i] < target`
 * - `0 < speed[i] <= 106`
 */
class CarFleet {
    /**
     * This solution determines the total number of car fleets arriving at the [target] destination with a time
     * complexity of O(n⋅log(n)) and a space complexity of O(n).
     *
     * First, a stack representing the lead cars per-fleet is instantiated, and the positions and speed values are
     * zipped and sorted in descending order according to the position.
     *
     * For each value pairing, the total time to reach the target destination is determined. If the stack is currently
     * empty or the time taken for the current car to reach the destination is greater than the top of the stack, the
     * time is added to the stack. In this way, we are able to track the leading car per fleet.
     *
     * After all cars have been considered, the length of the stack is returned as the number of fleets that will
     * reach the destination.
     */
    fun carFleet(target: Int, position: IntArray, speed: IntArray): Int {
        val stack = ArrayDeque<Double>()
        val cars = position.zip(speed).sortedByDescending { (pos) -> pos }

        for ((pos, spd) in cars) {
            val time = (target - pos) / spd.toDouble()

            if (stack.isEmpty() || time > stack.last()) {
                stack.add(time)
            }
        }

        return stack.size
    }
}
