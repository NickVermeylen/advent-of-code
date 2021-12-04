package be.nck.aoc2021

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths

class Day3 : Day<List<String>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input: List<String> = Files.readAllLines(Paths.get("src/main/resources/2021/day03.txt"))
            Day3().run(input)
        }

        fun calculateBothBytes(input: List<String>): Pair<String, String> {
            val bitMap = getBitMap(input)
            var mostCommonByte = ""
            var leastCommonByte = ""
            bitMap.forEach { (key, value) ->
                run {
                    val eachCount = value.groupingBy { it }.eachCount()
                    mostCommonByte = mostCommonByte.plus(eachCount.maxBy { it.value }?.key)
                    leastCommonByte = leastCommonByte.plus(eachCount.minBy { it.value }?.key)
                }
            }
            return Pair(mostCommonByte, leastCommonByte)
        }

        private fun getBitMap(input: List<String>): MutableMap<Int, MutableList<Char>> {
            val bitMap = mutableMapOf<Int, MutableList<Char>>()
            input.forEach { byte ->
                var index = 0
                byte.forEach {
                    if (bitMap[index] == null) {
                        bitMap[index] = mutableListOf()
                    }
                    bitMap[index]?.add(it)
                    index++
                }
            }
            return bitMap
        }

        fun calculateO2Scrubber(input: List<String>): String {
            var bitMap = getBitMap(input)
            var o2 = input
            var index = 0
            while (index < input.first().length) {
                val eachCount = bitMap[index]!!.groupingBy { it }.eachCount()
                val max = eachCount.maxBy { it.value }
                val min = eachCount.minBy { it.value }
                val mostCommonBit = if(max!!.value != min!!.value) max.key else '1'
                o2 = o2.filter { it[index] == mostCommonBit }
                bitMap = getBitMap(o2)
                index++
            }

            return o2.first()
        }

        fun calculateCO2Scrubber(input: List<String>): String {
            var bitMap = getBitMap(input)
            var co2 = input
            var index = 0
            while (index < input.first().length) {
                val eachCount = bitMap[index]!!.groupingBy { it }.eachCount()
                val max = eachCount.maxBy { it.value }
                val min = eachCount.minBy { it.value }
                val leastCommonBit = if(max!!.value != min!!.value) min.key else '0'
                co2 = co2.filter { it[index] == leastCommonBit }
                if (co2.size < 2) break
                bitMap = getBitMap(co2)
                index++
            }

            return co2.first()
        }
    }

    override fun part1(input: List<String>): String? {
        val calculateBothBytes = calculateBothBytes(input)
        val gamma = calculateBothBytes.first.toInt(2)
        val upsilon = calculateBothBytes.second.toInt(2)
        return (gamma * upsilon).toString()
    }

    override fun part2(input: List<String>): String? {
        return (calculateO2Scrubber(input).toInt(2) * calculateCO2Scrubber(input).toInt(2)).toString()
    }
}