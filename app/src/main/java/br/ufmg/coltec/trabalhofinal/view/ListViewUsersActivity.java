package br.ufmg.coltec.trabalhofinal.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.trabalhofinal.controller.ApplicationDB;
import br.ufmg.coltec.trabalhofinal.controller.dao.UserDAO;
import br.ufmg.coltec.trabalhofinal.models.Exercise;
import br.ufmg.coltec.trabalhofinal.models.adapter.ExerciseAdapter;
import br.ufmg.coltec.trabalhofinal.models.adapter.UserAdapter;

public class ListViewUsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_users);

        setListView();
    }

    private void setListView(){
        UserDAO userDAO = new UserDAO(ApplicationDB.getInstance(this));

        ListView listView;
        listView = findViewById(R.id.list_view_users);
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