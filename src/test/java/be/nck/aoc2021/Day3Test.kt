//package be.nck.aoc2021
//
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.jupiter.api.Assertions.*
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import java.nio.file.Files
//import java.nio.file.Paths
//
//internal class Day3Test{
//    lateinit var input: List<String>
//    @BeforeEach
//    fun before(){
//        input = Files.readAllLines(Paths.get("src/test/resources/2021/day03.txt"))
//    }
//
//    @Test
//    fun calculateGamma(){
//        assertThat(Day3.calculateBothBytes(input).first).isEqualTo("10110")
//    }
//
//    @Test
//    fun calculateEpsilon(){
//        assertThat(Day3.calculateBothBytes(input).second).isEqualTo("01001")
//    }
//
//    @Test
//    fun calculateO2Scrubber() {
//        assertThat(Day3.calculateO2Scrubber(input)).isEqualTo("10111")
//    }
//
//    @Test
//    fun calculateCO2Scrubber() {
//        assertThat(Day3.calculateCO2Scrubber(input)).isEqualTo("01010")
//    }
//}