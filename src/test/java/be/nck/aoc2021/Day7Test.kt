package be.nck.aoc2021

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths

internal class Day7Test{
    private lateinit var positions: List<Int>

    @BeforeEach
    fun before(){
        var input: List<String> = Files.readAllLines(Paths.get("src/test/resources/2021/day07.txt"))
        input = input.first().split(',')
        positions = input.map { it.toInt() }.toList()
    }

    @Test
    internal fun alignHorizontallyLeastFuel() {
        assertThat(Day7.alignHorizontallyLeastFuel(positions)).isEqualTo(Pair(2, 37))
    }

    @Test
    internal fun alignHorizontallyCrabFuel() {
        assertThat(Day7.alignHorizontallyCrabFuel(positions)).isEqualTo(Pair(5, 168))
    }
}