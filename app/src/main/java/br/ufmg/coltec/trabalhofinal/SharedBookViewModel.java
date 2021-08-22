package br.ufmg.coltec.trabalhofinal;

import android.content.ClipData;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.Serializable;
import java.util.List;

public class SharedBookViewModel extends ViewModel {

    private MutableLiveData<Book> text = new MutableLiveData<>();

    public void setText(Book input) {
        text.setValue(input);
    }

    public LiveData<Book> getText() {
        return text;
    }
}
