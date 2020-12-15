package be.nck.aoc2020

import be.nck.utils.Day


class Day15 : Day<MutableList<Int>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input: MutableList<Int> = mutableListOf(0, 3, 6)
            Day15().run(input)
        }
    }

    override fun part1(input: MutableList<Int>): String {
        for (i in input.size - 1..2020) {
            val last = input[i]
            val prev = input.slice(IntRange(0, i - 1))
                .reversed()
                .indexOf(last)
            if (prev == -1) {
                input.add(0)
            } else {
                input.add(prev + 1)
            }
        }
        return input[2020 - 1].toString()
    }


    override fun part2(input: MutableList<Int>): String {
        val input2 = mutableListOf(8,0,17,4,1,12)
        val map = mutableMapOf<Int, Int>()
        input2.forEach {
            map[it] = input2.indexOf(it)
        }
        var last = input2.last()
        for (i in input2.size until 30000000) {
            var temp = 0
            val lastMapVal = map[last]
            if (lastMapVal != null) {
                temp = i - lastMapVal - 1
            }
            map[last] = i - 1
            last = temp
        }
        return last.toString()
    }
}
