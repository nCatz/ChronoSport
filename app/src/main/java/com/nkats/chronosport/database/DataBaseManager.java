package com.nkats.chronosport.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.nkats.chronosport.model.Chrono;
import com.nkats.chronosport.model.ChronoElement;
import com.nkats.chronosport.model.ChronoRepetitionElement;
import com.nkats.chronosport.model.ChronoTimeElement;

/**
 * Created by amador on 3/02/17.
 */

public class DataBaseManager {

    public void addChrono(Chrono chrono){


    }

    public void updateChrono(Chrono chrono){


    }

    public void deleteChrono(Chrono chrono){


    }

    public Cursor getAllChronos(){

        return null;
    }

    public void addChronoElement(int fkChrono, ChronoElement element){

        if(element instanceof ChronoRepetitionElement){

            addChronoRepetitionElement(fkChrono,(ChronoRepetitionElement)element);

        }else {

            addChronoTimeElement(fkChrono,(ChronoTimeElement)element);
        }

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

    public void updateChronoElement(ChronoElement element){

        if(element instanceof ChronoRepetitionElement){

            updateChronoRepetitionElement((ChronoRepetitionElement) element);

        }else {

            updateChronoTimeElement((ChronoTimeElement) element);
        }
    }

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

    public void deleteChronoElement(ChronoElement element){

        String[] whereParams = {String.valueOf(element.getId())};
        String tableName = DatabaseContract.ChronoTimeElementEntry.TABLE_NAME;

        if(element instanceof ChronoRepetitionElement){

            tableName = DatabaseContract.ChronoRepetitionElementEntry.TABLE_NAME;
        }

        DataBaseHelper.getInstance().openDataBase().delete(tableName, "_id = ?", whereParams);
    }

}
