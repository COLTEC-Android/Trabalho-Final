package br.ufmg.coltec.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class BookDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}