package com.svetla.math_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Student_Dashboard extends AppCompatActivity {

    static public boolean isNewGame = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_of_classes);
    }
    public void LoadFirstGrade(View view)
    {
        MainActivity.IsSecondGrade = false;

        Intent i = new Intent(this, Student_Objectives.class);
        isNewGame = true;
        startActivity(i);
    }

    public void LoadSecondGrade(View view)
    {
        MainActivity.IsSecondGrade = true;

        Intent i = new Intent(this, Student_Objectives.class);
        isNewGame = true;
        startActivity(i);
    }
}
