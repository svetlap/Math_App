package com.svetla.math_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Student_Section extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__specific);
    }

    @Override
    protected void onStart() {
        super.onStart();
        sessionTopic = getIntent().getExtras().getInt("THEORY_SOURCE", 0);
    }

    public void LoadSectionTheory(View view)
    {
        Intent i = new Intent(this, Student_SectionTheory.class);
        i.putExtra("THEORY_SOURCE", sessionTopic);
        startActivity(i);
    }

    public void LoadSectionProblems(View view)
    {
        Intent i = new Intent(this, Student_SectionProblems.class);
        startActivity(i);
    }

    public void LoadInterestingStuff(View view)
    {
        Intent i = new Intent(this, Student_InterestingStuff.class);
        startActivity(i);
    }

    private int sessionTopic = -1;
}
