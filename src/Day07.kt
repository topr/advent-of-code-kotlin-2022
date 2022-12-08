import java.lang.UnsupportedOperationException

fun main() {

    fun part1(input: List<String>): Int {

        val topDirs = mutableMapOf<String, Int>()
        var current = "/"
        input.forEach {
            when {
                it == "$ cd /" -> "go to root"
                it == "$ ls" -> "no op"
                it.startsWith("$ cd") -> "go into a folder"
                it == "$ cd .." -> "go up to parent folder"
                it.startsWith("dir") -> "?"
                it.contains("^\\d+".toRegex()) -> "add file size"
                else -> throw UnsupportedOperationException()
            }
        }

        return 0
    }

    fun part2(input: List<String>) = 0

    val testInput = readInputLines("Day07_test")

    checkEqual(95437, part1(testInput))
//    checkEqual(-1, part2(testInput))


    val input = readInputLines("Day07")
    println(part1(input))
    println(part2(input))
}