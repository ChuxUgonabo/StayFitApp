package com.example.stayfit.Utilities;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StayFitDBClass {

    SQLiteDatabase stayFitDB;
    Context mContext;

    public StayFitDBClass(){
    }
    public StayFitDBClass(SQLiteDatabase DB){
        stayFitDB = DB;
    }

    public SQLiteDatabase getStayFitDB(){
        return stayFitDB;
    }

    public void openDB() {
        try {
            stayFitDB = mContext.openOrCreateDatabase("StayFitDB.db", Context.MODE_PRIVATE, null);

        } catch (Exception ex) {
            Log.e("DB For FITNESS JUNKIE", ex.getMessage());
        }
    }

    public void createStayFitDB(){
        try{
            stayFitDB = mContext.openOrCreateDatabase("StayFitDB.db", Context.MODE_PRIVATE, null);
            Toast.makeText(mContext, "Database Ready", Toast.LENGTH_SHORT).show();
        }catch (Exception ex){
            Log.e("DB For FITNESS JUNKIE", ex.getMessage());
        }
    }

    public  void createUserTables(){
        try{
            String dropUsersTable = "DROP TABLE IF EXISTS users;";

            String createUsersTable = "CREATE TABLE users "+"(username TEXT PRIMARY KEY," +
                    " password TEXT, name TEXT, email TEXT, weight DECIMAL, age INTEGER);";

            stayFitDB.execSQL(dropUsersTable);
            stayFitDB.execSQL(createUsersTable);
            Toast.makeText(mContext, "User Tables created", Toast.LENGTH_SHORT).show();
        }catch (Exception ex){
            Log.e("DB For FITNESS JUNKIE", ex.getMessage());
        }
    }

    public void createWorkoutTable(){
        try{
            String dropWorkoutTable = "DROP TABLE IF EXISTS workout;";

            String createWorkoutTable = "CREATE TABLE workout "+"(exId INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT, "+
                    "exercisename TEXT, type TEXT, sets INTEGER, reps INTEGER, weight DECIMAL, date INTEGER);";

            stayFitDB.execSQL(dropWorkoutTable);
            stayFitDB.execSQL(createWorkoutTable);
            Toast.makeText(mContext, "Workout Tables created", Toast.LENGTH_SHORT).show();

        }catch (Exception ex){
            Log.e("DB For FITNESS JUNKIE", ex.getMessage());
        }
    }

    public List<String> getUserNamesRec(){

        List<String> userNamesInDB = new ArrayList<>();

        String queryStr = "SELECT username FROM users;";
        try{
            Cursor cursor = stayFitDB.rawQuery(queryStr, null);

            if(cursor != null){
                cursor.moveToFirst();

                while (!cursor.isAfterLast()){
                    String userName = cursor.getString(0);
                    userNamesInDB.add(userName);
                    cursor.moveToNext();
                }
            }
        }catch (Exception ex){
            Log.e("DB For FITNESS JUNKIE", ex.getMessage());
        }
        return userNamesInDB;
    }

    public List<String> getPasswordsRec() {

        List<String> passwordsInDB = new ArrayList<>();
        String queryStr = "SELECT password FROM users;";
        try {
            Cursor cursor = stayFitDB.rawQuery(queryStr, null);

            if (cursor != null) {
                cursor.moveToFirst();

                while (!cursor.isAfterLast()) {
                    String password = cursor.getString(0);
                    passwordsInDB.add(password);
                    cursor.moveToNext();
                }
            }
        } catch (Exception ex) {
            Log.e("DB For FITNESS JUNKIE", ex.getMessage());
        }
        return passwordsInDB;
    }

    public List<String> browserIDsInDB(){

        List<String> exList = new ArrayList<>();

        String queryStr = "SELECT * FROM workout;";
        try{
            Cursor cursor = stayFitDB.rawQuery(queryStr,null);
            if(cursor != null){
                cursor.moveToFirst();
                cursor.moveToNext();

                while (!cursor.isAfterLast()){
                    String eachEx;

                    eachEx = cursor.getString(0);
                    exList.add(eachEx);
                    cursor.moveToNext();

                }
            }
        }catch (Exception ex){
            Log.e("DB For FITNESS JUNKIE", ex.getMessage());
        }
        return exList;
    }

    public List<String> browserExerciseInDB(){

        List<String> exList = new ArrayList<>();

        String queryStr = "SELECT * FROM workout;";
        try{
            Cursor cursor = stayFitDB.rawQuery(queryStr,null);
            if(cursor != null){
                cursor.moveToFirst();
                cursor.moveToNext();

                while (!cursor.isAfterLast()){
                    String eachEx;

                    eachEx = cursor.getString(3);
                    exList.add(eachEx);
                    cursor.moveToNext();

                }
            }
        }catch (Exception ex){
            Log.e("DB For FITNESS JUNKIE", ex.getMessage());
        }
        return exList;
    }

    public List<String> browserTypeInDB(){

        List<String> typeList = new ArrayList<>();

        String queryStr = "SELECT * FROM workout;";
        try{
            Cursor cursor = stayFitDB.rawQuery(queryStr,null);
            if(cursor != null){
                cursor.moveToFirst();
                cursor.moveToNext();
                while (!cursor.isAfterLast()){
                    String eachType = "";

                    eachType = cursor.getString(3);
                    typeList.add(eachType);
                    cursor.moveToNext();
                }
            }
        }catch (Exception ex){
            Log.e("DB For FITNESS JUNKIE", ex.getMessage());
        }
        return typeList;
    }

    public List<String> browserSetsInDB(){

        List<String> setsList = new ArrayList<>();

        String queryStr = "SELECT * FROM workout;";
        try{
            Cursor cursor = stayFitDB.rawQuery(queryStr,null);
            if(cursor != null){
                cursor.moveToFirst();
                cursor.moveToNext();
                while (!cursor.isAfterLast()){
                    String eachSet = "";

                    eachSet = cursor.getString(4);
                    setsList.add(eachSet);
                    cursor.moveToNext();
                }
            }
        }catch (Exception ex){
            Log.e("DB For FITNESS JUNKIE", ex.getMessage());
        }
        return setsList;
    }

    public List<String> browserRepsInDB(){

        List<String> repsList = new ArrayList<>();

        String queryStr = "SELECT * FROM workout;";
        try{
            Cursor cursor = stayFitDB.rawQuery(queryStr,null);
            if(cursor != null){
                cursor.moveToFirst();
                cursor.moveToNext();
                while (!cursor.isAfterLast()){
                    String eachrep = "";

                    eachrep = cursor.getString(5);
                    repsList.add(eachrep);
                    cursor.moveToNext();
                }
            }
        }catch (Exception ex){
            Log.e("DB For FITNESS JUNKIE", ex.getMessage());
        }
        return repsList;
    }

    public List<String> browserWeightsInDB(){

        List<String> weightsList = new ArrayList<>();

        String queryStr = "SELECT * FROM workout;";
        try{
            Cursor cursor = stayFitDB.rawQuery(queryStr,null);
            if(cursor != null){
                cursor.moveToFirst();
                cursor.moveToNext();
                while (!cursor.isAfterLast()){
                    String eachWeight = "";

                    eachWeight = cursor.getString(5);
                    weightsList.add(eachWeight);
                    cursor.moveToNext();
                }
            }
        }catch (Exception ex){
            Log.e("DB For FITNESS JUNKIE", ex.getMessage());
        }
        return weightsList;
    }

    public long[] browserDatesInDB(){

        List<String> dateList = new ArrayList<>();
        long[] tempDaterArr;

        String queryStr = "SELECT * FROM workout;";
        try{
            Cursor cursor = stayFitDB.rawQuery(queryStr,null);
            if(cursor != null){
                cursor.moveToFirst();
                cursor.moveToNext();

                while (!cursor.isAfterLast()){
                    String eachDate;

                    eachDate = cursor.getString(8);
                    dateList.add(eachDate);
                    cursor.moveToNext();

                }
            }
        }catch (Exception ex){
            Log.e("DB For FITNESS JUNKIE", ex.getMessage());
        }

        tempDaterArr = new long[dateList.size()];

        for (int i = 0; i < dateList.size(); i++){
            tempDaterArr[i] = Long.parseLong(dateList.get(i));
        }
        return tempDaterArr;
    }

}
