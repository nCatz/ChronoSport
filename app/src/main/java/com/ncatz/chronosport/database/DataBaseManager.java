package com.ncatz.chronosport.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.ncatz.chronosport.model.Chrono;
import com.ncatz.chronosport.model.ChronoElement;
import com.ncatz.chronosport.model.ChronoRepetitionElement;
import com.ncatz.chronosport.model.ChronoTimeElement;

import java.util.ArrayList;

/**
 * Created by amador on 3/02/17.
 */

public class DataBaseManager {


    //Public methods
    public void addChrono(Chrono chrono){

        ContentValues params = new ContentValues();
        params.put(DatabaseContract.ChronoEntry.COLUMN_NAME, chrono.getName());
        params.put(DatabaseContract.ChronoEntry.COLUMN_REPETITIONS, chrono.getRepetitions());
        chrono.setId((int)DataBaseHelper.getInstance().openDataBase()
                .insert(DatabaseContract.ChronoEntry.TABLE_NAME, null, params));

        for(int i = 0; i < chrono.getElements().size(); i++){

            addChronoElement(chrono.getId(), chrono.getElements().get(i));
        }
    }

    public void updateChrono(Chrono chrono){

        ContentValues params = new ContentValues();
        params.put(DatabaseContract.ChronoEntry.COLUMN_NAME, chrono.getName());
        params.put(DatabaseContract.ChronoEntry.COLUMN_REPETITIONS, chrono.getRepetitions());
        String[] whereParams = {String.valueOf(chrono.getId())};
        DataBaseHelper.getInstance().openDataBase().update(DatabaseContract.ChronoEntry.TABLE_NAME,
                params,"_id = ?", whereParams);

    }

    public void deleteChrono(Chrono chrono){

        String[] whereParams = {String.valueOf(chrono.getId())};
        DataBaseHelper.getInstance().openDataBase().delete(DatabaseContract.ChronoEntry.TABLE_NAME,
                "_id = ?", whereParams);

    }

    public ArrayList<Chrono> getAllChronos() throws Exception {

        ArrayList<Chrono> list = new ArrayList<Chrono>();
        Chrono chrono;
        Cursor cursor = DataBaseHelper.getInstance().openDataBase().query(DatabaseContract.ChronoEntry.TABLE_NAME,
                DatabaseContract.ChronoEntry.ALL_COLUMNS, null, null, null, null, null);

        if(cursor.moveToFirst()){

            do{

                chrono = new Chrono();
                chrono.setId(cursor.getInt(0));
                chrono.setName(cursor.getString(1));
                chrono.setRepetitions(cursor.getInt(2));
                chrono.getElements().addAll(getAllChronoRepetitionElementByIdChrono(chrono.getId()));
                chrono.getElements().addAll(getAllChronoTimeElementByIdChrono(chrono.getId()));

            }while (cursor.moveToNext());
        }

        return list;
    }

    public void addChronoElement(int fkChrono, ChronoElement element){

        if(element instanceof ChronoRepetitionElement){

            addChronoRepetitionElement(fkChrono,(ChronoRepetitionElement)element);

        }else {

            addChronoTimeElement(fkChrono,(ChronoTimeElement)element);
        }

    }

    public void updateChronoElement(ChronoElement element){

        if(element instanceof ChronoRepetitionElement){

            updateChronoRepetitionElement((ChronoRepetitionElement) element);

        }else {

            updateChronoTimeElement((ChronoTimeElement) element);
        }
    }

    public void deleteChronoElement(ChronoElement element){

        String[] whereParams = {String.valueOf(element.getId())};
        String tableName = DatabaseContract.ChronoTimeElementEntry.TABLE_NAME;

        if(element instanceof ChronoRepetitionElement){

            tableName = DatabaseContract.ChronoRepetitionElementEntry.TABLE_NAME;
        }

        DataBaseHelper.getInstance().openDataBase().delete(tableName, "_id = ?", whereParams);
    }

    private ArrayList<ChronoRepetitionElement> getAllChronoRepetitionElementByIdChrono(int idChrono){

        ArrayList<ChronoRepetitionElement> list = new ArrayList<ChronoRepetitionElement>();
        ChronoRepetitionElement element;
        String selection = String.format("%s = ?",
                DatabaseContract.ChronoRepetitionElementEntry.COLUMN_ID_CHRONO_FK);
        String[] whereParams = {String.valueOf(idChrono)};
        Cursor cursor = DataBaseHelper.getInstance().openDataBase().query(DatabaseContract.ChronoRepetitionElementEntry.TABLE_NAME,
                DatabaseContract.ChronoRepetitionElementEntry.ALL_COLUMNS, selection, whereParams, null, null,null);

        if(cursor.moveToFirst()){

            do{

                element = new ChronoRepetitionElement();
                element.setId(cursor.getInt(0));
                element.setName(cursor.getString(1));
                element.setRepetitions(cursor.getInt(2));
                list.add(element);

            }while (cursor.moveToNext());
        }

        return list;
    }

    private ArrayList<ChronoTimeElement> getAllChronoTimeElementByIdChrono(int idChrono){

        ArrayList<ChronoTimeElement> list = new ArrayList<ChronoTimeElement>();
        ChronoTimeElement element;
        String selection = String.format("%s = ?",
                DatabaseContract.ChronoTimeElementEntry.COLUMN_ID_CHRONO_FK);
        String[] whereParams = {String.valueOf(idChrono)};
        Cursor cursor = DataBaseHelper.getInstance().openDataBase().query(DatabaseContract.ChronoTimeElementEntry.TABLE_NAME,
                DatabaseContract.ChronoTimeElementEntry.ALL_COLUMNS, selection, whereParams, null, null,null);

        if(cursor.moveToFirst()){

            do{

                element = new ChronoTimeElement();
                element.setId(cursor.getInt(0));
                element.setName(cursor.getString(1));
                element.setTime(cursor.getInt(2));
                list.add(element);

            }while (cursor.moveToNext());
        }

        return list;
    }


    //Private methods
    private void updateChronoTimeElement(ChronoTimeElement element){

        ContentValues params = new ContentValues();
        params.put(DatabaseContract.ChronoTimeElementEntry.COLUMN_NAME, element.getName());
        params.put(DatabaseContract.ChronoTimeElementEntry.COLUMN_TIME, element.getTime());
        String[] whereParams = {String.valueOf(element.getId())};
        DataBaseHelper.getInstance().openDataBase().update(DatabaseContract.ChronoTimeElementEntry.TABLE_NAME,
                params, "_id = ?", whereParams);

    }

    private void updateChronoRepetitionElement(ChronoRepetitionElement element){

        ContentValues params = new ContentValues();
        params.put(DatabaseContract.ChronoRepetitionElementEntry.COLUMN_NAME, element.getName());
        params.put(DatabaseContract.ChronoRepetitionElementEntry.COLUMN_REPETITIONS, element.getRepetitions());
        String[] whereParams = {String.valueOf(element.getId())};
        DataBaseHelper.getInstance().openDataBase().update(DatabaseContract.ChronoRepetitionElementEntry.TABLE_NAME,
                params, "_id = ?", whereParams);
    }

    private void addChronoTimeElement(int fkChrono, ChronoTimeElement element){

        ContentValues params = new ContentValues();
        params.put(DatabaseContract.ChronoTimeElementEntry.COLUMN_NAME, element.getName());
        params.put(DatabaseContract.ChronoTimeElementEntry.COLUMN_TIME, element.getTime());
        params.put(DatabaseContract.ChronoTimeElementEntry.COLUMN_ID_CHRONO_FK, fkChrono);
        element.setId((int)DataBaseHelper.getInstance().openDataBase().insert(DatabaseContract.ChronoTimeElementEntry.TABLE_NAME,
                null, params));
    }

    private void addChronoRepetitionElement(int fkChrono, ChronoRepetitionElement element){

        ContentValues params = new ContentValues();
        params.put(DatabaseContract.ChronoRepetitionElementEntry.COLUMN_NAME, element.getName());
        params.put(DatabaseContract.ChronoRepetitionElementEntry.COLUMN_REPETITIONS, element.getRepetitions());
        params.put(DatabaseContract.ChronoRepetitionElementEntry.COLUMN_ID_CHRONO_FK, fkChrono);
        element.setId((int)DataBaseHelper.getInstance().openDataBase().insert(DatabaseContract.ChronoRepetitionElementEntry.TABLE_NAME,
                null, params));
    }

}
