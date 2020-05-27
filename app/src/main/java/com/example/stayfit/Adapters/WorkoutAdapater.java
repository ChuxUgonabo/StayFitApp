package com.example.stayfit.Adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stayfit.HomeActivities.WorkoutActivity;
import com.example.stayfit.R;
import com.example.stayfit.Utilities.StayFitDBClass;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class WorkoutAdapater extends BaseAdapter {

    WorkoutAdapater adapater;
    List<String> exerciseList = new ArrayList<>();
    List<String> setsList = new ArrayList<>();
    List<String> repsList = new ArrayList<>();
    List<String> idList = new ArrayList<>();
    SQLiteDatabase stayFitDB;
//    StayFitDBClass stayFitDB = new StayFitDBClass(tempDB);
    private Context mContext;


    public WorkoutAdapater( Context context, List<String> IDs, List<String> exList,  List<String> sList,  List<String> rList){

        mContext = context;
        idList = IDs;
        exerciseList = exList;
        setsList = sList;
        repsList = rList;
        adapater = this;
    }
    @Override
    public int getCount() {
        return exerciseList.size();
    }

    @Override
    public Object getItem(int position) {
        return exerciseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if(convertView == null){
            LayoutInflater myLayoutInflater = LayoutInflater.from(parent.getContext());
            convertView = myLayoutInflater.inflate(R.layout.layout_external, parent, false);
       }
        TextView exerciseTextView = convertView.findViewById(R.id.textViewExercise);
        exerciseTextView.setText(exerciseList.get(position));
        exerciseTextView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);

        TextView setsTextView = convertView.findViewById(R.id.textViewSets);
        setsTextView.setText(setsList.get(position));
        setsTextView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);

        TextView repTextView = convertView.findViewById(R.id.textViewReps);
        repTextView.setText(repsList.get(position));
        repTextView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);


        return convertView;
    }


}