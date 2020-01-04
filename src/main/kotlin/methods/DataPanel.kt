package methods

import javax.swing.*
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.IOException

class DataPanel : JPanel() {

    internal var headPanel: JPanel
    internal var dataPanel: JPanel
    internal var midPanel: JPanel
    internal var namePanel: JPanel
    internal var surnamePanel: JPanel
    internal var sexPanel: JPanel
    internal var datePanel: JPanel
    internal var townPanel: JPanel
    internal var resultPanel: JPanel
    internal var buttonPanel: JPanel
    internal var nameField: JTextField
    internal var surnameField: JTextField
    internal var yearField: JTextField
    internal var townField: JTextField
    internal var resultField: JTextField
    internal var f: JRadioButton
    internal var m: JRadioButton
    internal var dayMenu: JComboBox<String>
    internal var monthMenu: JComboBox<String>
    internal var head: JLabel
    internal var sub: JLabel
    internal var nameLabel: JLabel
    internal var surnameLabel: JLabel
    internal var sexLabel: JLabel
    internal var dayLabel: JLabel
    internal var monthLabel: JLabel
    internal var yearLabel: JLabel
    internal var townLabel: JLabel
    internal var spec: JLabel
    internal var resultLabel: JLabel
    internal var calc: JButton

    init {

        dataPanel = JPanel()
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

        sexPanel = JPanel()
        sexPanel.preferredSize = Dimension(380, 40)
        sexLabel = JLabel("Sex: ")
        f = JRadioButton("Female")
        m = JRadioButton("Male")
        val bg = ButtonGroup()
        bg.add(f)
        bg.add(m)
        sexPanel.add(sexLabel)
        sexPanel.add(f)
        sexPanel.add(m)

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
        calc.addActionListener(calcListener())
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
        midPanel.add(sexPanel)
        midPanel.add(datePanel)
        midPanel.add(townPanel)
        dataPanel.add(midPanel)
        dataPanel.add(buttonPanel)
        dataPanel.add(resultPanel)

        add(dataPanel)
    }

    inner class calcListener : ActionListener {
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
            fiscalCode += ComputeFiscalCode.computeDateOfBirth(birthday, month, year, fm)

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
