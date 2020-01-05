import computations.ComputeFiscalCode.computeControlChar
import computations.ComputeFiscalCode.computeDateOfBirth
import computations.ComputeFiscalCode.computeName
import computations.ComputeFiscalCode.computeSurname
import computations.ComputeFiscalCode.computeTownOfBirth
import computations.FunctionChecks.howManyConsonants
import computations.FunctionChecks.howManyVowels
import computations.FunctionChecks.isAllDigits
import computations.FunctionChecks.isAllLetters
import computations.FunctionChecks.isDateValid
import computations.FunctionChecks.isYearValid
import computations.FunctionChecks.replaceSpecialChars
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.IOException
import java.util.Calendar

class CalculationsTest {

    @Test
    fun testSurname() {
        assertEquals(computeSurname("Pancaldi"), "PNC")
        assertEquals(computeSurname("abatantuomo"), "BTN")
        assertEquals(computeSurname("a"), "AXX")
        assertEquals(computeSurname("Re"), "REX")
        assertEquals(computeSurname("R0ss1"), "0")
        assertEquals(computeSurname(""), "0")
    }

    @Test
    fun testName() {
        assertEquals(computeName("Marta"), "MRT")
        assertEquals(computeName("Li"), "LIX")
        assertEquals(computeName("M@rio"), "0")
        assertEquals(computeName(""), "0")
    }

    @Test
    fun testDate() {
        assertEquals(computeDateOfBirth("12", "7", "1995", "f"), "95L52")
        assertEquals(computeDateOfBirth("12", "3", "1959", "f"), "59C52")
        assertEquals(computeDateOfBirth("31", "12", "1940", "m"), "40T31")
        assertEquals(computeDateOfBirth("12", "11", "1859", "m"), "0")
        assertEquals(computeDateOfBirth("30", "02", "2014", "m"), "0")
    }

    @Test
    @Throws(IOException::class)
    fun testTown() {
        assertEquals(computeTownOfBirth("Guastalla"), "E253")
        assertEquals(computeTownOfBirth("Reggio Emilia"), "H223")
        assertEquals(computeTownOfBirth("Milanoo"), "0")
        assertEquals(computeTownOfBirth(""), "0")
    }

    @Test
    @Throws(InterruptedException::class)
    fun testControl() {
        assertEquals(computeControlChar("PNCMRT95L52E253"), "R")
        assertEquals(computeControlChar("MRNLBT59C52H223"), "N")
        assertEquals(computeControlChar("MRNLBT59C52H22"), "")
    }

    @Test
    fun testAllDigits() {
        assertTrue(isAllDigits("13425804"))
        assertFalse(isAllDigits("157893l"))
    }

    @Test
    fun testAllLetters() {
        assertTrue(isAllLetters("helloworld"))
        assertFalse(isAllLetters("h3llow0r1d"))
    }

    @Test
    fun testHowManyCons() {
        assertEquals(howManyConsonants("helloworld"), 7)
        assertEquals(howManyConsonants("sUpErCaliFragIlisTIceXpiAlidOCioUs"), 18)
        assertEquals(howManyConsonants("he11o"), 0)
    }

    @Test
    fun testHowManyVowels() {
        assertEquals(howManyVowels("helloworld"), 3)
        assertEquals(howManyVowels("sUpErCaliFragIlisTIceXpiAlidOCioUs"), 16)
        assertEquals(howManyVowels("he110"), 0)
    }

    @Test
    fun testYearValid() {
        assertFalse(isYearValid("1899"))
        assertTrue(isYearValid("1900"))
        assertTrue(isYearValid("1947"))
        assertTrue(isYearValid(Calendar.getInstance()[Calendar.YEAR].toString() + "")) //current year is valid
        assertFalse(isYearValid((Calendar.getInstance()[Calendar.YEAR] + 1).toString() + "")) //next year is not valid
        assertFalse(isYearValid("19o0"))
    }

    @Test
    fun testDateValid() {
        assertFalse(isDateValid("29", "2", "2014"))
        assertFalse(isDateValid("31", "4", "2014"))
        assertFalse(isDateValid("29", "13", "2014"))
        assertTrue(isDateValid("1", "1", "2015"))
        assertTrue(isDateValid("13", "3", "2015"))
        val c = Calendar.getInstance()
        c.time = Calendar.getInstance().time
        c.add(Calendar.DAY_OF_MONTH, 1) //tomorrow date as value is not a valid input
        val day = c[Calendar.DAY_OF_MONTH].toString() + ""
        val month =
            (c[Calendar.MONTH] + 1).toString() + "" //Java months counter is 0-11, whereas the method receives 1-12
        val year = c[Calendar.YEAR].toString() + ""
        assertFalse(isDateValid(day, month, year)) //Birthday cannot be after current day
    }

    @Test
    fun testReplaceSpecChars() {
        assertEquals(replaceSpecialChars("nicolò"), "NICOLO")
        assertEquals(replaceSpecialChars("de' medici"), "DEMEDICI")
        assertEquals(replaceSpecialChars("françois"), "FRANCOIS")
        assertEquals(replaceSpecialChars("müllerstraße"), "MUELLERSTRASSE")
    }
}