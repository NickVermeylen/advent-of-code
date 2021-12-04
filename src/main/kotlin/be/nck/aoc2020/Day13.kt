package be.nck.aoc2020

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.Double.Companion.POSITIVE_INFINITY
import kotlin.math.floor


class Day13 : Day<List<String>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input: List<String> = Files.readAllLines(Paths.get("src/main/resources/2020/day13.txt"))
            Day13().run(input)
        }
    }

    override fun part1(input: List<String>): String {
        val earliest = input.first().toDouble()
        var min = POSITIVE_INFINITY
        var m = 0
        input.last().split(",").forEach {
            if (it != "x") {
                val departure = it.toInt()
                val d: Double = floor(earliest / departure)
                val v = (d + 1) * departure
                val delta = v - earliest
                if (delta < min) {
                    min = delta
                    m = departure
                }
            }
        }
        return (min * m).toString()
    }


    override fun part2(input: List<String>): String? {
        return "oops"
    }
}
