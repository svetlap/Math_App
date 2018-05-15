package com.svetla.math_app;

import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static boolean IsSecondGrade = false;

    public static Teacher []tempTeachers = {
            new Teacher("Ivan", "Dimitrov", "Ivanov", "ivan", "pass")
    };

    public static Student []tempStudents = {
            new Student("Georgi", "Dimitrov", "Emilov", Student.ClassGrades.a1, "georgi", "pass"),
            new Student("Martin", "Ivaylov", "Nikolov", Student.ClassGrades.a2, "martin", "pass")
    };

    public static Parent []tempParents = {
            new Parent("Vladimir", "Ivanov", "Stoikov", "vlado", "pass"),
            new Parent("Iva", "Velikova", "Ivanova", "iva", "pass")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabEmail);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Email = new Intent(Intent.ACTION_SEND);
                Email.setType("text/email");
                Email.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"svetlamariap@gmail.com"});  //developer 's email
                Email.putExtra(Intent.EXTRA_SUBJECT,
                        "Add your Subject"); // Email 's Subject
                Email.putExtra(Intent.EXTRA_TEXT, "Dear Svetla-Maria," + "");  //Email 's Greeting text
                startActivity(Intent.createChooser(Email, "Send Feedback:"));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void LoginClick(View view)
    {
        User usr = null;
        try {
            usr = RestoreUser(((EditText)findViewById(R.id.txtUsername)).getText().toString(), ((EditText)findViewById(R.id.txtPassword)).getText().toString());
            sessionRole = usr.role;
        }
        catch (Exception e)
        {

        }
        finally {
            if(usr != null) {
                switch (sessionRole) {
                    case Student:
                        Intent st_intent = new Intent(this, Student_Dashboard.class);
                        st_intent.putExtra("STUDENT_ID", usr.getUsername());
                        startActivity(st_intent);
                        break;

                    case Teacher:
                        Intent teacher_intent = new Intent(this, Teacher_Dashboard.class);
                        teacher_intent.putExtra("TEACHER_ID", usr.getUsername());
                        startActivity(teacher_intent);
                        break;

                    case Parent:
                        Intent parent_intent = new Intent(this, Parent_Dashboard.class);
                        parent_intent.putExtra("PARENT_ID", 0); // To be taken from DB
                        startActivity(parent_intent);
                        break;

                    default:
                        //! With correct database this should never happen.
                        Context context = getApplicationContext();
                        CharSequence text = "User of unknown type...";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                }
            }
            else {
                Context context = getApplicationContext();
                CharSequence text = "Invalid username or password...";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }

    private User RestoreUser(String username, String password) {
       /* DatabaseDAO test = new DatabaseDAO();

        try {
            test.readDataBase();
        }
        catch(Exception e)
        {

        }
        finally {*/
            for(Teacher t : tempTeachers){
                if(t.getUsername().equals(username) && t.getPassword().equals(password)){
                    return t;
                }
            }

            for(Student s : tempStudents){
                if(s.getUsername().equals(username) && s.getPassword().equals(password)){
                    return s;
                }
            }

            for(Parent p : tempParents){
                if(p.getUsername().equals(username) && p.getPassword().equals(password)){
                    return p;
                }
            }

            return null;
    }

    public static User.Role sessionRole = User.Role.Unknown;
}
