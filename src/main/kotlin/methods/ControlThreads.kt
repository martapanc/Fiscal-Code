package methods

internal class EvenThread(private val fisCode: String, evenSum: Int) : Runnable {
    var evenSum: Int = 0
        private set

    init {
        this.evenSum = evenSum
    }

    override fun run() {
        // calculate sum of character values at even position
        var i = 2
        while (i <= 14) {
            when (fisCode[i - 1]) {
                '0' -> {
                    evenSum += 0
                }
                '1' -> {
                    evenSum += 1
                }
                '2' -> {
                    evenSum += 2
                }
                '3' -> {
                    evenSum += 3
                }
                '4' -> {
                    evenSum += 4
                }
                '5' -> {
                    evenSum += 5
                }
                '6' -> {
                    evenSum += 6
                }
                '7' -> {
                    evenSum += 7
                }
                '8' -> {
                    evenSum += 8
                }
                '9' -> {
                    evenSum += 9
                }
                'A' -> {
                    evenSum += 0
                }
                'B' -> {
                    evenSum += 1
                }
                'C' -> {
                    evenSum += 2
                }
                'D' -> {
                    evenSum += 3
                }
                'E' -> {
                    evenSum += 4
                }
                'F' -> {
                    evenSum += 5
                }
                'G' -> {
                    evenSum += 6
                }
                'H' -> {
                    evenSum += 7
                }
                'I' -> {
                    evenSum += 8
                }
                'J' -> {
                    evenSum += 9
                }
                'K' -> {
                    evenSum += 10
                }
                'L' -> {
                    evenSum += 11
                }
                'M' -> {
                    evenSum += 12
                }
                'N' -> {
                    evenSum += 13
                }
                'O' -> {
                    evenSum += 14
                }
                'P' -> {
                    evenSum += 15
                }
                'Q' -> {
                    evenSum += 16
                }
                'R' -> {
                    evenSum += 17
                }
                'S' -> {
                    evenSum += 18
                }
                'T' -> {
                    evenSum += 19
                }
                'U' -> {
                    evenSum += 20
                }
                'V' -> {
                    evenSum += 21
                }
                'W' -> {
                    evenSum += 22
                }
                'X' -> {
                    evenSum += 23
                }
                'Y' -> {
                    evenSum += 24
                }
                'Z' -> {
                    evenSum += 25
                }
            }
            i += 2
        }
    }
}

internal class OddThread(private val fisCode: String, oddSum: Int) : Runnable {
    var oddSum: Int = 0
        private set

    init {
        this.oddSum = oddSum
    }

    override fun run() {
        // calculate sum of character values at odd position
        var i = 1
        while (i <= 15) {
            when (fisCode[i - 1]) {
                '0' -> {
                    oddSum += 1
                }
                '1' -> {
                    oddSum += 0
                }
                '2' -> {
                    oddSum += 5
                }
                '3' -> {
                    oddSum += 7
                }
                '4' -> {
                    oddSum += 9
                }
                '5' -> {
                    oddSum += 13
                }
                '6' -> {
                    oddSum += 15
                }
                '7' -> {
                    oddSum += 17
                }
                '8' -> {
                    oddSum += 19
                }
                '9' -> {
                    oddSum += 21
                }
                'A' -> {
                    oddSum += 1
                }
                'B' -> {
                    oddSum += 0
                }
                'C' -> {
                    oddSum += 5
                }
                'D' -> {
                    oddSum += 7
                }
                'E' -> {
                    oddSum += 9
                }
                'F' -> {
                    oddSum += 13
                }
                'G' -> {
                    oddSum += 15
                }
                'H' -> {
                    oddSum += 17
                }
                'I' -> {
                    oddSum += 19
                }
                'J' -> {
                    oddSum += 21
                }
                'K' -> {
                    oddSum += 2
                }
                'L' -> {
                    oddSum += 4
                }
                'M' -> {
                    oddSum += 18
                }
                'N' -> {
                    oddSum += 20
                }
                'O' -> {
                    oddSum += 11
                }
                'P' -> {
                    oddSum += 3
                }
                'Q' -> {
                    oddSum += 6
                }
                'R' -> {
                    oddSum += 8
                }
                'S' -> {
                    oddSum += 12
                }
                'T' -> {
                    oddSum += 14
                }
                'U' -> {
                    oddSum += 16
                }
                'V' -> {
                    oddSum += 10
                }
                'W' -> {
                    oddSum += 22
                }
                'X' -> {
                    oddSum += 25
                }
                'Y' -> {
                    oddSum += 24
                }
                'Z' -> {
                    oddSum += 23
                }
            }
            i += 2
        }
    }
}
