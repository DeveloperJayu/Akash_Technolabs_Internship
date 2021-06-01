package com.jayu.akashtechnolabsinternship.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.jayu.akashtechnolabsinternship.R;
import com.jayu.akashtechnolabsinternship.adapters.PrimeIntervalRecyclerAdapter;
import java.util.ArrayList;
import java.util.Arrays;

public class PrimeNumberCheckerFragment extends Fragment {

    Button btnSingleNumber,btnNumberInterval, btnSingleNumberSubmit, btnNumberIntervalSubmit;
    TextInputEditText txtSingleNumber, txtNumberInterval1, txtNumberInterval2;
    TextView txtSingleNumberAnswer, txtNumberIntervalLabel;
    RelativeLayout singleNumberRelative, numberIntervalRelative;
    String[] primeIntervalArray;
    ArrayList<String> primeIntervalArrayList;
    boolean isPrime = false;
    RecyclerView primeIntervalRecyclerView;
    LinearLayoutManager primeIntervalLayoutManager;
    PrimeIntervalRecyclerAdapter primeIntervalRecyclerAdapter;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_prime_number_checker, container, false);

        btnSingleNumber = view.findViewById(R.id.btnSingleNumber);
        btnNumberInterval = view.findViewById(R.id.btnNumberInterval);
        btnNumberIntervalSubmit = view.findViewById(R.id.btnNumberIntervalSubmit);
        btnSingleNumberSubmit = view.findViewById(R.id.btnSingleNumberSubmit);
        txtSingleNumber = view.findViewById(R.id.txtSingleNumber);
        txtSingleNumberAnswer = view.findViewById(R.id.txtSingleNumberAnswer);
        txtNumberInterval1 = view.findViewById(R.id.txtNumberInterval1);
        txtNumberInterval2 = view.findViewById(R.id.txtNumberInterval2);
        singleNumberRelative = view.findViewById(R.id.singleNumberRelative);
        numberIntervalRelative = view.findViewById(R.id.numberIntervalRelative);
        primeIntervalRecyclerView = view.findViewById(R.id.primeIntervalRecyclerView);
        txtNumberIntervalLabel = view.findViewById(R.id.txtNumberIntervalLabel);

        btnSingleNumber.setOnClickListener(view1 -> {
            clearTextFields();
            btnSingleNumber.setBackgroundResource(R.drawable.custom_button_clicked);
            btnSingleNumber.setTextColor(getResources().getColor(R.color.colorWhite));
            btnNumberInterval.setBackgroundResource(R.drawable.custom_button);
            btnNumberInterval.setTextColor(getResources().getColor(R.color.colorPrimary));
            singleNumberRelative.setVisibility(View.VISIBLE);
            numberIntervalRelative.setVisibility(View.GONE);
        });

        btnNumberInterval.setOnClickListener(view12 -> {
            clearTextFields();
            btnNumberInterval.setBackgroundResource(R.drawable.custom_button_clicked);
            btnNumberInterval.setTextColor(getResources().getColor(R.color.colorWhite));
            btnSingleNumber.setBackgroundResource(R.drawable.custom_button);
            btnSingleNumber.setTextColor(getResources().getColor(R.color.colorPrimary));
            singleNumberRelative.setVisibility(View.GONE);
            numberIntervalRelative.setVisibility(View.VISIBLE);
        });

        btnSingleNumberSubmit.setOnClickListener(view13 -> {
            isPrime = false;
            String strSingleNumber = txtSingleNumber.getText().toString().trim();

            if (strSingleNumber.isEmpty()){
                txtSingleNumber.setError("Please enter number");
                txtSingleNumber.requestFocus();
                return;
            }
            int intSingleNumber = Integer.parseInt(strSingleNumber);

            for (int i = 2; i<intSingleNumber; i++){
                if (intSingleNumber % i == 0) {
                    isPrime = true;
                    break;
                }
            }

            if (isPrime){
                txtSingleNumberAnswer.setText(intSingleNumber + " is not prime number");
                txtSingleNumberAnswer.setTextColor(getResources().getColor(R.color.colorRed));
            }
            else {
                txtSingleNumberAnswer.setText(intSingleNumber + " is prime number");
                txtSingleNumberAnswer.setTextColor(getResources().getColor(R.color.colorGreen));
            }
        });

        btnNumberIntervalSubmit.setOnClickListener(view14 -> {
            String strFirstNumber = txtNumberInterval1.getText().toString().trim();
            String strSecondNumber = txtNumberInterval2.getText().toString().trim();

            if (strFirstNumber.isEmpty()){
                txtNumberInterval1.setError("Please enter number");
                txtNumberInterval1.requestFocus();
                return;
            }
            else if (strSecondNumber.isEmpty()){
                txtNumberInterval2.setError("Please enter number");
                txtNumberInterval2.requestFocus();
                return;
            }

            int intFirstNumber = Integer.parseInt(strFirstNumber);
            int intSecondNumber = Integer.parseInt(strSecondNumber);

            if (intFirstNumber > intSecondNumber){
                Toast.makeText(getActivity().getApplicationContext(),"First number must be less than or equals to Second number",Toast.LENGTH_LONG).show();
                return;
            }

            int counter = 0;
            for (int i = intFirstNumber; i<= intSecondNumber; i++){
                isPrime = false;
                for (int j = 2; j < i; j++){
                    if (i % j == 0){
                        isPrime = true;
                        break;
                    }
                }
                if (!isPrime){
                    counter++;
                }
            }
            primeIntervalArray = new String[counter];
            primeIntervalArrayList = new ArrayList<>(Arrays.asList(primeIntervalArray));
            primeIntervalArrayList.clear();

            for (int i = intFirstNumber; i<= intSecondNumber; i++){
                isPrime = false;
                for (int j = 2; j < i; j++){
                    if (i % j == 0){
                        isPrime = true;
                        break;
                    }
                }
                if (!isPrime){
                    primeIntervalArrayList.add(String.valueOf(i));
                }
            }
            txtNumberIntervalLabel.setVisibility(View.VISIBLE);
            txtNumberIntervalLabel.setText("Total numbers of prime numbers: " + counter);
            primeIntervalArray = primeIntervalArrayList.toArray(primeIntervalArray);
            primeIntervalLayoutManager = new LinearLayoutManager(getActivity());
            primeIntervalRecyclerAdapter = new PrimeIntervalRecyclerAdapter(primeIntervalArray);
            primeIntervalRecyclerView.setAdapter(primeIntervalRecyclerAdapter);
            primeIntervalRecyclerView.setLayoutManager(primeIntervalLayoutManager);

        });

        return view;
    }

    private void clearTextFields() {
        txtSingleNumber.setText("");
        txtSingleNumberAnswer.setText("");
        txtNumberInterval1.setText("");
        txtNumberInterval2.setText("");
        primeIntervalRecyclerView.swapAdapter(primeIntervalRecyclerAdapter,true);
        primeIntervalRecyclerView.setLayoutManager(null);
        txtNumberIntervalLabel.setVisibility(View.GONE);
    }
}