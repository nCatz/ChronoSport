package com.nkats.chronosport.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.nkats.chronosport.ChronoSportAplications;

/**
 * Created by amador on 3/02/17.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ChronoSport.db";
    public static DataBaseHelper instance;
    public SQLiteDatabase database;

    public synchronized static DataBaseHelper getInstance(){

        if(instance == null){

            instance = new DataBaseHelper();
        }

        return instance;
    }

    public synchronized SQLiteDatabase openDataBase(){

        if(database == null){

            database = getWritableDatabase();
        }

        return database;
    }

    public void closeDataBase(){

        close();
    }

    private DataBaseHelper() {
        super(ChronoSportAplications.getContex(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.beginTransaction();

        try {
            sqLiteDatabase.execSQL(DatabaseContract.ChronoTimeElemntEntry.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(DatabaseContract.ChronoRepetitionElementEntry.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(DatabaseContract.ChronoEntry.SQL_CREATE_ENTRIES);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {

            e.getMessage();


        }finally {

            sqLiteDatabase.endTransaction();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.beginTransaction();

        try {
            sqLiteDatabase.execSQL(DatabaseContract.ChronoTimeElemntEntry.SQL_DELETE_ENTRIES);
            sqLiteDatabase.execSQL(DatabaseContract.ChronoRepetitionElementEntry.SQL_DELETE_ENTRIES);
            sqLiteDatabase.execSQL(DatabaseContract.ChronoEntry.SQL_DELETE_ENTRIES);
            onCreate(sqLiteDatabase);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {


        }finally {

            sqLiteDatabase.endTransaction();
        }

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, newVersion, oldVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {

        super.onOpen(db);
        if(!db.isReadOnly()){

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){

                db.setForeignKeyConstraintsEnabled(true);

            }else {

                db.execSQL("PRAGMA foreign_keys = ON");
            }
        }
    }
}
