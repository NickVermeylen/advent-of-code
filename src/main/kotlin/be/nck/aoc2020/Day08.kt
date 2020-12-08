package be.nck.aoc2020

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths


class Day08 : Day<List<String>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input: List<String> = Files.readAllLines(Paths.get("src/main/resources/2020/day08.txt"))
            Day08().run(input)
        }
    }

    override fun part1(input: List<String>): String {
        return runProgram(input).second
    }

    private fun runProgram(input: List<String>): Pair<String, String> {
        var index = 0
        var accValue = 0
        val seen = mutableListOf<Int>()
        while (index < input.size) {
            when (input[index].split(" ")[0]) {
                "acc" -> {
                    accValue += input[index].split(" ")[1].toInt()
                    index++
                }
                "jmp" -> index += input[index].split(" ")[1].toInt()
                "nop" -> index++
            }
            if (seen.contains(index)) {
                return Pair("looped", accValue.toString())
            } else {
                seen.add(index)
            }
        }
        return Pair("terminated", accValue.toString())
    }

    override fun part2(input: List<String>): String {
        var index = 0
        input.forEach {
            val edit = input.toMutableList()
            when(it.split(" ")[0]){
                "jmp" -> edit[index] = "nop ${it.split(" ")[1]}"
                "nop" -> edit[index] = "jmp ${it.split(" ")[1]}"
            }
            index++
            val answer = runProgram(edit)
            if(answer.first != "looped") {
                return answer.second
            }
        }
        return "oops"
    }

}
