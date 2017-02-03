package com.nkats.chronosport.database;

import android.provider.BaseColumns;

import com.nkats.chronosport.model.Chrono;

/**
 * Created by amador on 3/02/17.
 */

public class DatabaseContract {


    public static class ChronoEntry implements BaseColumns {

        public static final String TABLE_NAME = "chrono";
        public static final String COLUMN_NAME = "ch_name";
        public static final String COLUMN_REPETITIONS = "ch_repetitions";
        public static final String[] ALL_COLUMNS = {_ID, COLUMN_NAME, COLUMN_REPETITIONS};
        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s TEXT NOT NULL," +
                "%s INTEGER NOT NULL)",
                TABLE_NAME, _ID,
                COLUMN_NAME,
                COLUMN_REPETITIONS);
        public static final String SQL_DELETE_ENTRIES = String.format("DELETE TABLE IF EXISTS %s", TABLE_NAME);

    }


    public static class ChronoRepetitionElementEntry implements BaseColumns{

        public static final String TABLE_NAME = "chrono_repetitions_element";
        public static final String COLUMN_NAME = "chr_name";
        public static final String COLUMN_REPETITIONS = "chr_repetitions";
        public static final String COLUMN_ID_CHRONO_FK = "chr_chrono";
        public static final String[] ALL_COLUMNS = {_ID, COLUMN_NAME, COLUMN_REPETITIONS, COLUMN_ID_CHRONO_FK};
        public static final String REFERENCE_CHRONO_TABLE_FK = String.format("REFERENCES %s (%s) ON DELETE RESTRICT ON" +
                " UPDATE CASCADE", ChronoEntry.TABLE_NAME, ChronoEntry._ID);
        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (" +
                "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s TEXT NOT NULL," +
                "%s INTEGER NOT NULL," +
                "%s INTEGER NOT NULL %s)", TABLE_NAME, _ID, COLUMN_NAME,
                COLUMN_REPETITIONS, COLUMN_ID_CHRONO_FK,
                REFERENCE_CHRONO_TABLE_FK);
        public static final String SQL_DELETE_ENTRIES = String.format("DELETE TABLE IF EXISTS %s", TABLE_NAME);

    }

    public static class ChronoTimeElemntEntry implements BaseColumns{

        public static final String TABLE_NAME = "chrono_time_element";
        public static final String COLUMN_NAME = "cht_name";
        public static final String COLUMN_TIME = "cht_time";
        public static final String COLUMN_ID_CHRONO_FK = "chr_chrono";
        public static final String[] ALL_COLUMNS = {_ID, COLUMN_NAME, COLUMN_TIME, COLUMN_ID_CHRONO_FK};
        public static final String REFERENCE_CHRONO_TABLE_FK = String.format("REFERENCES %s (%s) ON DELETE RESTRICT ON" +
                " UPDATE CASCADE", ChronoEntry.TABLE_NAME, ChronoEntry._ID);
        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL," +
                        "%s INTEGER NOT NULL," +
                        "%s INTEGER NOT NULL %s)", TABLE_NAME, _ID, COLUMN_NAME,
                COLUMN_TIME, COLUMN_ID_CHRONO_FK,
                REFERENCE_CHRONO_TABLE_FK);
        public static final String SQL_DELETE_ENTRIES = String.format("DELETE TABLE IF EXISTS %s", TABLE_NAME);

    }



}
