package be.nck.aoc2020

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths


class Day11b : Day<List<MutableList<Char>>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input: List<MutableList<Char>> = Files.readAllLines(Paths.get("src/main/resources/2020/day11.txt"))
                                                    .map { it.toCharArray().toMutableList() }
                                                    .toList()
            Day11b().run(input)
        }
    }

    override fun part1(input: List<MutableList<Char>>): String {
        return "oops"
    }


    override fun part2(input: List<MutableList<Char>>): String? {
        return "oops"
    }

    fun evolve(neighborCount: Int): Boolean {
        if (neighborCount >= 4) {
            return false
        }
        return true
    }

    data class Seat(
            var isOccupied: Boolean,
            val neighbors: MutableList<Seat> = arrayListOf()
    )

}
