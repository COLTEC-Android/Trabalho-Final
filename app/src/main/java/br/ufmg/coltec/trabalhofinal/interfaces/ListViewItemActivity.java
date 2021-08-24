package br.ufmg.coltec.trabalhofinal.interfaces;

import androidx.appcompat.app.AppCompatActivity;
import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.trabalhofinal.data.ApplicationDB;
import br.ufmg.coltec.trabalhofinal.data.dao.ExerciseDAO;
import br.ufmg.coltec.trabalhofinal.data.dao.FavoriteDAO;
import br.ufmg.coltec.trabalhofinal.data.entities.Exercise;
import br.ufmg.coltec.trabalhofinal.data.entities.Favorite;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewItemActivity extends AppCompatActivity {

    private TextView name;
    private TextView type;
    private TextView description;
    private ImageView image;
    private ImageButton btnFavorite;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_item);

        getComponents();
        setComponents();
        setFavoriteAction();
    }

    private void getComponents(){
        name = findViewById(R.id.item_name);
        type = findViewById(R.id.item_type);
        description = findViewById(R.id.item_description);
        image = findViewById(R.id.item_image);
        btnFavorite = findViewById(R.id.favorite);
    }

    private void setComponents(){
        ExerciseDAO exerciseDAO = new ExerciseDAO(ApplicationDB.getInstance(this));
        Exercise exercise = exerciseDAO.getByName(getIntent().getStringExtra("name"));

        name.setText(exercise.getName());
        type.setText(exercise.getType());
        description.setText(exercise.getDescription());
        image.setImageBitmap(BitmapFactory.decodeByteArray(exercise.getImage(), 0, exercise.getImage().length));

        email = getIntent().getStringExtra("email");
    }

    public void setFavoriteAction(){
        FavoriteDAO favoriteDAO = new FavoriteDAO(ApplicationDB.getInstance(this));
        Favorite favorite = new Favorite(email, getIntent().getStringExtra("name"));
        Boolean clicked;
        if(favoriteDAO.isFavorited(favorite))
        {
            btnFavorite.setImageResource(R.drawable.ic_baseline_star_24);
            clicked = new Boolean(true);
        }else{
            clicked = new Boolean(false);
        }
        btnFavorite.setTag(clicked);

        btnFavorite.setOnClickListener(view -> {
            if( ((Boolean)btnFavorite.getTag())==false ){
                btnFavorite.setImageResource(R.drawable.ic_baseline_star_24);
                btnFavorite.setTag(new Boolean(true));
                favoriteDAO.insert(favorite);
                Toast.makeText(this, R.string.like, Toast.LENGTH_SHORT).show();

            }else{
                btnFavorite.setImageResource(R.drawable.ic_baseline_star_24_black);
                btnFavorite.setTag(new Boolean(false));
                favoriteDAO.delete(favorite);
                Toast.makeText(this, R.string.dislike, Toast.LENGTH_SHORT).show();
            }
        });
    }
}