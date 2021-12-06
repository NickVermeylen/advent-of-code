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
        tailrec fun interateDay(input: List<Int>, days:Int): MutableList<Int> {
            val ages = input.toMutableList()
            val newFish = mutableListOf<Int>()
            var index = 0
            ages.forEach {
                if (it == 0){
                    ages[index] = 6
                    newFish.add(8)
                } else {
                    ages[index] = it - 1
                }
                index++
            }
            ages.addAll(newFish)
            return if (days - 1 < 1){
                ages
            } else{
                interateDay(ages, days-1)
            }
        }
    }

    override fun part1(input: List<Int>): String? {
        return interateDay(input, 80).size.toString()
    }

    override fun part2(input: List<Int>): String? {
        return interateDay(input, 256).size.toString()
    }



}