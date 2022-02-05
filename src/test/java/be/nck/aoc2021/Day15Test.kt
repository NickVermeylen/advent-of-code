package be.nck.aoc2021

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths

internal class Day15Test {
    lateinit var input: List<String>
    lateinit var grid: List<List<Int>>
    val day15 = Day15()

    @BeforeEach
    fun before() {
        input = Files.readAllLines(Paths.get("src/test/resources/2021/day15.txt"))
        grid = input.map { it.map { it.toString().toInt() }.toList() }.toList()
    }

    @Test
    internal fun part1() {
        assertThat(day15.part1(grid)).isEqualTo("40")
    }

}