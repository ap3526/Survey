
import java.util.*;

import input.InputDriver;
import input.InputReader;
import output.OutputMenuDriver;
import output.ConsoleOutput;

public class Main {

    Survey survey = null;
    private static final OutputMenuDriver outputMenuDriver = new ConsoleOutput();
    private static final InputDriver inputDriver = new InputReader();
    // create a dao for serialization
    private static final SerializableDAO dao = new SerializableDAO();


    public static void main(String[] args){
        Survey placeHold = null;
        ArrayList<String> firstMenu = new ArrayList<String>();
        firstMenu.add("Create a new Survey");
        firstMenu.add("Display an existing Survey");
        firstMenu.add("Load an existing Survey");
        firstMenu.add("Save the current Survey");
        firstMenu.add("Take the current Survey");
        firstMenu.add("Edit an existing Survey");
        firstMenu.add("Exit");
        while(true)
        {
            // display the enumerated menu and call on the requested function
            outputMenuDriver.displayNumberedOptions(firstMenu);
            System.out.print("Please select a menu option: ");
            int selection = inputDriver.getIntegerInput(7);
            switch(selection){
                case 1:
                    placeHold = createSurvey();
                    break;
                case 2:
                    displaySurvey(placeHold);
                    break;
                case 3:
                    placeHold = loadSurvey();
                    break;
                case 4:
                    saveSurvey(placeHold);
                    break;
                case 5:
                    takeSurvey(placeHold);
                    break;
                case 6:
                    editSurvey(placeHold);
                    break;
                case 7:
                    System.exit(0);
            }
        }

    }

    // Asks user for number of questions, then loops through asking for type and prompt for each question and adds it to a list of Questions
    public static Survey createSurvey()
    {
        Question questionBase = null;
        Question question = null;
        ArrayList<Question> questions = new ArrayList<Question>();
        ArrayList<String> addMenu = new ArrayList<>();
        addMenu.add("Add a Multiple Choice question");
        addMenu.add("Add a True / False question");
        addMenu.add("Add a Essay question");
        addMenu.add("Add a Short Answer question");
        addMenu.add("Add a Matching question");
        addMenu.add("Add a ValidDate question");
        addMenu.add("Return to previous menu");
        ArrayList<String> leftSet = new ArrayList<String>();
        ArrayList<String> rightSet = new ArrayList<String>();

        System.out.print("Enter the name of this survey: ");
        String surveyName = inputDriver.readStringInput(1);
        surveyName = surveyName.replace("\n", "");
        System.out.print("Please enter the number of questions in the survey: ");
        int numberOfQ = inputDriver.getIntegerInput(15);
        int counter = 1;
        while (counter < numberOfQ + 1)
        {
            outputMenuDriver.displayNumberedOptions(addMenu);
            System.out.print("Select your type: ");
            int typeOfQ = inputDriver.getIntegerInput(addMenu.size());
            switch (typeOfQ)
            {
                case 1:
                    System.out.println("Enter question" + Integer.toString(counter) + " prompt: ");
                    String qPrompt = inputDriver.readStringInput(1);
                    qPrompt = qPrompt.replace("\n", "");
                    // create a Question using the prompt and counter, will be used at the end of the case
                    questionBase = new Question(qPrompt, counter);
                    ArrayList<String> choices = new ArrayList<String>();
                    System.out.print("Enter the number of choices: ");
                    int numChoices = inputDriver.getIntegerInput(30);
                    System.out.print("Enter number of expected responses: ");
                    int numResponses = inputDriver.getIntegerInput(numChoices);
                    int choiceCounter = 0;
                    // make as many choices as the user requests
                    while(choiceCounter < numChoices) {
                        System.out.println("Please enter choice" + Integer.toString(choiceCounter + 1) + ": ");
                        String choice = inputDriver.readStringInput(1);
                        choice = choice.replace("\n", "");
                        choices.add(choice);
                        choiceCounter++;
                    }
                    // create the type of question
                    question = new MultipleChoice(questionBase, choices, numResponses);
                    break;
                case 2:
                    System.out.println("Enter question" + Integer.toString(counter) + " prompt: ");
                    qPrompt = inputDriver.readStringInput(1);
                    qPrompt = qPrompt.replace("\n", "");
                    questionBase = new Question(qPrompt, counter);
                    ArrayList<String> choicesTF = new ArrayList<String>();
                    // True / False will always have these options
                    choicesTF.add("True");
                    choicesTF.add("False");
                    question = new TrueFalse(questionBase, choicesTF);
                    break;
                case 3:
                    System.out.println("Enter question" + Integer.toString(counter) + " prompt: ");
                    qPrompt = inputDriver.readStringInput(1);
                    qPrompt = qPrompt.replace("\n", "");
                    questionBase = new Question(qPrompt, counter);
                    // used to increment letters
                    int letters = 'a';
                    ArrayList<String> partList = new ArrayList<>();
                    System.out.println("Enter number of parts (1 if 1 part question): ");
                    int numParts = inputDriver.getIntegerInput(5);
                    // account for multiple parts if necessary;
                    if (numParts > 1)
                    {
                        for(int x = 0; x < numParts; x++)
                        {
                            System.out.print("Enter part " + (char) (letters + x) + ": ");
                            String part = inputDriver.readStringInput(1);
                            part = part.replace("\n", "");
                            partList.add(part);
                        }
                    }
                    question = new Essay(questionBase, partList);
                    break;
                case 4:
                    System.out.println("Enter question" + Integer.toString(counter) + " prompt: ");
                    qPrompt = inputDriver.readStringInput(1);
                    qPrompt = qPrompt.replace("\n", "");
                    questionBase = new Question(qPrompt, counter);
                    // used to increment letters
                    int letter = 'a';
                    ArrayList<String> shortPartList = new ArrayList<>();
                    System.out.println("Enter number of parts (1 if 1 part question): ");
                    int numPart = inputDriver.getIntegerInput(5);
                    // account for multiple parts if necessary
                    if (numPart > 1)
                    {
                        for(int x = 0; x < numPart; x++)
                        {
                            System.out.print("Enter part " + (char) (letter + x) + ": ");
                            String part = inputDriver.readStringInput(1);
                            part = part.replace("\n", "");
                            shortPartList.add(part);
                        }
                    }
                    // create a limit for the short answer questions
                    System.out.print("Enter the max lines for a response: ");
                    int maxLines = inputDriver.getIntegerInput(250);
                    question = new ShortAnswer(questionBase, maxLines, shortPartList);
                    break;
                case 5:
                    System.out.println("Enter question" + Integer.toString(counter) + " prompt: ");
                    qPrompt = inputDriver.readStringInput(1);
                    qPrompt = qPrompt.replace("\n", "");
                    questionBase = new Question(qPrompt, counter);
                    System.out.println("How many options in each set? ");
                    int numOptions = inputDriver.getIntegerInput(10);
                    int optionCounter = 1;
                    // leftSet and rightSet will have the same amount of options, user will enter leftSet options, then rightSet options
                    while(optionCounter < numOptions + 1) {
                        System.out.println("Please enter left set choice" + Integer.toString(optionCounter) + ": ");
                        String choice = inputDriver.readStringInput(1);
                        choice = choice.replace("\n", "");
                        leftSet.add(choice);
                        optionCounter++;
                    }
                    optionCounter = 1;
                    while(optionCounter < numOptions + 1) {
                        System.out.println("Please enter right set choice" + Integer.toString(optionCounter) + ": ");
                        String choice = inputDriver.readStringInput(1);
                        choice = choice.replace("\n", "");
                        rightSet.add(choice);
                        optionCounter++;
                    }
                    question = new Matching(questionBase, leftSet, rightSet);
                    break;
                case 6:
                    System.out.println("Enter question" + Integer.toString(counter) + " prompt: ");
                    qPrompt = inputDriver.readStringInput(1);
                    qPrompt = qPrompt.replace("\n", "");
                    questionBase = new Question(qPrompt, counter);
                    question = new ValidDate(questionBase);
                    break;
                case 7:
                    return null;
            }
            // add newly created question to question list to be used to create the survey
            questions.add(question);
            counter++;

        }
        // once all questions are added, create the survey and return it
        Survey s = new Survey(questions, surveyName);
        return s;
    }

    // displays the loaded survey
    public static void displaySurvey(Survey loadedSurvey)
    {
        if(loadedSurvey == null)
        {
            System.out.println("You must load a survey to display it");
        }
        // uses display from Survey class
        else {
            loadedSurvey.display();
        }
    }

    // allows user to edit, add, or remove question
    public static void editSurvey(Survey loadedSurvey)
    {
        if(loadedSurvey == null)
        {
            System.out.println("You must load a survey to edit it");
        }
        else {
            loadedSurvey.display();
            ArrayList<String> editMenu = new ArrayList<>();
            editMenu.add("Edit an existing question");
            editMenu.add("Add a new question");
            editMenu.add("Remove an existing question");
            outputMenuDriver.displayNumberedOptions(editMenu);
            System.out.print("Select an option: ");
            int menuChoice = inputDriver.getIntegerInput(editMenu.size());
            switch(menuChoice)
            {
                case 1:
                    System.out.print("Select a question to edit: ");
                    int qNum = inputDriver.getIntegerInput(loadedSurvey.questions.size());
                    loadedSurvey.editSurvey(qNum);
                    break;
                case 2:
                    // utilizes similar logic to createSurvey() to add a question
                    Question questionBase = null;
                    Question question = null;
                    ArrayList<Question> questions = new ArrayList<Question>();
                    ArrayList<String> choices = new ArrayList<String>();
                    ArrayList<String> leftSet = new ArrayList<String>();
                    ArrayList<String> rightSet = new ArrayList<String>();
                    ArrayList<String> addMenu = new ArrayList<>();
                    addMenu.add("Add a Multiple Choice question");
                    addMenu.add("Add a True / False question");
                    addMenu.add("Add a Essay question");
                    addMenu.add("Add a Short Answer question");
                    addMenu.add("Add a Matching question");
                    addMenu.add("Add a ValidDate question");
                    addMenu.add("Return to previous menu");
                    System.out.println("Enter question prompt: ");
                    String qPrompt = inputDriver.readStringInput(1);
                    qPrompt = qPrompt.replace("\n", "");
                    questionBase = new Question(qPrompt, loadedSurvey.questions.size() + 1);
                    outputMenuDriver.displayNumberedOptions(addMenu);
                    System.out.print("Select your type: ");
                    int typeOfQ = inputDriver.getIntegerInput(addMenu.size());
                    switch (typeOfQ) {
                        case 1:
                            System.out.print("Enter the number of choices: ");
                            int numChoices = inputDriver.getIntegerInput(5);
                            System.out.print("Enter number of expected responses: ");
                            int numResponses = inputDriver.getIntegerInput(numChoices);
                            int choiceCounter = 0;
                            while (choiceCounter < numChoices) {
                                System.out.println("Please enter choice" + Integer.toString(choiceCounter + 1) + ": ");
                                String choice = inputDriver.readStringInput(1);
                                choices.add(choice);
                                choiceCounter++;
                            }
                            question = new MultipleChoice(questionBase, choices, numResponses);
                            loadedSurvey.questions.add(question);
                            break;
                        case 2:
                            choices.add("True");
                            choices.add("False");
                            question = new TrueFalse(questionBase, choices);
                            loadedSurvey.questions.add(question);
                            break;
                        case 3:
                            int letters = 'a';
                            ArrayList<String> partList = new ArrayList<>();
                            System.out.println("Enter number of parts (1 if 1 part question): ");
                            int numParts = inputDriver.getIntegerInput(5);
                            if (numParts > 0)
                            {
                                for(int x = 0; x < numParts; x++)
                                {
                                    System.out.print("Enter part " + (char) (letters + x) + ": ");
                                    String part = inputDriver.readStringInput(2);
                                    part = part.replace("\n", "");
                                    partList.add(part);
                                }
                            }
                            question = new Essay(questionBase, partList);
                            loadedSurvey.questions.add(question);
                            break;
                        case 4:
                            ArrayList<String> parts = new ArrayList<>();
                            System.out.print("Enter the max words for a response: ");
                            int maxWords = inputDriver.getIntegerInput(10);
                            question = new ShortAnswer(questionBase, maxWords, parts);
                            loadedSurvey.questions.add(question);
                            break;
                        case 5:
                            System.out.println("How many options in each set? ");
                            int numOptions = inputDriver.getIntegerInput(10);
                            int optionCounter = 1;
                            while (optionCounter < numOptions + 1) {
                                System.out.println("Please enter left set choice" + Integer.toString(optionCounter) + ": ");
                                String choice = inputDriver.readStringInput(1);
                                leftSet.add(choice);
                                optionCounter++;
                            }
                            optionCounter = 1;
                            while (optionCounter < numOptions + 1) {
                                System.out.println("Please enter right set choice" + Integer.toString(optionCounter) + ": ");
                                String choice = inputDriver.readStringInput(1);
                                rightSet.add(choice);
                                optionCounter++;
                            }
                            question = new Matching(questionBase, leftSet, rightSet);
                            loadedSurvey.questions.add(question);
                            break;
                        case 6:
                            question = new ValidDate(questionBase);
                            loadedSurvey.questions.add(question);
                            break;
                    }
                    break;
                case 3:
                    // removes question from the Survey's question list
                    System.out.print("Select a question to remove: ");
                    qNum = inputDriver.getIntegerInput(loadedSurvey.questions.size());
                    loadedSurvey.removeQuestion(qNum);
            }

            }
        }

    // Allows user to select the survey they want to load, then uses Survey's deserialize function to load it and returns it
    public static Survey loadSurvey() {
        ArrayList<String> loadableSurveys = dao.getAllFiles();

        if (loadableSurveys.size() == 0)
        {
            System.out.println("There are no loadable surveys");
            return null;
        }
        outputMenuDriver.displayNumberedOptions(loadableSurveys);
        System.out.print("Select a survey: ");
        int selection = inputDriver.getIntegerInput(loadableSurveys.size());

        Survey loaded = Survey.deserialize(loadableSurveys.get(selection - 1));

        return loaded;
    }

    // makes sure there is a survey loaded, then uses Survey's serialize function
    public static void saveSurvey(Survey loadedSurvey)
    {
        if(loadedSurvey == null)
        {
            System.out.println("You need to load a survey to save it");
        }
        else {
            Survey.serialize(loadedSurvey);
        }
    }

    // allows the user to take the currently loaded survey
    public static void takeSurvey(Survey loadedSurvey)
    {
        if(loadedSurvey == null)
        {
            System.out.println("Please load the survey you wish to take");
        }
        else {
            // Surveys with responses are saved in a separately file than the original survey, need an identifier
            System.out.print("Enter a name for your response survey: ");
            String resName = inputDriver.readStringInput(1);
            resName = resName.replace("\n", "");
            // create a duplicate of the survey that is being taken, add user responses to the questions
            Survey dup = new Survey(loadedSurvey.questions, resName);
            for (Question q : dup.questions) {
                q.displayQuestion();
                q.getUserResponse(q);
            }
            // save the response survey
            saveSurvey(dup);
        }
    }

}
