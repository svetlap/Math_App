package com.svetla.math_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Student_Award extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__award);
    }

    protected void onStart(){
        super.onStart();

        award = getIntent().getExtras().getFloat("RESULTS_VAL");

        if( award < 0.6) {
            ((ImageView)findViewById(R.id.imgResult)).setImageResource(R.drawable.owl_2);
        }
        else if (award >= 0.6 && award < 0.72) {
            ((ImageView)findViewById(R.id.imgResult)).setImageResource(R.drawable.owl_3);
        }
        else if (award >= 0.72 && award < 0.8) {
            ((ImageView)findViewById(R.id.imgResult)).setImageResource(R.drawable.owl_4);
        }
        else if (award >= 0.8 && award < 0.9) {
            ((ImageView)findViewById(R.id.imgResult)).setImageResource(R.drawable.owl_5);
        }
        else
            ((ImageView)findViewById(R.id.imgResult)).setImageResource(R.drawable.owl_6);
    }

    public void LoadChoiceOfClasses(View view)
    {
        Intent i = new Intent(this, Student_Dashboard.class);
        startActivity(i);
    }

    private float award = 0.0f;
}
