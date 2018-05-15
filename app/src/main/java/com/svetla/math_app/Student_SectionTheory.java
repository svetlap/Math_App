package com.svetla.math_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Student_SectionTheory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__section_theory);
    }

    protected void onStart() {
        super.onStart();

        sessionTopic = getIntent().getExtras().getInt("THEORY_SOURCE", 0);

        switch(sessionTopic) {
            case 0: ((TextView)findViewById(R.id.txtViewTopic)).setText("Addition");
                break;
            case 1: ((TextView)findViewById(R.id.txtViewTopic)).setText("Subtraction");
                break;
            case 2: ((TextView)findViewById(R.id.txtViewTopic)).setText("Multiplication");
                break;
            case 3: ((TextView)findViewById(R.id.txtViewTopic)).setText("Division");
                break;
            default: ((TextView)findViewById(R.id.txtViewTopic)).setText("Else");
        }
    }

    private int sessionTopic;
}
