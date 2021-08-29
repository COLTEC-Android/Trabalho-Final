package br.ufmg.coltec.trabalhofinal.classesintermediarias;


import java.util.List;

import br.ufmg.coltec.classesdemodelo.Book;

public class BookSearchProxy implements BookSearchInterface {

    private BookSearchInterface base;
    private List<Book> cache;

    public BookSearchProxy(BookSearchInterface base) {
        this.base = base;
    }

    @Override
    public Book getBook(int position) {

        if (cache.contains(cache.get(position))) {
            return cache.get(position);
        } else {
            Book book1 = base.getBook(position);
            if (book1 != null) {
                cache.add(book1);
                return book1;
            }
        }
        return null;
    }

    public List<Book> getCache() {
        return cache;
    }

    public void setCache(List<Book> cache) {
        this.cache = cache;
    }
}
