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

public class Parent_Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent__dashboard);
    }

    protected void onStart( ) {
        super.onStart();

        childrenList = (ListView)findViewById(R.id.lvChildren);
        sessionID = getIntent().getExtras().getInt("PARENT_ID", 0);

        LoadChildrenFromDatabase();

        final Intent st_intent = new Intent(this, Results_Activity.class);

        // Set an item click listener for ListView
        childrenList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                String selectedItem = (String) parent.getItemAtPosition(position);
                st_intent.putExtra("STUDENT_ID", position);
                startActivity(st_intent);
            }
        });
    }

    private void LoadChildrenFromDatabase() {
        // Artificially created
        MainActivity.tempParents[sessionID].setChildren(new ArrayList<Student>(Arrays.asList(MainActivity.tempStudents)));

        ArrayList<Student> children = MainActivity.tempParents[sessionID].getChildren();

        ArrayList<String> test = new ArrayList<String>();

        for(Student child : children) {
            test.add(child.getFirst_name() + " " + child.getLast_name());
        }

        // Create an ArrayAdapter from List
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, test);

        // DataBind ListView with items from ArrayAdapter
        childrenList.setAdapter(arrayAdapter);
    }

    private int sessionID;
    private ListView childrenList;
}
