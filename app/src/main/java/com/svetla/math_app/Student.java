package com.svetla.math_app;

public class Student extends User {

    public static enum ClassGrades {
        a1, a2, b1, b2;
    }

    Student(String first_name, String middle_name, String last_name, ClassGrades grade, String username, String password) {
        this.role = Role.Student;
        this.setFirst_name(first_name);
        this.setMiddle_name(middle_name);
        this.setLast_name(last_name);
        this.setCurrentGrade(grade);
        this.setUsername(username);
        this.setPassword(password);
    }

    public ClassGrades getCurrentGrade() {
        return currentGrade;
    }

    public void setCurrentGrade(ClassGrades currentGrade) {
        this.currentGrade = currentGrade;
    }

    private ClassGrades currentGrade;
}
