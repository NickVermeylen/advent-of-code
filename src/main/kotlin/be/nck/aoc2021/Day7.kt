package be.nck.aoc2021

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.concurrent.fixedRateTimer
import kotlin.math.abs
import kotlin.math.absoluteValue

class Day7 : Day<List<Int>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var input: List<String> = Files.readAllLines(Paths.get("src/main/resources/2021/day07.txt"))
            input = input.first().split(',')
            val positions = input.map { it.toInt() }.toList()
            Day7().run(positions)
        }

        fun alignHorizontallyLeastFuel(input: List<Int>): Pair<Int, Int> {
            val i = (input.size - 1) / 2
            val targetPosition = input.sorted()[i]
            return alignAndCalculateFuel(input, targetPosition)
        }

        private fun alignAndCalculateFuel(input: List<Int>, targetPosition: Int): Pair<Int, Int> {
            var fuel = 0
            input.forEach {
                fuel += abs(it - targetPosition)
            }
            return Pair(targetPosition, fuel)
        }

        fun alignHorizontallyCrabFuel(positions: List<Int>): Pair<Int, Int> {
            val mean = positions.sum() / positions.size
            val first = calculateCrabFuel(positions, mean)
            val second = calculateCrabFuel(positions, mean+1)
            return if(first.second > second.second) second else first
        }

        private fun calculateCrabFuel(positions: List<Int>, mean: Int): Pair<Int, Int> {
            var fuel = 0
            positions.forEach { pos ->
                fuel += (pos - mean).absoluteValue.let { it * (it + 1) / 2 }
            }
            return Pair(mean, fuel)
        }

    }

    override fun part1(input: List<Int>): String? {
        return alignHorizontallyLeastFuel(input).toString()
    }

    override fun part2(input: List<Int>): String? {
        return alignHorizontallyCrabFuel(input).toString()
    }
}