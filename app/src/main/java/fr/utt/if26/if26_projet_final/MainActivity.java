package fr.utt.if26.if26_projet_final;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MyCustomAdapter myCustomAdapter=null;
    ListView listView=null;
    DroidDatabaseHelper db=null;
    ArrayList<ToDo> todo=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DroidDatabaseHelper(this);


        // Inserting Rows
        Log.d("Insert: ", "Inserting ..");
        db.addElement("Test","Blue","21/01/2018");
        db.addElement("Tes22t","Red","21/01/2018");

        Log.d("Reading: ", "Reading all shops..");
        todo=db.getData();

        myCustomAdapter= new MyCustomAdapter(this,R.layout.liste_detail,todo);

        listView = (ListView) findViewById(R.id.simpleListView);
        listView.setAdapter(myCustomAdapter);
    }

    public void addNewElement(View v) {
        Intent i = new Intent(this, add_element.class);
        startActivity(i);
    }
}
