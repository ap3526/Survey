import input.InputReader;
import output.ConsoleOutput;

public class Response {

    public String userResponse;
    private static final output.OutputMenuDriver outputMenuDriver = new ConsoleOutput();
    private static final input.InputDriver inputDriver = new InputReader();
    private static final long serialVersionUID = 1L;


    public Response(String r)
    {
        this.userResponse = r;
    }

    public String getUserResponse()
    {
        return this.userResponse;
    }
    // allows the user to edit their response
    public void editUserResponse()
    {
        System.out.print("Enter new response: ");
        String newResponse = inputDriver.readStringInput(1);
        this.userResponse= newResponse;

    }
    // adds the response to Question's response list
    public void addResponseToQuestion(Question q)
    {
        q.response.add(this.userResponse);
    }
}
