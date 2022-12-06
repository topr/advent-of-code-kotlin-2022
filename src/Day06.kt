fun main() {

    fun capture(marker: Int, input: String) = input
        .windowedSequence(marker)
        .indexOfFirst { it.toSet().size == marker } + marker

    fun part1(input: String) = capture(4, input)

    fun part2(input: String) = capture(14, input)

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