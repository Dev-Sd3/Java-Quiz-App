import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Class to read the CSV file "Questions.csv"
public class CSV_Reader {
    // Questions are stored in the ArrayList Data structure
    private ArrayList<Question> questions = new ArrayList<>();

    // Constructor Reads the file accordingly, stores the value in an instance of
    // question and stores the question to the array list if it matches the subject
    // given, throws Exception if file not found
    CSV_Reader(String Subject) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("Questions.csv")); // scanner to read file
        scan.useDelimiter(";"); // questions are separated by ;

        // while the there are questions
        while (scan.hasNext()) {
            String questionSet = scan.next(); // reads the entire question format
            String parts[] = questionSet.split(","); // splits question into parts delimited by ,
            if (parts[1].equals(Subject) || Subject.equals("Any")) // matching the subject desired by the user
            {
                // Store the different parts into an instance of Question, then stores it in the
                // array
                questions.add(new Question(parts[1], parts[2], parts[7], parts[3], parts[4], parts[5], parts[6]));
            }
        }
        Collections.shuffle(questions); // shuffle questions to get the random order
        questions = new ArrayList<Question>(questions.subList(0, 11)); // Keeps only the amount of questions required
        scan.close(); // close the scanner
    }

    // Getter for the questions
    public ArrayList<Question> getQuestions() {
        return questions;
    }

}
