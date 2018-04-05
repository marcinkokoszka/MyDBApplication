package pl.mkokoszka.mydbapplication.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Person implements Serializable {

    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String pesel;
    private String name;
    private String surname;
    private Date birthDate;
    private Address address;
    @Builder.Default
    private List<Book> books = new ArrayList<>();
}
