/*
 * First Part to be run by the program
 * Contains the Frame used to display all panels and information
 */

import javax.imageio.ImageIO;

import javax.swing.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;

import java.util.ArrayList;

/*
 * RoundedTF inherits from JTextField and overrides methods of 
 * its parent to get a rounded border, which is nicer is our program
 */
class RoundedTF extends JTextField {

    public RoundedTF(int size) {
        super(size);
        setOpaque(false);
    }

    // Paints the component in a way to get a rounded shape
    protected void paintComponent(Graphics g) {
        g.setColor(Color.decode("#D6EFF6"));
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
        super.paintComponent(g);
    }

    // Paints the border of component in a way to get a rounded shape
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
    }
}

/*
 * This class also inherits and overrides the JPanel
 * to get a rounded border with a desired color
 */
class WelcomePanel extends JPanel {
    WelcomePanel(int x, int y, int w, int h) {
        setBounds(x, y, w, h);
        setLayout(null);
    }

    // Paints the panel in a way to get a rounded shape
    public void paint(Graphics g) {
        Graphics2D g1 = (Graphics2D) g.create();
        g1.setColor(Color.decode("#BAD5F0"));
        g1.fillRoundRect(156, 36, 688, 477, 50, 50);
    }
}

// Welcome Screen JFrame which implemenets the ActionListener Interface
public class Welcome_Screen extends JFrame implements ActionListener {

    // Subject Options
    String[] Subj = { "Any", "Math", "Programming", "History", "Culture" };
    WelcomePanel panel1; // Background colored rounded panel
    JPanel mainPanel; // All components stored in this panel to easily set visibility
    JLabel lblWelcome, lblName, lblSubject; // Labels containing indicative text
    RoundedTF NameField; // Rounded text field
    JComboBox<String> Subjects; // List of subjects to be selected
    JButton Go; // Button to move to the other panel
    String Username, SubjectWanted; // Stores the Username and Subject wanted to be sent to CSV_Reader and
                                    // Result_page respectively
    ArrayList<Question> QSet; // Questions set Created by CSV_Reader
    Question_Screen questionSession; // Secondary Panel

    public Welcome_Screen() {
        // Main panel to hold components
        mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 1000, 600);
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));

        // Welcome message
        lblWelcome = new JLabel("Hello User!");
        lblWelcome.setBounds(400, 47, 277, 60);
        lblWelcome.setFont(new Font("Inter", Font.PLAIN, 48));
        mainPanel.add(lblWelcome);

        // Indicatory label to enter name
        lblName = new JLabel("Enter Your Name");
        lblName.setBounds(380, 153, 333, 42);
        lblName.setFont(new Font("Inter", Font.PLAIN, 36));
        mainPanel.add(lblName);

        // Rounded text field to enter name
        NameField = new RoundedTF(1);
        NameField.setBounds(340, 213, 361, 53);
        mainPanel.add(NameField);

        // Indicatory label to enter name
        lblSubject = new JLabel("Select Your Prefered Subject");
        lblSubject.setBounds(290, 300, 524, 42);
        lblSubject.setFont(new Font("Inter", Font.PLAIN, 36));
        mainPanel.add(lblSubject);

        // Subject comboBox to select subject
        Subjects = new JComboBox<>(Subj);
        Subjects.setBounds(370, 360, 295, 53);
        mainPanel.add(Subjects);

        // Go Button which uses the "go.png" image as its asset.
        Icon go = new ImageIcon("go.png");
        Go = new JButton(go);
        Go.setBackground(Color.decode("#BAD5F0"));
        Go.setBounds(470, 430, 80, 80);
        Go.setOpaque(false);
        Go.setFocusPainted(false);
        Go.setBorderPainted(false);
        Go.setContentAreaFilled(false);
        Go.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mainPanel.add(Go);

        // Background panel used just for design
        panel1 = new WelcomePanel(0, 0, 1000, 600);
        add(mainPanel);
        add(panel1);

        /*
         * The go button does:
         * set The background of the frame as a nice cozy picture.
         * Acquired the username and subject wanted then sends uses them to
         * generate a question set, then create a question session panel.
         * The then question session is displayed and original main panel is hidden
         * 
         */
        Go.addActionListener(new ActionListener() // Anonymous function
        {
            public void actionPerformed(ActionEvent e) {
                // Tries to get background image
                try {
                    setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("QuestBG.png")))));
                } catch (Exception f) {}

                // Acquiring attributes
                Username = NameField.getText();
                SubjectWanted = (String) Subjects.getSelectedItem();

                // Tries to Create Question set
                try {
                    QSet = new CSV_Reader(SubjectWanted).getQuestions();
                } catch (Exception g) {
                }

                // Hides panel then displays new panel
                questionSession = new Question_Screen(getSelf(), QSet, Username);
                mainPanel.setVisible(false);
                add(questionSession);
                // resizing the window seems to "refresh" the page and display our image
                setSize(100, 100);
                setSize(1000, 600);
            }
        });

        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.decode("#D7AECF"));
        setLayout(null);
        setResizable(false);
        setVisible(true);
    }

    // Gets the frame itself
    public JFrame getSelf() {
        return this;
    }

    public void actionPerformed(ActionEvent a) {
    }
}