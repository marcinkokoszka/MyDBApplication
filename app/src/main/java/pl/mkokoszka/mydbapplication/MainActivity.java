package pl.mkokoszka.mydbapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.facebook.stetho.Stetho;
import com.github.javafaker.Faker;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import pl.mkokoszka.mydbapplication.component.DaggerMyDBApplicationComponent;
import pl.mkokoszka.mydbapplication.component.MyDBApplicationComponent;
import pl.mkokoszka.mydbapplication.domain.Address;
import pl.mkokoszka.mydbapplication.domain.Book;
import pl.mkokoszka.mydbapplication.domain.Person;
import pl.mkokoszka.mydbapplication.repository.PersonRepository;
import pl.mkokoszka.mydbapplication.repository.impl.entity.AddressEntity;
import pl.mkokoszka.mydbapplication.repository.impl.entity.BookEntity;
import pl.mkokoszka.mydbapplication.repository.impl.entity.PersonEntity;

public class MainActivity extends AppCompatActivity {

    private PersonRepository personRepository;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        initialisePersonRepository();
        initialiseRealm();
        initialiseStetho();
        initialiseRecyclerView();

//        deleteAllData();
//        prepareSampleData();
    }

    private void prepareSampleData() {
        for(int i = 0; i < 15; i++) {
            personRepository.saveOrUpdate(prepareSamplePerson());
        }
    }

    private void initialisePersonRepository() {
        MyDBApplicationComponent component = DaggerMyDBApplicationComponent.create();
        personRepository = component.getRealmPersonRepository();
    }

    private void initialiseRecyclerView() {
        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(personRepository.findAll());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initialiseRealm() {
        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);
    }

    private void initialiseStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
    }

    private Person prepareSamplePerson() {
        Faker faker = new Faker();

        return Person.builder()
                .pesel("12312312312")
                .name(faker.name().firstName())
                .surname(faker.name().lastName())
                .birthDate(new Date(603154800000L))
                .address(prepareSampleAddress(faker))
                .books(prepareSampleBooks(faker))
                .build();
    }

    private Address prepareSampleAddress(Faker faker) {
        return Address.builder()
                .country("Polska")
                .city("Wrocław")
                .postalCode("50-123")
                .street(faker.address().streetAddress())
                .build();
    }

    private List<Book> prepareSampleBooks(Faker faker) {
        return Arrays.asList(
                Book.builder().title("Dune").author(faker.name().fullName()).build(),
                Book.builder().title("Ringworld").author(faker.name().fullName()).build()
        );
    }

    private void deleteAllData() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<PersonEntity> persons = realm.where(PersonEntity.class).findAll();
        persons.forEach(person -> person.deleteFromRealm());
        RealmResults<AddressEntity> addresses = realm.where(AddressEntity.class).findAll();
        addresses.forEach(address -> address.deleteFromRealm());
        RealmResults<BookEntity> books = realm.where(BookEntity.class).findAll();
        books.forEach(book -> book.deleteFromRealm());
        realm.commitTransaction();
        realm.close();
    }
}
