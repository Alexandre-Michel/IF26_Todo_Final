package fr.utt.if26.if26_projet_final;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
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

    int position, identifiant;
    ArrayList<ToDo> todo=null;
    DroidDatabaseHelper db=null;

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
        identifiant = i.getExtras().getInt("identifiant");
        System.out.println("Position modify+1 " + position);

        modify_name = (EditText)findViewById(R.id.modify_name);
        modify_detail = (EditText)findViewById(R.id.modify_detail);
        modify_date = (EditText)findViewById(R.id.modify_date);

        DroidDatabaseHelper db = new DroidDatabaseHelper(this);

        System.out.println("bool " + !db.isNotEmpty());
        if(db.isNotEmpty()) {
            todo = db.getOneData(identifiant);
            System.out.println("Nom modify " + todo.get(0).getName());

            //Date du jour
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();

            modify_name.setText(todo.get(0).getName(), TextView.BufferType.EDITABLE);
            modify_detail.setText(todo.get(0).getDetail(), TextView.BufferType.EDITABLE);
            modify_date.setText(dateFormat.format(date), TextView.BufferType.EDITABLE);

        }

    }

    public void updateElement(View v) {
        db = new DroidDatabaseHelper(this);
        //System.out.println("modifier name : "+ modify_name.getText());
        db.updateData(identifiant, modify_name.getText().toString(), modify_detail.getText().toString(), modify_date.getText().toString());
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }

    public void deleteElement(View v) {
        db = new DroidDatabaseHelper(this);
        db.deleteData(identifiant);
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }
}
