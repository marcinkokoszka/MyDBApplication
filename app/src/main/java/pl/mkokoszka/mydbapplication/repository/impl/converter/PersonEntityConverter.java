package pl.mkokoszka.mydbapplication.repository.impl.converter;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import io.realm.RealmResults;
import pl.mkokoszka.mydbapplication.domain.Person;
import pl.mkokoszka.mydbapplication.repository.impl.entity.PersonEntity;

public class PersonEntityConverter {

    private AddressEntityConverter addressEntityConverter;
    private BookEntityConverter bookEntityConverter;

    @Inject
    public PersonEntityConverter(AddressEntityConverter addressEntityConverter, BookEntityConverter bookEntityConverter) {
        this.addressEntityConverter = addressEntityConverter;
        this.bookEntityConverter = bookEntityConverter;
    }

    public Person convert(PersonEntity personEntity) {
        return Person.builder()
                .id(personEntity.getId())
                .pesel(personEntity.getPesel())
                .name(personEntity.getName())
                .surname(personEntity.getSurname())
                .birthDate(personEntity.getBirthDate())
                .address(addressEntityConverter.convert(personEntity.getAddressEntity()))
                .books(bookEntityConverter.convert(personEntity.getBookEntities()))
                .build();
    }

    public List<Person> convert(RealmResults<PersonEntity> personEntities) {
        return personEntities.stream().map(this::convert).collect(Collectors.toList());
    }

    public PersonEntity convert (Person person) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(person.getId());
        personEntity.setPesel(person.getPesel());
        personEntity.setName(person.getName());
        personEntity.setSurname(person.getSurname());
        personEntity.setBirthDate(person.getBirthDate());
        personEntity.setAddressEntity(addressEntityConverter.convert(person.getAddress()));
        personEntity.setBookEntities(bookEntityConverter.convert(person.getBooks()));
        return personEntity;
    }

}
