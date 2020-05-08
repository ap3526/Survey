
import java.util.*;

public class TrueFalse extends MultipleChoice{

    public ArrayList<String> choices;

    // Uses MultipleChoice's constructor, will have its own set of choices and only accepts 1 response
    public TrueFalse(Question q, ArrayList<String> c)
    {
        super(q, c, 1);
        this.choices = c;
    }

    // True/False will only have 2 options, display them and the question
    public void displayQuestion()
    {
        System.out.println("Q" + questionNumber + ": " + questionPrompt);
        System.out.println("a. " + choices.get(0));
        System.out.println("b. " + choices.get(1));
        System.out.print("\n");
    }
}
