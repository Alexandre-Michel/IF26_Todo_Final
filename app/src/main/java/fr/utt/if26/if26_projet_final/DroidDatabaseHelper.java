package fr.utt.if26.if26_projet_final;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Alexandre on 21/01/2018.
 */

public class DroidDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Todo.db";
    public static final String TABLE_NAME = "Events";
    public static final String COLUMN_NAME = "c_name";
    public static final String COLUMN_DETAIL = "c_detail";
    public static final String COLUMN_DATE = "c_date";

    public DroidDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table  " +           TABLE_NAME+
                "(_id integer primary key AUTOINCREMENT NOT NULL,"+ COLUMN_NAME +
                " Text,"+ COLUMN_DETAIL +
                " Text,"+ COLUMN_DATE +
                " Text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_NAME);
        onCreate(db);
    }

    //Ajout d'une tâche en BDD
    public boolean addElement(String name, String detail, String date) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_DETAIL, detail);
        contentValues.put(COLUMN_DATE, date);

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    //Récupération de toutes les tâches
    public ArrayList<ToDo> getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ToDo> todo= new ArrayList<ToDo>();
        Cursor result = db.rawQuery("SELECT * from "+TABLE_NAME + " ORDER BY " + COLUMN_DATE, null);
        while(result.moveToNext()){
            todo.add( new ToDo(result.getInt(result.getColumnIndex("_id")), result.getString(result.getColumnIndex(COLUMN_NAME)), result.getString(result.getColumnIndex(COLUMN_DETAIL)),result.getString(result.getColumnIndex(COLUMN_DATE))));
        }
        return todo;
    }

    //Récupération d'une tâche selon son id
    public ArrayList<ToDo> getOneData(int identifiant) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ToDo> todo= new ArrayList<ToDo>();
        Cursor result = db.rawQuery("SELECT * from "+TABLE_NAME + " WHERE _id = " + identifiant, null);
        while(result.moveToNext()){
            todo.add( new ToDo(result.getInt(result.getColumnIndex("_id")),result.getString(result.getColumnIndex(COLUMN_NAME)), result.getString(result.getColumnIndex(COLUMN_DETAIL)),result.getString(result.getColumnIndex(COLUMN_DATE))));
        }
        return todo;
    }

    //Mise à jour d'une tâche selon son id
    public boolean updateData(int id, String name, String detail, String date) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_DETAIL, detail);
        contentValues.put(COLUMN_DATE, date);

        db.update(TABLE_NAME, contentValues, "_id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    //Suppression d'une tâche selon son id
    public Integer deleteData(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                "_id = ? ",
                new String[]{Integer.toString(id)});
    }

    //Vider la BDD
    public void clearDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        String clearDBQuery = "DELETE FROM "+ TABLE_NAME;
        db.execSQL(clearDBQuery);
    }

    //Vérifie si la table est vide
    public boolean isNotEmpty() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        Boolean rowExists;

        rowExists = mCursor.moveToFirst();
        return rowExists;
    }


}
