package com.svetla.math_app;

import java.util.ArrayList;

public class Teacher extends User {
    Teacher(String first_name, String middle_name, String last_name, String username, String password) {
        this.role = Role.Teacher;
        this.setFirst_name(first_name);
        this.setMiddle_name(middle_name);
        this.setLast_name(last_name);
        this.setUsername(username);
        this.setPassword(password);
    }

    public void ViewResultsByStudent(Student st) {

    }

    public void ViewQuestionResults(Question q) {

    }

    public void ViewAllResults() {

    }

    public void AddQuestion(Question q) {

    }

    public void EditQuestion(Question q) {

    }

    public void DeleteQuestion(Question q) {

    }

    public ArrayList<Student> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Student> children) {
        this.children = children;
    }

    public void ViewChildResults(Student child) {

    }

    private ArrayList<Student> children = new ArrayList<Student>();
}
