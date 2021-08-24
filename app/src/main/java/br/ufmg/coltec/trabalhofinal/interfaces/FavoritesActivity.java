package br.ufmg.coltec.trabalhofinal.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.trabalhofinal.data.ApplicationDB;
import br.ufmg.coltec.trabalhofinal.data.dao.ExerciseDAO;
import br.ufmg.coltec.trabalhofinal.data.dao.FavoriteDAO;
import br.ufmg.coltec.trabalhofinal.data.entities.Exercise;
import br.ufmg.coltec.trabalhofinal.business.adapter.ExerciseAdapter;

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
        List<Exercise> exerciseList = exerciseDAO.getSpecificList(favoriteDAO.getByUser(getIntent().getStringExtra("userId")));
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