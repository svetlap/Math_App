package com.svetla.math_app;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Student_SectionProblems extends AppCompatActivity {

    static Question.AnswerOptions answers1 = Question.AnswerOptions.B;
    static int sessionQuestionIndex = 0;

    static public Question []database = {
            new Question("How much is 2+2", Question.QuestionTopics.Addition, "3", "4", "5", "6", answers1),
            new Question("How much is 2+7", Question.QuestionTopics.Addition, "3", "9", "5", "6", answers1),
            new Question("How much is 10-2", Question.QuestionTopics.Subtraction, "3", "8", "5", "6", answers1),
            new Question("How much is 10+2", Question.QuestionTopics.Addition, "3", "12", "5", "6", answers1)
    };

    private void SendDataToDatabase() {
        // Do nothing for now.
    }

    public void LoadSectionTheory(View view)
    {
        Intent i = new Intent(this, Student_SectionTheory.class);
        i.putExtra("THEORY_SOURCE", sessionQuestions.get(sessionQuestionIndex).getTopic());
        startActivity(i);
    }

    private void DeactivateAllControls() {
        ((TextView)findViewById(R.id.txtQuestion)).setEnabled(false);
        ((CheckBox)findViewById(R.id.cbA)).setEnabled(false);
        ((CheckBox)findViewById(R.id.cbB)).setEnabled(false);
        ((CheckBox)findViewById(R.id.cbC)).setEnabled(false);
        ((CheckBox)findViewById(R.id.cbD)).setEnabled(false);
    }

    private void ActivateAllControls() {
        ((TextView)findViewById(R.id.txtQuestion)).setEnabled(true);
        ((CheckBox)findViewById(R.id.cbA)).setEnabled(true);
        ((CheckBox)findViewById(R.id.cbB)).setEnabled(true);
        ((CheckBox)findViewById(R.id.cbC)).setEnabled(true);
        ((CheckBox)findViewById(R.id.cbD)).setEnabled(true);
    }

    // Makes sure all boxes are cleared/unchecked.
    private void UncheckAllBoxes() {
        ((CheckBox)findViewById(R.id.cbA)).setChecked(false);
        ((CheckBox)findViewById(R.id.cbB)).setChecked(false);
        ((CheckBox)findViewById(R.id.cbC)).setChecked(false);
        ((CheckBox)findViewById(R.id.cbD)).setChecked(false);
    }

    private boolean GiveAnswer(Question.AnswerOptions givenAnswer) {
        Context context = getApplicationContext();
        boolean IsCurrentAnswerCorrect = false;
        Toast toast;

        sessionQuestions.get(sessionQuestionIndex).setGivenAnswer(givenAnswer);

        if(givenAnswer == sessionQuestions.get(sessionQuestionIndex).getCorrectAnswers()) {
            IsCurrentAnswerCorrect = true;
        }

        if(IsCurrentAnswerCorrect == true) {
            toast = Toast.makeText(context, Html.fromHtml("<font color='#00ff00' ><b>" + "Correct" + "</b></font>"),
                    Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            toast = Toast.makeText(context, Html.fromHtml("<font color='#ff0000' ><b>" + "Incorrect, check the theory?" + "</b></font>"),
                    Toast.LENGTH_LONG);
            toast.show();

            // Activate icon with theory.
            ((ImageView)findViewById(R.id.imgTheory)).setVisibility(View.VISIBLE);
        }

        return IsCurrentAnswerCorrect;
    }

    private void SetCurrentQuestion(int id) {
        // Set initial question
        if(sessionQuestions.isEmpty())
        {
            question.setText("No questions set for this topic");
        }
        else
        {
            if(id >= 0) {
                answer_A.setChecked(false);
                answer_B.setChecked(false);
                answer_C.setChecked(false);
                answer_D.setChecked(false);

                question.setText(sessionQuestions.get(id).getText());
                answer_A.setText(sessionQuestions.get(id).getAnswer_A());
                answer_B.setText(sessionQuestions.get(id).getAnswer_B());
                answer_C.setText(sessionQuestions.get(id).getAnswer_C());
                answer_D.setText(sessionQuestions.get(id).getAnswer_D());

                sessionQuestionIndex = id;
                Question.AnswerOptions tempAnswer = sessionQuestions.get(id).getGivenAnswer();

                if(tempAnswer != Question.AnswerOptions.Undefined) {
                    DeactivateAllControls();

                    if(tempAnswer == Question.AnswerOptions.A) {
                        answer_A.setChecked(true);
                    }
                    else if (tempAnswer == Question.AnswerOptions.B) {
                        answer_B.setChecked(true);
                    }
                    else if (tempAnswer == Question.AnswerOptions.C) {
                        answer_C.setChecked(true);
                    }
                    else if (tempAnswer == Question.AnswerOptions.D) {
                        answer_D.setChecked(true);
                    }
                }
                else {
                    ActivateAllControls();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__section_problems);
    }

    protected void onStart() {
        super.onStart();
        ConfigureQuestionnaire(database);
    }

    public void ContinueToResults(View view) {
        Intent i = new Intent(this, Student_Award.class);
        i.putExtra("RESULTS_VAL", (float)((float)sessionResult/(float)sessionQuestions.size()));
        startActivity(i);
    }

    public void CheckAnswer(View view) {
        int invokerId = view.getId();

        switch (invokerId) {
            case R.id.cbA:
                if (GiveAnswer(Question.AnswerOptions.A)) {
                    sessionResult++;
                }

                SendDataToDatabase();
                break;
            case R.id.cbB:
                if (GiveAnswer(Question.AnswerOptions.B)) {
                    sessionResult++;
                }

                SendDataToDatabase();
                break;
            case R.id.cbC:
                if (GiveAnswer(Question.AnswerOptions.C)) {
                    sessionResult++;
                }

                SendDataToDatabase();
                break;
            case R.id.cbD:
                if (GiveAnswer(Question.AnswerOptions.D)) {
                    sessionResult++;
                }

                SendDataToDatabase();
                break;
            default://Do nothing for now.
        }

        DeactivateAllControls();
    }

    private void ConfigureQuestionnaire(Question[] db){
        seekBar = (SeekBar)findViewById(R.id.seekBarRemaining);
        question = (TextView)findViewById(R.id.txtQuestion);
        answer_A = (CheckBox) findViewById(R.id.cbA);
        answer_B = (CheckBox) findViewById(R.id.cbB);
        answer_C = (CheckBox) findViewById(R.id.cbC);
        answer_D = (CheckBox) findViewById(R.id.cbD);
        ready = (Button) findViewById(R.id.btnReady);

        ready.setVisibility(View.INVISIBLE);

        // Handle if new game is started in the same session;
        if(Student_Dashboard.isNewGame == true) {
            ActivateAllControls();

            sessionQuestionIndex = 0;
            sessionResult = 0;

            sessionQuestions.removeAll((sessionQuestions));
            UncheckAllBoxes();

            // Enable the possibility for multiple re-navigations,
            // unless coming from Dashboard.
            Student_Dashboard.isNewGame = false;
        }

        // Reloading of the page in the current session will not re-load the questions.
        if(sessionQuestions.isEmpty()) {
            for(Question it : db){
                if(Student_Objectives.sessionTopic == it.getTopic()) {
                    // If answer was stored from previous iteration
                    // of the same session set it to Undefined.
                    it.setGivenAnswer(Question.AnswerOptions.Undefined);

                    // Add to the session questions.
                    sessionQuestions.add(it);
                }
            }

            // Simulate question randomization. We do +1, so if random returns 0
            // we will have at least one rotation.
            Collections.rotate(sessionQuestions, (new Random()).nextInt(sessionQuestions.size()) + 1);
        }
        else { // Keep questions as-is.
        }

        // Initialize seek bar.
        seekBar.setMax(sessionQuestions.size() - 1); // The seek bar adds one more discrete as a start point
        seekBar.setProgress(0);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(seekBar.getProgress() == seekBar.getMax()) {
                    ready.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
               // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SetCurrentQuestion(progress);

                if(progress == seekBar.getMax()) {
                    ready.setVisibility(View.VISIBLE);
                    ((ImageView)findViewById(R.id.imgTheory)).setVisibility(View.INVISIBLE);
                }
            }
        });

        SetCurrentQuestion(0);
    }

    private short sessionResult = 0;
    private SeekBar seekBar;
    private TextView question;
    private CheckBox answer_A;
    private CheckBox answer_B;
    private CheckBox answer_C;
    private CheckBox answer_D;
    private Button   ready;
    private ArrayList<Question> sessionQuestions = new ArrayList<Question>();
}
