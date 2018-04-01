package pl.mkokoszka.mydbapplication.repository.impl.entity;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class PersonEntity extends RealmObject {

    @PrimaryKey
    @Required
    private String id = UUID.randomUUID().toString();
    private String pesel;
    private String name;
    private String surname;
    private Date birthDate;
    private AddressEntity addressEntity;
    private RealmList<BookEntity> bookEntities;

    public String getPesel() {
        return pesel;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
    }

    public RealmList<BookEntity> getBookEntities() {
        return bookEntities;
    }

    public void setBookEntities(RealmList<BookEntity> bookEntities) {
        this.bookEntities = bookEntities;
    }

    public String getId() {
        return id;
    }
}
