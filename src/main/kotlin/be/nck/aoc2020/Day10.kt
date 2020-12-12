package be.nck.aoc2020

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths


class Day10 : Day<List<Int>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input: List<Int> = Files.readAllLines(Paths.get("src/main/resources/2020/day10.txt")).map { it.toInt() }
            Day10().run(input)
        }
    }

    override fun part1(input: List<Int>): String {
        var delta3 = 0
        var delta1 = 0
        var currentJolt = 0
        input.stream().sorted().forEach {
            if (it - currentJolt == 1) {
                delta1++
                currentJolt = it
            } else if (it - currentJolt == 3) {
                delta3++
                currentJolt = it
            }
        }
        delta3++
        return (delta1 * delta3).toString()
    }


    override fun part2(input: List<Int>): String {
//        var l1 = 0L
//        var l2 = 0L
//        var l3 = 0L
//        val array = arrayOfNulls<Long>(input.max()!! + 1)
//        array[0] = 1
//        input.sorted().forEach { adapter ->
//            l1 = if (adapter - 1 >= 0) array[adapter - 1]!! else 0
//            l2 = if (adapter - 2 >= 0) array[adapter - 2]!! else 0
//            l3 = if (adapter - 3 >= 0) array[adapter - 3]!! else 0
//            array[adapter] = l1 + l2 + l3
//        }
//        return array[-1].toString()
        val differences = input.map { it.toInt() }
                .toMutableList()
                .apply { add(0); add(maxOrNull()!! + 3) }
                .sorted()
                .zipWithNext { a, b -> b - a }

        // Different variations only happen with at least two ones in a row, count the length of all sequences of one.
        var onesInARow = 0
        val sequencesOfOnes = differences
                .map { if (it == 1) 0.also { onesInARow++ } else onesInARow.also { onesInARow = 0 } }
                .filter { it > 1 }

        // We're in luck, the input never has more than 4 ones in a row. We don't need any generic
        // formula for calculating the length, we can just count by hand how many possibilities
        // each sequence generate. Then we just multiply everything to get the total number of combinations.
        return sequencesOfOnes.map {
            when (it) {
                2 -> 2L
                3 -> 4L
                4 -> 7L
                else -> error("unsupported amount: $it")
            }
        }.reduce(Long::times).toString()
    }
}
