package com.example.stayfit.Utilities;

import android.provider.BaseColumns;

public class StayFitContractClass {

    private StayFitContractClass() {}

    public static final class Users implements BaseColumns{

        public static final String TABLE_NAME = "Users";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_FNAME = "fullname";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_BODYWEIGHT = "bodyWeight";
        public static final String COLUMN_AGE = "age";
    }

    public static final class WorkoutTracked implements BaseColumns{

        public static final String TABLE_NAME = "workoutList";
        public static final String COLUMN_USERNAME = "usernameEX";
        public static final String COLUMN_EXNAME = "exerciseName";
        public static final String COLUMN_EXTYPE = "exType";
        public static final String COLUMN_SETS = "sets";
        public static final String COLUMN_REPS = "reps";
        public static final String COLUMN_WEIGHT = "weightEx";
        public static final String COLUMN_TIMESTAMP = "dateStamps";
    }
}
