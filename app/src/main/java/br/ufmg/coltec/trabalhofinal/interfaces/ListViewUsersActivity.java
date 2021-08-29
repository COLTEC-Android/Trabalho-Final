package br.ufmg.coltec.trabalhofinal.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.ufmg.coltec.data.ApplicationDB;
import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.data.dao.UserDAO;
import br.ufmg.coltec.trabalhofinal.business.ThemeManager;
import br.ufmg.coltec.trabalhofinal.business.adapter.UserAdapter;

public class ListViewUsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeManager themeManager = new ThemeManager(this);
        this.setTheme(themeManager.getCurrentTheme());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_users);

        setListView();
    }

    @Override
    protected void onResume() {
        ThemeManager themeManager = new ThemeManager(this);
        this.setTheme(themeManager.getCurrentTheme());
        super.onResume();
    }

    private void setListView(){
        UserDAO userDAO = new UserDAO(ApplicationDB.getInstance(this));

        ListView listView = findViewById(R.id.list_view_users);
        listView.setAdapter(new UserAdapter(this, userDAO.getAll()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("LIST_VIEW_ON_CLICK", "item "+position);
                String userId = userDAO.getAll().get(position).getEmail();
                Log.d("userID", userId);
                Intent favoriteActivity = new Intent(ListViewUsersActivity.this, FavoritesActivity.class);
                favoriteActivity.putExtra("userId", userId);
                startActivity(favoriteActivity);
            }
        });
    }
}