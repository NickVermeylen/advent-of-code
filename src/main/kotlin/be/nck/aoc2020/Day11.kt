package be.nck.aoc2020

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths


class Day11 : Day<List<MutableList<Char>>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input: List<MutableList<Char>> = Files.readAllLines(Paths.get("src/main/resources/2020/day11.txt"))
                    .map { it.toCharArray().toMutableList() }
                    .toList()
            Day11().run(input)
        }
    }

    override fun part1(input: List<MutableList<Char>>): String {
        val dx = listOf(1, -1, 0, 0, -1, -1, 1, 1)
        val dy = listOf(0, 0, 1, -1, -1, 1, -1, 1)
        var list = input.toMutableList()
        while (true) {
            val next = list.map { it.toMutableList() }.toMutableList()
            var changed = false
            for (i in list.indices) {
                for (j in list[i].indices) {
                    var occupied = 0
                    for (k in dx.indices) {
                        var x = i + dx[k]
                        var y = j + dy[k]
                        try {
                            while (true) {
                                if (list[x][y] == '#') {
                                    occupied++
                                    break
                                } else if(list[x][y] == 'L') {
                                    break
                                }
                                x += dx[k]
                                y += dy[k]
                            }
                        } catch (e: Exception) {}
                    }

                    if (list[i][j] == 'L' && occupied == 0) {
                        changed = true
                        next[i][j] = '#'
                    } else if (list[i][j] == '#' && occupied >= 5) {
                        changed = true
                        next[i][j] = 'L'
                    }
                }
            }
//            next.forEach { println(it) }
            println("boop")
            list = next.toMutableList()
            if (!changed) break
        }
        list.forEach { println(it) }
        return list.map { row -> row.count { it == '#' } }.sum().toString()
    }


        override fun part2(input: List<MutableList<Char>>): String? {
            return "oops"
        }

        fun evolve(neighborCount: Int): Boolean {
            if (neighborCount >= 4) {
                return false
            }
            return true
        }

        data class Seat(
                var isOccupied: Boolean,
                val neighbors: MutableList<Seat> = arrayListOf()
        )

    }
