package be.nck.aoc2020

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths


class DayCinqo : Day<List<String>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input: List<String> = Files.readAllLines(Paths.get("src/main/resources/2020/day05.txt"))
            DayCinqo().run(input)
        }
    }

    override fun part1(input: List<String>): String {
        val seatIds = getSeats(input)
        return seatIds.max().toString()
    }

    private fun getSeats(input: List<String>): MutableList<Int> {
        val seatIds = emptyList<Int>().toMutableList()
        input.forEach {
            var rowHi = 127
            var rowLo = 0
            var colHi = 7
            var colLo = 0
            it.split("").forEach { char ->
                val rowMid = (rowLo + rowHi).div(2)
                val colMid = (colLo + colHi).div(2)
                when (char) {
                    "F" -> rowHi = rowMid
                    "B" -> rowLo = rowMid
                    "L" -> colHi = colMid
                    "R" -> colLo = colMid
                }
            }
            seatIds.add(8 * rowHi + colHi)
        }
        return seatIds
    }

    override fun part2(input: List<String>): String {
        val seats = getSeats(input)
        val endInclusive = seats.max()!!.toInt() + 1
        val all = IntRange(seats.min()!!, endInclusive).toMutableSet()
        all.removeAll(seats)
        return all.first().toString()
    }
}

