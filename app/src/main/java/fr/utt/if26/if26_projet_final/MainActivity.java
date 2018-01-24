package fr.utt.if26.if26_projet_final;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyCustomAdapter myCustomAdapter=null;
    ListView listView;
    DroidDatabaseHelper db=null;
    ArrayList<ToDo> todo=null;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.simpleListView);

        //On récupère le Toolbar et on le peuple
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setSubtitle("Rassembler vos tâches !");

        //Appel de la DB
        db = new DroidDatabaseHelper(this);
        //db.clearDatabase();

        // Inserting Rows
        Log.d("Reading: ", "Reading all shops..");
        todo=db.getData();

        //Appel de l'adapter
        myCustomAdapter= new MyCustomAdapter(this,R.layout.liste_detail,todo);
        listView.setAdapter(myCustomAdapter);

        //Mise en place d'un listener sur chaque item de la liste
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                ToDo selectedItem = (ToDo)parent.getItemAtPosition(position);
                Intent i = new Intent(MainActivity.this, modify_element.class);
                // Pass listview item click id
                i.putExtra("identifiant", selectedItem.getId());
                // Open SingleItemView.java Activity
                startActivity(i);
            }
        });
    }

    //Bouton de lancement de création d'une nouvelle tâche
    public void addNewElement(View v) {
        Intent i = new Intent(this, add_element.class);
        startActivity(i);
    }

    //Swipe pour chaque Item - DEPRECATED
    /*SwipeMenuCreator creator = new SwipeMenuCreator() {

        @Override
        public void create(SwipeMenu menu) {
            // create "open" item
            SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
            // set item background
            openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
            // set item width
            openItem.setWidth(170);
            // set item title
            openItem.setTitle("Open");
            // set item title fontsize
            openItem.setTitleSize(18);
            // set item title font color
            openItem.setTitleColor(Color.WHITE);
            // add to menu
            menu.addMenuItem(openItem);

            // create "delete" item
            SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
            // set item background
            deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,0x3F, 0x25)));
            // set item width
            deleteItem.setWidth(170);
            // set a icon
            deleteItem.setIcon(R.drawable.ic_check);
            // add to menu
            menu.addMenuItem(deleteItem);
        }
    };*/

    //listView.setMenuCreator(creator);
    /*listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
            switch (index) {
                case 0:
                    // open
                    break;
                case 1:
                    // delete
                    break;
            }
            // false : close the menu; true : not close the menu
            return false;
        }
    });*/
}
