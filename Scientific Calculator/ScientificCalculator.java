import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScientificCalculator extends JFrame {
    private JTextField displayField;
    private double num1, num2, result;
    private String operator = "";
    
    // Define the constructor to set up the GUI
    public ScientificCalculator() {
        displayField = new JTextField();
        displayField.setEditable(true);
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setFont(new Font("Arial", Font.BOLD, 25));
        

    
        
        JPanel mainPanel = new JPanel(new GridLayout(5, 4));
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");
        JButton buttonDivide = new JButton("/");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton buttonMultiply = new JButton("*");
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");

        JButton buttonSubtract = new JButton("-");
        JButton button0 = new JButton("0");
        JButton buttonDecimal = new JButton(".");
        JButton buttonPi = new JButton("π");
        JButton buttonAdd = new JButton("+");
        JButton buttonClear = new JButton("C");
        JButton buttonDelete = new JButton("Del");
        JButton buttonPower = new JButton("^");
        JButton buttonSin = new JButton("sin");
        JButton buttonCos = new JButton("cos");
        JButton buttonTan = new JButton("tan");
        JButton buttonLog = new JButton("log");
        JButton buttonSqrt = new JButton("sqrt");
        JButton buttonEquals = new JButton("=");
        
        // Set up the number button actions

        button0.addActionListener(new NumberButtonActionListener("0"));
        button1.addActionListener(new NumberButtonActionListener("1"));
        button2.addActionListener(new NumberButtonActionListener("2"));
        button3.addActionListener(new NumberButtonActionListener("3"));
        button4.addActionListener(new NumberButtonActionListener("4"));
        button5.addActionListener(new NumberButtonActionListener("5"));
        button6.addActionListener(new NumberButtonActionListener("6"));
        button7.addActionListener(new NumberButtonActionListener("7"));
        button8.addActionListener(new NumberButtonActionListener("8"));
        button9.addActionListener(new NumberButtonActionListener("9"));
        buttonDecimal.addActionListener(new NumberButtonActionListener("."));
        buttonPi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayField.setText(Double.toString(Math.PI));
            }
        });
        
        // Set up the operator button actions

        buttonAdd.addActionListener(new OperatorButtonAction("+"));
        buttonSubtract.addActionListener(new OperatorButtonAction("-"));
        buttonMultiply.addActionListener(new OperatorButtonAction("*"));
        buttonDivide.addActionListener(new OperatorButtonAction("/"));
        buttonPower.addActionListener(new OperatorButtonAction("^"));
        
        // Set up the function button actions

        buttonSin.addActionListener(new FunctionButtonAction("sin"));
        buttonCos.addActionListener(new FunctionButtonAction("cos"));
        buttonTan.addActionListener(new FunctionButtonAction("tan"));
        buttonLog.addActionListener(new FunctionButtonAction("log"));
        buttonSqrt.addActionListener(new FunctionButtonAction("sqrt"));
        
        buttonEquals.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num2 = Double.parseDouble(displayField.getText());
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                    break;
                    case "^":
                        result = Math.pow(num1, num2);
                        break;
                }
                displayField.setText(Double.toString(result));
                num1 = result;
                operator = "";
            }
        });
        
        // Set up the clear button action

        buttonClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num1 = 0;
                num2 = 0;
                result = 0;
                operator = "";
                displayField.setText("");
            }
        });
        
        // Set up the delete button action

        buttonDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String currentText = displayField.getText();
                if (currentText.length() > 0) {
                    displayField.setText(currentText.substring(0, currentText.length() - 1));
                }
            }
        });
        
        // Add the components to the main panel

        mainPanel.add(buttonClear);
        mainPanel.add(buttonDelete);
        mainPanel.add(buttonPower);
       
        mainPanel.add(button0);
        mainPanel.add(button1);
        mainPanel.add(button2);
        mainPanel.add(button3);
        mainPanel.add(button4);
        mainPanel.add(button5);
        mainPanel.add(button6);
        mainPanel.add(button7);
        mainPanel.add(button8);
        mainPanel.add(button9);
       
        mainPanel.add(buttonDecimal);
        mainPanel.add(buttonPi);
        mainPanel.add(buttonEquals);
        mainPanel.add(buttonSin);
        mainPanel.add(buttonCos);
        mainPanel.add(buttonTan);
        mainPanel.add(buttonLog);
        mainPanel.add(buttonSqrt);
        mainPanel.add(buttonAdd);
        mainPanel.add(buttonSubtract);
        mainPanel.add(buttonMultiply);
        mainPanel.add(buttonDivide);

        // Add the components to the frame

        add(displayField, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        
        // Set up the frame

        setTitle("Scientific Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(450, 300);
    }

    // Define the action listener for the number buttons

    private class NumberButtonActionListener implements ActionListener {
        private String number;
        
        public NumberButtonActionListener(String number) {
            this.number = number;
        }
        
        public void actionPerformed(ActionEvent e) {
            displayField.setText(displayField.getText() + number);
        }
    }
    
    // Define the action listener for the operator buttons

    private class OperatorButtonAction implements ActionListener {
        private String op;
        
        public OperatorButtonAction(String op) {
            this.op = op;
        }
        
        public void actionPerformed(ActionEvent e) {
            num1 = Double.parseDouble(displayField.getText());
            operator = op;
            displayField.setText("");
        }
    }
    
    // Define the action listener for the function buttons
    
    private class FunctionButtonAction implements ActionListener {
        private String function;
        
        public FunctionButtonAction(String function) {
            this.function = function;
        }
        
        public void actionPerformed(ActionEvent e) {
            double value = Double.parseDouble(displayField.getText());
            switch (function) {
                case "sin":
                    value = Math.sin(value);
                    break;
                case "cos":
                    value = Math.cos(value);
                    break;
                case "tan":
                    value = Math.tan(value);
                    break;
                case "log":
                    value = Math.log10(value);
                    break;
                case "sqrt":
                    value = Math.sqrt(value);
                    break;
            }
            displayField.setText(Double.toString(value));
        }
    }
    
    // Define the main method
    public static void main (String args[])
    {
        new ScientificCalculator();
    }
}
    