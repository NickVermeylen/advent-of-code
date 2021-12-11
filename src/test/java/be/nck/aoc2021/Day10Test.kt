package be.nck.aoc2021

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths

internal class Day10Test {
    lateinit var input: List<String>
        val day10 = Day10()

    @BeforeEach
    fun before() {
        input = Files.readAllLines(Paths.get("src/test/resources/2021/day10.txt"))
    }

    @Test
    internal fun part1() {
        assertThat(day10.part1(input)).isEqualTo("26397")
    }

    @Test
    internal fun part2() {
        assertThat(day10.part2(input)).isEqualTo("288957")
    }
}