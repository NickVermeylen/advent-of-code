package be.nck.aoc2020

import be.nck.utils.Day
import java.nio.file.Files
import java.nio.file.Paths


class DayQuatro : Day<List<Map<String, String>>> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input: List<Map<String, String>> = readArray()
            DayQuatro().run(input)
        }

        fun readArray(): List<Map<String, String>> {
            val rawInput = Files.readAllLines(Paths.get("src/main/resources/2020/day04.txt"))
            val parsedInput: MutableList<Map<String, String>> = mutableListOf()
            var counter = 0
            var map: MutableMap<String, String> = mutableMapOf()
            rawInput.forEach{
                if(it != "") {
                    it.split(" ").forEach { keyVal ->
                        val p = keyVal.split(":")
                        map.put(p.get(0), p.get(1))
                    }
                } else {
                    parsedInput.add(map)
                    map = emptyMap<String, String>().toMutableMap()
                    counter++
                }
            }
            parsedInput.add(map)
            return parsedInput.toList()
        }
        val keys = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    }

    override fun part1(input: List<Map<String, String>>): String {
        var valid = 0
        input.forEach {
            if (it.keys.containsAll(keys)){
                valid++
            }
        }
        return valid.toString()
    }

    override fun part2(input: List<Map<String, String>>): String {
        var valid = 0
        input.forEach {
            if(validate2(it)){
                valid++
            }
        }
        return valid.toString()
    }

    fun validate2(pass: Map<String, String>): Boolean {
        val valid = listOf<Boolean>().toMutableList()
        pass.entries.forEach {
            when (it.key) {
                "iyr" -> valid.add(it.value.toInt() in 2010..2020)
                "eyr" -> valid.add(it.value.toInt() in 2020..2030)
                "byr" -> valid.add(it.value.toInt() in 1920..2002)
                "hgt" -> valid.add(it.value.contains("cm", true) && it.value.dropLast(2).toInt() in 150..193 ||
                        it.value.contains("in", true) && it.value.dropLast(2).toInt() in 59..76)
                "hcl" -> valid.add(Regex("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})\$").containsMatchIn(it.value))
                "ecl" -> valid.add(Regex("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})\$").containsMatchIn(it.value))
                "pid" -> valid.add(Regex("^\\d{9}$").containsMatchIn(it.value))
            }
        }
        return valid.all { it }
    }
}

