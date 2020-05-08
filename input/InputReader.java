package input;

import java.util.Scanner;

public class InputReader implements InputDriver {

    //1 scanner to read everything, multiple scanners creates problems

    private static final Scanner scanner = new Scanner(System.in);

    // checks for integer input, argument i is the max integer that can be inputted
    @Override
    public int getIntegerInput(int i) {
        int userInput;

        while(true)
        {
            try
            {
                String choice = scanner.nextLine();
                choice.strip();
                userInput = Integer.parseInt(choice);
                if ((userInput < 1 || userInput > i))
                {
                    throw new IllegalArgumentException();
                }
                break;
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Must enter a number between 1 and " + Integer.toString(i));
            }
        }
        return userInput;
    }

    // checks for String input, only gives the user the amount of maxLines lines to input their response
    @Override
    public String readStringInput(int maxLines) {
        String userInput = "";
        int lineCounter = 0;
        while (lineCounter < maxLines)
        {
            userInput = userInput.concat(scanner.nextLine()).concat("\n");
            lineCounter++;
        }
        return userInput;
    }
}
