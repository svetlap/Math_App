package com.svetla.math_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Student_InterestingStuff extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__interesting_stuff);

        btnAnswer = (Button)findViewById(R.id.btnCamera);
        imgAnswer = (ImageView)findViewById(R.id.imgAnswer);

        final Context activityContext = this.getApplicationContext();

        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btnAnswer.getText().equals("Send to teacher")) {
                    Intent i = new Intent(getApplicationContext(), Student_Objectives.class);

                    File outputFile = null;
                    try {
                        outputFile = File.createTempFile("screenshot", ".png", activityContext.getCacheDir());

                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);

                            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                            emailIntent.setType("image/*");
                            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"svetlamariap@gmail.com"});
                            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"New task");
                            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "From a good student");
                            emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(outputFile));
                            startActivity(Intent.createChooser(emailIntent, "Send mail..."));

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    startActivity(i);
                }
                else {
                    Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(i, 0);
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bitmap = (Bitmap)data.getExtras().get("data");

        if(bitmap != null) {
            imgAnswer.setImageBitmap(bitmap);
            btnAnswer.setText("Send to teacher");
        }
        else {//Do nothing for now.
        }
    }

    private ImageView imgAnswer;
    private Button btnAnswer;
    private Bitmap bitmap;
}
