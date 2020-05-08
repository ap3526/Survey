
import input.InputReader;

import java.util.*;

public class MultipleChoice extends Question {

    public ArrayList<String> choices;
    public String response;
    public int numResponses;
    public ArrayList<String> responseList;
    private static final input.InputDriver inputDriver = new InputReader();
    private static final long serialVersionUID = 1L;

    // Question created with prompt/number from super, choices and number of responses from user
    public MultipleChoice(Question q, ArrayList<String> c, int nr){
        super(q.questionPrompt, q.questionNumber);
        this.choices = c;
        this.numResponses = nr;
    }

    public ArrayList<String> getChoices()
    {
        return this.choices;
    }

    // creates a list of all options that are valid
    public ArrayList<String> validInputs()
    {
        ArrayList<String> valid = new ArrayList<>();
        int letters = 'a';
        for(int x = 0; x < choices.size(); x++)
        {
            char option = (char) (letters + x);
            String op = Character.toString(option);
            valid.add(op);
        }
        return valid;
    }

    // gets user response, checks if answer is valid
    public void getUserResponse(Question q)
    {
        ArrayList<String> valid = validInputs();
        if(this.numResponses > 1)
        {
            for(int x = 0; x < this.numResponses; x++)
            {
                while(true) {
                    System.out.print("Enter response " + (x+1) + ": ");
                    String response = inputDriver.readStringInput(1);
                    response = response.replace("\n", "");
                    if (valid.contains((response))) {
                        Response r = new Response(response);
                        r.addResponseToQuestion(q);
                        break;
                    } else {
                        System.out.println("Please enter a valid response");
                    }
                }
            }
        }
        else {
            System.out.print("Enter your response: ");
            String response = inputDriver.readStringInput(1);
            response = response.replace("\n", "");
            if (valid.contains((response))) {
                Response r = new Response(response);
                r.addResponseToQuestion(q);
            } else {
                System.out.println("Please enter a valid response");
                getUserResponse(q);
            }
        }
    }

    // displays the prompt and choices the user entered
    public void displayQuestion()
    {
        System.out.println("Q" + questionNumber + ": " + questionPrompt);
        int letters = 'a';
        for( int x = 0; x < choices.size(); x++)
        {
            System.out.println((char) (letters + x) + ". " + choices.get(x));
        }
        System.out.print("\n");
    }

    // allows the user to edit the prompt or a choice
    public void editQuestion()
    {
        displayQuestion();
        System.out.print("Do you wish to modify the prompt? (yes or no) ");
        String ans = inputDriver.readStringInput(1);
        ans = ans.replace("\n", "");
        if(ans.equals("yes"))
        {
            System.out.println("Enter new question prompt: ");
            String newPrompt = inputDriver.readStringInput(1);
            newPrompt = newPrompt.replace("\n", "");
            this.questionPrompt = newPrompt;
        }
        System.out.print("Do you wish to modify the choices? (yes or no) ");
        ans = inputDriver.readStringInput(1);
        ans = ans.replace("\n", "");
        if(ans.equals("yes")) {
            System.out.print("Please enter the number of the choice you wish to edit (a = 1, b = 2, etc.): ");
            int mod = inputDriver.getIntegerInput(this.choices.size());
            System.out.println("Enter new choice: ");
            String newChoice = inputDriver.readStringInput(1);
            newChoice = newChoice.replace("\n", "");
            this.choices.set(mod-1, newChoice);
        }
    }

}
