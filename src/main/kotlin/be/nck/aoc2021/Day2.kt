package be.nck.aoc2021

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths

class Day2 : Day<List<String>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input: List<String> = Files.readAllLines(Paths.get("src/main/resources/2021/day02.txt"))
            Day2().run(input)
        }
    }

    override fun part1(input: List<String>): String? {
        var x = 0
        var y = 0
        input.forEach {
            val split = it.split(" ")
            when (split[0]) {
                "forward" -> x += split[1].toInt()
                "down" -> y += split[1].toInt()
                "up" -> y -= split[1].toInt()
            }
        }
        return (x * y).toString()
    }

    override fun part2(input: List<String>): String? {
        var x = 0
        var y = 0
        var aim = 0
        input.forEach {
            val split = it.split(" ")
            when (split[0]) {
                "forward" -> {
                    x += split[1].toInt()
                    y += aim * split[1].toInt()
                }
                "down" -> aim += split[1].toInt()
                "up" -> aim -= split[1].toInt()
            }
        }
        return "$x, $y, ${x * y}"
    }
}