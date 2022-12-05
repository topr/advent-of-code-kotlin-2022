import java.util.Stack

data class Stacks(private val stacks: List<Stack<Char>>) {

    constructor(numberOfStacks: Int) : this((0 until numberOfStacks).map { Stack() })

    fun topCrates() = stacks
        .map(Stack<Char>::peek)
        .joinToString("")

    fun moveOneByOne(move: Move) = repeat(move.amount) {
        stack(move.to).add(stack(move.from).pop())
    }

    fun moveAllAtOnce(move: Move) {
        stack(move.to).addAll(stack(move.from).popAll(move.amount))
    }

    fun add(inputLine: String) {
        stacks.forEachIndexed { index, stack ->
            inputLine[index * 4 + 1]
                .takeIf(Char::isLetter)
                ?.let(stack::add)
        }
    }

    private fun stack(position: Int) = stacks[position - 1]

    private fun <E> Stack<E>.popAll(amount: Int) = amount
        .downTo(1)
        .map { pop() }
        .reversed()
}

data class Move(
    val amount: Int,
    val from: Int,
    val to: Int
)

fun main() {

    fun stacksOf(stacksInput: String): Stacks {
        val startingStacks = stacksInput.lines().toMutableList().asReversed()
        val numberOfStacks = startingStacks.removeFirst().trim().last().digitToInt()

        return Stacks(numberOfStacks).also {
            startingStacks.forEach(it::add)
        }
    }

    fun movesOf(rearrangementInput: String) = rearrangementInput.lines()
        .filterNot(String::isBlank)
        .map { it.split(' ') }
        .map {
            Move(
                it[1].toInt(),
                it[3].toInt(),
                it[5].toInt()
            )
        }

    fun rearrange(stacks: Stacks, moves: List<Move>, action: (Stacks, Move) -> Unit) =
        stacks.apply {
            moves.forEach { action(this, it) }
        }

    fun part1(stacksInput: String, rearrangementInput: String) =
        rearrange(
            stacksOf(stacksInput),
            movesOf(rearrangementInput),
            Stacks::moveOneByOne
        ).topCrates()

    fun part2(stacksInput: String, rearrangementInput: String) =
        rearrange(
            stacksOf(stacksInput),
            movesOf(rearrangementInput),
            Stacks::moveAllAtOnce
        ).topCrates()

    fun splitInput(input: String) = input.split("\n\n")

    val (testStacksInput, testRearrangementInput) = readInputText("Day05_test").let(::splitInput)
    check(part1(testStacksInput, testRearrangementInput) == "CMZ")
    check(part2(testStacksInput, testRearrangementInput) == "MCD")

    val (stacksInput, rearrangementInput) = readInputText("Day05").let(::splitInput)
    println(part1(stacksInput, rearrangementInput))
    println(part2(stacksInput, rearrangementInput))
}