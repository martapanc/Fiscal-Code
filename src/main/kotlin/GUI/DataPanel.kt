package GUI

import computations.ComputeFiscalCode
import javax.swing.*
import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.IOException

class DataPanel : JPanel() {

    private var buttonPanel: JPanel
    private var dataPanel = JPanel()
    private var datePanel: JPanel
    private var genderPanel: JPanel
    private var headPanel: JPanel
    private var midPanel: JPanel
    private var namePanel: JPanel
    private var resultPanel: JPanel
    private var surnamePanel: JPanel
    private var townPanel: JPanel
    private var head: JLabel
    private var sub: JLabel
    private var dayLabel: JLabel
    private var genderLabel: JLabel
    private var monthLabel: JLabel
    private var nameLabel: JLabel
    private var resultLabel: JLabel
    private var surnameLabel: JLabel
    private var townLabel: JLabel
    private var yearLabel: JLabel
    private var spec: JLabel
    private var calc: JButton
    internal var nameField: JTextField
    internal var resultField: JTextField
    internal var surnameField: JTextField
    internal var townField: JTextField
    internal var yearField: JTextField
    internal var f: JRadioButton
    internal var m: JRadioButton
    internal var dayMenu: JComboBox<String>
    internal var monthMenu: JComboBox<String>

    init {
        dataPanel.preferredSize = Dimension(450, 600)

        headPanel = JPanel()
        headPanel.preferredSize = Dimension(450, 90)
        headPanel.background = Color.WHITE
        head = JLabel("Italian Fiscal Code Calculator")
        head.font = Font("Arial", 32, 32)
        sub = JLabel("Insert your data:")
        sub.font = Font("Arial", 20, 20)
        headPanel.add(head)
        headPanel.add(sub)

        midPanel = JPanel()
        midPanel.preferredSize = Dimension(400, 330)
        midPanel.border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4)

        namePanel = JPanel()
        namePanel.preferredSize = Dimension(380, 30)
        nameLabel = JLabel("Name: ")
        nameField = JTextField(10)
        namePanel.add(nameLabel)
        namePanel.add(nameField)

        surnamePanel = JPanel()
        surnamePanel.preferredSize = Dimension(380, 40)
        surnameLabel = JLabel("Surname: ")
        surnameField = JTextField(10)
        surnamePanel.add(surnameLabel)
        surnamePanel.add(surnameField)

        genderPanel = JPanel()
        genderPanel.preferredSize = Dimension(380, 40)
        genderLabel = JLabel("Gender: ")
        f = JRadioButton("Female")
        m = JRadioButton("Male")
        val bg = ButtonGroup()
        bg.add(f)
        bg.add(m)
        genderPanel.add(genderLabel)
        genderPanel.add(f)
        genderPanel.add(m)

        datePanel = JPanel()
        datePanel.preferredSize = Dimension(180, 110)
        dayLabel = JLabel("Birthday:")
        val days = arrayOfNulls<String>(31)
        for (i in 0..30) {
            days[i] = (i + 1).toString() + ""
        }
        dayMenu = JComboBox<String>(days)
        monthLabel = JLabel("Month")
        val months = arrayOf(
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        )
        monthMenu = JComboBox(months)
        yearLabel = JLabel("Year: ")
        yearField = JTextField(3)
        datePanel.add(dayLabel)
        datePanel.add(dayMenu)
        datePanel.add(monthLabel)
        datePanel.add(monthMenu)
        datePanel.add(yearLabel)
        datePanel.add(yearField)

        townPanel = JPanel()
        townPanel.preferredSize = Dimension(380, 70)
        townLabel = JLabel("Place of birth*: ")
        townField = JTextField(10)
        spec = JLabel("*Italian town, or foreign country")
        spec.font = Font("Arial", 12, 12)
        townPanel.add(townLabel)
        townPanel.add(townField)
        townPanel.add(spec)

        buttonPanel = JPanel()
        buttonPanel.preferredSize = Dimension(380, 80)
        calc = JButton("Calculate")
        calc.font = Font("Arial", 18, 18)
        calc.preferredSize = Dimension(150, 50)
        calc.addActionListener(CalcListener())
        buttonPanel.add(calc)

        resultPanel = JPanel()
        resultPanel.preferredSize = Dimension(400, 50)
        resultLabel = JLabel("Your fiscal code is: ")
        resultLabel.font = Font("Arial", 18, 18)
        resultPanel.border = BorderFactory.createLoweredBevelBorder()
        resultField = JTextField(15)
        resultField.isEditable = false
        resultField.font = Font("Arial", 16, 16)
        resultPanel.add(resultLabel)
        resultPanel.add(resultField)

        dataPanel.add(headPanel)
        midPanel.add(namePanel)
        midPanel.add(surnamePanel)
        midPanel.add(genderPanel)
        midPanel.add(datePanel)
        midPanel.add(townPanel)
        dataPanel.add(midPanel)
        dataPanel.add(buttonPanel)
        dataPanel.add(resultPanel)

        add(dataPanel)
    }

    inner class CalcListener : ActionListener {
        override fun actionPerformed(event: ActionEvent) {

            resultField.text = ""
            val name = nameField.text
            val surname = surnameField.text
            var fm = "0"

            if (f.isSelected) {
                fm = "f"
            } else if (m.isSelected) {
                fm = "m"
            } else {
                JOptionPane.showMessageDialog(
                    null, "Please select a value \"Sex\" Field",
                    "Error", JOptionPane.WARNING_MESSAGE
                )
            }

            val birthday = (dayMenu.selectedIndex + 1).toString() + ""
            val month = (monthMenu.selectedIndex + 1).toString() + ""
            val year = yearField.text
            val town = townField.text

            var fiscalCode = ComputeFiscalCode.computeSurname(surname)
            fiscalCode += ComputeFiscalCode.computeName(name)
            fiscalCode += ComputeFiscalCode.computeDateOfBirth(
                birthday,
                month,
                year,
                fm
            )

            try {
                fiscalCode += ComputeFiscalCode.computeTownOfBirth(town)
            } catch (e: IOException) {
                println("Town codes file not found")
            }

            try {
                fiscalCode += ComputeFiscalCode.computeControlChar(fiscalCode)
            } catch (e: InterruptedException) {
                println("Error in calcListener")
            }

            if (fiscalCode.length == 16) {
                resultField.text = "  $fiscalCode"
            }
        }
    }
}
