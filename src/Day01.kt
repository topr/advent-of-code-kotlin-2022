import java.util.concurrent.atomic.AtomicInteger

fun main() {

    fun elfInventories(input: List<String>): List<Int> {

        val elves = ArrayList<AtomicInteger>().apply { add(AtomicInteger()) }

        input.forEach { line: String ->

            if (line.isEmpty()) {
                elves.add(AtomicInteger())
            } else {
                elves.last().addAndGet(line.toInt())
            }
        }

        return elves.map(AtomicInteger::get)
    }

    fun part1(input: List<String>): Int {
        return elfInventories(input).max()
    }

    fun part2(input: List<String>): Int {
        return elfInventories(input)
            .sortedDescending()
            .subList(0, 3)
            .sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}