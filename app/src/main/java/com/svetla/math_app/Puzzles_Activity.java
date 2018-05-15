package com.svetla.math_app;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class Puzzles_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzles_);
    }

    protected void onStart() {
        super.onStart();

        final Random rnd = new Random();
        final String str = "puzzle_" + (rnd.nextInt(5) + 1);
        ((ImageView)findViewById(R.id.imgPuzzle)).setImageDrawable(getResources().getDrawable(getResourceID(str,
                "drawable", getApplicationContext()), null));
    }

    protected final static int getResourceID (final String resName, final String resType, final Context ctx)
    {
        final int ResourceID = ctx.getResources().getIdentifier(resName, resType, ctx.getPackageName());
        if (ResourceID == 0)
        {
            throw new IllegalArgumentException
            (
                    "No resource string found with name " + resName
            );
        }
        else
        {
            return ResourceID;
        }
    }
}
