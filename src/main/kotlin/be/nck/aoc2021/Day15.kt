package be.nck.aoc2021

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths

class Day15: Day<List<List<Int>>> {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val grid = parse()
            Day15().run(grid)
        }

        private fun parse(): List<List<Int>> {
            val input: List<String> = Files.readAllLines(Paths.get("src/main/resources/2021/day15.txt"))
            val grid = input.map { it.map { it.toString().toInt() }.toList() }.toList()
            return grid
        }

        private tailrec fun solveRecursive(input: List<List<Int>>, grid: Map<Point, Int>): Int {
            val w = input.first().size
            val h = input.size

            val newGrid = grid
                .flatMap { (point, cost) ->
                    getNeighbours(
                        x = point.first,
                        y = point.second,
                        input.first().size,
                        input.size
                    ).map { (x, y) -> (x to y) to input[y][x] + cost } + listOf(point to cost)
                }
                .groupBy { it.first }
                .mapValues { (_, value) -> value.minOf { pp -> pp.second } }
                .toMap()

            if (newGrid == grid) {
                return grid[(h - 1) to (w - 1)]!!
            }
            println("here we go")
            return solveRecursive(input, newGrid)
        }

        private fun getNeighbours(x: Int, y: Int, w: Int, h: Int) = listOf(
            x - 1 to y,
            x to y - 1,
            x + 1 to y,
            x to y + 1
        ).filter { (x, y) -> x >= 0 && y >= 0 && x < w && y < h }

        private fun enlargeMap(ys: List<List<Int>>): List<List<Int>> {
            val something = ys.map { xs -> (0..4).fold(emptyList<Int>()) { acc, i -> acc + enlargeRowTimes(xs, i) } }

            return (0..4).fold(emptyList()) { acc, i -> acc + something.map { enlargeRowTimes(it, i) } }
        }

        private tailrec fun enlargeRowTimes(xs: List<Int>, times: Int): List<Int> = when (times) {
            0 -> xs
            1 -> enlargeRow(xs)
            else -> enlargeRowTimes(enlargeRow(xs), times - 1)
        }

        private fun enlargeRow(xs: List<Int>) = xs.map { new ->
            when (new + 1) {
                10 -> 1
                else -> new + 1
            }
        }
    }

    override fun part1(input: List<List<Int>>): String? {
        return solveRecursive(input, mapOf((0 to 0) to 0)).toLong().toString()
    }

    override fun part2(input: List<List<Int>>): String? {
        return solveRecursive(enlargeMap(input), mapOf((0 to 0) to 0)).toLong().toString()
    }
}

typealias Point = Pair<Int, Int>