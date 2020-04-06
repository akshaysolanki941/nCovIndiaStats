package com.akshay.ncovindiastats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.akshay.ncovindiastats.Adapters.RVAllCountriesAdapter;
import com.akshay.ncovindiastats.Adapters.RVStatesAdpater;
import com.akshay.ncovindiastats.Models.AllCountries.Example;
import com.akshay.ncovindiastats.Models.IndiaStates.Example2;
import com.akshay.ncovindiastats.Network.AllCountries.AllCountriesAPIClient;
import com.akshay.ncovindiastats.Network.AllCountries.AllCountriesAPIInterface;
import com.akshay.ncovindiastats.Network.IndiaStates.StatesAPIClient;
import com.akshay.ncovindiastats.Network.IndiaStates.StatesAPIInterface;

public class StateWiseActivity extends AppCompatActivity {

    private RecyclerView rv_states;
    private EditText et_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_wise);

        et_search = findViewById(R.id.et_search_state);
        rv_states = findViewById(R.id.rv_states);
        rv_states.setHasFixedSize(true);
        rv_states.setLayoutManager(new LinearLayoutManager(this));

        getStateWiseData();
    }

    private void getStateWiseData() {
        final StatesAPIInterface statesAPIInterface = StatesAPIClient.getClient().create(StatesAPIInterface.class);

        Call<Example2> call = statesAPIInterface.getStateWiseData();
        call.enqueue(new Callback<Example2>() {
            @Override
            public void onResponse(Call<Example2> call, Response<Example2> response) {
                Example2 mydata = response.body();
                final RVStatesAdpater adapter = new RVStatesAdpater(mydata, StateWiseActivity.this);
                adapter.notifyDataSetChanged();
                rv_states.setAdapter(adapter);

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
            public void onFailure(Call<Example2> call, Throwable t) {
                Toast.makeText(StateWiseActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
