package fr.utt.if26.if26_projet_final;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Alexandre on 23/01/2018.
 */

public class modify_element extends Activity {

    int position;
    ArrayList<ToDo> todo=null;

    EditText modify_name;
    EditText modify_detail;
    EditText modify_date;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_element);

        // Retrieve data from MainActivity on listview item click
        Intent i = getIntent();
        // Get the listview item click position
        position = i.getExtras().getInt("position");
        System.out.println("Position modify " + position);

        modify_name = (EditText)findViewById(R.id.modify_name);
        modify_detail = (EditText)findViewById(R.id.modify_detail);
        modify_date = (EditText)findViewById(R.id.modify_date);

        DroidDatabaseHelper db = new DroidDatabaseHelper(this);

        System.out.println("bool " + !db.isNotEmpty());
        if(db.isNotEmpty()) {
            todo = db.getOneData(position);
            System.out.println("Nom modify " + todo.get(0).getName());

            //Date du jour
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();

            modify_name.setText(todo.get(0).getName(), TextView.BufferType.EDITABLE);
            modify_detail.setText(todo.get(0).getDetail(), TextView.BufferType.EDITABLE);
            modify_date.setText(dateFormat.format(date), TextView.BufferType.EDITABLE);

        }

    }
}
