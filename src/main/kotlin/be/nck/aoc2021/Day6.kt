package be.nck.aoc2021

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths

class Day6 : Day<List<Int>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var input: List<String> = Files.readAllLines(Paths.get("src/main/resources/2021/day06.txt"))
            input = input.first().split(',')
            val ages = input.map { it.toInt() }.toList()
            Day6().run(ages)
        }
        tailrec fun interateDay(input: Map<Int, Long>, days:Int): Map<Int, Long> {
            val ages = input.toMutableMap()
            val newAges = mutableMapOf<Int, Long>()
            newAges[6] = (ages[7]?:0) + (ages[0]?:0)
            for (i in 0..5) {
                newAges[i] = ages[i+1]?:0
            }
            newAges[7] = ages[8]?:0
            newAges[8] = ages[0]?:0
            for (i in 0..8) {
                ages[i] = newAges[i]?:0
            }
            return if (days - 1 < 1){
                ages
            } else{
                interateDay(ages, days-1)
            }
        }
    }

    override fun part1(input: List<Int>): String? {
        val input1 = input.groupingBy { it }.eachCount().mapValues { it.value.toLong() }
        return interateDay(input1, 80).values.sum().toString()
    }

    override fun part2(input: List<Int>): String? {
        val input1 = input.groupingBy { it }.eachCount().mapValues { it.value.toLong() }
        return interateDay(input1, 256).values.sum().toString()
    }



}