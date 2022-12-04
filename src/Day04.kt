import kotlin.math.max
import kotlin.math.min

fun main() {

    operator fun ClosedRange<Int>.contains(other: ClosedRange<Int>) =
        this.start <= other.start && this.endInclusive >= other.endInclusive

    fun ClosedRange<Int>.overlaps(other: ClosedRange<Int>) =
        max(this.start, other.start) - min(this.endInclusive, other.endInclusive) <= 0

    fun toRange(assignment: String) = assignment
        .split('-')
        .let { it.first().toInt()..it.last().toInt() }

    fun assignmentPairs(inputLine: String) = inputLine
        .split(',')
        .map(::toRange)
        .zipWithNext()
        .first()

    fun part1(input: List<String>) = input
        .map(::assignmentPairs)
        .count {
            it.run { first in second || second in first }
        }

    fun part2(input: List<String>) = input
        .map(::assignmentPairs)
        .count { it.first.overlaps(it.second) }

    val testInput = readInputLines("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInputLines("Day04")
    println(part1(input))
    println(part2(input))
}