package com.example.stayfit.HomeActivities;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stayfit.Adapters.WorkOutRecyclerAdapter;
import com.example.stayfit.Adapters.WorkoutAdapater;
import com.example.stayfit.MainActivities.MainActivity;
import com.example.stayfit.R;
import com.example.stayfit.Utilities.StayFitContractClass;
import com.example.stayfit.Utilities.StayFitDBHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.example.stayfit.Utilities.StayFitContractClass.*;
public class WorkoutActivity extends AppCompatActivity {
    private SQLiteDatabase stayFitDB;
    private WorkOutRecyclerAdapter myRecyclerAdaper;

    private EditText exerciseName;
    private RadioGroup typeOfExercise;
    private EditText numOfSets;
    private EditText numOfReps;
    private EditText weightinLBS;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        SQLiteOpenHelper DBHelp = new StayFitDBHelper(this);
        stayFitDB = DBHelp.getWritableDatabase();

        RecyclerView myRecyclerView = findViewById(R.id.recycleViewForWO);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerAdaper = new WorkOutRecyclerAdapter(this, getAllItems());
        myRecyclerView.setAdapter(myRecyclerAdaper);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                removeWorkout((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(myRecyclerView);


        exerciseName = findViewById(R.id.editTextExerciseName);
        typeOfExercise = findViewById(R.id.radioExTypes);
        numOfSets = findViewById(R.id.editTextSets);
        numOfReps = findViewById(R.id.editTextReps);
        weightinLBS = findViewById(R.id.editTextWeight);

        Button btnAddExercise = findViewById(R.id.buttonAddExercise);




        btnAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String exName = exerciseName.getText().toString();
                String type;
                if(typeOfExercise.getCheckedRadioButtonId() == R.id.radioButtonBodyBuilding )
                    type = "BodyBuilding";
                else
                    type = "cardio";
                int sets = Integer.parseInt(numOfSets.getText().toString());
                int reps = Integer.parseInt(numOfReps.getText().toString());
                double weight = Double.parseDouble(weightinLBS.getText().toString());


                boolean ans = addWorkoutDetails(MainActivity.userN, exName, type, sets, reps, weight);

               if(ans == true)
                   Toast.makeText(WorkoutActivity.this, "this is your username: "+ MainActivity.userN, Toast.LENGTH_SHORT).show();
               else
                   Toast.makeText(WorkoutActivity.this, "Error adding", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void removeWorkout(long ID){
        stayFitDB.delete(WorkoutTracked.TABLE_NAME,WorkoutTracked._ID+ "="+ID, null);
        myRecyclerAdaper.swapCursor(getAllItems());
    }

    private boolean addWorkoutDetails(String userN, String exName, String exType, int sets, int reps, double weight) {

        if(exerciseName.getText().toString().trim().length() == 0  ||
                numOfSets.getText().toString().trim().length() == 0 ||
                numOfReps.getText().toString().trim().length() == 0 || sets == 0 || reps == 0){
            return false;
        }
        boolean added = false;
        long resultOfInsert = 0;
        ContentValues values = new ContentValues();
        values.put(WorkoutTracked.COLUMN_USERNAME, userN);
        values.put(WorkoutTracked.COLUMN_EXNAME, exName);
        values.put(WorkoutTracked.COLUMN_EXTYPE, exType);
        values.put(WorkoutTracked.COLUMN_SETS, sets);
        values.put(WorkoutTracked.COLUMN_REPS, reps);
        values.put(WorkoutTracked.COLUMN_WEIGHT, weight);


        resultOfInsert = stayFitDB.insert(WorkoutTracked.TABLE_NAME, null, values);

        exerciseName.getText().clear();
        numOfSets.getText().clear();
        numOfReps.getText().clear();
        weightinLBS.getText().clear();

        myRecyclerAdaper.swapCursor(getAllItems());

        if (resultOfInsert != -1) {
            added = true;
            Log.e("DB For FITNESS JUNKIE", "Added details of workout for" + userN);
        } else
            Log.e("DB For FITNESS JUNKIE", "Error in adding workout " + userN);
        return added;
    }

    private Cursor getAllItems(){
        return stayFitDB.query(
                WorkoutTracked.TABLE_NAME, null,null, null,
                null, null,WorkoutTracked._ID+" DESC");
    }


}


