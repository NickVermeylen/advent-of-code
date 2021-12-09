package be.nck.aoc2021

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths

internal class Day9Test{
    lateinit var map: List<List<Int>>

    @BeforeEach
    fun before(){
        val input: List<String> = Files.readAllLines(Paths.get("src/test/resources/2021/day09.txt"))
        map = input.map { it.toList() }.map { it.map { it.toString().toInt() } }
    }
    @Test
    internal fun countEasyNumbers() {
        assertThat(Day9.calculateLowPoints(map)).isEqualTo("15")
    }

    @Test
    internal fun determineOutput() {

    }
}