import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CurrencyConverter extends JFrame implements ActionListener {
        
    // Declare variables for the GUI components

    private JLabel amountLabel, fromLabel, toLabel, resultLabel;
    private JTextField amountField, resultField;
    private JComboBox<String> fromBox, toBox;
    private JButton convertButton;

    private final String[] currencies = {"USD", "EUR", "GBP", "INR", "CAD", "AUD", "JPY", "CHF", "CNY", "HKD", "KRW", "MXN", "NOK", "NZD", "SEK", "SGD", "THB", "TRY", "ZAR"};
    private final double[] exchangeRates = {82.77, 88.38, 99.57, 1, 61.48,57.18, 0.616, 89.60, 12.06, 10.55, 0.06, 4.499, 8.07, 1.60, 7.99,  61.93, 2.40, 18.05,4.57};

        // Declare arrays for the currency codes and exchange rates

    public CurrencyConverter() {
             
        // Set up the GUI window

        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setResizable(false);
        setLocationRelativeTo(null);

                // Create a panel to hold the GUI components

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBackground(new Color(240, 240, 240)); // set light gray background color

                // Create the GUI components

        amountLabel = new JLabel("Amount:");
        fromLabel = new JLabel("From:");
        toLabel = new JLabel("To:");
        resultLabel = new JLabel("Result:");

        amountField = new JTextField();
        resultField = new JTextField();
        resultField.setEditable(false);

        fromBox = new JComboBox<>(currencies);
        toBox = new JComboBox<>(currencies);

        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);

                // Add the components to the panel

        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(fromLabel);
        panel.add(fromBox);
        panel.add(toLabel);
        panel.add(toBox);
        panel.add(resultLabel);
        panel.add(resultField);

                // Add the panel and the convert button to the window

        add(panel, BorderLayout.CENTER);
        add(convertButton, BorderLayout.SOUTH);

                // Make the window visible

        setVisible(true);
    }

    public static void main(String[] args) {

                // Create an instance of the CurrencyConverter class

        new CurrencyConverter();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

                // Handle the convert button click event

        if (e.getSource() == convertButton) {
            try {

            // Get the amount, "from" currency, and "to" currency values from the GUI components

                double amount = Double.parseDouble(amountField.getText());
                int fromIndex = fromBox.getSelectedIndex();
                int toIndex = toBox.getSelectedIndex();

             // Calculate the result

                double result = amount * exchangeRates[fromIndex] / exchangeRates[toIndex];
                DecimalFormat df = new DecimalFormat("#.##");
                resultField.setText(df.format(result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount entered.");
            }
        }
    }
}