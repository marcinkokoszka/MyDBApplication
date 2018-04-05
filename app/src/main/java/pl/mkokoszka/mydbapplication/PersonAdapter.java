package pl.mkokoszka.mydbapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pl.mkokoszka.mydbapplication.domain.Address;
import pl.mkokoszka.mydbapplication.domain.Person;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    private List<Person> persons;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView address;

        public ViewHolder(View v, TextView name, TextView address) {
            super(v);
            this.name = name;
            this.address = address;
        }
    }

    public PersonAdapter(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.custom_list_layout, parent, false);
        TextView customListLayoutTitle = v.findViewById(R.id.customListLayoutTitle);
        TextView customListLayoutAuthor = v.findViewById(R.id.customListLayoutAuthor);
        return new ViewHolder(v, customListLayoutTitle, customListLayoutAuthor);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Person person = persons.get(position);
        holder.name.setText(person.getName() + " " + person.getSurname());
        Address address = person.getAddress();
        holder.address.setText(address.getCountry() + ", "
                + address.getCity() + ", "
                + address.getPostalCode() + ", "
                + address.getStreet());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext() , PersonDetailsActivity.class);
            intent.putExtra("Person", person);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }
}