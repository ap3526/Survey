import input.InputReader;

import java.text.SimpleDateFormat;
import java.util.*;

public class ValidDate extends Question{

    private static final input.InputDriver inputDriver = new InputReader();
    private static final long serialVersionUID = 1L;

    // valid date uses Question's constructor
    public ValidDate(Question q)
    {
        super(q.questionPrompt, q.questionNumber);
    }


    // gets user response and makes sure it is in the right format, will keep asking if it is not
    public void getUserResponse(Question q)
    {
        String response = "";
        while(true) {
            System.out.print("Enter your response: ");
            response = inputDriver.readStringInput(1);
            response = response.replace("\n", "");
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            sdf.setLenient(false);
            try {
                Date valid = sdf.parse(response);
                break;
            } catch (Exception e) {
                System.out.println("Please enter a date in the format provided");
            }
        }
        Response r = new Response(response);
        r.addResponseToQuestion(q);
    }

    // displays the question
    public void displayQuestion()
    {
        System.out.println("Q" + questionNumber + ": " + questionPrompt + " (Response must be in MM-DD-YYYY format) \n");
    }

}
