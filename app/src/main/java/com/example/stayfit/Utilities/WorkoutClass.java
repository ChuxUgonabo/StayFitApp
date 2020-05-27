package com.example.stayfit.Utilities;

public class WorkoutClass {
    private int exerciseID;
    private String usernameEX;
    private String exerciseName;
    private  String exType;
    private int sets;
    private int reps;
    private double weightEx;
    private long dateStamps;


    public int getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(int exerciseID) {
        this.exerciseID = exerciseID;
    }

    public String getUsernameEX() {
        return usernameEX;
    }

    public void setUsernameEX(String usernameEX) {
        this.usernameEX = usernameEX;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExType() {
        return exType;
    }

    public void setExType(String exType) {
        this.exType = exType;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public double getWeightEx() {
        return weightEx;
    }

    public void setWeightEx(double weightEx) {
        this.weightEx = weightEx;
    }

    public long getDateStamps() {
        return dateStamps;
    }

    public void setDateStamps(long dateStamps) {
        this.dateStamps = dateStamps;
    }

}
