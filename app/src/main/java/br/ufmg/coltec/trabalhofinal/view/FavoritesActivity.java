package br.ufmg.coltec.trabalhofinal.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.trabalhofinal.controller.ApplicationDB;
import br.ufmg.coltec.trabalhofinal.controller.dao.ExerciseDAO;
import br.ufmg.coltec.trabalhofinal.controller.dao.FavoriteDAO;
import br.ufmg.coltec.trabalhofinal.models.Exercise;
import br.ufmg.coltec.trabalhofinal.models.adapter.ExerciseAdapter;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        setListView();
    }

    private void setListView(){
        FavoriteDAO favoriteDAO = new FavoriteDAO(ApplicationDB.getInstance(this));
        ExerciseDAO exerciseDAO = new ExerciseDAO(ApplicationDB.getInstance(this));
        List<Exercise> exerciseList = new ArrayList<>();

        exerciseList = exerciseDAO.getSpecificList(favoriteDAO.getByUser(getIntent().getStringExtra("userId")));
        ListView listView = findViewById(R.id.list_view_favorites);
        listView.setAdapter(new ExerciseAdapter(this, exerciseList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("LIST_VIEW_ON_CLICK", "item "+position);
            }
        });
    }
}