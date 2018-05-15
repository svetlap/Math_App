package com.svetla.math_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class Question_Acvitity extends AppCompatActivity {

    public void AddQuestion(View view) {
        Intent question_intent = new Intent(this, Teacher_ProblemView.class);
        question_intent.putExtra("IS_ADD", true);
        startActivity(question_intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question__acvitity);
    }

    protected void onStart( ) {
        super.onStart();

        questionList = (ListView)findViewById(R.id.lvQuestions);
        LoadQuestionsFromDatabase();

        final Intent problem_intent = new Intent(this, Teacher_ProblemView.class);

        // Set an item click listener for ListView
        questionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                problem_intent.putExtra("ID", position);
                problem_intent.putExtra("IS_ADD", false);
                startActivity(problem_intent);
            }
        });
    }

    private void LoadQuestionsFromDatabase() {
        ArrayList<String> test = new ArrayList<String>();

        for(Question q : Student_SectionProblems.database) {
            test.add(q.getText());
        }

        // Create an ArrayAdapter from List
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, test);

        // DataBind ListView with items from ArrayAdapter
        questionList.setAdapter(arrayAdapter);
    }

    private ListView questionList;
}
