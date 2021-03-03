package com.yogdroidtech.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.yogdroidtech.assignment.response.ItemsResponse;

import java.util.stream.IntStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private TextView item11, item12, item13, allowedQuant1,selectedQuant1, remainingQuant1, choose;
    private Spinner spinner11,spinner12, spinner13;

    private TextView item21, item22, item23, allowedQuant2,selectedQuant2, remainingQuant2, choose2;
    private Spinner spinner21,spinner22, spinner23;

    private ProgressBar progressBar;
    ConstraintLayout conLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        choose = findViewById(R.id.choose);
        choose2 = findViewById(R.id.choose2);
        progressBar = findViewById(R.id.progressBar);
        conLayout = findViewById(R.id.conLayout);

        item11 = findViewById(R.id.item11);
        item12 = findViewById(R.id.item12);
        item13 = findViewById(R.id.item13);

        spinner11 = findViewById(R.id.spinner11);
        spinner12 = findViewById(R.id.spinner12);
        spinner13 = findViewById(R.id.spinner13);


        allowedQuant1 = findViewById(R.id.allowedQuant1);
        selectedQuant1 = findViewById(R.id.selectedQuant1);
        remainingQuant1 = findViewById(R.id.remainingQuant1);

//=================================

        item21 = findViewById(R.id.item21);
        item22 = findViewById(R.id.item22);
        item23 = findViewById(R.id.item23);

        spinner21 = findViewById(R.id.spinner21);
        spinner22 = findViewById(R.id.spinner22);
        spinner23 = findViewById(R.id.spinner23);

        allowedQuant2 = findViewById(R.id.allowedQuant2);
        selectedQuant2 = findViewById(R.id.selectedQuant2);
        remainingQuant2 = findViewById(R.id.remainingQuant2);
//        ------------------------------------------
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        retrofitInterface.getItems().enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {
                ItemsResponse itemsResponse = response.body();
                Log.i("responseyog", response.body().getMessage());

                progressBar.clearAnimation();
                progressBar.setVisibility(View.GONE);
                conLayout.setVisibility(View.VISIBLE);

                int maxQuantity = Integer.parseInt(response.body().getData().get(0).getMaxQty());
                int maxQuantity2 = Integer.parseInt(response.body().getData().get(1).getMaxQty());

                Log.i("maxQantyog", String.valueOf(maxQuantity));
                choose.setText(response.body().getData().get(0).getName());
                item11.setText(response.body().getData().get(0).getItemChoice().get(0).getName());
                item12.setText(response.body().getData().get(0).getItemChoice().get(1).getName());
                item13.setText(response.body().getData().get(0).getItemChoice().get(2).getName());
                allowedQuant1.setText(maxQuantity+"");

                choose2.setText(response.body().getData().get(1).getName());
                item21.setText(response.body().getData().get(1).getItemChoice().get(0).getName());
                item22.setText(response.body().getData().get(1).getItemChoice().get(1).getName());
                item23.setText(response.body().getData().get(1).getItemChoice().get(2).getName());
                allowedQuant2.setText(maxQuantity2+"");

                int[] spinnerMaxValue2 = new int[maxQuantity2];
                for(int i = 0; i<maxQuantity2; i++){
                    spinnerMaxValue2[i]=i+1;
                }
                Log.i("array", spinnerMaxValue2.toString());

                String[] spinnerMaxValueString2 = new String[maxQuantity2];
                for(int i = 0; i<maxQuantity2; i++){
                    spinnerMaxValueString2[i]=i+1+"";
                }

                ArrayAdapter arrayAdapter2 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, spinnerMaxValueString2);
                arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner21.setAdapter(arrayAdapter2);
                spinner22.setAdapter(arrayAdapter2);
                spinner23.setAdapter(arrayAdapter2);




                int[] spinnerMaxValue = new int[maxQuantity];
                for(int i = 0; i<maxQuantity; i++){
                    spinnerMaxValue[i]=i+1;
                }
                Log.i("array", spinnerMaxValue.toString());

                String[] spinnerMaxValueString = new String[maxQuantity];
                for(int i = 0; i<maxQuantity; i++){
                    spinnerMaxValueString[i]=i+1+"";
                }

                ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, spinnerMaxValueString);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner11.setAdapter(arrayAdapter);
                spinner12.setAdapter(arrayAdapter);
                spinner13.setAdapter(arrayAdapter);

                String spinner1text = spinner11.getSelectedItem().toString();
                String spinner2text = spinner12.getSelectedItem().toString();
                String spinner3text = spinner13.getSelectedItem().toString();

                int spinner1int = Integer.parseInt(spinner1text);
                int spinner2int = Integer.parseInt(spinner2text);
                int spinner3int = Integer.parseInt(spinner3text);

                int totalSpinnerVal = spinner1int+spinner2int+spinner3int;
                remainingQuant1.setText(maxQuantity-totalSpinnerVal+"");

                spinner21.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String selectedValue = arrayAdapter2.getItem(i).toString();
                        String spinner2text = spinner22.getSelectedItem().toString();
                        String spinner3text = spinner23.getSelectedItem().toString();

                        int spinner1int = Integer.parseInt(selectedValue);
                        int spinner2int = Integer.parseInt(spinner2text);
                        int spinner3int = Integer.parseInt(spinner3text);

                        int totalSpinnerVal = spinner1int+spinner2int+spinner3int;
                        int remainingQuant = maxQuantity2 - totalSpinnerVal;

                        if(remainingQuant<=0){
                            remainingQuant2.setText("0");
                        }
                        else {
                            remainingQuant2.setText(maxQuantity2-totalSpinnerVal+"");

                        }
                        selectedQuant2.setText(totalSpinnerVal+"");

                        Log.i("selected", selectedValue+"-"+spinner2text+"-"+spinner3text);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                spinner22.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String selectedValue = arrayAdapter2.getItem(i).toString();
                        String spinner2text = spinner22.getSelectedItem().toString();
                        String spinner3text = spinner23.getSelectedItem().toString();

                        int spinner1int = Integer.parseInt(selectedValue);
                        int spinner2int = Integer.parseInt(spinner2text);
                        int spinner3int = Integer.parseInt(spinner3text);

                        int totalSpinnerVal = spinner1int+spinner2int+spinner3int;
                        int remainingQuant = maxQuantity2 - totalSpinnerVal;

                        if(remainingQuant<=0){
                            remainingQuant2.setText("0");
                        }
                        else {
                            remainingQuant2.setText(maxQuantity2-totalSpinnerVal+"");

                        }
                        selectedQuant2.setText(totalSpinnerVal+"");

                        Log.i("selected", selectedValue+"-"+spinner2text+"-"+spinner3text);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                spinner23.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String selectedValue = arrayAdapter2.getItem(i).toString();
                        String spinner2text = spinner22.getSelectedItem().toString();
                        String spinner3text = spinner23.getSelectedItem().toString();

                        int spinner1int = Integer.parseInt(selectedValue);
                        int spinner2int = Integer.parseInt(spinner2text);
                        int spinner3int = Integer.parseInt(spinner3text);

                        int totalSpinnerVal = spinner1int+spinner2int+spinner3int;
                        int remainingQuant = maxQuantity2 - totalSpinnerVal;

                        if(remainingQuant<=0){
                            remainingQuant2.setText("0");
                        }
                        else {
                            remainingQuant2.setText(maxQuantity2-totalSpinnerVal+"");

                        }
                        selectedQuant2.setText(totalSpinnerVal+"");

                        Log.i("selected", selectedValue+"-"+spinner2text+"-"+spinner3text);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });




                spinner11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String selectedValue = arrayAdapter.getItem(i).toString();
                        String spinner2text = spinner12.getSelectedItem().toString();
                        String spinner3text = spinner13.getSelectedItem().toString();

                        int spinner1int = Integer.parseInt(selectedValue);
                        int spinner2int = Integer.parseInt(spinner2text);
                        int spinner3int = Integer.parseInt(spinner3text);

                        int totalSpinnerVal = spinner1int+spinner2int+spinner3int;
                        int remainingQuant = maxQuantity - totalSpinnerVal;

                        if(remainingQuant<=0){
                            remainingQuant1.setText("0");
                        }
                        else {
                            remainingQuant1.setText(maxQuantity-totalSpinnerVal+"");

                        }
                        selectedQuant1.setText(totalSpinnerVal+"");

                        Log.i("selected", selectedValue+"-"+spinner2text+"-"+spinner3text);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                spinner12.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String selectedValue = arrayAdapter.getItem(i).toString();
                        String spinner1text = spinner11.getSelectedItem().toString();
                        String spinner3text = spinner13.getSelectedItem().toString();

                        int spinner2int = Integer.parseInt(selectedValue);
                        int spinner1int = Integer.parseInt(spinner1text);
                        int spinner3int = Integer.parseInt(spinner3text);

                        int totalSpinnerVal = spinner1int+spinner2int+spinner3int;

                        selectedQuant1.setText(totalSpinnerVal+"");
                        int remainingQuant = maxQuantity - totalSpinnerVal;

                        if(remainingQuant<=0){
                            remainingQuant1.setText("0");
                        }
                        else {
                            remainingQuant1.setText(maxQuantity-totalSpinnerVal+"");
                        }
                        Log.i("selected", selectedValue+"-"+spinner1text+"-"+spinner3text);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                spinner13.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String selectedValue = arrayAdapter.getItem(i).toString();
                        String spinner1text = spinner11.getSelectedItem().toString();
                        String spinner2text = spinner12.getSelectedItem().toString();

                        int spinner3int = Integer.parseInt(selectedValue);
                        int spinner1int = Integer.parseInt(spinner1text);
                        int spinner2int = Integer.parseInt(spinner2text);

                        int totalSpinnerVal = spinner1int+spinner2int+spinner3int;

                        selectedQuant1.setText(totalSpinnerVal+"");
                        int remainingQuant = maxQuantity - totalSpinnerVal;

                        if(remainingQuant<=0){
                            remainingQuant1.setText("0");
                        }
                        else {
                            remainingQuant1.setText(maxQuantity-totalSpinnerVal+"");

                        }
                        Log.i("selected", selectedValue+"-"+spinner1text+"-"+spinner2text);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

            }

            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {

            }
        });
    }
}