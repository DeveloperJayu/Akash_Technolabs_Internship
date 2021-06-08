package com.jayu.akashtechnolabsinternship.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.jayu.akashtechnolabsinternship.R;
public class TemperatureConverterFragment extends Fragment {

    String[] fromArray = {"From", "Celsius °C", "Fahrenheit F", "Kelvin K"};
    Spinner spinnerFrom;
    Button btnCalculate;
    TextView spinnerError, answerCelsius, answerFahrenheit, answerKelvin;
    TextInputEditText txtTemperature;
    ScrollView answerScrollView;
    int position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_temperature_converter, container, false);
        requireActivity().setTitle("Temperature Converter");
        spinnerFrom = view.findViewById(R.id.spinnerFrom);
        btnCalculate = view.findViewById(R.id.btnCalculate);
        spinnerError = view.findViewById(R.id.spinnerError);
        answerCelsius = view.findViewById(R.id.answerCelsius);
        answerFahrenheit = view.findViewById(R.id.answerFahrenheit);
        answerKelvin = view.findViewById(R.id.answerKelvin);
        txtTemperature = view.findViewById(R.id.txtTemperature);
        answerScrollView = view.findViewById(R.id.answerScrollView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(),R.layout.spinner_text,fromArray);
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spinnerFrom.setAdapter(arrayAdapter);

        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = txtTemperature.getText().toString().trim();
                answerScrollView.setVisibility(View.GONE);
                if (temp.isEmpty()){
                    txtTemperature.setError("Please Enter a value");
                    txtTemperature.requestFocus();
                    return;
                }
                double temperature = Float.parseFloat(temp);
                if (position == 0){ //None selected
                    spinnerError.setVisibility(View.VISIBLE);
                    answerScrollView.setVisibility(View.GONE);
                }
                else if (position == 1){ //Celsius selected
                    spinnerError.setVisibility(View.GONE);
                    answerScrollView.setVisibility(View.VISIBLE);
                    double celsiusToFahrenheit = celsiusToFahrenheit(temperature);
                    double celsiusToKelvin = celsiusToKelvin(temperature);
                    answerCelsius.setText(String.valueOf(temperature));
                    answerFahrenheit.setText(String.valueOf(celsiusToFahrenheit));
                    answerKelvin.setText(String.valueOf(celsiusToKelvin));
                }
                else if (position == 2){ //Celsius selected
                    spinnerError.setVisibility(View.GONE);
                    answerScrollView.setVisibility(View.VISIBLE);
                    double fahrenheitToCelsius = fahrenheitToCelsius(temperature);
                    double fahrenheitToKelvin = celsiusToKelvin(fahrenheitToCelsius);
                    answerCelsius.setText(String.valueOf(fahrenheitToCelsius));
                    answerFahrenheit.setText(String.valueOf(temperature));
                    answerKelvin.setText(String.valueOf(fahrenheitToKelvin));
                }
                else if (position == 3){ //Celsius selected
                    spinnerError.setVisibility(View.GONE);
                    answerScrollView.setVisibility(View.VISIBLE);
                    double kelvinToCelsius = kelvinToCelsius(temperature);
                    double kelvinToFahrenheit = celsiusToFahrenheit(kelvinToCelsius);
                    answerCelsius.setText(String.valueOf(kelvinToCelsius));
                    answerFahrenheit.setText(String.valueOf(kelvinToFahrenheit));
                    answerKelvin.setText(String.valueOf(temperature));
                }
            }

            private double kelvinToCelsius(double temperature) {
                return temperature - 273.15;
            }

            private double fahrenheitToCelsius(double temperature) {
                return (temperature - 32)*5/9;
            }

            private double celsiusToFahrenheit(double temperature) {
                return (temperature * 9/5) + 32;
            }

            private double celsiusToKelvin(double temperature) {
                return temperature + 273.15;
            }

        });

        return view;
    }
}