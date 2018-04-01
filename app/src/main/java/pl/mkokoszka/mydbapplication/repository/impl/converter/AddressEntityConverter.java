package pl.mkokoszka.mydbapplication.repository.impl.converter;

import javax.inject.Inject;

import pl.mkokoszka.mydbapplication.domain.Address;
import pl.mkokoszka.mydbapplication.repository.impl.entity.AddressEntity;

public class AddressEntityConverter {

    @Inject
    public AddressEntityConverter() {
    }

    public Address convert(AddressEntity addressEntity) {
        return Address.builder()
                .id(addressEntity.getId())
                .country(addressEntity.getCountry())
                .city(addressEntity.getCity())
                .postalCode(addressEntity.getPostalCode())
                .street(addressEntity.getStreet())
                .build();
    }

    public AddressEntity convert(Address address) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(address.getId());
        addressEntity.setCountry(address.getCountry());
        addressEntity.setCity(address.getCity());
        addressEntity.setPostalCode(address.getPostalCode());
        addressEntity.setStreet(address.getStreet());
        return addressEntity;
    }
}
