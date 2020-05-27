package com.example.stayfit.HomeActivities;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.util.Log;
import android.widget.Toast;

import com.example.stayfit.R;
import com.example.stayfit.Utilities.StayFitContractClass;
import com.example.stayfit.Utilities.StayFitDBClass;
import com.example.stayfit.Utilities.StayFitDBHelper;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReportActivity extends AppCompatActivity {

    CompactCalendarView reportCalender;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());


    private StayFitDBClass mainDB  = new StayFitDBClass();
    private SQLiteDatabase stayFitDB;


////      test variables
    public List<Long> testListOfLongTimestamp = new ArrayList<>();
    private void addEx(){
        testListOfLongTimestamp.add(1573053222000L);
        testListOfLongTimestamp.add(1573398822000L);
        testListOfLongTimestamp.add(1573917222000L);
        testListOfLongTimestamp.add(1574262822000L);
        testListOfLongTimestamp.add(1574435622000L);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(null);

        SQLiteOpenHelper DBHelp = new StayFitDBHelper(this);
        stayFitDB = DBHelp.getWritableDatabase();

        reportCalender = findViewById(R.id.reportcalendar_view);
        reportCalender.setUseThreeLetterAbbreviation(true);
        addEx();


        //get dates from DB & set events
        try {
            // get timestamp in string
            List<String> timeSTampInString = browserDatesInDB();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date;
            List<Long> listOfLongTimestampFromDB = new ArrayList<>();
            long tempTimeStamp = 0;

            for(int i = 0; i < timeSTampInString.size(); i++){
                date = dateFormat.parse(timeSTampInString.get(1));
                tempTimeStamp = date.getTime();
                listOfLongTimestampFromDB.add(tempTimeStamp);
            }
            listOfLongTimestampFromDB.size();
//        dateInTimestamp = browserDatesInDB();
            List<String> listOfExer = browserExerciseInDB();


            for (int i = 0; i < listOfExer.size(); i++) {
                Event eachEvent;
//
                eachEvent = new Event(Color.RED, listOfLongTimestampFromDB.get(i), listOfExer.get(i));
//                eachEvent = new Event(Color.RED, testListOfLongTimestamp.get(i), listOfExer.get(i));
                reportCalender.addEvent(eachEvent);

            }

        }catch (ParseException ex){
            Log.e("DB For FITNESS JUNKIE", ex.getMessage());
        }

        reportCalender.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();
                }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });
                            }
    public List<String> browserDatesInDB(){

        List<String> dateList = new ArrayList<>();

        String queryStr = "SELECT * FROM workoutList;";
        try{
            Cursor cursor = stayFitDB.rawQuery(queryStr,null);
            if(cursor != null){
                cursor.moveToFirst();
//                cursor.moveToNext();

                while (!cursor.isAfterLast()){
                    String eachDate;

                    eachDate = cursor.getString(7);
                    dateList.add(eachDate);
                    cursor.moveToNext();
                }
            }
        }catch (Exception ex){
            Log.e("DB For FITNESS JUNKIE", ex.getMessage());
        }


        return dateList;
    }

    public List<String> browserExerciseInDB(){

        List<String> exList = new ArrayList<>();

        String queryStr = "SELECT * FROM workoutList;";
        try{
            Cursor cursor = stayFitDB.rawQuery(queryStr,null);
            if(cursor != null){
                cursor.moveToFirst();
//                cursor.moveToNext();

                while (!cursor.isAfterLast()){
                    String eachEx;

                    eachEx = cursor.getString(2);
                    exList.add(eachEx);
                    cursor.moveToNext();

                }
            }
        }catch (Exception ex){
            Log.e("DB For FITNESS JUNKIE", ex.getMessage());
        }
        return exList;
    }
}
