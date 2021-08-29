package br.ufmg.coltec.trabalhofinal.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.ufmg.coltec.data.ApplicationDB;
import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.data.dao.ExerciseDAO;
import br.ufmg.coltec.data.dao.FavoriteDAO;
import br.ufmg.coltec.data.dao.UserDAO;
import br.ufmg.coltec.data.entities.Exercise;
import br.ufmg.coltec.trabalhofinal.business.ThemeManager;
import br.ufmg.coltec.trabalhofinal.business.adapter.ExerciseAdapter;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeManager themeManager = new ThemeManager(this);
        this.setTheme(themeManager.getCurrentTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        setListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setListView();
        ThemeManager themeManager = new ThemeManager(this);
        this.setTheme(themeManager.getCurrentTheme());
        super.onResume();
    }

    private void setListView(){
        UserDAO userDAO = new UserDAO(ApplicationDB.getInstance(this));
        FavoriteDAO favoriteDAO = new FavoriteDAO(ApplicationDB.getInstance(this));
        ExerciseDAO exerciseDAO = new ExerciseDAO(ApplicationDB.getInstance(this));
        String userId = getIntent().getStringExtra("userId");

        List<Exercise> exerciseList = exerciseDAO.getSpecificList(favoriteDAO.getByUser(userId));
        ListView listView = findViewById(R.id.list_view_favorites);
        listView.setAdapter(new ExerciseAdapter(this, exerciseList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("LIST_VIEW_ON_CLICK", "item "+position);
                String userId = userDAO.getAll().get(position).getEmail();
                Intent intent = new Intent(FavoritesActivity.this, ListViewItemActivity.class);
                intent.putExtra("name", exerciseList.get(position).getName());
                intent.putExtra("email", userId);
                intent.putExtra("canFavorite", false);
                startActivity(intent);
            }
        });
    }
}