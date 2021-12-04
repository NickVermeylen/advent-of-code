package be.nck.aoc2021

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths

class Day4 : Day<Pair<String, List<Day4.BingoBoard>>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input: List<String> = Files.readAllLines(Paths.get("src/main/resources/2021/day04.txt"))
            val boardList = createBoard(input)
            Day4().run(Pair(input.first(), boardList))
        }

        fun createBoard(input: List<String>): MutableList<BingoBoard> {
            val boardList = mutableListOf<BingoBoard>()
            var bingoBoard = BingoBoard(mutableListOf(), mutableListOf(), false)
            input.drop(1).forEach {
                if (it != "") {
                    bingoBoard.addRow(toNumbers(it))
                } else {
                    boardList.add(bingoBoard)
                    bingoBoard = BingoBoard(mutableListOf(), mutableListOf(), false)
                }
            }
            return boardList
        }

        private fun toNumbers(input: String): List<Int> {
            return input.split(' ').filter { it != "" }.map {
                it.strip().toInt()
            }.toList()
        }
    }

    data class BingoBoard(
        val rows: MutableList<BingoRow>,
        val cols: MutableList<BingoCol> = mutableListOf(),
        var bingo: Boolean
    ) {
        fun addRow(input: List<Int>): BingoBoard {
            this.rows.add(BingoRow(input))
            var index = 0
            input.forEach {
                if (cols.size < index + 1) cols.add(BingoCol(mutableListOf(), false))
                cols[index].numbers.add(BingoNumber(input[index], false))
                index++
                if (index == 5) index = 0
            }
            return this
        }

        fun checkRows(char: Int) {
            rows.forEach {
                it.check(char)
                if (it.bingo) {
                    this.bingo = true
                }
            }
        }

        fun checkCols(char: Int) {
            cols.forEach {
                it.check(char)
                if (it.bingo) {
                    this.bingo = true
                }
            }
        }

        fun clearBoard() {
            this.bingo = false
            this.rows.forEach { bingoRow -> bingoRow.bingo = false; bingoRow.numbers.forEach { it.found = false } }
            this.cols.forEach { bingoCol -> bingoCol.bingo = false; bingoCol.numbers.forEach { it.found = false } }
        }
    }

    data class BingoRow(
        val numbers: List<BingoNumber>,
        var bingo: Boolean = false
    ) {
        constructor(input: List<Int>) : this(input.map { BingoNumber(it, false) }.toList())

        fun check(char: Int) {
            numbers.forEach {
                if (it.number == char) {
                    it.found()
                }
            }
            val founds = numbers.map { it.found }.distinct()
            if (founds.size == 1 && founds.first()) this.bingo = true
        }
    }

    data class BingoCol(
        val numbers: MutableList<BingoNumber>,
        var bingo: Boolean = false
    ) {
        constructor(input: List<Int>) : this(input.map { BingoNumber(it, false) }.toMutableList())

        fun check(char: Int) {
            numbers.forEach {
                if (it.number == char) {
                    it.found()
                }
            }
            val founds = numbers.map { it.found }.distinct()
            if (founds.size == 1 && founds.first()) this.bingo = true
        }
    }

    data class BingoNumber(
        val number: Int,
        var found: Boolean
    ) {
        fun found() {
            this.found = true
        }
    }

    override fun part1(input: Pair<String, List<BingoBoard>>): String? {
        var result = 0
        var winningNumber = 0
        run run@{
            input.first.split(',').map { it.toInt() }
                .forEach outer@{ char ->
                    input.second.forEach inner@{
                        it.checkRows(char)
                        it.checkCols(char)
                        if (it.bingo) {
                            it.rows.forEach { row ->
                                row.numbers.forEach { number ->
                                    if (!number.found) {
                                        result += number.number
                                    }
                                }
                            }
                            result *= char
                            winningNumber = char
                        }
                        if (result != 0) return@run
                    }
                }
        }
        return result.toString()
    }

    override fun part2(input: Pair<String, List<BingoBoard>>): String? {
        input.second.forEach { it.clearBoard() }
        var result = 0
        val numberOfBoards = input.second.size
        var lastBoardReachingBingo = BingoBoard(mutableListOf(), mutableListOf(), bingo = false)
        var lastWinningNumber = 0
        run run@{
            input.first.split(',').map { it.toInt() }
                .forEach outer@{ char ->
                    lastWinningNumber = char
                    input.second.forEach inner@{ bingoBoard ->
                        if (!bingoBoard.bingo) {
                            bingoBoard.checkRows(char)
                            bingoBoard.checkCols(char)
                            lastBoardReachingBingo = bingoBoard
                        }
                        val nrOfBingos = input.second.filter { it.bingo }.size
                        if (nrOfBingos == numberOfBoards - 1) {
                            lastBoardReachingBingo.rows.forEach { row ->
                                row.numbers.forEach { number ->
                                    if (!number.found) {
                                        result += number.number
                                    }
                                }
                            }
                            result *= char
                        }
                    if (result != 0) return@run
                    }
                }
        }
        return result.toString()
    }

}