package methods

import methods.FunctionChecks.howManyConsonants
import methods.FunctionChecks.isAllLetters
import methods.FunctionChecks.isDateValid
import methods.FunctionChecks.isYearValid
import javax.swing.*
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader
import java.util.ArrayList
import java.util.Calendar

import methods.FunctionChecks.replaceSpecialChars
import methods.NameAndSurnameComputations.pickFirstConsonantAndFirstTwoVowels
import methods.NameAndSurnameComputations.pickFirstThirdAndFourthConsonant
import methods.NameAndSurnameComputations.pickFirstThreeConsonants
import methods.NameAndSurnameComputations.pickFirstThreeVowels
import methods.NameAndSurnameComputations.pickFirstTwoConsonantsAndFirstVowel

object ComputeFiscalCode {

    fun computeSurname(input: String): String {
        var input = input
        val error = "0"
        input = replaceSpecialChars(input)

        if (isAllLetters(input)) {
            var result = StringBuilder()
            input = input.toUpperCase()

            if (input.length < 3) {
                result = StringBuilder(input)
                while (result.length < 3) {
                    result.append("X")
                }
            } else {
                when (howManyConsonants(input)) {
                    0 -> result.append(pickFirstThreeVowels(input))
                    1 -> result.append(pickFirstConsonantAndFirstTwoVowels(input))
                    2 -> result.append(pickFirstTwoConsonantsAndFirstVowel(input))
                    else -> result.append(pickFirstThreeConsonants(input))
                }
            }
            return result.toString()
        } else {
            JOptionPane.showMessageDialog(
                null,
                "Please insert a valid input in \"Surname\" field.",
                "Error",
                JOptionPane.WARNING_MESSAGE
            )
            return error
        }
    }

    fun computeName(inputName: String): String {
        var inputName = inputName
        val error = "0"
        inputName = replaceSpecialChars(inputName)
        if (isAllLetters(inputName)) {
            var result = StringBuilder()
            inputName = inputName.toUpperCase()

            if (inputName.length < 3) {
                result = StringBuilder(inputName)
                while (result.length < 3)
                    result.append("X")
            } else {
                when (howManyConsonants(inputName)) {
                    0 -> result.append(pickFirstThreeVowels(inputName))
                    1 -> result.append(pickFirstConsonantAndFirstTwoVowels(inputName))
                    2 -> result.append(pickFirstTwoConsonantsAndFirstVowel(inputName))
                    3 -> result.append(pickFirstThreeConsonants(inputName))
                    else -> result.append(pickFirstThirdAndFourthConsonant(inputName))
                }
            }
            return result.toString()

        } else {
            JOptionPane.showMessageDialog(
                null,
                "Please insert a valid input in \"Name\" field.",
                "Error",
                JOptionPane.WARNING_MESSAGE
            )
            return error
        }
    }

    fun computeDateOfBirth(dayString: String, monthString: String, yearString: String, gender: String): String {
        val yearError = "0"
        val dateError = "0"

        if (isYearValid(yearString)) {
            if (isDateValid(dayString, monthString, yearString)) {
                var result = ""
                try {
                    val day = Integer.parseInt(dayString)
                    val month = Integer.parseInt(monthString)
                    val year = Integer.parseInt(yearString)

                    // get the last 2 digits of the year
                    if (year % 100 >= 10) {
                        result += year % 100
                    } else {
                        result = result + 0 + year % 100
                    }

                    // get the letter corresponding to the month
                    when (month) {
                        1 -> {
                            result += "A"
                        }
                        2 -> {
                            result += "B"
                        }
                        3 -> {
                            result += "C"
                        }
                        4 -> {
                            result += "D"
                        }
                        5 -> {
                            result += "E"
                        }
                        6 -> {
                            result += "H"
                        }
                        7 -> {
                            result += "L"
                        }
                        8 -> {
                            result += "M"
                        }
                        9 -> {
                            result += "P"
                        }
                        10 -> {
                            result += "R"
                        }
                        11 -> {
                            result += "S"
                        }
                        12 -> {
                            result += "T"
                        }
                    }
                    when (gender) {
                        "f" -> {
                            result += day + 40
                        }
                        "m" -> {
                            result += if (day <= 10) "0$day" else day
                        }
                    }
                } catch (e: NumberFormatException) {
                    println("Check numeric input.")
                }

                return result
            } else {
                JOptionPane.showMessageDialog(null, "Invalid date", "Error", JOptionPane.WARNING_MESSAGE)
                return dateError
            }
        } else {
            val message = ("Please insert a numeric value between 1900 and "
                    + Calendar.getInstance().get(Calendar.YEAR)
                    + " in \"Year\" field.")
            JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE)
            return yearError
        }
    }

    @Throws(IOException::class)
    fun computeTownOfBirth(townString: String): String {
        val townList = ArrayList<Town>()
        var townCode = "0"
        var townString = townString.toUpperCase()
        try {
            BufferedReader(
                InputStreamReader(ComputeFiscalCode::class.java.getResourceAsStream("/TownCodeList.txt"))
            ).use { read ->
                var line: String? = read.readLine()
                var town: Array<String>
                while (line != null) {
                    town = line.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    townList.add(Town(town[0], town[1]))
                    line = read.readLine()
                }
                var i = 0
                while (i < townList.size) {
                    if (townString == townList[i].townName) {
                        townCode = townList[i].townCode
                        break
                    }
                    i++
                }
            }
        } catch (e: FileNotFoundException) {
            JOptionPane.showMessageDialog(null, "File was not found", "Error", JOptionPane.WARNING_MESSAGE)
        }

        if (townCode == "0") {
            JOptionPane.showMessageDialog(null, "Town was not found", "Error", JOptionPane.WARNING_MESSAGE)
        }
        return townCode
    }

    @Throws(InterruptedException::class)
    fun computeControlChar(incompleteFiscalCode: String): String {
        var control = ""
        var evenSum = 0
        var oddSum = 0
        incompleteFiscalCode.toUpperCase()
        if (incompleteFiscalCode.length == 15) {
            val ot = OddThread(incompleteFiscalCode, oddSum)
            val t1 = Thread(ot)
            t1.start()
            t1.join()
            oddSum = ot.oddSum

            val et = EvenThread(incompleteFiscalCode, evenSum)
            val t2 = Thread(et)
            t2.start()
            t2.join()
            evenSum = et.evenSum

            // The remainder of the division is the control character
            val sum = (oddSum + evenSum) % 26
            when (sum) {
                0 -> {
                    control = "A"
                }
                1 -> {
                    control = "B"
                }
                2 -> {
                    control = "C"
                }
                3 -> {
                    control = "D"
                }
                4 -> {
                    control = "E"
                }
                5 -> {
                    control = "F"
                }
                6 -> {
                    control = "G"
                }
                7 -> {
                    control = "H"
                }
                8 -> {
                    control = "I"
                }
                9 -> {
                    control = "J"
                }
                10 -> {
                    control = "K"
                }
                11 -> {
                    control = "L"
                }
                12 -> {
                    control = "M"
                }
                13 -> {
                    control = "N"
                }
                14 -> {
                    control = "O"
                }
                15 -> {
                    control = "P"
                }
                16 -> {
                    control = "Q"
                }
                17 -> {
                    control = "R"
                }
                18 -> {
                    control = "S"
                }
                19 -> {
                    control = "T"
                }
                20 -> {
                    control = "U"
                }
                21 -> {
                    control = "V"
                }
                22 -> {
                    control = "W"
                }
                23 -> {
                    control = "X"
                }
                24 -> {
                    control = "Y"
                }
                25 -> {
                    control = "Z"
                }
            }
        }
        return control
    }
}
