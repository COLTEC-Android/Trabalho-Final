package br.ufmg.coltec.trabalhofinal.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.ufmg.coltec.classesdemodelo.Book;
import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.trabalhofinal.classesintermediarias.BookDAO;

public class SplashActivity extends AppCompatActivity {

    BookDAO bookDAO = new BookDAO(SplashActivity.this);
    private static final String APP_PREF_ID = "SP_MAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        int SPLASH_TIME_OUT = 4000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //devolve último acesso e salva o acesso atual em Shared Preferences
                SharedPreferences pref2 = getSharedPreferences(APP_PREF_ID, 0);
                String chaveData = pref2.getString("chaveData", "");

                String text = String.format("%s %s", getString(R.string.string_last_access), chaveData);

                if (!chaveData.equals("")) {
                    Toast.makeText(SplashActivity.this, text, Toast.LENGTH_LONG).show();
                }

                String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

                SharedPreferences pref = getSharedPreferences(APP_PREF_ID, 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("chaveData", currentDate + " - " + currentTime);
                editor.commit();


                Book memoriasPostumasDeBrasCubas = new Book(R.drawable.memorias_postumas_de_bras_cubas, "Memórias Póstumas de Brás Cubas", "Machado de Assis", "Romance", "Tipografia Nacional");
                Book oCortico = new Book(R.drawable.o_cortico, "O Cortiço", "Aluísio Azevedo", "Romance", "Centaur");
                Book osSertoes = new Book(R.drawable.os_sertoes, "Os Sertões", "Euclides da Cunha", "Livro-reportagem", "Laemmert");
                Book tristeFimDePolicarpoQuaresma = new Book(R.drawable.triste_fim_de_policarpo_quaresma, "Triste Fim de Policarpo Quaresma", "Lima Barreto", "Romance", "Revista dos Tribunaes");
                Book macunaima = new Book(R.drawable.macunaima, "Macunaíma", "Mário de Andrade", "Literatura do Brasil, Ficção, Romance, humor", "Oficinas Gráficas de Eugênio Cupolo");
                Book oQuinze = new Book(R.drawable.o_quinze, "O Quinze", "Rachel de Queiroz", "Romance", "José Olympio");
                Book vidasSecas = new Book(R.drawable.vidas_secas, "Vidas Secas", "Graciliano Ramos", "Romance", "José Olympio");
                Book vestidoDeNoiva = new Book(R.drawable.vestido_da_noiva, "Vestido de Noiva", "Nelson Rodrigues", "Teatro", "Nova Fronteira");
                Book grandeSertaoVeredas = new Book(R.drawable.grande_sertao_veredas, "Grande Sertão: Veredas", "Guimarães Rosa", "Romance", "José Olympio");
                Book morteEVidaSeverina = new Book(R.drawable.morte_e_vida_severina, "Morte e Vida Severina", "João Cabral de Melo Neto", "Poema", "TUCA");

                if (!bookDAO.getAll().contains(memoriasPostumasDeBrasCubas)){
                    bookDAO.insert(memoriasPostumasDeBrasCubas);
                }
                if (!bookDAO.getAll().contains(memoriasPostumasDeBrasCubas)){
                    bookDAO.insert(oCortico);
                }
                if (!bookDAO.getAll().contains(osSertoes)){
                    bookDAO.insert(osSertoes);
                }
                if (!bookDAO.getAll().contains(tristeFimDePolicarpoQuaresma)){
                    bookDAO.insert(tristeFimDePolicarpoQuaresma);
                }
                if (!bookDAO.getAll().contains(macunaima)){
                    bookDAO.insert(macunaima);
                }
                if (!bookDAO.getAll().contains(oQuinze)){
                    bookDAO.insert(oQuinze);
                }
                if (!bookDAO.getAll().contains(vidasSecas)){
                    bookDAO.insert(vidasSecas);
                }
                if (!bookDAO.getAll().contains(vestidoDeNoiva)){
                    bookDAO.insert(vestidoDeNoiva);
                }
                if (!bookDAO.getAll().contains(grandeSertaoVeredas)){
                    bookDAO.insert(grandeSertaoVeredas);
                }
                if (!bookDAO.getAll().contains(morteEVidaSeverina)){
                    bookDAO.insert(morteEVidaSeverina);
                }


                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}