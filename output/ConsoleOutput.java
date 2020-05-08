package output;

import java.io.Serializable;
import java.util.*;

public class ConsoleOutput implements OutputMenuDriver, Serializable {

    private static final long serialVersionUID = 1L;

    // displays the contents of a list with numbers
    public void displayNumberedOptions(ArrayList<String> menuOptions)
    {
        int num = 1;
        for (String option: menuOptions)
        {
            String optionDisplay = Integer.toString(num) + ". " + option;
            System.out.println(optionDisplay);
            num++;
        }
    }
}
