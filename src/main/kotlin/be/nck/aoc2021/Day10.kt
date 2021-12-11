package be.nck.aoc2021

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

        val scoreMap = mapOf(
            ')' to 1,
            ']' to 2,
            '}' to 3,
            '>' to 4
        )

        fun findSyntaxErrors(input: List<String>): MutableList<Char> {
            val syntaxErrors = mutableListOf<Char>()
            input.forEach line@{ line ->
                val errorsOnLine = findErrorsOnLine(line).first
                if (errorsOnLine.isNotEmpty())
                syntaxErrors.add(errorsOnLine.first())
            }
            return syntaxErrors
        }

        fun scoreIncompleteLines(input: List<String>): List<Long>{
            val scores = mutableListOf<Long>()
            input.forEach { line ->
                val findErrorsOnLine = findErrorsOnLine(line)
                if (findErrorsOnLine.first.isEmpty()){
                    val incompletePart = findErrorsOnLine.second
                    var score: Long = 0
                    incompletePart.reversed().forEach { score = (5 * score + scoreMap[it]!!) }
                    scores.add(score)
                }
            }
            return scores
        }

        private fun findErrorsOnLine(line: String): Pair<List<Char>, List<Char>> {
            val errors = mutableListOf<Char>()
            val openingChars = mutableListOf<Char>()
            line.forEach { char ->
                if (char == '{' || char == '[' || char == '(' || char == '<') {
                    openingChars.add(charMap[char]!!)
                } else {
                    if (openingChars.last() != char) {
                        errors.add(char)
                    } else openingChars.removeLast()
                }
            }
            return Pair(errors, openingChars)
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
        val sortedScores = scoreIncompleteLines(input).sorted()
        return sortedScores[sortedScores.size / 2].toString()
    }
}