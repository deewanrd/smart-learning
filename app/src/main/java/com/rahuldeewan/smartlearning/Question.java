package com.rahuldeewan.smartlearning;

/**
 * Created by Rahul
 * on 05-11-2017.
 */

public class Question {
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String hint;
    private String solution;
    private String answer;

    public Question() {
    }

    public Question(String question, String optionA, String optionB, String optionC, String optionD, String hint, String solution, String answer) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.hint = hint;
        this.solution = solution;
        this.answer = answer;
    }

    String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}