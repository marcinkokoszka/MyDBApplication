package pl.mkokoszka.mydbapplication.domain;

import java.io.Serializable;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Address implements Serializable {

    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String country;
    private String city;
    private String street;
    private String postalCode;
}
