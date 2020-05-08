import input.InputReader;
import java.util.*;

public class ShortAnswer extends Essay {

    public int maxLength;
    private static final input.InputDriver inputDriver = new InputReader();
    private static final long serialVersionUID = 1L;

    // uses Essay's constructor but includes a max response length
    public ShortAnswer(Question q, int maxResLength, ArrayList<String> parts)
    {
        super(q, parts);
        this.maxLength = maxResLength;
    }

    // gets user response for each part individually if multiple, else it is the same as Question's
    public void getUserResponse(Question q)
    {
        int letters = 'a';
        if(partList.size() > 1) {
            for (int x = 0; x < partList.size(); x++) {
                System.out.print("Enter your response for " + (char) (letters + x) + ": ");
                String response = inputDriver.readStringInput(maxLength);
                response = response.replace("\n", "");
                Response r = new Response(response);
                r.addResponseToQuestion(q);
                System.out.print("\n");
            }
        }
        else {
            System.out.print("Enter your response: ");
            String response = inputDriver.readStringInput(maxLength);
            response = response.replace("\n", "");
            Response r = new Response(response);
            r.addResponseToQuestion(q);
        }
    }
}
