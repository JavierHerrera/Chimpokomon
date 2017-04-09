package chimpokomon.com.example.pep.chimpokomon.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import chimpokomon.com.example.pep.chimpokomon.database.DBtest.Table_test;


public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ToDoApp.db";

    private static final String SQL_CREATE =
            "CREATE TABLE " + Table_test.TABLE_NAME + " (" +
                    Table_test._ID + " INTEGER PRIMARY KEY," +
                    Table_test.COLUMN_NAME_TITLE + " TEXT," +
                    Table_test.COLUMN_NAME_DATE + " DATETIME," +
                    Table_test.COLUMN_NAME_DONE + " BOOLEAN NOT NULL CHECK (" + Table_test.COLUMN_NAME_DONE + " IN (0,1))," +
                    Table_test.COLUMN_NAME_LATITUDE + " DOUBLE," +
                    Table_test.COLUMN_NAME_LONGITUDE + " DOUBLE," +
                    Table_test.COLUMN_NAME_STARRED + " BOOLEAN NOT NULL CHECK (" + Table_test.COLUMN_NAME_STARRED + " IN (0,1))," +
                    Table_test.COLUMN_NAME_NOTES + " TEXT," +
                    Table_test.COLUMN_NAME_NOTIFY + " BOOLEAN NOT NULL CHECK (" + Table_test.COLUMN_NAME_NOTIFY + " IN (0,1)))";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}