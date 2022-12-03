fun main() {

    val allItems = (('a'..'z') + ('A'..'Z')).toSet()

    fun commonItem(compartments: Iterable<String>) = compartments
        .map(CharSequence::toSet)
        .fold(allItems) { acc, compartment -> acc.intersect(compartment) }
        .first()

    fun priority(item: Char): Int =
        item.code.inc() -
            if (item.isUpperCase())
                'A'.code - priority('z')
            else
                'a'.code

    fun part1(input: List<String>) = input
        .map { it.chunked(it.length / 2) }
        .map(::commonItem)
        .sumOf(::priority)

    fun part2(input: List<String>) = input
        .chunked(3)
        .map(::commonItem)
        .sumOf(::priority)


    val testInput = readInputLines("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInputLines("Day03")
    println(part1(input))
    println(part2(input))
}