package br.ufmg.coltec.trabalhofinal.classesintermediarias;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.ufmg.coltec.classesdemodelo.Book;

public class SharedBookViewModel extends ViewModel {

    private MutableLiveData<Book> text = new MutableLiveData<>();

    public void setText(Book input) {
        text.setValue(input);
    }

    public LiveData<Book> getText() {
        return text;
    }
}
