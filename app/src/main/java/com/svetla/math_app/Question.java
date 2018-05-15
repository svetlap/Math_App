package com.svetla.math_app;

public class Question {

    public enum AnswerOptions {
        A, B, C, D, Undefined
    }

    public enum QuestionTopics {
        Addition, Subtraction, Multiplication, Division, Undefined
    }

    public Question(String text, QuestionTopics topic,
                    String answer_A, String answer_B, String answer_C, String answer_D,
                    AnswerOptions correctAnswer) {
        this.text = text;
        this.topic = topic;
        this.answer_A = answer_A;
        this.answer_B = answer_B;
        this.answer_C = answer_C;
        this.answer_D = answer_D;
        this.correctAnswer = correctAnswer;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnswer_A() {
        return answer_A;
    }

    public void setAnswer_A(String answer_A) {
        this.answer_A = answer_A;
    }

    public String getAnswer_B() {
        return answer_B;
    }

    public void setAnswer_B(String answer_B) {
        this.answer_B = answer_B;
    }

    public String getAnswer_C() {
        return answer_C;
    }

    public void setAnswer_C(String answer_C) {
        this.answer_C = answer_C;
    }

    public String getAnswer_D() {
        return answer_D;
    }

    public void setAnswer_D(String answer_D) {
        this.answer_D = answer_D;
    }

    private String text;

    public QuestionTopics getTopic() {
        return topic;
    }

    public void setTopic(QuestionTopics topic) {
        this.topic = topic;
    }

    public AnswerOptions getCorrectAnswers() {
        return correctAnswer;
    }

    public void setCorrectAnswers(AnswerOptions correctAnswers) {
        this.correctAnswer = correctAnswers;
    }

    public AnswerOptions getGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(AnswerOptions givenAnswer) {
        this.givenAnswer = givenAnswer;
    }

    static public char TransformAnswerToChar(AnswerOptions ans) {
        if (ans == AnswerOptions.A) return 'a';
        else if (ans == AnswerOptions.B) return 'b';
        else if (ans == AnswerOptions.C) return 'c';
        else if (ans == AnswerOptions.D) return 'd';
        else return ' ';
    }

    static public AnswerOptions TransformAnswerToOption(char ans) {
        AnswerOptions resAnswer = AnswerOptions.Undefined;
        switch(ans){
            case 'a': resAnswer = AnswerOptions.A;
            break;
            case 'b': resAnswer = AnswerOptions.B;
            break;
            case 'c': resAnswer = AnswerOptions.C;
            break;
            case 'd': resAnswer = AnswerOptions.D;
            default:
                // Do nothing.
        }

        return resAnswer;
    }

    private QuestionTopics topic;
    private String answer_A;
    private String answer_B;
    private String answer_C;
    private String answer_D;

    private AnswerOptions correctAnswer;
    private AnswerOptions givenAnswer = AnswerOptions.Undefined;
}
