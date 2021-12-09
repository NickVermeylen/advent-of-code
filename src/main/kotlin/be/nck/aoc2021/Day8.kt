package be.nck.aoc2021

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths

class Day8 : Day<List<Pair<String, String>>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input: List<String> = Files.readAllLines(Paths.get("src/main/resources/2021/day08.txt"))
            val signals = input.map { it.split(" | ") }.map { Pair(it[0], it[1]) }.toList()
            Day8().run(signals)
        }

        fun countEasyNumbers(input: List<Pair<String, String>>): Int {
            val parts = input.flatMap { part ->
                part.second.split(" ").map { it.strip() }
            }.toList()
            var counter = 0
            parts.forEach {
                when (it.length) {
                    2 -> counter++
                    3 -> counter++
                    4 -> counter++
                    7 -> counter++
                }
            }
            return counter
        }

        fun determineOutput(input: List<Pair<String, String>>): Int {
            val numbers =
                input.map { part ->
                part.first.split(" ").map { it.strip() }.map { it.toSet() } to part.second.split(" ").map { it.toSet()
            }}.toList()
            var sum = 0
            numbers.forEach { (signals, output) ->
            val mapping = mutableMapOf(
                1 to signals.first { it.size == 2 },
                4 to signals.first { it.size == 4 },
                7 to signals.first { it.size == 3 },
                8 to signals.first { it.size == 7 },
            )
            with(mapping) {
                put(3, signals.filter { it.size == 5 }.first { it.intersect(getValue(1)).size == 2 })
                put(2, signals.filter { it.size == 5 }.first { it.intersect(getValue(4)).size == 2 })
                put(5, signals.filter { it.size == 5 }.first { it !in values })
                put(6, signals.filter { it.size == 6 }.first { it.intersect(getValue(1)).size == 1 })
                put(9, signals.filter { it.size == 6 }.first { it.intersect(getValue(4)).size == 4 })
                put(0, signals.filter { it.size == 6 }.first { it !in values })
            }
            val mappedPatterns = mapping.entries.associateBy({ it.value }) { it.key }
            sum += output.joinToString("") { mappedPatterns.getValue(it).toString()}.toInt()
            }
        return sum
    }

}

override fun part1(input: List<Pair<String, String>>): String? {
    return countEasyNumbers(input).toString()
}

override fun part2(input: List<Pair<String, String>>): String? {
    return determineOutput(input).toString()
}
}