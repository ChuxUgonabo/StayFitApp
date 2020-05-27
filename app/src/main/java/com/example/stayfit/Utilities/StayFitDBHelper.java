package com.example.stayfit.Utilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.stayfit.Utilities.StayFitContractClass.*;

public class StayFitDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "stayFitDB.db";
    public static final int DATABASE_VERSION = 1;

    public StayFitDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String createUsersTable = "CREATE TABLE "+
                Users.TABLE_NAME+ " ("+
                Users.COLUMN_USERNAME+" TEXT PRIMARY KEY, " +
                Users.COLUMN_PASSWORD+" TEXT NOT NULL, "+
                Users.COLUMN_FNAME+" TEXT NOT NULL, "+
                Users.COLUMN_EMAIL+" TEXT NOT NULL, "+
                Users.COLUMN_BODYWEIGHT+" DECIMAL NOT NULL, "+
                Users.COLUMN_AGE+ " INTEGER NOT NULL"+
                ");";

        final String createWorkoutTable = "CREATE TABLE "+
                WorkoutTracked.TABLE_NAME+" ("+
                WorkoutTracked._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                WorkoutTracked.COLUMN_USERNAME +" TEXT NOT NULL, "+
                WorkoutTracked.COLUMN_EXNAME+" TEXT NOT NULL, "+
                WorkoutTracked.COLUMN_EXTYPE+" TEXT NOT NULL, "+
                WorkoutTracked.COLUMN_SETS+ " INTEGER NOT NULL, "+
                WorkoutTracked.COLUMN_REPS+ " INTEGER NOT NULL, "+
                WorkoutTracked.COLUMN_WEIGHT+" DECIMAL NOT NULL, "+
                WorkoutTracked.COLUMN_TIMESTAMP+" TIMESTAMP DEFAULT CURRENT_TIMESTAMP"+
                ");";

        db.execSQL(createUsersTable);
        db.execSQL(createWorkoutTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+WorkoutTracked.TABLE_NAME);
        onCreate(db);

    }

}
