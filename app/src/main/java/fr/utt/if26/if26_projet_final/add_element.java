package fr.utt.if26.if26_projet_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class add_element extends AppCompatActivity {

    AutoCompleteTextView name;
    EditText date;
    EditText detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_element);

        name = findViewById(R.id.add_name);
        date = findViewById(R.id.add_date);
        detail = findViewById(R.id.add_detail);

        //Date du jour
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date_du_jour = new Date();

        date.setText(dateFormat.format(date_du_jour));

    }


    public void addElementToBD(View view) {
        DroidDatabaseHelper db = new DroidDatabaseHelper(this);
        String nom_ajout = name.getText().toString();
        String date_ajout = date.getText().toString();
        String detail_ajout = detail.getText().toString();
        db.addElement(nom_ajout, detail_ajout, date_ajout);
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
