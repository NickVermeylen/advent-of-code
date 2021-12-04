package be.nck.aoc2020

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths


class Day07 : Day<List<String>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input: List<String> = Files.readAllLines(Paths.get("src/main/resources/2020/day07.txt"))
            Day07().run(input)
        }
        val bags = mutableMapOf<String, Map<String, String>>()
    }

    override fun part1(input: List<String>): String {
        input.forEach { line ->
            val innerBags = mutableMapOf<String, String>()
            val splitVals = line.trim().replace(".\n", "").replace("bags", "bag").replace(" bag", "")
                    .split(" contain ")
            splitVals[1].split(", ").filter { !it.contains("no other") }
                    .forEach {
                        innerBags[it.split(" ", limit = 2)[1]] = it.split(" ", limit = 2)[0]
                    }
            bags.put(splitVals[0], innerBags)
        }

        return optionsFor("shiny gold").count().toString()
    }
    fun optionsFor(s: String): List<String> {
        val options = bags.filter { (_, v) ->
            v.any { it.key == "shiny gold" }
        }.map { it.key }.flatMap { optionsFor(it) }.toMutableList()
        options.add(s)
        return options.distinct().toList()
    }




    override fun part2(input: List<String>): String {
        return "oops"
    }
}
