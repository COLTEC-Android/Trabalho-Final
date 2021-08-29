package br.ufmg.coltec.trabalhofinal.interfaces;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import br.ufmg.coltec.data.ApplicationDB;
import br.ufmg.coltec.data.ThemeData;
import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.data.dao.ExerciseDAO;
import br.ufmg.coltec.data.entities.Exercise;
import br.ufmg.coltec.trabalhofinal.business.ThemeManager;
import br.ufmg.coltec.trabalhofinal.business.adapter.ExerciseAdapter;

public class HomeActivity extends AppCompatActivity {
    private static int CURRENT_THEME;
    private String email;
    private ListView listView;
    private ApplicationDB applicationDB;
    private ExerciseDAO exerciseDAO;
    ThemeManager themeManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeManager = new ThemeManager(this);
        Log.d("CURRENT_THEME", String.valueOf(themeManager.getCurrentTheme()));
        this.setTheme(themeManager.getCurrentTheme());

        setContentView(R.layout.activity_home);
        setDataBase();
        email = getIntent().getStringExtra("email");
        setListView();

    }

    @Override
    protected void onResume() {
        ThemeManager themeManager = new ThemeManager(this);
        this.setTheme(themeManager.getCurrentTheme());
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.set_theme:
                themeManager.setCurrentTheme();
                HomeActivity.this.finish();
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                intent.putExtra("email", getIntent().getStringExtra("email"));
                HomeActivity.this.startActivity(intent);
                return true;
            case R.id.fav:
                Intent favoriteActivity = new Intent(HomeActivity.this, FavoritesActivity.class);
                favoriteActivity.putExtra("userId", email);
                try{
                    startActivity(favoriteActivity);
                }catch (Exception e){
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
                }
                return  true;
            case R.id.users_list:
                Intent usersActivity = new Intent(HomeActivity.this, ListViewUsersActivity.class);
                startActivity(usersActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setDataBase(){
        applicationDB = ApplicationDB.getInstance(this);
        exerciseDAO = new ExerciseDAO(applicationDB);
    }

    private void setListView(){
        listView = findViewById(R.id.list_view_exercise);
        listView.setAdapter(new ExerciseAdapter(this, exerciseDAO.getAll()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("LIST_VIEW_ON_CLICK", "item "+position);
                Exercise e = exerciseDAO.getAll().get(position);

                Intent intent = new Intent(HomeActivity.this, ListViewItemActivity.class);
                intent.putExtra("name", e.getName());
                intent.putExtra("email", email);
                intent.putExtra("canFavorite", true);
                startActivity(intent);
            }
        });
    }
}