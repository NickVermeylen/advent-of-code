package be.nck.aoc2021

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths

internal class Day11Test{
    lateinit var input: List<String>
    val day11 = Day11()

    @BeforeEach
    fun before() {
        input = Files.readAllLines(Paths.get("src/test/resources/2021/day11.txt"))
    }

    @Test
    internal fun part1() {
        Assertions.assertThat(day11.part1(input)).isEqualTo("1656")
    }

    @Test
    internal fun part2() {
        Assertions.assertThat(day11.part2(input)).isEqualTo("288957")
    }
}