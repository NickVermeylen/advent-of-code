package be.nck.utils

interface Day<T> {
    fun run(input: T) {
//        part1(input)
//        part2(input)
        val start = System.currentTimeMillis()
        println(String.format("Part 1 result: %s", part1(input)))
        val part1 = System.currentTimeMillis()
        println("Part 1 duration (ms): " + (part1 - start))
        println("---------------------------------------------------")
        println(String.format("Part 2 result: %s", part2(input)))
        val end = System.currentTimeMillis()
        println("Part 2 duration (ms): " + (end - part1))
    }

    fun part1(input: T): String?
    fun part2(input: T): String?
}
