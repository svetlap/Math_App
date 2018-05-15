package com.svetla.math_app;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class Results_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_);

        quizes.add(new Quiz(0, 'c', 0));
        quizes.add(new Quiz(1, 'b', 0));
        quizes.add(new Quiz(2, 'b', 0));
    }

    protected void onStart( ) {
        super.onStart();

        childrenList = (ListView)findViewById(R.id.lvAnsweredQuestions);
        sessionID = getIntent().getExtras().getInt("STUDENT_ID", 0);

        if(MainActivity.sessionRole == User.Role.Teacher) {
            ((Button)findViewById(R.id.btnGraphicalResults)).setVisibility(View.VISIBLE);
        }

        LoadQuestionsFromDatabase();
    }

    private void LoadQuestionsFromDatabase() {
        // Artificially created
        ArrayList<String> test = new ArrayList<String>();

        for(Quiz q : quizes) {
            test.add(Student_SectionProblems.database[q.question_id].getText());
        }

        // Create an ArrayAdapter from List
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, test) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // Get the current item from ListView
                View view = super.getView(position, convertView, parent);
                if (Student_SectionProblems.database[quizes.get(position).question_id].getCorrectAnswers() ==
                        Question.TransformAnswerToOption(quizes.get(position).answer)) {
                    // Set a background color for ListView regular row/item
                    view.setBackgroundColor(Color.parseColor("#D2FFFC"));
                } else {
                    // Set the background color for alternate row/item
                    view.setBackgroundColor(Color.parseColor("#FFF0F0"));
                }
                return view;
            }
        };

        // DataBind ListView with items from ArrayAdapter
        childrenList.setAdapter(arrayAdapter);
    }

    public void LoadQuestions(View view) {
        Intent i = new Intent(this, Question_Acvitity.class);
        startActivity(i);
    }

    public void GraphicalResults(View view) {
        findViewById(R.id.lvAnsweredQuestions).setVisibility(View.INVISIBLE);
        findViewById(R.id.wvGraphics).setVisibility(View.VISIBLE);

        WebView wb = (WebView)findViewById(R.id.wvGraphics);
        WebSettings webSettings=wb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wb.loadUrl("https://www.mathapp.bg");
    }

    private int sessionID;
    private ListView childrenList;

    private class Quiz {
        protected int question_id;
        protected char answer;
        protected int student_id;

        protected Quiz(int question_id, char answer, int student_id) {
            this.question_id = question_id;
            this.answer = answer;
            this.student_id = student_id;
        }
    }

    private ArrayList<Quiz> quizes = new ArrayList<Quiz>();
}
