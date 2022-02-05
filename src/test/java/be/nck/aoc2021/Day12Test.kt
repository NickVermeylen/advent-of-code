package be.nck.aoc2021

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths

internal class Day12Test {
    lateinit var input: List<String>
    val day12 = Day12()

    @BeforeEach
    fun before() {
        input = Files.readAllLines(Paths.get("src/test/resources/2021/day12.txt"))
    }

    @Test
    internal fun part1() {
        assertThat(day12.part1(input)).isEqualTo("226")
    }

    @Test
    internal fun findAllPaths() {
        val expected = listOf<String>(
            "start,HN,dc,HN,end",
            "start,HN,dc,HN,kj,HN,end",
            "start,HN,dc,end",
            "start,HN,dc,kj,HN,end",
            "start,HN,end",
            "start,HN,kj,HN,dc,HN,end",
            "start,HN,kj,HN,dc,end",
            "start,HN,kj,HN,end",
            "start,HN,kj,dc,HN,end",
            "start,HN,kj,dc,end",
            "start,dc,HN,end",
            "start,dc,HN,kj,HN,end",
            "start,dc,end",
            "start,dc,kj,HN,end",
            "start,kj,HN,dc,HN,end",
            "start,kj,HN,dc,end",
            "start,kj,HN,end",
            "start,kj,dc,HN,end",
            "start,kj,dc,end"
        )
        assertThat(Day12.findAllPaths(input)).isEqualTo(expected)
    }
}