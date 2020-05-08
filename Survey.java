import input.InputReader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Survey implements Serializable{

    public Scanner reader;
    public ArrayList<Question> questions;
    public String surveyName;
    private static final long serialVersionUID = 1L;
    private static final input.InputDriver inputDriver = new InputReader();
    // create a dao for serialization
    private static final SerializableDAO dao = new SerializableDAO();

    // Survey is instantiated with a name and list of Question objects
    public Survey(ArrayList<Question> listOfQuestions, String name)
    {
        this.questions = listOfQuestions;
        this.surveyName = name;
    }

    // displays each question and its response if the survey loaded has been taken
    public void display()
    {
        for (Question x: this.questions)
        {
            x.displayQuestion();
            if(x.response.size() > 0) {
                x.displayResponse();
            }
        }
    }

    // adds a question to the Survey
    public void addQuestion(Question q)
    {
        this.questions.add(q);
    }

    // gets a question using its number, uses Question's edit function
    public void editSurvey(int qNumber)
    {
        for (Question x: questions)
        {
            if(x.questionNumber == qNumber)
            {
                x.editQuestion();
            }
        }
    }

    // removes a question from the survey
    public void removeQuestion(int qNumber)
    {
        this.questions.remove(qNumber - 1);
    }

    // change the name of the survey if needed
    public void changeSurveyName()
    {
        System.out.print("Enter new name: ");
        String name = inputDriver.readStringInput(1);
        name = name.replace("\n", "");
        this.surveyName = name;
    }

    // utilizes the dao object to save the survey it is passed
    public static void serialize(Survey s)
    {
        Survey duplicate = s;
        dao.saveFile(duplicate, s.surveyName);
        s.display();
        System.out.println("Saved");
    }

    // utilizes the dao object to load the survey from the path it is passed, returns the loaded Survey
    public static Survey deserialize(String path)
    {
        Survey loaded = dao.loadFile(path);
        return loaded;
    }
}
