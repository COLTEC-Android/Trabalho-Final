package br.ufmg.coltec.trabalhofinal.view;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.trabalhofinal.controller.ApplicationDB;
import br.ufmg.coltec.trabalhofinal.controller.dao.ExerciseDAO;
import br.ufmg.coltec.trabalhofinal.models.Exercise;
import br.ufmg.coltec.trabalhofinal.models.adapter.ExerciseAdapter;

public class HomeActivity extends AppCompatActivity {
    private static int CURRENT_THEME = R.style.Theme_TrabalhoFinal;
    private String email;
    private TextView textViewUserName;
    private ListView listView;
    private ApplicationDB applicationDB;
    private ExerciseDAO exerciseDAO;
    private ImageView exerciseImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(CURRENT_THEME);
        setContentView(R.layout.activity_home);
        setDataBase();
        email = getIntent().getStringExtra("email");
        setListView();
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
                if(CURRENT_THEME == R.style.Theme_TrabalhoFinal){
                    Log.d("CHANGE THEME", "DARK");
                    switchActivityTheme(R.style.Theme_TrabalhoFinalDark);
                }else{
                    Log.d("CHANGE THEME", "LIGHT");
                    switchActivityTheme(R.style.Theme_TrabalhoFinal);
                }
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void switchActivityTheme(int themeId) {
        CURRENT_THEME = themeId;
        HomeActivity.this.finish();
        HomeActivity.this.startActivity(new Intent(HomeActivity.this, HomeActivity.class));
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
                startActivity(intent);
            }
        });
    }
}