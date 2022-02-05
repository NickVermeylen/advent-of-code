package be.nck.aoc2021

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths

class Day11 : Day<List<String>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input: List<String> = Files.readAllLines(Paths.get("src/main/resources/2021/day11.txt"))
            Day10().run(input)
        }
    }

    class Octopus(var energyLevel: Int) {
        fun increaseEnergy() {
            energyLevel++
            if (energyLevel == 10)
                surroundingOctopuses.forEach { it.increaseEnergy() }
        }

        fun flash(): Int {
            if (energyLevel < 10) return 0
            energyLevel = 0
            return 1
        }

        var surroundingOctopuses: List<Octopus> = emptyList()
    }

    private fun List<Octopus>.step(): Int {
        this.forEach { it.increaseEnergy() }
        return this.sumBy { it.flash() }
    }

    override fun part1(input: List<String>): String? {
        var totalFlashes = 0L
        val octopi = input.flatMap { row -> row.map { Octopus(it.toString().toInt()) } }.toList()
        repeat(100) { totalFlashes += octopi.step() }
        return totalFlashes.toString()
    }

    override fun part2(input: List<String>): String? {
        TODO("Not yet implemented")
    }
}