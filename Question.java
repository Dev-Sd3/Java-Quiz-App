
/*
 * Question class, contains all information related to the question
 */

public class Question {
    //Self documenting variable names
    private String Question, RightAwnser, OptionOne, OptionTwo, OptionThree, OptionFour, Subject;
    private String AwnserGiven = "Left Empty!";
    
    //Contructing the object using our setters
    Question(String Subject, String q, String a,String ow, String otw, String otr, String of) {
        setSubject(Subject);
        setQuestion(q);
        setRightAwnser(a);
        setOptionOne(ow);
        setOptionTwo(otw);
        setOptionThree(otr);
        setOptionFour(of);
    }


    // Asserting a size lenght bigger than 0 and less than 200 for design purposes
    public boolean ValidateText(String t) {
        return t.length() > 0 && t.length() < 200;
    }

    // Getters & Setters for attributes needed

    public void setOptionOne(String OptionOne) {
        if (ValidateText(RightAwnser))
            this.OptionOne = OptionOne;
        else
            OptionOne = "";
    }
    public String getOptionOne() {
        return OptionOne;
    }
    public void setSubject(String subject) {
        if(ValidateText(subject))
            this.Subject = subject;
        else Subject = "";
    }
    public String getSubject() {
        return Subject;
    }

    public void setRightAwnser(String RightAwnser) {
        if (ValidateText(RightAwnser))
            this.RightAwnser = RightAwnser;
        else
            RightAwnser = "";
    }

    public String getRightAwnser() {
        return RightAwnser;
    }

    public void setQuestion(String question) {
        if (ValidateText(question))
            Question = question;
        else
            Question = "ERROR";
    }

    public String getQuestion() {
        return Question;
    }

    public void setOptionTwo(String optionTwo) {
        if (ValidateText(optionTwo))
            OptionTwo = optionTwo;
        else
            OptionTwo = "invalid";
    }

    public String getOptionTwo() {
        return OptionTwo;
    }

    public void setOptionThree(String optionThree) {
        if (ValidateText(optionThree))
            OptionThree = optionThree;
        else
            OptionThree = "invalid";
    }

    public String getOptionThree() {
        return OptionThree;
    }

    public void setOptionFour(String optionFour) {
        if (ValidateText(optionFour))
            OptionFour = optionFour;
        else
            OptionFour = "invalid";
    }

    public String getOptionFour() {
        return OptionFour;
    }

    
    public void setAwnserGiven(String awnserGiven) {
        AwnserGiven = awnserGiven;
    }
    public String getAwnserGiven() {
        return AwnserGiven;
    }

    //Validates an awnser by checking if its equal to the right awnser, returns a boolean
    public boolean ValidateRightAwnser()
    {
        if (RightAwnser != null && AwnserGiven != null)
            return AwnserGiven.equals(RightAwnser);
        else return false;
    }

    // Returns a string to display the result of the specific question, 
    // also mentions the right awnser if user got a wrong awnser
    // Used in Result_page.java
    public String getCorrection()
    {
        if (RightAwnser != null && AwnserGiven != null && RightAwnser.equals(AwnserGiven))
        {
            return AwnserGiven+ ", Correct!";
        }
        else
        {
            return AwnserGiven+ " Incorrect!, "+RightAwnser;
        }
    }

    //To string method used for debugging purposes 
    @Override
    public String toString() {
        return Subject+Question+ RightAwnser+ OptionTwo+ OptionThree+ OptionFour;
    }

}
