package com.jayu.akashtechnolabsinternship.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.jayu.akashtechnolabsinternship.R;

public class HomeFragment extends Fragment{

    Button btnBMICalculator, btnPrimeNumberChecker, btnNumberConverter, btnCreditCardChecker, btnTemperatureConverter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        requireActivity().setTitle("Home");

        btnBMICalculator = view.findViewById(R.id.btnBMICalculator);
        btnPrimeNumberChecker = view.findViewById(R.id.btnPrimeNumberChecker);
        btnNumberConverter = view.findViewById(R.id.btnNumberConverter);
        btnCreditCardChecker = view.findViewById(R.id.btnCreditCardChecker);
        btnTemperatureConverter = view.findViewById(R.id.btnTemperatureConverter);

        btnBMICalculator.setOnClickListener(view1 -> requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout,new BMICalculatorFragment())
                .addToBackStack("BMI Calculator")
                .commit());

        btnPrimeNumberChecker.setOnClickListener(view12 -> requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout,new PrimeNumberCheckerFragment())
                .addToBackStack("Prime Number")
                .commit());

        btnNumberConverter.setOnClickListener(view13 -> requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout,new NumberConverterFragment())
                .addToBackStack("Number Converter")
                .commit());

        btnCreditCardChecker.setOnClickListener(view14 -> requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout,new CreditCardCheckerFragment())
                .addToBackStack("Credit Card Checker")
                .commit());

        btnTemperatureConverter.setOnClickListener(view15 -> requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout,new TemperatureConverterFragment())
                .addToBackStack("Temperature Converter")
                .commit());

        return view;
    }
}