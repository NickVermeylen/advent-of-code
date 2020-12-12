package be.nck.aoc2020

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.math.absoluteValue


class Day12 : Day<List<String>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input: List<String> = Files.readAllLines(Paths.get("src/main/resources/2020/day12.txt"))
            Day12().run(input)
        }
    }

    override fun part1(input: List<String>): String {
        var north = 0
        var east = 0
        var facing = 90
        input.forEach {
            when (it.first()) {
                'R' -> facing += it.substring(1).toInt()
                'L' -> facing -= it.substring(1).toInt()
                'N' -> north += it.substring(1).toInt()
                'E' -> east += it.substring(1).toInt()
                'S' -> north -= it.substring(1).toInt()
                'W' -> east -= it.substring(1).toInt()
                'F' -> {
                    when (facing) {
                        0 -> north += it.substring(1).toInt()
                        90 -> east += it.substring(1).toInt()
                        180 -> north -= it.substring(1).toInt()
                        270 -> east -= it.substring(1).toInt()
                    }
                }
            }
            if (facing < 0) facing += 360
            if (facing >= 360) facing -= 360
        }
        return (north.absoluteValue + east.absoluteValue).toString()
    }


    override fun part2(input: List<String>): String? {
        var north = 1
        var east = 10
        var facing = 90
        var shipN = 0
        var shipE = 0
        input.forEach {
            when (it.first()) {
                'R' -> {
                    val i = it.substring(1).toInt() / 90
                    for (times in 1..i) {
                        val prevE = east
                        east = north
                        north = prevE * -1
                    }
                }
                'L' -> {
                    val i = it.substring(1).toInt() / 90
                    for (times in 1..i) {
                        val prevN = north
                        north = east
                        east = prevN * -1
                    }
                }
                'N' -> north += it.substring(1).toInt()
                'E' -> east += it.substring(1).toInt()
                'S' -> north -= it.substring(1).toInt()
                'W' -> east -= it.substring(1).toInt()
                'F' -> {
                    shipN += north * it.substring(1).toInt()
                    shipE += east * it.substring(1).toInt()
                }
            }
            if (facing < 0) facing += 360
            if (facing >= 360) facing -= 360
        }
        return (shipE.absoluteValue + shipN.absoluteValue).toString()
    }
}
