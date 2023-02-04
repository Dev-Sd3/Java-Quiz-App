/*
 * Question screen where user takes the test, user can move freely between questions
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/* 
*Child class inherits from JButton and overrides paint methods 
*to create a rounded button, these buttons are used as options in questions
*/
class RoundJButton extends JButton {
    private Color c;

    public RoundJButton(Color c) {
        setOpaque(false);
        this.c = c;
    }

    protected void paintComponent(Graphics g) {
        g.setColor(c);
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(Color.decode("#D7AECF"));
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
    }
}

/*
 * Also a background rounded panel used to display a rounded colors panel
 * in the background, only used for design
 */

class BackgroundPanelQ extends JPanel {
    BackgroundPanelQ(int x, int y, int w, int h) {
        setBounds(x, y, w, h);
        setLayout(null);
    }

    public void paint(Graphics g) {
        Graphics2D g1 = (Graphics2D) g.create();
        g1.setColor(Color.decode("#BAD5F0"));
        g1.fillRoundRect(200, 55, 600, 146, 50, 50);
    }
}

// Question screen
class Question_Screen extends JLabel implements ActionListener {
    private ArrayList<Question> QSet; // Question arraylist
    private int pos; // Current position
    private String Username; //User's name given from Welcome_Screen.java
    private BackgroundPanelQ QuestionBox; //Backgroud panel
    private JLabel Q; // Current question
    private RoundJButton Option1, Option2, Option3, Option4; // 4 Options where 1 is the Awnser
    private JButton Next, Previous; // Next/Previous button
    private Result_page result; // Final result page
    private JFrame parentF; //Parent frame from Welcome_Screen

    //Constructor takes as parameter the parent Frame, question set to display and the user's name
    Question_Screen(JFrame parentF, ArrayList<Question> QSet, String Username) {

        this.parentF = parentF;
        this.QSet = QSet;
        this.Username = Username;
        pos = 0; // Current question to be displayed

        //Label where questions are places
        Q = new JLabel();
        Q.setFont(new Font("Inter", Font.BOLD, 20));
        Q.setBounds(220, 55, 700, 140);
        add(Q);

        /*
         * First option in the MCQ, all buttons are rounded with "inter" font
         * with specific colors, all buttons also have the actionPerformed action
         */
        Option1 = new RoundJButton(Color.decode("#F0D5BA"));
        Option1.setFont(new Font("Inter", Font.BOLD, 20));
        Option1.setBounds(200, 250, 280, 85);
        Option1.setBackground(Color.decode("#D6EFF6"));
        Option1.setForeground(Color.decode("#D6EFF6"));
        Option1.addActionListener(this);
        add(Option1);

        // Second option
        Option2 = new RoundJButton(Color.decode("#C08AEA"));
        Option2.setBounds(500, 250, 280, 85);
        Option2.setFont(new Font("Inter", Font.BOLD, 20));
        Option2.setBackground(Color.decode("#D6EFF6"));
        Option2.setForeground(Color.decode("#D6EFF6"));
        Option2.addActionListener(this);
        add(Option2);

        // Third option
        Option3 = new RoundJButton(Color.decode("#C2D5A8"));
        Option3.setBounds(200, 360, 280, 85);
        Option3.setFont(new Font("Inter", Font.BOLD, 20));
        Option3.setBackground(Color.decode("#D6EFF6"));
        Option3.setForeground(Color.decode("#D6EFF6"));
        Option3.addActionListener(this);
        add(Option3);

        // Fourth option
        Option4 = new RoundJButton(Color.decode("#F99696"));
        Option4.setBounds(500, 360, 280, 85);
        Option4.setFont(new Font("Inter", Font.BOLD, 20));
        Option4.setBackground(Color.decode("#D6EFF6"));
        Option4.setForeground(Color.decode("#D6EFF6"));
        Option4.addActionListener(this);
        add(Option4);

        // Rounded panel used as a background for the question
        QuestionBox = new BackgroundPanelQ(0, 0, 800, 300);
        add(QuestionBox);

        // Next button is a picture which increments the 
        // position and updates questions and awnsers
        Icon next = new ImageIcon("next.png");
        Next = new JButton(next);
        Next.setBackground(Color.decode("#BAD5F0"));
        Next.setBounds(500, 470, 60, 60);
        Next.setOpaque(false);
        Next.setFocusPainted(false);
        Next.setBorderPainted(false);
        Next.setContentAreaFilled(false);
        Next.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        Next.addActionListener(this);
        add(Next);

        // Previous is also a button, which does the opposite of next
        Icon prev = new ImageIcon("previous.png");
        Previous = new JButton(prev);
        Previous.setBackground(Color.decode("#BAD5F0"));
        Previous.setBounds(425, 470, 60, 60);
        Previous.setOpaque(false);
        Previous.setFocusPainted(false);
        Previous.setBorderPainted(false);
        Previous.setContentAreaFilled(false);
        Previous.addActionListener(this);
        Previous.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        add(Previous);

        setSize(1000, 600);
        setLayout(null);
        setVisible(true);
        setQandA();
    }

    // Button action which logs the awnser given by the user and sets up the mcq
    public void actionPerformed(ActionEvent e) {
        
        // Logs the option given in the question object in the AwnserGiven attribute
        // then moves to the next question
        if (e.getSource() == Option1) {
            QSet.get(pos).setAwnserGiven(Option1.getText());
            goNext();
        }
        if (e.getSource() == Option2) {
            QSet.get(pos).setAwnserGiven(Option2.getText());
            goNext();
        }
        if (e.getSource() == Option3) {
            QSet.get(pos).setAwnserGiven(Option3.getText());
            goNext();
        }
        if (e.getSource() == Option4) {
            QSet.get(pos).setAwnserGiven(Option4.getText());
            goNext();
        }

        // Next and previous buttons
        if (e.getSource() == Next && pos != 10) {
            goNext();
        }
        if (e.getSource() == Previous && pos != 0) {
            goPrev();
        }
    }

/*
Go prev and go next decrements/increments the QSet positions and sets up the mcq,
If, the positon is 10, we go to the result window since there are no more questions
*/ 
    public void goPrev() {
        if (pos != 0) {
            pos--;
            setQandA();
        }
    }
    public void goNext() {
        if (pos != 10) {
            pos++;
            setQandA();
        }
        // if pos == 10, go to result screen by adding the panel 
        // and hiding the previous one
        if (pos == 10) {
            result = new Result_page(Username, parentF, QSet);
            parentF.add(result);
            setVisible(false);
            result.setVisible(true);
        }
    }

    // SetQandA modifies the question and 4 options according to position
    // of the current question is QSet
    public void setQandA() {
        Option1.setText(QSet.get(pos).getOptionOne());
        Option2.setText(QSet.get(pos).getOptionTwo());
        Option3.setText(QSet.get(pos).getOptionThree());
        Option4.setText(QSet.get(pos).getOptionFour());
        Q.setText(QSet.get(pos).getQuestion());
    }

}
