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
import org.jetbrains.annotations.NotNull;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberConverterFragment extends Fragment {

    String[] fromArray = {"From","Binary","Octal","Decimal","Hexadecimal"}, hexArray = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
    Spinner spinnerFrom;
    int position;
    Button btnCalculate;
    TextView spinnerError,answerBinary, answerOctal, answerDecimal, answerHexadecimal;
    TextInputEditText txtNumber;
    ScrollView answerScrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_number_converter, container, false);
        requireActivity().setTitle("Number Converter");
        spinnerFrom = view.findViewById(R.id.spinnerFrom);
        btnCalculate = view.findViewById(R.id.btnCalculate);
        spinnerError = view.findViewById(R.id.spinnerError);
        txtNumber = view.findViewById(R.id.txtNumber);
        answerBinary = view.findViewById(R.id.answerBinary);
        answerOctal = view.findViewById(R.id.answerOctal);
        answerDecimal = view.findViewById(R.id.answerDecimal);
        answerHexadecimal = view.findViewById(R.id.answerHexadecimal);
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

        btnCalculate.setOnClickListener(view1 -> {
            String strNumber = Objects.requireNonNull(txtNumber.getText()).toString().trim();
            answerScrollView.setVisibility(View.GONE);
            if (strNumber.isEmpty()){
                txtNumber.setError("Please Enter a value");
                txtNumber.requestFocus();
                return;
            }
            if (position == 0){ //Null
                spinnerError.setVisibility(View.VISIBLE);
            }

            else if (position == 1){ //Binary
                long intNumber = Long.parseLong(strNumber);
                spinnerError.setVisibility(View.GONE);
                answerScrollView.setVisibility(View.VISIBLE);
                if (validBinary(strNumber)){
                    long binaryToDecimal = getBinaryToDecimal(intNumber);
                    long decimalToOctal = getDecimalToOctal(binaryToDecimal);
                    String decimalToHexadecimal = getDecimalToHexadecimal(binaryToDecimal);
                    answerBinary.setText(strNumber);
                    answerDecimal.setText(String.valueOf(binaryToDecimal));
                    answerOctal.setText(String.valueOf(decimalToOctal));
                    answerHexadecimal.setText(decimalToHexadecimal);
                }
                else {
                    txtNumber.setError("Please enter valid binary digits");
                    txtNumber.requestFocus();
                }
            }

            else if (position == 2){ //Octal
                long intNumber = Long.parseLong(strNumber);
                spinnerError.setVisibility(View.GONE);
                answerScrollView.setVisibility(View.VISIBLE);
                if (validOctal(strNumber)){
                    String octalToBinary = getOctalToBinary(strNumber);
                    long octalToDecimal = getOctalToDecimal(intNumber);
                    String octalToHexadecimal = getDecimalToHexadecimal(octalToDecimal);
                    answerBinary.setText(octalToBinary);
                    answerOctal.setText(strNumber);
                    answerDecimal.setText(String.valueOf(octalToDecimal));
                    answerHexadecimal.setText(octalToHexadecimal);
                }
                else {
                    txtNumber.setError("Please enter valid octal digits");
                    txtNumber.requestFocus();
                }
            }

            else if (position == 3){ //Decimal
                long intNumber = Long.parseLong(strNumber);
                spinnerError.setVisibility(View.GONE);
                answerScrollView.setVisibility(View.VISIBLE);
                if (validDecimal(strNumber)){
                    String decimalToBinary = getDecimalToBinary(intNumber);
                    long decimalToOctal = getDecimalToOctal(intNumber);
                    String decimalToHexadecimal = getDecimalToHexadecimal(intNumber);
                    answerBinary.setText(decimalToBinary);
                    answerOctal.setText(String.valueOf(decimalToOctal));
                    answerDecimal.setText(strNumber);
                    answerHexadecimal.setText(decimalToHexadecimal);
                }
                else {
                    txtNumber.setError("Please enter valid octal digits");
                    txtNumber.requestFocus();
                }
            }

            else if (position == 4){ //Hexadecimal
                spinnerError.setVisibility(View.GONE);
                answerScrollView.setVisibility(View.VISIBLE);
                String upperStrNumber =strNumber.toUpperCase();
                if (validHexadecimal(upperStrNumber)){
                    long getHexadecimalToDecimal = getHexadecimalToDecimal(upperStrNumber);
                    String getDecimalToBinary = getDecimalToBinary(getHexadecimalToDecimal);
                    long getDecimalToOctal = getDecimalToOctal(getHexadecimalToDecimal);
                    answerBinary.setText(getDecimalToBinary);
                    answerOctal.setText(String.valueOf(getDecimalToOctal));
                    answerDecimal.setText(String.valueOf(getHexadecimalToDecimal));
                    answerHexadecimal.setText(upperStrNumber);
                }
                else {
                    txtNumber.setError("Please enter valid octal digits");
                    txtNumber.requestFocus();
                }
            }

        });

        return view;
    }

    private int getHexadecimalToDecimal(@NotNull String upperStrNumber) {

        String digits = "0123456789ABCDEF";
        int val = 0;

        for (int i = 0; i < upperStrNumber.length(); i++){
            char c = upperStrNumber.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }

        return val;
    }

    private boolean validBinary(String strNumber) {
        String regex = "[0-1]+";
        Pattern pattern = Pattern.compile(regex);

        Matcher m = pattern.matcher(strNumber);
        return m.matches();
    }

    private boolean validOctal(String strNumber) {
        String regex = "[0-7]+";
        Pattern pattern = Pattern.compile(regex);

        Matcher m = pattern.matcher(strNumber);
        return m.matches();
    }

    private boolean validDecimal(String strNumber) {
        String regex = "[0-9]+";
        Pattern pattern = Pattern.compile(regex);

        Matcher m = pattern.matcher(strNumber);
        return m.matches();
    }

    private boolean validHexadecimal(String strNumber) {
        String regex = "[0-9A-F]+";
        Pattern pattern = Pattern.compile(regex);

        Matcher m = pattern.matcher(strNumber);
        return m.matches();
    }

    private int getBinaryToDecimal(long binary) {
        int decimal = 0;
        int n = 0;
        while(binary!=0){
            long temp = binary%10;
            decimal += temp*Math.pow(2, n);
            binary = binary/10;
            n++;
        }
        return decimal;
    }

    private String getDecimalToBinary(long intNumber) {
        long rem;
        StringBuilder binary = new StringBuilder();

        while (intNumber != 0){
            rem = intNumber % 2;
            intNumber /= 2;
            binary.insert(0, rem);
        }

        return binary.toString();
    }

    private int getDecimalToOctal(long decimalNumber) {
        int octalNumber = 0,i = 1;
        while (decimalNumber != 0) {
            octalNumber += (decimalNumber % 8) * i;
            decimalNumber /= 8;
            i *= 10;
        }
        return octalNumber;
    }

    private String getDecimalToHexadecimal(long decimalNumber) {
        long rem;
        StringBuilder hexNumber = new StringBuilder();
        while (decimalNumber != 0) {
            rem = (decimalNumber % 16);
            int intRem = Integer.parseInt(String.valueOf(rem));
            hexNumber.insert(0, hexArray[intRem]);
            decimalNumber = decimalNumber / 16;
        }
        return hexNumber.toString();
    }

    private String getOctalToBinary(@NotNull String strNumber) {
        int i = 0;
        StringBuilder binaryValue = new StringBuilder();
        while (i<strNumber.length()){
            char c = strNumber.charAt(i);
            switch (c) {
                case '0':
                    binaryValue.append("000");
                    break;
                case '1':
                    binaryValue.append("001");
                    break;
                case '2':
                    binaryValue.append("010");
                    break;
                case '3':
                    binaryValue.append("011");
                    break;
                case '4':
                    binaryValue.append("100");
                    break;
                case '5':
                    binaryValue.append("101");
                    break;
                case '6':
                    binaryValue.append("110");
                    break;
                case '7':
                    binaryValue.append("111");
                    break;
                default:
                    return "";
            }
            i++;
        }
        return binaryValue.toString();
    }

    private int getOctalToDecimal(long intNumber) {
        int decimal = 0,n = 0;
        while (intNumber != 0){
            long temp = intNumber % 10;
            decimal += temp*Math.pow(8,n);
            intNumber = intNumber / 10;
            n++;
        }
        return decimal;
    }
}