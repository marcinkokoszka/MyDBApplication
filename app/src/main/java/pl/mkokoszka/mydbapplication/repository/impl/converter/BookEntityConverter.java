package pl.mkokoszka.mydbapplication.repository.impl.converter;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import io.realm.RealmList;
import pl.mkokoszka.mydbapplication.domain.Book;
import pl.mkokoszka.mydbapplication.repository.impl.entity.BookEntity;

public class BookEntityConverter {

    @Inject
    public BookEntityConverter() {
    }

    public Book convert(BookEntity bookEntity) {
        return Book.builder()
                .id(bookEntity.getId())
                .title(bookEntity.getTitle())
                .author(bookEntity.getAuthor())
                .build();
    }

    public List<Book> convert(RealmList<BookEntity> bookEntities) {
        return bookEntities.stream().map(this::convert).collect(Collectors.toList());
    }



    private BookEntity convert(Book book) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(book.getId());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthor(book.getAuthor());
        return bookEntity;
    }

    public RealmList<BookEntity> convert(List<Book> books) {
        RealmList<BookEntity> bookEntities = new RealmList<>();
        books.forEach(book -> bookEntities.add(convert(book)));
        return bookEntities;
    }
}
