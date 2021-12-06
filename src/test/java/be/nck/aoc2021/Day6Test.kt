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
        val map = input.groupingBy { it }.eachCount().mapValues { it.value.toLong() }
        assertThat(input).isEqualTo(listOf(3,4,3,1,2))
        var fish = Day6.interateDay(map, 1)
        assertThat(fish.values.sum()).isEqualTo(listOf(2,3,2,0,1).size.toLong())
        fish = Day6.interateDay(map, 2)
        assertThat(fish.values.sum()).isEqualTo(listOf(1,2,1,6,0,8).size.toLong())
        fish = Day6.interateDay(map, 3)
        assertThat(fish.values.sum()).isEqualTo(listOf(0,1,0,5,6,7,8).size.toLong())
        assertThat(Day6.interateDay(map, 80).values.sum()).isEqualTo(5934)
    }
}