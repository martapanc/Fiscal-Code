import methods.DataPanel

import javax.swing.*

object FiscalCodeCalculator {

    @Throws(SecurityException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val frame1 = JFrame()
        frame1.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        val dp = DataPanel()
        frame1.contentPane.add(dp)
        frame1.setLocation(200, 100)
        frame1.pack()
        frame1.isVisible = true
    }
}
