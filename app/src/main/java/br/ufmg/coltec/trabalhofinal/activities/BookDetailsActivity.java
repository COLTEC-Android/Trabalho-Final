package br.ufmg.coltec.trabalhofinal.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;

import br.ufmg.coltec.classesdemodelo.Book;
import br.ufmg.coltec.trabalhofinal.R;

public class BookDetailsActivity extends AppCompatActivity {

    private static final String APP_PREF_THEME_ID = "SP_THEME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = this.getSharedPreferences(APP_PREF_THEME_ID, 0);
        boolean theme = preferences.getBoolean("theme", true);
        if (theme) {
            this.setTheme(R.style.Theme_TrabalhoFinal);
        } else {
            this.setTheme(R.style.Theme_TrabalhoFinalGold);
        }
        setContentView(R.layout.activity_book_details);

        Book book = (Book) getIntent().getSerializableExtra("book");

        ImageView img = findViewById(R.id.img_book_details_photo);
        TextView title = findViewById(R.id.txt_book_details_title);
        TextView author = findViewById(R.id.txt_book_details_author);
        TextView genre = findViewById(R.id.txt_book_details_genre);
        TextView publisher = findViewById(R.id.txt_book_details_publisher);

        img.setImageResource(book.getPhotoId());
        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        genre.setText(book.getGenre());
        publisher.setText(book.getPublisher());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem shareItem = menu.findItem(R.id.action_share);
        ShareActionProvider shareProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);

        Book book = (Book) getIntent().getSerializableExtra("book");

        ImageView img = findViewById(R.id.img_book_details_photo);
        img.setImageResource(book.getPhotoId());

        if (img.getResources() != null) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("image/*");

            String path = String.valueOf(book.getPhotoId());

            Uri uri = Uri.parse(path);

            intent.putExtra(Intent.EXTRA_STREAM, uri);

            shareProvider.setShareIntent(intent);
        } else {
            Toast.makeText(BookDetailsActivity.this, R.string.string_toast_share, Toast.LENGTH_LONG).show();
        }

        return super.onCreateOptionsMenu(menu);

    }

}