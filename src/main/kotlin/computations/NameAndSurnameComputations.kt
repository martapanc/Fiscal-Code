package computations

import java.util.regex.Pattern

object NameAndSurnameComputations {

    private const val VOWEL_PATTERN = "[AEIOU]"
    private const val CONSONANT_PATTERN = "[B-DF-HJ-NP-TV-Z]"
    private val COMPILE_WOVELS = Pattern.compile(VOWEL_PATTERN)
    private val COMPILE_CONSONANTS = Pattern.compile(CONSONANT_PATTERN)

    internal fun pickFirstThreeConsonants(input: String): String {
        val m = COMPILE_CONSONANTS.matcher(input)
        val result = StringBuilder()
        var cont = 1
        while (m.find() && cont <= 3) {
            result.append(m.group())
            cont++
        }
        return result.toString()
    }

    internal fun pickFirstTwoConsonantsAndFirstVowel(input: String): String {
        var m = COMPILE_CONSONANTS.matcher(input)
        val result = StringBuilder()
        var cont = 1
        while (m.find() && cont <= 2) {
            result.append(m.group())
            cont++
        }
        m = COMPILE_WOVELS.matcher(input)
        cont = 1
        while (m.find() && cont <= 1) {
            result.append(m.group())
            cont++
        }
        return result.toString()
    }

    internal fun pickFirstConsonantAndFirstTwoVowels(input: String): String {
        var m = COMPILE_CONSONANTS.matcher(input)
        val result = StringBuilder()
        var cont = 1
        while (m.find() && cont <= 1) {
            result.append(m.group())
            cont++
        }
        m = COMPILE_WOVELS.matcher(input)
        cont = 1
        while (m.find() && cont <= 2) {
            result.append(m.group())
            cont++
        }
        return result.toString()
    }

    internal fun pickFirstThreeVowels(input: String): String {
        val m = COMPILE_WOVELS.matcher(input)
        val result = StringBuilder()
        var cont = 1
        while (m.find() && cont <= 3) {
            result.append(m.group())
            cont++
        }
        return result.toString()
    }

    internal fun pickFirstThirdAndFourthConsonant(inputName: String): String {
        val m = COMPILE_CONSONANTS.matcher(inputName)
        val result = StringBuilder()
        var cont = 1
        while (m.find() && cont <= 4) {
            if (cont != 2) {
                result.append(m.group())
            }
            cont++
        }
        return result.toString()
    }
}
