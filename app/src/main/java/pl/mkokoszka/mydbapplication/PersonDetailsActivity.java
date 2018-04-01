package pl.mkokoszka.mydbapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import pl.mkokoszka.mydbapplication.domain.Person;

public class PersonDetailsActivity extends AppCompatActivity {

    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);
        person = (Person) getIntent().getSerializableExtra("Person");
        TextView name = findViewById(R.id.etName);
        name.setText(person.getName() + " " + person.getSurname());
    }
}
