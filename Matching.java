import java.util.*;
import input.InputReader;

public class Matching extends Question{

    public ArrayList<String> leftSet;
    public ArrayList<String> rightSet;
    private static final input.InputDriver inputDriver = new InputReader();
    private static final long serialVersionUID = 1L;


    public Matching(Question q, ArrayList<String> ls, ArrayList<String> rs)
    {
        super(q.questionPrompt, q.questionNumber);
        this.leftSet = ls;
        this.rightSet = rs;
    }

    public String getResponse()
    {
        return null;
    }

    // displays leftset numerically, right set alphabetically
    public void displayQuestion()
    {
        String output = "";
        System.out.println("Q" + questionNumber + ": " + questionPrompt);
        int letters = 'a';
        for( int x = 0; x < leftSet.size(); x++)
        {
            String left = (x+1) + ". " + leftSet.get(x);
            String right = (char) (letters + x) + ". " + rightSet.get(x);
            output = output.concat(String.format("%-20s%-20s", left, right)).concat("\n");
        }
        System.out.println(output);
    }

    // creates a list of valid inputs depending on the right set
    public ArrayList<String> validInputs()
    {
        ArrayList<String> valid = new ArrayList<>();
        int letters = 'a';
        for(int x = 0; x < leftSet.size(); x++)
        {
            char option = (char) (letters + x);
            String op = Character.toString(option);
            valid.add(op);
        }
        return valid;
    }

    // allows user to modify the prompt or a set
    public void editQuestion()
    {
        ArrayList<String> left = new ArrayList<>();
        ArrayList<String> right = new ArrayList<>();

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
        System.out.print("Do you wish to edit a set? (yes or no) ");
        ans = inputDriver.readStringInput(1);
        ans = ans.replace("\n", "");
        if(ans.equals("yes")) {
            System.out.print("Which set? (left or right) ");
            ans = inputDriver.readStringInput(1);
            ans = ans.replace("\n", "");
            if (ans.equals("left")) {
                System.out.print("Please enter the number of the choice you wish to edit (a = 1, b = 2, etc.): ");
                int mod = inputDriver.getIntegerInput(this.leftSet.size());
                System.out.println("Enter new choice: ");
                String newChoice = inputDriver.readStringInput(1);
                newChoice = newChoice.replace("\n", "");
                this.leftSet.set(mod - 1, newChoice);
            } else {
                System.out.print("Please enter the number of the choice you wish to edit (a = 1, b = 2, etc.): ");
                int mod = inputDriver.getIntegerInput(this.rightSet.size());
                System.out.println("Enter new choice: ");
                String newChoice = inputDriver.readStringInput(1);
                newChoice = newChoice.replace("\n", "");
                this.rightSet.set(mod - 1, newChoice);
            }
        }
    }

    // gets user response for each element in the left set individually, adds the response to a list of responses
    public void getUserResponse(Question q)
    {
        ArrayList<String> valid = validInputs();
        for( int x = 0; x < leftSet.size(); x++) {
            while(true)
            {
                System.out.print("Enter your response for " + (x + 1) + ": ");
                String response = inputDriver.readStringInput(1);
                response = response.replace("\n", "");
                if (valid.contains(response)) {
                    Response r = new Response(response);
                    r.addResponseToQuestion(q);
                    break;
                } else {
                    System.out.println("Please enter a valid response");
                }
            }
        }
    }

}
