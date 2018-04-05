package pl.mkokoszka.mydbapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import pl.mkokoszka.mydbapplication.component.DaggerMyDBApplicationComponent;
import pl.mkokoszka.mydbapplication.component.MyDBApplicationComponent;
import pl.mkokoszka.mydbapplication.domain.Address;
import pl.mkokoszka.mydbapplication.domain.Person;
import pl.mkokoszka.mydbapplication.repository.PersonRepository;

public class PersonDetailsActivity extends AppCompatActivity {

    private Person person;

    private PersonRepository personRepository;

    @BindView(R.id.etName)
    EditText name;

    @BindView(R.id.etSurname)
    EditText surname;

    @BindView(R.id.etPesel)
    EditText pesel;

    @BindView(R.id.etBirthDate)
    EditText birthDate;

    @BindView(R.id.etCountry)
    EditText country;

    @BindView(R.id.etCity)
    EditText city;

    @BindView(R.id.etStreet)
    EditText street;

    @BindView(R.id.etPostalCode)
    EditText postalCode;

    @BindView(R.id.btnEditPerson)
    Button editPerson;

    @BindView(R.id.btnSavePerson)
    Button savePerson;

    @BindView(R.id.btnDeletePerson)
    Button deletePerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);
        ButterKnife.bind(this);

        person = (Person) getIntent().getSerializableExtra("Person");
        if (person == null) {
            person = Person.builder().address(Address.builder().build()).build();
            makeEditable(true);
        } else {
            fillPersonForm();
            makeEditable(false);
        }

        initialisePersonRepository();
    }

    private void fillPersonForm() {
        name.setText(person.getName());
        surname.setText(person.getSurname());
        pesel.setText(person.getPesel());
        birthDate.setText(birthDateToText());
        country.setText(person.getAddress().getCountry());
        city.setText(person.getAddress().getCity());
        street.setText(person.getAddress().getStreet());
        postalCode.setText(person.getAddress().getPostalCode());
    }

    private String birthDateToText() {
        if (person.getBirthDate() != null) {
            Calendar bd = Calendar.getInstance();
            bd.setTime(person.getBirthDate());
            return bd.get(Calendar.DAY_OF_MONTH) + "-" + (bd.get(Calendar.MONTH) + 1) + "-" + bd.get(Calendar.YEAR);
        }
        return null;
    }

    private void initialisePersonRepository() {
        MyDBApplicationComponent component = DaggerMyDBApplicationComponent.create();
        personRepository = component.getRealmPersonRepository();
    }

    @OnClick(R.id.btnEditPerson)
    public void makeFormEditable(View view) {
        makeEditable(true);
    }

    @OnClick(R.id.btnSavePerson)
    public void savePersonAndMakeFormNotEditable(View view) {
        updatePersonFromForm();
        try {
            personRepository.saveOrUpdate(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
        makeEditable(false);
    }

    @OnLongClick(R.id.btnDeletePerson)
    public boolean deletePersonAndFinish() {
        personRepository.delete(person);
        finish();
        return false;
    }

    private void updatePersonFromForm() {
        person.setName(name.getText().toString());
        person.setSurname(surname.getText().toString());
        person.setPesel(pesel.getText().toString());
        person.getAddress().setCountry(country.getText().toString());
        person.getAddress().setCity(city.getText().toString());
        person.getAddress().setStreet(street.getText().toString());
        person.getAddress().setPostalCode(postalCode.getText().toString());
    }

    private void makeEditable(boolean editable) {
        List<EditText> editTexts = Arrays.asList(name, surname, pesel, country, city, street, postalCode);
        editTexts.forEach(et -> setEditability(et, editable));
    }

    private void setEditability(EditText editText, boolean editable) {
        editText.setFocusable(editable);
        editText.setEnabled(editable);
        editText.setFocusableInTouchMode(editable);
        if (editable) {
            editPerson.setVisibility(View.GONE);
            deletePerson.setVisibility(View.GONE);
            savePerson.setVisibility(View.VISIBLE);
        } else {
            savePerson.setVisibility(View.GONE);
            deletePerson.setVisibility(View.VISIBLE);
            editPerson.setVisibility(View.VISIBLE);
        }
    }

}
