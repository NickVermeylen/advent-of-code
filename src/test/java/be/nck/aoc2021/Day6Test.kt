package be.nck.aoc2021

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths

internal class Day6Test{
    lateinit var input: List<Int>
    @BeforeEach
    fun before() {
        input = Files.readAllLines(Paths.get("src/test/resources/2021/day06.txt")).first().split(',').map { it.toInt() }.toList()
    }

    @Test
    internal fun days() {
        assertThat(input).isEqualTo(listOf(3,4,3,1,2))
        var fish = Day6.interateDay(input)
        assertThat(fish).isEqualTo(listOf(2,3,2,0,1))
        fish = Day6.interateDay(fish)
        assertThat(fish).isEqualTo(listOf(1,2,1,6,0,8))
        fish = Day6.interateDay(fish)
        assertThat(fish).isEqualTo(listOf(0,1,0,5,6,7,8))
    }
}