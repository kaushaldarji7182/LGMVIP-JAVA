import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultEditorKit;

public class TextEditor extends JFrame implements ActionListener {

    private JTextArea textArea;
    private JButton openButton;
    private JButton saveButton;
    private JButton saveAsButton;
    private JButton closeButton;
    private JFileChooser fileChooser;

    public TextEditor() {

         // Set up the GUI components

        setTitle("Text Editor");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textArea);

        openButton = new JButton("Open");
        openButton.addActionListener(this);
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        saveAsButton = new JButton("Save As");
        saveAsButton.addActionListener(this);
        closeButton = new JButton("Close");
        closeButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(saveAsButton);
        buttonPanel.add(closeButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        fileChooser = new JFileChooser();

          // Set up the Edit menu 

        JMenuBar menuBar = new JMenuBar();
        JMenu editMenu = new JMenu("Edit");
        JMenuItem cutMenuItem = new JMenuItem(new DefaultEditorKit.CutAction());
        cutMenuItem.setText("Cut");
        cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        JMenuItem copyMenuItem = new JMenuItem(new DefaultEditorKit.CopyAction());
        copyMenuItem.setText("Copy");
        copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        JMenuItem pasteMenuItem = new JMenuItem(new DefaultEditorKit.PasteAction());
        pasteMenuItem.setText("Paste");
        pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        JMenuItem fontSizeMenuItem = new JMenuItem("Font Size");
        fontSizeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String size = JOptionPane.showInputDialog(TextEditor.this, "Enter font size:", textArea.getFont().getSize());
                try {
                    int newSize = Integer.parseInt(size);
                    textArea.setFont(new Font(textArea.getFont().getName(), textArea.getFont().getStyle(), newSize));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(TextEditor.this, "Invalid font size", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JMenuItem fontStyleMenuItem = new JMenuItem("Font Style");
        fontStyleMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] styles = {"Plain", "Bold", "Italic", "Bold Italic"};
                int selection = JOptionPane.showOptionDialog(TextEditor.this, "Select font style:", "Font Style", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, styles, "Plain");
                int style = Font.PLAIN;
                switch (selection) {
                    case 0:
                        style = Font.PLAIN;
                        break;
                    case 1:
                        style = Font.BOLD;
                        break;
                   
case 2:
style = Font.ITALIC;
break;
case 3:
style = Font.BOLD | Font.ITALIC;
break;
}
textArea.setFont(new Font(textArea.getFont().getName(), style, textArea.getFont().getSize()));
}
});
editMenu.add(cutMenuItem);
editMenu.add(copyMenuItem);
editMenu.add(pasteMenuItem);
editMenu.addSeparator();
editMenu.add(fontSizeMenuItem);
editMenu.add(fontStyleMenuItem);
menuBar.add(editMenu);
    setJMenuBar(menuBar);
}

@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == openButton) {

         // Handle opening a file

        int returnVal = fileChooser.showOpenDialog(TextEditor.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder sb = new StringBuilder();
                String line = reader.readLine();
                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = reader.readLine();
                }
                textArea.setText(sb.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    } else if (e.getSource() == saveButton) {

             // Handle saving a file

        saveFile();
    } else if (e.getSource() == saveAsButton) {

         // Handle saving a file with a new name/location

        int returnVal = fileChooser.showSaveDialog(TextEditor.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            saveFile(fileChooser.getSelectedFile());
        }
    } else if (e.getSource() == closeButton) {

        // Handle closing the editor window

        dispose();
    }
}

private void saveFile() {
    if (fileChooser.getSelectedFile() != null) {

        // If a file is already selected, save to that file

        saveFile(fileChooser.getSelectedFile());
    } else {

        // Otherwise, prompt the user to select a new file to save to
        
        int returnVal = fileChooser.showSaveDialog(TextEditor.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            saveFile(fileChooser.getSelectedFile());
        }
    }
}

private void saveFile(java.io.File file) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        writer.write(textArea.getText());
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}

public static void main(String[] args) {
    TextEditor editor = new TextEditor();
    editor.setVisible(true);
}
}