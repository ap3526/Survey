import input.InputReader;

import java.util.*;


public class Essay extends Question{

    public String response;
    public ArrayList<String> responseList;
    private static final long serialVersionUID = 1L;
    public ArrayList<String> partList;
    private static final input.InputDriver inputDriver = new InputReader();

    public Essay(Question q, ArrayList<String> parts)
    {
        super(q.questionPrompt, q.questionNumber);
        this.partList = parts;
    }

    // gets the user response, goes through each part 1 at a time if multiple parts. Adds response to a list in case multiple responses
    public void getUserResponse(Question q) {

        int letters = 'a';
        if(partList.size() > 1) {
            for (int x = 0; x < partList.size(); x++) {
                System.out.print("Enter your response for " + (char) (letters + x) + ": ");
                String response = inputDriver.readStringInput(3);
                response = response.replace("\n", "");
                Response r = new Response(response);
                r.addResponseToQuestion(q);
            }
        }
        else
        {
            System.out.print("Enter your response: ");
            String response = inputDriver.readStringInput(3);
            response = response.replace("\n", "");
            Response r = new Response(response);
            r.addResponseToQuestion(q);
        }
    }
    // displays the question
    public void displayQuestion()
    {
        int letters = 'a';
        System.out.println("Q" + questionNumber + ": " + questionPrompt);
        for (int x = 0; x < this.partList.size(); x++)
        {
            System.out.println((char) (letters + x) + ". " + partList.get(x));
        }
        System.out.print("\n");
    }

    // allows user to modify the prompt or subquestions
    public void editQuestion() {
        displayQuestion();
        System.out.print("Do you wish to modify the prompt? (yes or no) ");
        String ans = inputDriver.readStringInput(1);
        ans = ans.replace("\n", "");
        if (ans.equals("yes")) {
            System.out.println("Enter new question prompt: ");
            String newPrompt = inputDriver.readStringInput(1);
            newPrompt = newPrompt.replace("\n", "");
            this.questionPrompt = newPrompt;
        }
        if (this.partList.size() > 1)
        {
            System.out.print("Do you wish to modify a subquestion? (yes or no) ");
            ans = inputDriver.readStringInput(1);
            ans = ans.replace("\n", "");
            if (ans.equals("yes")) {
                System.out.print("Which subquestion? (a = 1, b = 2, etc.): ");
                int sub = inputDriver.getIntegerInput(partList.size());
                System.out.print("Enter new prompt: ");
                String newSub = inputDriver.readStringInput(1);
                newSub = newSub.replace("\n", "");
                partList.set(sub-1, newSub);
            }
        }
    }
}
