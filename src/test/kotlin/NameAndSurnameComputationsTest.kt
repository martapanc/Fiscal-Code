import computations.NameAndSurnameComputations
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class NameAndSurnameComputationsTest {
    @Test
    fun pickFirstThreeConsonants() {
        assertEquals("MRT", NameAndSurnameComputations.pickFirstThreeConsonants("MARTA"))
        assertEquals("PNC", NameAndSurnameComputations.pickFirstThreeConsonants("PANCALDI"))
    }

    @Test
    fun pickFirstTwoConsonantsAndFirstVowel() {
        assertEquals("RTI", NameAndSurnameComputations.pickFirstTwoConsonantsAndFirstVowel("RITA"))
        assertEquals("MRA", NameAndSurnameComputations.pickFirstTwoConsonantsAndFirstVowel("MARIA"))
    }

    @Test
    fun pickFirstConsonantAndFirstTwoVowels() {
        assertEquals("LOI", NameAndSurnameComputations.pickFirstConsonantAndFirstTwoVowels("OLI"))
        assertEquals("PAA", NameAndSurnameComputations.pickFirstConsonantAndFirstTwoVowels("PANCALDI"))
    }

    @Test
    fun pickFirstThreeVowels() {
        assertEquals("AOI", NameAndSurnameComputations.pickFirstThreeVowels("PAOLINI"))
        assertEquals("AAI", NameAndSurnameComputations.pickFirstThreeVowels("PANCALDI"))
    }

    @Test
    fun pickFirstThirdAndFourthConsonant() {
        assertEquals("LBT", NameAndSurnameComputations.pickFirstThirdAndFourthConsonant("ELISABETTA"))
        assertEquals("MRT", NameAndSurnameComputations.pickFirstThirdAndFourthConsonant("UMBERTO"))
    }
}