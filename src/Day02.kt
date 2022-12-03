import HandShape.Companion.handShape

enum class HandShape(val shapeScore: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    fun defeats(other: HandShape) = when (this) {
        ROCK -> other == SCISSORS
        PAPER -> other == ROCK
        SCISSORS -> other == PAPER
    }

    companion object {

        fun of(char: Char) = when (char) {
            'A', 'X' -> ROCK
            'B', 'Y' -> PAPER
            'C', 'Z' -> SCISSORS
            else -> throw IllegalArgumentException("$char")
        }

        fun handShape(predicate: (HandShape) -> Boolean) = values().find(predicate)!!
    }
}

enum class NeededResult {
    DRAW, LOSE, WIN;

    fun resolveBy(other: HandShape) = when (this) {
        DRAW -> other
        LOSE -> handShape { other.defeats(it) }
        WIN -> handShape { it.defeats(other) }
    }

    companion object {

        private val neededResults = mapOf('X' to LOSE, 'Y' to DRAW, 'Z' to WIN)

        fun of(char: Char) = neededResults[char]!!
    }
}

fun main() {

    fun play(opponent: HandShape, me: HandShape) =
        if (opponent.defeats(me)) 0
        else if (me.defeats(opponent)) 6
        else 3

    fun roundScore(opponent: HandShape, me: HandShape) =
        play(opponent, me) + me.shapeScore

    fun part1(input: List<String>) = input.sumOf {
        val opponent = HandShape.of(it.first())
        val me = HandShape.of(it.last())

        roundScore(opponent, me)
    }

    fun part2(input: List<String>) = input.sumOf {
        val opponent = HandShape.of(it.first())
        val me = NeededResult.of(it.last()).resolveBy(opponent)

        roundScore(opponent, me)
    }

    val testInput = readInputLines("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInputLines("Day02")
    println(part1(input))
    println(part2(input))
}