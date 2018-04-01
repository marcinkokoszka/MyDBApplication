package pl.mkokoszka.mydbapplication.repository.impl.entity;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class BookEntity extends RealmObject {

    @PrimaryKey
    @Required
    private String id = UUID.randomUUID().toString();
    @Required
    private String title;
    @Required
    private String author;

    public BookEntity() {}

    public BookEntity(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
