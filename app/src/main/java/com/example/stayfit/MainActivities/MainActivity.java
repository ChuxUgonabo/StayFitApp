package com.example.stayfit.MainActivities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stayfit.HomeActivities.HomeActivity;
import com.example.stayfit.HomeActivities.WorkoutActivity;
import com.example.stayfit.R;
import com.example.stayfit.Utilities.StayFitDBHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase stayFitDB;
    public static String userN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText username = findViewById(R.id.editTxtUserNameLogin);
        final EditText password = findViewById(R.id.editTxtPasswordLogin);
        Button btnLogin = (Button) findViewById(R.id.buttonLogin);

        SQLiteOpenHelper DBHelp = new StayFitDBHelper(this);
        stayFitDB = DBHelp.getWritableDatabase();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get username from user and make it global
                userN = username.getText().toString();

                String passW = password.getText().toString();
                int position = 0;

                List<String> usernameList = getUserNamesRec();
                List<String> passwordList = getPasswordsRec();

                if(usernameList.contains(userN)){
                    position = usernameList.indexOf(userN);
                    if(passW.equals(passwordList.get(position))){
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    }else
                        Toast.makeText(MainActivity.this, "Error: Incorrect  password", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(MainActivity.this, "Error: Incorrect username ", Toast.LENGTH_SHORT).show();
            }
        });

}

    private List<String> getPasswordsRec(){

        List<String> passwordsInDB = new ArrayList<>();
        String queryStr = "SELECT password FROM Users;";
        try{
            Cursor cursor = stayFitDB.rawQuery(queryStr, null);

            if(cursor != null){
                cursor.moveToFirst();

                while(!cursor.isAfterLast()){
                    String password = cursor.getString(0);
                    passwordsInDB.add(password);
                    cursor.moveToNext();
                }
            }
        }catch (Exception ex){
            Log.e("DB For FITNESS JUNKIE", ex.getMessage());
        }
        return passwordsInDB;
    }

    private List<String> getUserNamesRec(){

        List<String> userNamesInDB = new ArrayList<>();

        String queryStr = "SELECT username FROM Users;";
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


}
