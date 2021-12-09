package be.nck.aoc2021

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths

internal class Day8Test{
    lateinit var signals: List<Pair<String, String>>

    @BeforeEach
    fun before(){
        val input: List<String> = Files.readAllLines(Paths.get("src/test/resources/2021/day08.txt"))
        signals = input.map { it.split(" | ") }.map { Pair(it[0], it[1]) }.toList()
    }
    @Test
    internal fun countEasyNumbers() {
        assertThat(Day8.countEasyNumbers(signals)).isEqualTo(26)
    }

    @Test
    internal fun determineOutput() {
        assertThat(Day8.determineOutput(signals)).isEqualTo(61229)
    }
}