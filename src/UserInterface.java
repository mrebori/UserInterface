import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.awt.Color.GREEN;

public class UserInterface extends JFrame implements ActionListener {
    private JMenuItem menuItem1, menuItem2, menuItem3, menuItem4;
    private JTextArea outputArea;
    private String formattedDate;
    private Date date;

    // Create a PrintWriter
    File myFile = new File("log.txt");
    PrintWriter dateWriter;

    {
        try {
            dateWriter = new PrintWriter(myFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public UserInterface() throws FileNotFoundException {

        // Create the main frame
        setTitle("Date & Time Application");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        outputArea = new JTextArea();
        date = new Date();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        formattedDate = currentDate.format(date);

        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create the menu
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);

        // Create and add Menu items
        menuItem1 = new JMenuItem("Date and Time");
        menuItem2 = new JMenuItem("Print to File");
        menuItem3 = new JMenuItem("Change Background Color");
        menuItem4 = new JMenuItem("Exit App");

        // Add action listeners to menu items
        menuItem1.addActionListener(this);
        menuItem2.addActionListener(this);
        menuItem3.addActionListener(this);
        menuItem4.addActionListener(this);

        Font font = new Font("Cambria", Font.BOLD, 14);

        menu.setFont(font);
        menuItem1.setFont(font);
        menuItem2.setFont(font);
        menuItem3.setFont(font);
        menuItem4.setFont(font);

        // Add menu items to the menu
        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menu.add(menuItem4);

        // Add the menu bar to the frame
        setJMenuBar(menuBar);

    }

    @Override
    public void actionPerformed(ActionEvent evnt) {
        try {
            if (evnt.getSource() == menuItem1) {
                outputArea.setText("Current Date and Time: " + formattedDate);

            } else if (evnt.getSource() == menuItem2) {
                dateWriter.write(formattedDate);
                JOptionPane.showMessageDialog(this, "Date and Time Printed to file log.txt!");
                dateWriter.close();

            } else if (evnt.getSource() == menuItem3) {
            setBackground(GREEN);
            outputArea.setText("Background color changed to Green");

            } else if (evnt.getSource() == menuItem4) {
                System.exit(0);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Action");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserInterface ui = null;
            try {
                ui = new UserInterface();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            ui.setVisible(true);
        });
    }
}
