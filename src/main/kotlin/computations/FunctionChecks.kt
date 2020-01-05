package computations

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.regex.Pattern

object FunctionChecks {

    fun isAllLetters(string: String): Boolean {
        return Pattern.matches("[a-zA-Z]+", string)
    }

    fun isAllDigits(string: String): Boolean {
        return Pattern.matches("[0-9]+", string)
    }

    fun howManyConsonants(input: String): Int {
        var string = input
        val match = StringBuilder()
        if (isAllLetters(string)) {
            string = string.toUpperCase()
            val pattern = Pattern.compile("[B-DF-HJ-NP-TV-Z]+")
            val matcher = pattern.matcher(string)
            while (matcher.find()) {
                match.append(matcher.group())
            }
        }
        return match.length
    }

    fun howManyVowels(input: String): Int {
        var string = input
        val match = StringBuilder()
        if (isAllLetters(string)) {
            string = string.toUpperCase()
            val pattern = Pattern.compile("[AEIOU]+")
            val matcher = pattern.matcher(string)
            while (matcher.find()) {
                match.append(matcher.group())
            }
        }
        return match.length
    }

    fun isYearValid(yearString: String): Boolean {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val year: Int
        if (isAllDigits(yearString)) {
            year = Integer.parseInt(yearString)
            return year in 1900..currentYear
        }
        return false
    }

    fun isDateValid(day: String, month: String, year: String): Boolean {
        if (isYearValid(year)) {
            val dateToCheck = "$day-$month-$year"
            Calendar.getInstance()
            val sdf = SimpleDateFormat("dd-MM-yy")
            sdf.isLenient = false
            try {
                sdf.parse(dateToCheck)
                val date = sdf.parse(dateToCheck)
                val current = Calendar.getInstance().time
                if (date.after(current))
                    return false
            } catch (e: ParseException) {
                return false
            }

            return true
        }
        return false
    }

    fun replaceSpecialChars(input: String): String {
        return input
            .toUpperCase()
            .replace("[ÀÁÂÃÅĀ]".toRegex(), "A")
            .replace("[ÄÆ]".toRegex(), "AE")
            .replace("[ÈÉÊËĘĖĒ]".toRegex(), "E")
            .replace("[ÌÍÎÏĮĪ]".toRegex(), "I")
            .replace("[ÒÓÔÕOŌ]".toRegex(), "O")
            .replace("[ÖŒØ]".toRegex(), "OE")
            .replace("[ÙÚÛŪ]".toRegex(), "U")
            .replace("[Ü]".toRegex(), "UE")
            .replace("[ŚŠ]".toRegex(), "S")
            .replace("ß".toRegex(), "SS")
            .replace("[ÇĆČ]".toRegex(), "C")
            .replace(" ".toRegex(), "")
            .replace("-".toRegex(), "")
            .replace("'".toRegex(), "")
    }
}
