package be.nck.aoc2021

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths

lateinit var MAP: Map<String, String>
lateinit var template: Map<String, Long>
var lastChar: Char = '\u0000'

class Day14 : Day<Pair<String, Map<String, String>>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input = Files.readAllLines(Paths.get("src/main/resources/2021/day14.txt"))
            val second = input.drop(2).associate {
                val split = it.split(" -> ")
                split[0] to split[1]
            }
            MAP = second
            template = input.first()
                .windowed(2)
                .groupingBy { it }
                .eachCount().mapValues { it.value.toLong() }
            lastChar = input.first().last()
            val pair = Pair(input.first(), second)
            Day14().run(pair)
        }

        tailrec fun step(input: Pair<String, Map<String, String>>, steps: Int): String {
            var newTemplate = ""
            for (i in 0 until input.first.length) {
                newTemplate += input.first[i]
                if (i + 1 <= input.first.length - 1) {
                    val set = input.first[i].toString() + input.first[i + 1].toString()
                    newTemplate += input.second[set]
                }
            }
            if (steps - 1 == 0) {
                return newTemplate
            }
            return step(Pair(newTemplate, input.second), steps - 1)
        }

        private fun Map<String, Long>.react(): Map<String, Long> =
            buildMap {
                this@react.forEach { (pair, count) ->
                    val inserted = MAP.getValue(pair)
                    plus("${pair.first()}$inserted", count)
                    plus("$inserted${pair.last()}", count)
                }
            }

        private fun <T> MutableMap<T, Long>.plus(key: T, amount: Long) {
            this[key] = this.getOrDefault(key, 0L) + amount
        }

        private fun solve(iterations: Int): Long =
            (0 until iterations)
                .fold(template) { polymer, _ -> polymer.react() }
                .byCharFrequency()
                .values
                .sorted()
                .let { it.last() - it.first() }


        private fun Map<String, Long>.byCharFrequency(): Map<Char, Long> =
            this
                .map { it.key.first() to it.value }
                .groupBy({ it.first }, { it.second })
                .mapValues { it.value.sum() + if (it.key == lastChar) 1 else 0 }
    }

    override fun part1(input: Pair<String, Map<String, String>>): String? {
        return "null"
    }

    override fun part2(input: Pair<String, Map<String, String>>): String? {
        val solve = solve(40)
        return solve.toString()
    }
}