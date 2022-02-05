package be.nck.aoc2021

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths

class Day12: Day<List<String>> {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val input: List<String> = Files.readAllLines(Paths.get("src/main/resources/2021/day12.txt"))
            Day12().run(input)
        }

        fun findAllPaths(input: List<String>): List<String> {
            return emptyList<String>()
        }
    }
    override fun part1(input: List<String>): String? {
        return findAllPaths(input).size.toString()
    }

    override fun part2(input: List<String>): String? {
        TODO("Not yet implemented")
    }
}