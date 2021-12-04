package be.nck.aoc2020

import be.nck.utils.Day


class Day20 : Day<MutableList<Int>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input: MutableList<Int> = mutableListOf(8,0,17,4,1,12)
            Day20().run(input)
        }
    }

    override fun part1(input: MutableList<Int>): String {

        return input[2020 - 1].toString()
    }


    override fun part2(input: MutableList<Int>): String {
        return "null"
    }

    sealed class Tile(
        val id: Long,
        val image: List<List<Boolean>>
    ) {
        val tp: Long = 0
        val bttm: Long = 0
        val lft: Long = 0
        val rght: Long = 0


    }
}
