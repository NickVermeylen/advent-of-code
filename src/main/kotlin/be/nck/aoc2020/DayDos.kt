package be.nck.aoc2020

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths


class DayDos : Day<List<String>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input: List<String> = Files.readAllLines(Paths.get("src/main/resources/2020/day02.txt"))
            DayDos().run(input)
        }
    }

    override fun part1(input: List<String>): String? {
        var valid = 0;
        input.forEach {
            val list = it.split(" ")
            val range = list[0].split("-")
            val char = list[1].toCharArray()
            val pass = list[2]
            val count = pass.count { it == char[0] }
            if (range[0].toInt() <= count && count <= range[1].toInt()) {
                valid++
            }

        }
        return valid.toString()
    }

    override fun part2(input: List<String>): String? {
        var valid = 0;
        input.forEach {
            val list = it.split(" ")
            val range = list[0].split("-")
            val char = list[1].toCharArray()
            val pass = list[2].toCharArray()
            if (pass[range[0].toInt()-1] == char[0] && pass[range[1].toInt()-1] != char[0]) {
                valid++
            }
            if (pass[range[0].toInt()-1] != char[0] && pass[range[1].toInt()-1] == char[0]) {
                valid++
            }

        }
        return valid.toString()
    }
}

