# SE310 HW2B Survey Akash Patel

- Creating or taking a survey will automatically make it the loaded file
- takeSurvey will take the loaded survey, the survey the user wishes to take must be loaded before they take it
- takeSurvey will automatically save and serialize once all responses are entered
- Taking a survey that is already populated with responses will add the new responses onto the question's response list
    - To avoid this, could have surveys be serialized with ".survey" and responses with ".resp"
    - For now, files are serialized with ".ser"
- Several places that required user input, the input was being saved with "\n"
    - The program has a few string.replace("\n", "") lines
- For essay or short answer questions, the program will not move on until the max amount of lines is reached
    - If the response is less than the max lines, user will have to press enter until the lines are reached
    - For testing purposes, I set essay's to 3 lines, and on user input made short answer's 3 as well, these can be modified if needed
- Sample files are in the top level folder
- "sample.ser" is a sample survey and "sampleResponse.ser" is an example of a response to that survey
- "sampleMultiple.ser" shows questions that accept multiple answers, "responseMultiple.ser" is an example of taking that survey