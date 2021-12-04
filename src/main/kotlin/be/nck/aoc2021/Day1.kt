package be.nck.aoc2021

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths

class Day1 : Day<List<Int>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input: List<String> = Files.readAllLines(Paths.get("src/main/resources/2021/day01.txt"))
            val list = input.map { it.toInt() }
            Day1().run(list)
        }
    }

    override fun part1(input: List<Int>): String? {
        return calcNumberOfIncreases(input).toString()
    }

    fun calcNumberOfIncreases(input: List<Int>): Int {
        var increases = 0
        var previous = input.first()
        input.forEach {
            if (it > previous) increases++
            previous = it
        }
        return increases
    }

    override fun part2(input: List<Int>): String? {
        var increases = 0
        var previous = input[0] + input[1] + input[2]
        input.windowed(3, 1).forEach {
            if (it.sum() > previous) increases++
            previous = it.sum()
        }
        return increases.toString()
    }
}