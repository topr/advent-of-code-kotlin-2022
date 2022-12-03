fun main() {

    fun caloriesPerInventory(inventory: String): Int = inventory.lines().sumOf(String::toInt)

    fun elfInventories(input: String): List<Int> = input.split("\n\n").map(::caloriesPerInventory)

    fun part1(input: String): Int = elfInventories(input).max()

    fun part2(input: String): Int = elfInventories(input).sorted().takeLast(3).sum()

    val testInput = readInputText("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInputText("Day01")
    println(part1(input))
    println(part2(input))
}