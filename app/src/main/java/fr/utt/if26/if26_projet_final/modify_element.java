package fr.utt.if26.if26_projet_final;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Alexandre on 23/01/2018.
 */

public class modify_element extends AppCompatActivity{

    int position, identifiant;
    ArrayList<ToDo> todo=null;
    DroidDatabaseHelper db=null;
    Toolbar toolbar;

    EditText modify_name;
    EditText modify_detail;
    EditText modify_date;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_element);

        //On récupère le Toolbar et on le peuple
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setSubtitle("Modifier votre tâche !");

        Intent i = getIntent();
        identifiant = i.getExtras().getInt("identifiant");

        modify_name = findViewById(R.id.modify_name);
        modify_detail = findViewById(R.id.modify_detail);
        modify_date = findViewById(R.id.modify_date);

        DroidDatabaseHelper db = new DroidDatabaseHelper(this);

        if(db.isNotEmpty()) {
            //On récupère l'item sélectionné
            todo = db.getOneData(identifiant);

            //Date du jour
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();

            //On réaffecte les valeurs
            modify_name.setText(todo.get(0).getName(), TextView.BufferType.EDITABLE);
            modify_detail.setText(todo.get(0).getDetail(), TextView.BufferType.EDITABLE);
            modify_date.setText(dateFormat.format(date), TextView.BufferType.EDITABLE);

        }

    }

    //Méthode pour updater l'item sélectionné
    public void updateElement(View v) {
        db = new DroidDatabaseHelper(this);
        db.updateData(identifiant, modify_name.getText().toString(), modify_detail.getText().toString(), modify_date.getText().toString());
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }

    //Méthode pour supprimer l'item sélectionné
    public void deleteElement(View v) {
        db = new DroidDatabaseHelper(this);
        db.deleteData(identifiant);
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
