package com.example.stayfit.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stayfit.R;
import com.example.stayfit.Utilities.StayFitContractClass;

public class WorkOutRecyclerAdapter extends RecyclerView.Adapter<WorkOutRecyclerAdapter.WorkoutVIewH> {
    private Context myContext;
    private Cursor myCursor;

    public WorkOutRecyclerAdapter(Context context, Cursor cursor){
        myContext = context;
        myCursor = cursor;
    }
    public class WorkoutVIewH extends RecyclerView.ViewHolder{

        public TextView exNamText;
        public TextView setsText;
        public TextView repsText;

        public WorkoutVIewH(@NonNull View itemView) {
            super(itemView);

            exNamText = itemView.findViewById(R.id.textViewExercise);
            setsText = itemView.findViewById(R.id.textViewSets);
            repsText = itemView.findViewById(R.id.textViewReps);
        }
    }

    @NonNull
    @Override
    public WorkoutVIewH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater myLayoutInflater = LayoutInflater.from(myContext);
        View viewr = myLayoutInflater.inflate(R.layout.layout_external, viewGroup, false);
//        new WorkoutVIewH(viewr).setIsRecyclable(false);
        return new WorkoutVIewH(viewr);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutVIewH workoutVIewH, int i) {
    if (!myCursor.moveToPosition(i)){
        return;
    }
    // these gets data from the DB  which would be used from the recycler view
    String exNam = myCursor.getString(myCursor.getColumnIndex(StayFitContractClass.WorkoutTracked.COLUMN_EXNAME));
    int exSets = myCursor.getInt(myCursor.getColumnIndex(StayFitContractClass.WorkoutTracked.COLUMN_SETS));
    int exReps = myCursor.getInt(myCursor.getColumnIndex(StayFitContractClass.WorkoutTracked.COLUMN_REPS));
    long id = myCursor.getLong(myCursor.getColumnIndex(StayFitContractClass.WorkoutTracked._ID));


    // then place the data in the textView
        workoutVIewH.exNamText.setText(exNam);
        workoutVIewH.setsText.setText(String.valueOf(exSets));
        workoutVIewH.repsText.setText(String.valueOf(exReps));
        workoutVIewH.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return myCursor.getCount();
    }
    public void swapCursor(Cursor newCursor){
        if (myCursor != null){
            myCursor.close();
        }
        myCursor = newCursor;

        if(newCursor != null){
            notifyDataSetChanged();
        }
    }
}
