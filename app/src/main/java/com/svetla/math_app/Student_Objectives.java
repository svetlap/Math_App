package com.svetla.math_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Student_Objectives extends AppCompatActivity {

    public static Question.QuestionTopics sessionTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__objectives);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(MainActivity.IsSecondGrade == false) {
            ((Button)this.findViewById(R.id.btnMult)).setVisibility(View.GONE);
            ((Button)this.findViewById(R.id.btnDivision)).setVisibility(View.GONE);
        }
    }

    public void LoadSpecialSection(View view)
    {
        Intent topic_intent;
        int invokerId = view.getId();

        switch(invokerId) {
            case R.id.btnAdd: sessionTopic = Question.QuestionTopics.Addition;
                topic_intent = new Intent(this, Student_Section.class);
                topic_intent.putExtra("THEORY_SOURCE", 0);
                break;
            case R.id.btnSubtr: sessionTopic = Question.QuestionTopics.Subtraction;
                topic_intent = new Intent(this, Student_Section.class);
                topic_intent.putExtra("THEORY_SOURCE", 1);
                break;
            case R.id.btnMult: sessionTopic = Question.QuestionTopics.Multiplication;
                topic_intent = new Intent(this, Student_Section.class);
                topic_intent.putExtra("THEORY_SOURCE", 2);
                break;
            case R.id.btnDivision: sessionTopic = Question.QuestionTopics.Division;
                topic_intent = new Intent(this, Student_Section.class);
                topic_intent.putExtra("THEORY_SOURCE", 3);
                break;
            case R.id.btnPuzzles:
                topic_intent = new Intent(this, Puzzles_Activity.class);
                break;
            default:
                topic_intent = new Intent(this, this.getClass());
                //Do nothing for now.
        }

        startActivity(topic_intent);
    }
}
