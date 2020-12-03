package be.nck.aoc2020

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths


class DayTres : Day<Array<CharArray>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input: Array<CharArray> = readArray()
            DayTres().run(input)
        }

        fun readArray(): Array<CharArray>{
            val rowAndColumn = Files.readAllLines(Paths.get("src/main/resources/2020/day03.txt"))
            val rows = rowAndColumn.size
            val columns = rowAndColumn[0].length
            val board = Array(rows){ CharArray(columns) }
            for (row in 0 until rows) {
                board[row] = rowAndColumn[row]
                        .asIterable()
                        .map {
                            it
                        }.toCharArray()
            }
            return board
        }
    }

    fun trees(forest: Array<CharArray>, slopeRight: Int, slopeDown: Int): Long{
        val height = forest.size
        val width = forest[0].size

        var trees = 0L
        var currentHeight = 0
        var currentWidth = 0


        while (currentHeight < height){
            if (forest[currentHeight][currentWidth] == '#'){
                trees++
            }
            currentWidth = (currentWidth+slopeRight)%width
            currentHeight += slopeDown
        }
        return trees
    }

    override fun part1(input: Array<CharArray>): String {
        return trees(input, 3, 1).toString()
    }

    override fun part2(input: Array<CharArray>): String {
        var trees = trees(input, 1, 1)
        trees *= trees(input, 3, 1)
        trees *= trees(input, 5, 1)
        trees *= trees(input, 7, 1)
        trees *= trees(input, 1, 2)
        return trees.toString()
    }
}

