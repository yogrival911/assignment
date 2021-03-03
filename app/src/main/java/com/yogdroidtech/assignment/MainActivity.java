package com.yogdroidtech.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;
import android.widget.TextView;

import com.yogdroidtech.assignment.response.ItemsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private TextView item11, item12, item13, allowedQuant1,selectedQuant1, remainingQuant1, choose;
    private Spinner spinner11,spinner12, spinner13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        choose = findViewById(R.id.choose);

        item11 = findViewById(R.id.item11);
        item12 = findViewById(R.id.item12);
        item13 = findViewById(R.id.item13);

        spinner11 = findViewById(R.id.spinner11);
        spinner12 = findViewById(R.id.spinner12);
        spinner13 = findViewById(R.id.spinner13);

        allowedQuant1 = findViewById(R.id.allowedQuant1);
        selectedQuant1 = findViewById(R.id.selectedQuant1);
        remainingQuant1 = findViewById(R.id.remainingQuant1);

        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        retrofitInterface.getItems().enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {
                ItemsResponse itemsResponse = response.body();
                Log.i("responseyog", response.body().getMessage());
                choose.setText(response.body().getData().get(0).getName());
                item11.setText(response.body().getData().get(0).getItemChoice().get(0).getName());
                item12.setText(response.body().getData().get(0).getItemChoice().get(1).getName());
                item13.setText(response.body().getData().get(0).getItemChoice().get(2).getName());

            }

            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {

            }
        });
    }
}