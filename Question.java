

import input.InputReader;
import java.io.Serializable;

import java.util.*;

public class Question implements Serializable{

    public String questionPrompt;
    public int questionNumber;
    public ArrayList<String> response;
    public ArrayList<String> responseList;
    private static final input.InputDriver inputDriver = new InputReader();
    private static final long serialVersionUID = 1L;

    // Question is created with an empty response list, which will be populated when user takes the survey
    public Question(String prompt, int qNumber)
    {
        this.questionPrompt = prompt;
        this.questionNumber = qNumber;
        this.response = new ArrayList<>();
    }

    // basic edit function, allows for editing the prompt
    public void editQuestion()
    {
        displayQuestion();
        System.out.print("Do you wish to modify the prompt? (yes or no)");
        String ans = inputDriver.readStringInput(1);
        ans = ans.replace("\n", "");
        if(ans.equals("yes"))
        {
            System.out.println("Enter new question prompt: ");
            String newPrompt = inputDriver.readStringInput(1);
            newPrompt = newPrompt.replace("\n", "");
            this.questionPrompt = newPrompt;
        }
    }

    public Survey getSurvey(Survey s)
    {
        return null;
    }

    // returns question number if needed
    public int getQuestionNumber(Question q)
    {
        return 2;
    }

    // returns just the question prompt
    public String getPrompt(Question q)
    {
        return q.questionPrompt;
    }

    // displays question number and prompt
    public void displayQuestion()
    {
        System.out.println("Q" + questionNumber + ": " + questionPrompt);
    }

    // displays the response(s) to the associated question
    public void displayResponse()
    {
        System.out.println("Response: " + response);
        System.out.print("\n");
    }

    // gets user response, adds it to its response attribute
    public void getUserResponse(Question q)
    {
        System.out.print("Enter your response: ");
        String response = inputDriver.readStringInput(1);
        response = response.replace("\n", "");
        Response r = new Response(response);
        r.addResponseToQuestion(q);
    }

}
