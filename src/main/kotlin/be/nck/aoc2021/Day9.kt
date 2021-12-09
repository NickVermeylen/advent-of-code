package be.nck.aoc2021

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths
import javax.xml.stream.events.Characters

class Day9 : Day<List<List<Int>>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input: List<String> = Files.readAllLines(Paths.get("src/main/resources/2021/day09.txt"))
            val map = input.map { it.toList() }.map { chars -> chars.map { it.toString().toInt() } }
            Day9().run(map)
        }
        private fun isLowAdjacent(j: Int, i: Int, points: List<List<Int>>): Boolean {
            val point = points[i][j]
            val n = points.getOrNull(i - 1)?.getOrNull(j) ?: -1
            val s = points.getOrNull(i + 1)?.getOrNull(j) ?: -1
            val e = points.getOrNull(i)?.getOrNull(j + 1) ?: -1
            val w = points.getOrNull(i)?.getOrNull(j - 1) ?: -1

            if (n in 0..point) return false
            if (s in 0..point) return false
            if (e in 0..point) return false
            if (w in 0..point) return false

            return true
        }

        fun calculateLowPoints(input: List<List<Int>>): String? {
            val lowPoints = mutableListOf<Int>()
            for (r in input.indices) {
                for (c in input[r].indices) {
                    if (isLowAdjacent(c, r,input)){
                        lowPoints.add(input[r][c])
                    }
                }
            }
            return (lowPoints.size + lowPoints.sum()).toString()
        }
    }

    override fun part1(input: List<List<Int>>): String? {
        return calculateLowPoints(input)
    }

    override fun part2(input: List<List<Int>>): String? {
        return "Null"
    }
}