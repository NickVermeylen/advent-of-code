package be.nck.aoc2020

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths


class Day06 : Day<List<String>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input: List<String> = Files.readAllLines(Paths.get("src/main/resources/2020/day06.txt"))
            Day06().run(input)
        }
    }

    override fun part1(input: List<String>): String {
        val groups = mutableListOf<List<String>>()
        var answers = mutableListOf<String>()
        var counter = 0
        input.forEach {
            if (it == "") {
                groups.add(counter, answers.distinct())
                answers = mutableListOf()
                counter++
            } else {
                val elements = it.split("").toMutableList()
                elements.removeFirst()
                elements.removeLast()
                answers.addAll(elements)
            }
        }
        groups.add(counter, answers.distinct())
        var sum = 0
        groups.forEach{
            sum += it.count()
        }
        return sum.toString()
    }

    override fun part2(input: List<String>): String {
        val groups = mutableListOf<List<String>>()
        var answers = mutableListOf<String>()
        var counter = 0
        input.forEach {
            if (it == "") {
                var all = answers
                var distinct = answers.distinct()
                answers = distinct.intersect(all).toMutableList()
                groups.add(counter, answers)
                answers = mutableListOf()
                counter++
            } else {
                val elements = it.split("").toMutableList()
                elements.removeFirst()
                elements.removeLast()
                answers.addAll(elements)
            }
        }
        groups.add(counter, answers)
        var sum = 0
        groups.forEach{
            sum += it.count()
        }
        return sum.toString()
    }
}

