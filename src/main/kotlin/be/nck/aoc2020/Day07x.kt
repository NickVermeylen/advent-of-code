package be.nck.aoc2020

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*


class Day07x : Day<List<String>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input: List<String> = Files.readAllLines(Paths.get("src/main/resources/2020/day07.txt"))
            Day07x().run(input)
        }
    }


    val bagName = "shiny gold"

    override fun part1(input: List<String>): String {
        return input
                .map { Regex("(?<=(\\d )|^)[a-z]+ [a-z]+(?= bag)").findAllToList(it) }
                .map { s -> s.subList(1, s.size).map { Pair(s[0], it) } }
                .flatten()
                .groupBy { it.second }
                .mapValues { entry -> entry.value.map { it.first }.toSet() }
                .withDefault { setOf() }
                .countBags(bagName)
                .size
                .minus(1).toString()
    }

    override fun part2(input: List<String>): String {
        return input
                .map { Regex("(?<=(\\d )|^)[a-z]+ [a-z]+(?= bag)|\\d").findAllToList(it) }
                .associate {
                    it[0] to it.subList(1, it.size)
                            .chunked(2) { s -> Pair(s[1], s[0].toInt()) }
                }
                .withDefault { listOf() }
                .sumBags(bagName, 1)
                .minus(1).toString()
    }

    fun Map<String, Set<String>>.countBags(bag: String): Set<String> =
            this.getValue(bag)
                    .flatMapTo(HashSet()) { this.countBags(it) }
                    .plus(bag)

    fun Map<String, List<Pair<String, Int>>>.sumBags(bag: String, multiplier: Int): Int =
            this.getValue(bag)
                    .sumBy { this.sumBags(it.first, it.second * multiplier) }
                    .plus(multiplier)

    fun Regex.findAllToList(line: String): List<String> =
            this.findAll(line).map { matchResult -> matchResult.value }.toList()


}
