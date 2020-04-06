package com.akshay.ncovindiastats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.akshay.ncovindiastats.Adapters.RVAllCountriesAdapter;
import com.akshay.ncovindiastats.Adapters.RVContactsAdapter;
import com.akshay.ncovindiastats.Models.AllCountries.Example;
import com.akshay.ncovindiastats.Models.Numbers.Example3;
import com.akshay.ncovindiastats.Network.AllCountries.AllCountriesAPIClient;
import com.akshay.ncovindiastats.Network.AllCountries.AllCountriesAPIInterface;
import com.akshay.ncovindiastats.Network.Numbers.NumberAPIClient;
import com.akshay.ncovindiastats.Network.Numbers.NumberAPIInterface;

public class NumbersActivity extends AppCompatActivity {

    private TextView tv_number, tv_tollfree_number, tv_email, tv_twitter, tv_fb;
    private EditText et_search;
    private RecyclerView rv_contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        tv_number = findViewById(R.id.tv_number);
        tv_tollfree_number = findViewById(R.id.tv_tollfree_number);
        tv_email = findViewById(R.id.tv_email);
        tv_twitter = findViewById(R.id.tv_twitter);
        tv_fb = findViewById(R.id.tv_fb);
        et_search = findViewById(R.id.et_search);
        rv_contacts = findViewById(R.id.rv_contacts);
        rv_contacts.setHasFixedSize(true);
        rv_contacts.setLayoutManager(new LinearLayoutManager(this));

        getContacts();
    }

    private void getContacts() {
        final ProgressDialog dialog = ProgressDialog.show(NumbersActivity.this, "", "Loading. Please wait....", true);

        final NumberAPIInterface numberAPIInterface = NumberAPIClient.getClient().create(NumberAPIInterface.class);

        Call<Example3> call = numberAPIInterface.getContacts();
        call.enqueue(new Callback<Example3>() {
            @Override
            public void onResponse(Call<Example3> call, Response<Example3> response) {
                Example3 mydata = response.body();
                final RVContactsAdapter adapter = new RVContactsAdapter(mydata, NumbersActivity.this);
                adapter.notifyDataSetChanged();
                rv_contacts.setAdapter(adapter);

                tv_number.setText("Number:-   " + mydata.getData().getContacts().getPrimary().getNumber());
                tv_tollfree_number.setText("Toll Free Number:-   " + mydata.getData().getContacts().getPrimary().getNumberTollfree());
                tv_email.setText("Email:-   " + mydata.getData().getContacts().getPrimary().getEmail());
                tv_twitter.setText("Twitter:-   " + mydata.getData().getContacts().getPrimary().getTwitter());
                tv_fb.setText("Facebook:-   " + mydata.getData().getContacts().getPrimary().getFacebook());

                et_search.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        adapter.getFilter().filter(charSequence.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<Example3> call, Throwable t) {
                Toast.makeText(NumbersActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
    }
}
