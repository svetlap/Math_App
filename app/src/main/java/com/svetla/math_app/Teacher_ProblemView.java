package com.svetla.math_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class Teacher_ProblemView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__problem_view);
    }

    protected void onStart() {
        super.onStart();

        isAdding = getIntent().getExtras().getBoolean("IS_ADD", false);

        if(isAdding == true) {
            findViewById(R.id.txtEditA).setVisibility(View.VISIBLE);
            findViewById(R.id.txtEditB).setVisibility(View.VISIBLE);
            findViewById(R.id.txtEditC).setVisibility(View.VISIBLE);
            findViewById(R.id.txtEditD).setVisibility(View.VISIBLE);
            ((Button)findViewById(R.id.btnDelete)).setText("Cancel");
        }
        else {
            sessionQuestion = Student_SectionProblems.database[getIntent().getExtras().getInt("ID", 0)];
            ConfigureQuestion();
        }

    }

    public void SaveQuestion(View view) {
        sessionQuestion.setCorrectAnswers(sessionAnswer);
        sessionQuestion.setText(((TextView)findViewById(R.id.txtTeacherQuestion)).getText().toString());
        sessionQuestion.setAnswer_A(((TextView)findViewById(R.id.txtEditA)).getText().toString());
        sessionQuestion.setAnswer_B(((TextView)findViewById(R.id.txtEditB)).getText().toString());
        sessionQuestion.setAnswer_C(((TextView)findViewById(R.id.txtEditC)).getText().toString());
        sessionQuestion.setAnswer_D(((TextView)findViewById(R.id.txtEditD)).getText().toString());

        Intent question_intent = new Intent(this, Question_Acvitity.class);
        startActivity(question_intent);
    }

    public void EditQuestion(View view) {
        ((Button)findViewById(R.id.btnEdit)).setVisibility(View.VISIBLE);

        findViewById(R.id.txtEditA).setVisibility(View.VISIBLE);
        if(((CheckBox) findViewById(R.id.cbTeacherA)).getText().length() > 0) {
            ((TextView)findViewById(R.id.txtEditA)).setText(((CheckBox) findViewById(R.id.cbTeacherA)).getText());
        }

        findViewById(R.id.txtEditB).setVisibility(View.VISIBLE);
        if(((CheckBox) findViewById(R.id.cbTeacherB)).getText().length() > 0) {
            ((TextView) findViewById(R.id.txtEditB)).setText(((CheckBox) findViewById(R.id.cbTeacherB)).getText());
        }

        findViewById(R.id.txtEditC).setVisibility(View.VISIBLE);
        if(((CheckBox) findViewById(R.id.cbTeacherC)).getText().length() > 0) {
            ((TextView) findViewById(R.id.txtEditC)).setText(((CheckBox) findViewById(R.id.cbTeacherC)).getText());
        }

        findViewById(R.id.txtEditD).setVisibility(View.VISIBLE);
        if(((CheckBox) findViewById(R.id.cbTeacherD)).getText().length() > 0) {
            ((TextView) findViewById(R.id.txtEditD)).setText(((CheckBox) findViewById(R.id.cbTeacherD)).getText());
        }

        ((CheckBox) findViewById(R.id.cbTeacherA)).setText("");
        ((CheckBox) findViewById(R.id.cbTeacherB)).setText("");
        ((CheckBox) findViewById(R.id.cbTeacherC)).setText("");
        ((CheckBox) findViewById(R.id.cbTeacherD)).setText("");
    }

    public void DeleteQuestion(View view) {
        if(isAdding == false) {
            // Delete
        }

        Intent question_intent = new Intent(this, Question_Acvitity.class);
        startActivity(question_intent);
    }

    public void EditCheckA(View view) {
        sessionAnswer = Question.AnswerOptions.A;

        ((CheckBox) findViewById(R.id.cbTeacherB)).setChecked(false);
        ((CheckBox) findViewById(R.id.cbTeacherC)).setChecked(false);
        ((CheckBox) findViewById(R.id.cbTeacherD)).setChecked(false);
    }

    public void EditCheckB(View view) {
        sessionAnswer = Question.AnswerOptions.B;

        ((CheckBox) findViewById(R.id.cbTeacherA)).setChecked(false);
        ((CheckBox) findViewById(R.id.cbTeacherC)).setChecked(false);
        ((CheckBox) findViewById(R.id.cbTeacherD)).setChecked(false);
    }

    public void EditCheckC(View view) {
        sessionAnswer = Question.AnswerOptions.C;

        ((CheckBox) findViewById(R.id.cbTeacherA)).setChecked(false);
        ((CheckBox) findViewById(R.id.cbTeacherB)).setChecked(false);
        ((CheckBox) findViewById(R.id.cbTeacherD)).setChecked(false);
    }

    public void EditCheckD(View view) {
        sessionAnswer = Question.AnswerOptions.D;

        ((CheckBox) findViewById(R.id.cbTeacherA)).setChecked(false);
        ((CheckBox) findViewById(R.id.cbTeacherB)).setChecked(false);
        ((CheckBox) findViewById(R.id.cbTeacherC)).setChecked(false);
    }

    private void ConfigureQuestion(){
        ((TextView)findViewById(R.id.txtTeacherQuestion)).setText(sessionQuestion.getText());
        ((CheckBox) findViewById(R.id.cbTeacherA)).setText(sessionQuestion.getAnswer_A());
        ((CheckBox) findViewById(R.id.cbTeacherB)).setText(sessionQuestion.getAnswer_B());
        ((CheckBox) findViewById(R.id.cbTeacherC)).setText(sessionQuestion.getAnswer_C());
        ((CheckBox) findViewById(R.id.cbTeacherD)).setText(sessionQuestion.getAnswer_D());
    }

    private Question sessionQuestion;
    private Question.AnswerOptions sessionAnswer = Question.AnswerOptions.Undefined;
    private boolean isAdding = false;
}
