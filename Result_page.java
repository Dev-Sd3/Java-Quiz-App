
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Round colored panel as background for results,
// Color is determined if the question is correct or not
class RoundPanel extends JPanel {
    int x, y, w, h;
    Color c;

    // Constuctor to get the positions and color
    RoundPanel(int x, int y, int w, int h, Color c) {
        setBounds(x, y, w, h);
        this.c = c;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        setLayout(null);
    }

    // Painting the rounded panel
    public void paint(Graphics g) {
        Graphics2D g1 = (Graphics2D) g.create();
        g1.setColor(c);
        g1.fillRoundRect(0, 0, w, h, 30, 30);
    }
}

// Final result page
public class Result_page extends JLabel {
    private int result = 0; // overall score
    private JLabel Title; // Title to display score
    RoundPanel Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9, Q10; // Rounded backgroud for the 10 question
    JLabel q1, q2, q3, q4, q5, q6, q7, q8, q9, q10; // Correction for questions

    // Constructor takes the User's name, parent's frame, and question set to check awnser
    Result_page(String Username, JFrame parentF, ArrayList<Question> questions) {

        // Sets an the bg2 background image
        try {
            parentF.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("bg2.png")))));
        } catch (Exception f) {
        }

        // Checks total score
        for (int i = 0; i < 10; i++) {
            if (questions.get(i).ValidateRightAwnser()) {
                result++;
            }
        }

        // Title with user's name and result
        Title = new JLabel(Username + " scored: " + result + "/10!");
        Title.setFont(new Font("Inter", Font.BOLD, 46));
        Title.setBounds(167, 30, 500, 81);
        add(Title);

        /*
         * All Components from Q1 to Q10 are results the the test where 
         * each panel is colored according to the result, then displays the correction
         * of the specific question if the user got it wrong
         */
        Q1 = new RoundPanel(31, 135, 250, 61,
                questions.get(0).ValidateRightAwnser() ? Color.decode("#C2D5A8") : Color.decode("#F43E3E"));
        q1 = new JLabel();
        q1.setText("Q1 " + questions.get(0).getCorrection());
        add(q1);
        q1.setBounds(41, 135, 250, 61);
        add(Q1);

        Q2 = new RoundPanel(331, 135, 250, 61,
                questions.get(1).ValidateRightAwnser() ? Color.decode("#C2D5A8") : Color.decode("#F43E3E"));
        q2 = new JLabel();
        q2.setText("Q2: " + questions.get(1).getCorrection());
        add(q2);
        q2.setBounds(341, 135, 250, 61);
        add(Q2);

        Q3 = new RoundPanel(31, 225, 250, 61,
                questions.get(2).ValidateRightAwnser() ? Color.decode("#C2D5A8") : Color.decode("#F43E3E"));
        q3 = new JLabel();
        q3.setText("Q3: " + questions.get(2).getCorrection());
        add(q3);
        q3.setBounds(41, 225, 250, 61);
        add(Q3);

        Q4 = new RoundPanel(331, 225, 250, 61,
                questions.get(3).ValidateRightAwnser() ? Color.decode("#C2D5A8") : Color.decode("#F43E3E"));
        q4 = new JLabel();
        q4.setText("Q4: " + questions.get(3).getCorrection());
        add(q4);
        q4.setBounds(341, 225, 250, 61);
        add(Q4);

        Q5 = new RoundPanel(31, 315, 250, 61,
                questions.get(4).ValidateRightAwnser() ? Color.decode("#C2D5A8") : Color.decode("#F43E3E"));
        q5 = new JLabel();
        q5.setText("Q5: " + questions.get(4).getCorrection());
        add(q5);
        q5.setBounds(41, 315, 250, 61);
        add(Q5);

        Q6 = new RoundPanel(331, 315, 250, 61,
                questions.get(5).ValidateRightAwnser() ? Color.decode("#C2D5A8") : Color.decode("#F43E3E"));
        q6 = new JLabel();
        q6.setText("Q6: " + questions.get(5).getCorrection());
        add(q6);
        q6.setBounds(341, 315, 250, 61);
        add(Q6);

        Q7 = new RoundPanel(31, 400, 250, 61,
                questions.get(6).ValidateRightAwnser() ? Color.decode("#C2D5A8") : Color.decode("#F43E3E"));
        q7 = new JLabel();
        q7.setText("Q7: " + questions.get(6).getCorrection());
        add(q7);
        q7.setBounds(41, 400, 250, 61);
        add(Q7);

        Q8 = new RoundPanel(331, 400, 250, 61,
                questions.get(7).ValidateRightAwnser() ? Color.decode("#C2D5A8") : Color.decode("#F43E3E"));
        q8 = new JLabel();
        q8.setText("Q8: " + questions.get(7).getCorrection());
        add(q8);
        q8.setBounds(341, 400, 250, 61);
        add(Q8);

        Q9 = new RoundPanel(31, 485, 250, 61,
                questions.get(8).ValidateRightAwnser() ? Color.decode("#C2D5A8") : Color.decode("#F43E3E"));
        q9 = new JLabel();
        q9.setText("Q9: " + questions.get(8).getCorrection());
        add(q9);
        q9.setBounds(41, 485, 250, 61);
        add(Q9);

        Q10 = new RoundPanel(331, 485, 250, 61,
                questions.get(9).ValidateRightAwnser() ? Color.decode("#C2D5A8") : Color.decode("#F43E3E"));
        q10 = new JLabel();
        q10.setText("Q10: " + questions.get(9).getCorrection());
        add(q10);
        q10.setBounds(331, 485, 250, 61);
        add(Q10);

        setSize(1000, 600);
        setLayout(null);
        setVisible(true);

    }
}
