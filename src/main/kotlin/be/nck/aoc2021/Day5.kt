package be.nck.aoc2021

import be.nck.utils.Day
import java.lang.Double.sum
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.math.abs
import kotlin.math.max

class Day5 : Day<List<String>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input: List<String> = Files.readAllLines(Paths.get("src/main/resources/2021/day05.txt"))
            Day5().run(input)
        }
    }

    data class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {
        fun pointsToList(): List<Pair<Int, Int>> {
            val dx = if (x1 == x2) 0 else if (x1 < x2) 1 else -1
            val dy = if (y1 == y2) 0 else if (y1 < y2) 1 else -1
            val nSteps = max(dx * (x2 - x1), dy * (y2 - y1))
            return (0..nSteps).map { (x1 + dx * it) to (y1 + dy * it) }
        }

        fun isDiagonal() = x1 != x2 && y1 != y2
    }

    fun countVents(input: List<String>, diagonals: Boolean): Int {
        return input.flatMap {
            val (x1, y1, x2, y2) = """\d+""".toRegex().findAll(it).map { found -> found.value.toInt() }.toList()
            val line = Line(x1, y1, x2, y2)
            if (diagonals || !line.isDiagonal()) line.pointsToList() else listOf()
        }.groupingBy { it }.eachCount().count { it.value > 1 }
    }

    override fun part1(input: List<String>): String? {
        return countVents(input, diagonals = false).toString()
    }

    override fun part2(input: List<String>): String? {
        return countVents(input, diagonals = true).toString()
    }



}