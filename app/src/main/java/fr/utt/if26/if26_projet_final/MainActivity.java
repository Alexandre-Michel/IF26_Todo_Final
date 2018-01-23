package fr.utt.if26.if26_projet_final;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import android.widget.AdapterView.OnItemClickListener;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyCustomAdapter myCustomAdapter=null;
    ListView listView;
    DroidDatabaseHelper db=null;
    ArrayList<ToDo> todo=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DroidDatabaseHelper(this);
        //db.clearDatabase();


        // Inserting Rows
        Log.d("Insert: ", "Inserting ..");
        //db.addElement("Test","Blue","21/01/2018");
        //db.addElement("Tes22t","Red","21/01/2018");

        Log.d("Reading: ", "Reading all shops..");
        todo=db.getData();

        myCustomAdapter= new MyCustomAdapter(this,R.layout.liste_detail,todo);

        listView = findViewById(R.id.simpleListView);
        listView.setAdapter(myCustomAdapter);
        //System.out.println("ALLLLLLE : " +db.getOneData(1).get(0).getName());

        /*listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(MainActivity.this, modify_element.class);
                // Pass listview item click position
                i.putExtra("position", position);
                // Open SingleItemView.java Activity
                startActivity(i);
            }

        });*/

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                ToDo selectedItem = (ToDo)parent.getItemAtPosition(position);
                System.out.println("Name todo " + selectedItem.getId());
                System.out.println("ID position : " +listView.getItemIdAtPosition(position));
                Intent i = new Intent(MainActivity.this, modify_element.class);
                // Pass listview item click position
                i.putExtra("position", position+1);
                // Open SingleItemView.java Activity
                startActivity(i);
            }
        });
    }

    public void addNewElement(View v) {
        Intent i = new Intent(this, add_element.class);
        startActivity(i);
    }

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
