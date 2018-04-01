package pl.mkokoszka.mydbapplication.repository.impl;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import pl.mkokoszka.mydbapplication.domain.Person;
import pl.mkokoszka.mydbapplication.repository.PersonRepository;
import pl.mkokoszka.mydbapplication.repository.impl.converter.PersonEntityConverter;
import pl.mkokoszka.mydbapplication.repository.impl.entity.PersonEntity;

public class RealmPersonRepository implements PersonRepository {

    private PersonEntityConverter personEntityConverter;

    @Inject
    public RealmPersonRepository(PersonEntityConverter personEntityConverter) {
        this.personEntityConverter = personEntityConverter;
    }

    @Override
    public void saveOrUpdate(Person person) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction((realm) -> realm.insertOrUpdate(personEntityConverter.convert(person)));
        }
    }

    @Override
    public void delete(Person person) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction((realm) -> findAndDelete(person, realm));
        }
    }

    private void findAndDelete(Person person, Realm realm) {
        PersonEntity found = realm.where(PersonEntity.class).equalTo("id", person.getId()).findFirst();
        found.deleteFromRealm();
    }

    @Override
    public List<Person> findAll() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<PersonEntity> results = realm.where(PersonEntity.class).findAll();
        List<Person> persons = personEntityConverter.convert(results);
        realm.commitTransaction();
        realm.close();
        return persons;
    }
}
