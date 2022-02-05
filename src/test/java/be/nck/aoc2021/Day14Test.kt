package be.nck.aoc2021

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths

internal class Day14Test {
    lateinit var input: List<String>
    lateinit var pair: Pair<String, Map<String, String>>
    val day14 = Day14()

    @BeforeEach
    fun before() {
        val input = Files.readAllLines(Paths.get("src/test/resources/2021/day14.txt"))
        val second = input.drop(2).map {
            val split = it.split(" -> ")
            split[0] to split[1]
        }.toMap()
        pair = Pair(input.first(), second)
    }

    @Test
    internal fun part1() {
        assertThat(day14.part1(pair)).isEqualTo("1588")
    }

    @Test
    internal fun part2() {
        assertThat(day14.part2(pair)).isEqualTo("2188189693529")
    }

}