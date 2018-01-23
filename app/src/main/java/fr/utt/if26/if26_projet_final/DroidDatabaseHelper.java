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
    public static final String COLUMN_ID = "id";
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

    public ArrayList<ToDo> getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ToDo> todo= new ArrayList<ToDo>();
        Cursor result = db.rawQuery("SELECT * from "+TABLE_NAME , null);
        while(result.moveToNext()){
            todo.add( new ToDo(result.getInt(result.getColumnIndex("_id")), result.getString(result.getColumnIndex(COLUMN_NAME)), result.getString(result.getColumnIndex(COLUMN_DETAIL)),result.getString(result.getColumnIndex(COLUMN_DATE))));
        }
        return todo;
    }

    public ArrayList<ToDo> getOneData(int position) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ToDo> todo= new ArrayList<ToDo>();
        Cursor result = db.rawQuery("SELECT * from "+TABLE_NAME + " WHERE _id = " + position, null);
        while(result.moveToNext()){
            todo.add( new ToDo(result.getInt(result.getColumnIndex("_id")),result.getString(result.getColumnIndex(COLUMN_NAME)), result.getString(result.getColumnIndex(COLUMN_DETAIL)),result.getString(result.getColumnIndex(COLUMN_DATE))));
        }
        return todo;
    }

    /*public boolean updatePlates(int id, int number, int color, int place) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("c_number", number);
        contentValues.put("c_Color", color);
        contentValues.put("c_Place", place);

        db.update(TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deletePlates(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                "id = ? ",
                new String[]{Integer.toString(id)});
    }*/

    public void clearDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        String clearDBQuery = "DELETE FROM "+ TABLE_NAME;
        db.execSQL(clearDBQuery);
    }

    public boolean isNotEmpty() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        Boolean rowExists;

        if (mCursor.moveToFirst())
        {
            // DO SOMETHING WITH CURSOR
            rowExists = true;

        } else
        {
            // I AM EMPTY
            rowExists = false;
        }
        return rowExists;
    }


}
