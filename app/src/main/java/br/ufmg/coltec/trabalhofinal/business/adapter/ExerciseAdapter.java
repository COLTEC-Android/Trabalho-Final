package br.ufmg.coltec.trabalhofinal.business.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.data.entities.Exercise;

public class ExerciseAdapter extends BaseAdapter {
    private ArrayList<Exercise> exercises;
    private Context context;

    public ExerciseAdapter(Context context, List<Exercise> exercises){
        this.context = context;
        this.exercises = (ArrayList<Exercise>) exercises;
    }

    @Override
    public int getCount() {
        return exercises.size();
    }

    @Override
    public Object getItem(int position) {
        return exercises.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Exercise currentExercise = this.exercises.get(position);

        View newView = LayoutInflater.from(this.context).inflate(R.layout.adapter_exercises, parent, false);

        TextView lblName = newView.findViewById(R.id.adapter_name);
        TextView lblType = newView.findViewById(R.id.adapter_type);
        ImageView image = newView.findViewById(R.id.adapter_image);
        lblName.setText(currentExercise.getName());
        lblType.setText(currentExercise.getType());
        image.setImageBitmap(BitmapFactory.decodeByteArray(currentExercise.getImage(), 0, currentExercise.getImage().length));

        return newView;
    }
}
