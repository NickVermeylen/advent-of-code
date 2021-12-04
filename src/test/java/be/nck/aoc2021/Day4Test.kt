package be.nck.aoc2021

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths

internal class Day4Test {

    lateinit var input: List<String>
    lateinit var numbers: String
    lateinit var boards: MutableList<Day4.BingoBoard>

    @BeforeEach
    fun before() {
        input = Files.readAllLines(Paths.get("src/test/resources/2021/day04.txt"))
        numbers = input.first()
        boards = Day4.createBoard(input)
    }

    @Test
    internal fun calculateBingo() {
        assertThat(Day4().part1(Pair(numbers, boards))).isEqualTo("4512")
    }

    @Test
    internal fun calculateLastBingo() {
        boards = Day4.createBoard(input)
        assertThat(Day4().part2(Pair(numbers, boards))).isEqualTo("1924")
    }

    @Test
    internal fun calculateBingoReal() {
        input = Files.readAllLines(Paths.get("src/main/resources/2021/day04.txt"))
        numbers = input.first()
        boards = Day4.createBoard(input)
        assertThat(Day4().part1(Pair(numbers, boards))).isEqualTo("69579 winning number 81")
    }

    @Test
    internal fun createBoard() {
        val input = listOf(
            "blablablablabl",
            "22 13 17 11  0 ",
            "8  2 23  4 24 ",
            "21  9 14 16  7 ",
            "6 10  3 18  5 ",
            "1 12 20 15 19",
            ""
        )
        val board = Day4.createBoard(input).first()
        assertThat(board.rows.size).isEqualTo(5)
    }

    @Test
    internal fun checkRow() {
        boards.first().checkRows(22)
        assertThat(boards.first().rows.first().numbers.first().found).isTrue
    }

    @Test
    internal fun checkRowBingo() {
        boards.first().checkRows(22)
        boards.first().checkRows(13)
        boards.first().checkRows(17)
        boards.first().checkRows(11)
        assertThat(boards.first().rows.first().bingo).isFalse
        boards.first().checkRows(0)
        assertThat(boards.first().rows.first().bingo).isTrue
    }

    @Test
    internal fun checkBoardBingo() {
        boards.first().checkRows(22)
        boards.first().checkRows(13)
        boards.first().checkRows(17)
        boards.first().checkRows(11)
        boards.first().checkRows(0)
        assertThat(boards.first().rows.first().bingo).isTrue
        assertThat(boards.first().bingo).isTrue
    }
}