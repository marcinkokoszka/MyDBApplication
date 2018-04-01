package pl.mkokoszka.mydbapplication.component;

import dagger.Component;
import pl.mkokoszka.mydbapplication.repository.impl.RealmPersonRepository;

@Component
public interface MyDBApplicationComponent {

    RealmPersonRepository getRealmPersonRepository();
}
