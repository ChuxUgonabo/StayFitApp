package com.example.stayfit.MainActivities;

import android.content.ContentValues;
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
import com.example.stayfit.R;
import com.example.stayfit.Utilities.StayFitContractClass;
import com.example.stayfit.Utilities.StayFitDBHelper;

import java.util.ArrayList;
import java.util.List;

public class SignupActivity extends AppCompatActivity {
    private SQLiteDatabase stayFitDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        SQLiteOpenHelper DBHelp = new StayFitDBHelper(this);
        stayFitDB = DBHelp.getWritableDatabase();

        final EditText userName = findViewById(R.id.editTextUserN);
        final EditText password = findViewById(R.id.editTextPassword);
        final EditText confirmPassord = findViewById(R.id.editTextConfirmPassword);
        final EditText fName = findViewById(R.id.editTextFullName);
        final EditText email = findViewById(R.id.editTextEmail);
        final EditText weight = findViewById(R.id.editTextWeight);
        final EditText age = findViewById(R.id.editTextAge);

        Button signupActionBtn = findViewById(R.id.btnSubmitSignup);

        signupActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userN = userName.getText().toString();
                MainActivity.userN = userN;
                String pword = password.getText().toString();
                String pwordCon = confirmPassord.getText().toString();
                String fullName = fName.getText().toString();
                String mail =  email.getText().toString();
                double wt =  Double.parseDouble (weight.getText().toString());
                int ag =  Integer.parseInt(age.getText().toString());
                boolean addedDB = false;



                if(pword.equals(pwordCon)){

                   addedDB = addUserInfo(userN, pword, fullName, mail, wt, ag);

                    if(addedDB == true)
                    startActivity(new Intent(SignupActivity.this, HomeActivity.class));
                    else
                        Toast.makeText(SignupActivity.this, "Error: could not add to Database", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SignupActivity.this, "Error: Password don't Match", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    private boolean addUserInfo(String userN, String passW, String fName, String email, double weight, int age ){

        boolean added = false;
        long resultOfInsert = 0;
        ContentValues values = new ContentValues();
        values.put(StayFitContractClass.Users.COLUMN_USERNAME, userN);
        values.put(StayFitContractClass.Users.COLUMN_PASSWORD, passW);
        values.put(StayFitContractClass.Users.COLUMN_FNAME, fName);
        values.put(StayFitContractClass.Users.COLUMN_EMAIL, email);
        values.put(StayFitContractClass.Users.COLUMN_BODYWEIGHT, weight);
        values.put(StayFitContractClass.Users.COLUMN_AGE, age);

        resultOfInsert = stayFitDB.insert("Users", null, values);

        if(resultOfInsert != -1){
            added = true;
            Log.e("DB For FITNESS JUNKIE", "Added info for user"+userN);
        }
        else
            Log.e("DB For FITNESS JUNKIE", "Error in adding user "+userN);
        return added;
    }

}
