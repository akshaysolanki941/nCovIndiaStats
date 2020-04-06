package com.akshay.ncovindiastats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.akshay.ncovindiastats.Adapters.RVAllCountriesAdapter;
import com.akshay.ncovindiastats.Models.AllCountries.Example;
import com.akshay.ncovindiastats.Network.AllCountries.AllCountriesAPIClient;
import com.akshay.ncovindiastats.Network.AllCountries.AllCountriesAPIInterface;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv_countries;
    private Button btn_india_states;
    private EditText et_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_india_states = findViewById(R.id.btn_india_states);
        et_search = findViewById(R.id.et_search);
        rv_countries = findViewById(R.id.rv_countries);
        rv_countries.setHasFixedSize(true);
        rv_countries.setLayoutManager(new LinearLayoutManager(this));

        btn_india_states.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInternetConnected())
                    startActivity(new Intent(MainActivity.this, StateWiseActivity.class));
                else
                    Toast.makeText(MainActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
            }
        });

        if (isInternetConnected())
            getAllCountriesData();
        else
            Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show();

    }

    private void getAllCountriesData() {
        final AllCountriesAPIInterface allCountriesAPIInterface = AllCountriesAPIClient.getClient().create(AllCountriesAPIInterface.class);

        Call<Example> call = allCountriesAPIInterface.getAllCountriesData();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example mydata = response.body();
                final RVAllCountriesAdapter adapter = new RVAllCountriesAdapter(mydata, MainActivity.this);
                adapter.notifyDataSetChanged();
                rv_countries.setAdapter(adapter);

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
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean isInternetConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return true;
        } else
            return false;
    }
}
