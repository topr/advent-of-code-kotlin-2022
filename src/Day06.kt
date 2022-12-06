import java.util.*

fun main() {

    data class Marker(val limit: Int, private val captured: Queue<Char> = LinkedList()) {

        fun receive(character: Char): Boolean = with(captured) {
            if (size == limit) remove()
            add(character)

            return distinct().size == limit
        }
    }

    fun part1(input: String) = input.indexOfFirst(Marker(4)::receive) + 1

    fun part2(input: String) = input.indexOfFirst(Marker(14)::receive) + 1

    val testInput = readInputLines("Day06_test")

    listOf(7, 5, 6, 10, 11).forEachIndexed { i, expected ->
        part1(testInput[i]).let {
            check(it == expected) { "expected: <$expected>, got: <$it>" }
        }
    }

    listOf(19, 23, 23, 29, 26).forEachIndexed { i, expected ->
        part2(testInput[i]).let {
            check(it == expected) { "expected: <$expected>, got: <$it>" }
        }
    }

    val input = readInputText("Day06")
    println(part1(input))
    println(part2(input))
}