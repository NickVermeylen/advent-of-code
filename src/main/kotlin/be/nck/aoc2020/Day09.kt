package be.nck.aoc2020

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths


class Day09 : Day<List<String>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input: List<String> = Files.readAllLines(Paths.get("src/main/resources/2020/day09.txt"))
            Day09().run(input)
        }
        const val preamble = 25
        var invalidNumber = 0L
    }

    override fun part1(input: List<String>): String {
        val xmas = input.map { it.toLong() }
        var index = 0
        xmas.forEach {
            var valid = false
            if(index >= preamble){
                val dropped = xmas.subList(index - preamble, index)
                dropped.forEach { number ->
                    if(dropped.contains(it-number)){
                        valid = true
                    }
                }
                if (!valid){
                    invalidNumber = it
                    return it.toString()
                }
            }
            index++
        }
        return "oops"
    }

//    fun part1(): Long {
//        val prevFive = ArrayDeque<Long>()
//        prevFive.addAll(input.subList(0, 25))
//        for (i in 25 until input.size) {
//            val elem = input[i]
//            if (!prevFive.any { prevFive.contains(elem - it) }) return elem
//            prevFive.removeFirst()
//            prevFive.addLast(elem)
//        }
//        return -1
//    }

    override fun part2(input: List<String>): String {
        val numbers = input.map { it.toLong() }
        for (i in numbers.indices) {
            for (j in i + 1 until numbers.size) {
                val range = numbers.subList(i, j+1)
                if (range.sum() == invalidNumber) return (range.minOrNull()!! + range.maxOrNull()!!).toString()
            }
        }
        return "oops"
    }
//    override fun part2(input: List<String>): String {
//        val numbers = input.map { it.toLong() }
//        val dp = Array(1001) { LongArray(1001) }
//        dp@for (i in 1 until numbers.size) {
//            for (k in 1..i) {
//                dp[i][k] = numbers[i-1] + dp[i-1][k]
//                if (dp[i][k] == invalidNumber && i != k) {
//                    numbers.subList(k-1, i).run { return (min()!! + max()!!).toString() }
//                    break@dp
//                }
//            }
//        }
//        return "oops"
//    }

}
