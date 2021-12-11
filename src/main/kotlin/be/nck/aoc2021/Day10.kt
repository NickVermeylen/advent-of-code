package be.nck.aoc2021

import be.nck.aoc2021.Day8.Companion.determineOutput
import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths

class Day10 : Day<List<String>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input: List<String> = Files.readAllLines(Paths.get("src/main/resources/2021/day10.txt"))
            Day10().run(input)
        }

        val charMap = mapOf(
            '(' to ')',
            '[' to ']',
            '{' to '}',
            '<' to '>'
        )

        fun findSyntaxErrors(input: List<String>): MutableList<Char> {
            val errors = mutableListOf<Char>()
            input.forEach line@{ line ->
                val openingChars = mutableListOf<Char>()
                line.forEach { char ->
                    if (char == '{' || char == '[' || char == '(' || char == '<') {
                        openingChars.add(charMap[char]!!)
                    } else {
                        if (openingChars.last() != char){ errors.add(char); return@line}
                        else openingChars.removeLast()
                    }
                }
            }
            return errors
        }
    }

    override fun part1(input: List<String>): String? {
        return findSyntaxErrors(input).map {
            when (it) {
                ')'-> 3
                ']'-> 57
                '}'-> 1197
                '>'-> 25137
                else -> 0
            }
        }.sum().toString()
    }

    override fun part2(input: List<String>): String? {
        return "null"
    }
}