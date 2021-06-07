package com.jayu.akashtechnolabsinternship.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.jayu.akashtechnolabsinternship.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import kotlin.text.Regex;

public class CreditCardCheckerFragment extends Fragment {

    TextInputLayout tnl_CardNumber,tnl_Expiry,tnl_SecurityCode,tnl_fName,tnl_sName;
    TextInputEditText txtCardNumber,txtExpiry,txtSecurityCode,txtFName,txtSName;
    TextView txtCardError,txtExpiryError,txtSecurityError,txtFNameError, txtSNameError;
    Button btnSubmit,btnReset;
    boolean cardSupported = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_credit_card_checker, container, false);

        requireActivity().setTitle("Credit Card Checker");

        tnl_CardNumber = view.findViewById(R.id.tnl_CardNumber);
        tnl_Expiry = view.findViewById(R.id.tnl_Expiry);
        tnl_SecurityCode = view.findViewById(R.id.tnl_SecurityCode);
        tnl_fName = view.findViewById(R.id.tnl_fName);
        tnl_sName = view.findViewById(R.id.tnl_sName);
        txtCardNumber = view.findViewById(R.id.txtCardNumber);
        txtExpiry = view.findViewById(R.id.txtExpiry);
        txtSecurityCode = view.findViewById(R.id.txtSecurityCode);
        txtFName = view.findViewById(R.id.txtFName);
        txtSName = view.findViewById(R.id.txtSName);
        txtCardError = view.findViewById(R.id.txtCardError);
        txtExpiryError = view.findViewById(R.id.txtExpiryError);
        txtSecurityError = view.findViewById(R.id.txtSecurityError);
        txtFNameError = view.findViewById(R.id.txtFNameError);
        txtSNameError = view.findViewById(R.id.txtSNameError);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnReset = view.findViewById(R.id.btnReset);

        btnSubmit.setOnClickListener(view1 -> {
            String cardNumber = Objects.requireNonNull(txtCardNumber.getText()).toString().trim();
            String expiryDate = Objects.requireNonNull(txtExpiry.getText()).toString().trim();
            String securityCode = Objects.requireNonNull(txtSecurityCode.getText()).toString().trim();
            String firstName = Objects.requireNonNull(txtFName.getText()).toString().trim();
            String lastName = Objects.requireNonNull(txtSName.getText()).toString().trim();

            setDefault();

            boolean validator = validatorClass(cardNumber,expiryDate,securityCode,firstName,lastName);

            if (validator){
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setMessage("Card Verified Successfully");
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}
                });
                alertDialog.show();
            }

        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtCardNumber.setText("");
                txtExpiry.setText("");
                txtSecurityCode.setText("");
                txtFName.setText("");
                txtSName.setText("");
                txtCardNumber.requestFocus();
                setDefault();
            }
        });

        return view;
    }

    private void setDefault() {
        txtCardError.setVisibility(View.GONE);
        txtSecurityError.setVisibility(View.GONE);
        txtExpiryError.setVisibility(View.GONE);
        txtFNameError.setVisibility(View.GONE);
        txtSNameError.setVisibility(View.GONE);
        tnl_CardNumber.setBoxStrokeColor(getResources().getColor(R.color.colorPrimary));
        tnl_Expiry.setBoxStrokeColor(getResources().getColor(R.color.colorPrimary));
        tnl_SecurityCode.setBoxStrokeColor(getResources().getColor(R.color.colorPrimary));
        tnl_fName.setBoxStrokeColor(getResources().getColor(R.color.colorPrimary));
        tnl_sName.setBoxStrokeColor(getResources().getColor(R.color.colorPrimary));
        tnl_CardNumber.setHintTextAppearance(R.style.normal_appearance);
        tnl_Expiry.setHintTextAppearance(R.style.normal_appearance);
        tnl_SecurityCode.setHintTextAppearance(R.style.normal_appearance);
        tnl_CardNumber.setHintTextAppearance(R.style.normal_appearance);
        tnl_fName.setHintTextAppearance(R.style.normal_appearance);
        tnl_sName.setHintTextAppearance(R.style.normal_appearance);
    }

    private boolean validatorClass(String cardNumber, String expiryDate, String securityCode, String firstName, String lastName) {

        if (cardNumber.isEmpty()){
            txtCardError.setVisibility(View.VISIBLE);
            txtCardError.setText(getString(R.string.cardNumberBlankError));
            tnl_CardNumber.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
            tnl_CardNumber.setHintTextAppearance(R.style.error_appearance);
            txtCardNumber.requestFocus();
            return false;
        }
        else if (expiryDate.isEmpty()){
            txtExpiryError.setVisibility(View.VISIBLE);
            txtExpiryError.setText(getString(R.string.expiryBlankError));
            tnl_Expiry.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
            tnl_Expiry.setHintTextAppearance(R.style.error_appearance);
            txtExpiry.requestFocus();
            return false;
        }
        else if (securityCode.isEmpty()){
            txtSecurityError.setVisibility(View.VISIBLE);
            txtSecurityError.setText(getString(R.string.securityCodeBlankError));
            tnl_SecurityCode.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
            tnl_SecurityCode.setHintTextAppearance(R.style.error_appearance);
            txtSecurityCode.requestFocus();
            return false;
        }
        else if (firstName.isEmpty()){
            txtFNameError.setVisibility(View.VISIBLE);
            txtFNameError.setText(getString(R.string.fNameBlankError));
            tnl_fName.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
            tnl_fName.setHintTextAppearance(R.style.error_appearance);
            txtFName.requestFocus();
            return false;
        }
        else if (lastName.isEmpty()){
            txtSNameError.setVisibility(View.VISIBLE);
            txtSNameError.setText(getString(R.string.lNameBlankError));
            tnl_sName.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
            tnl_sName.setHintTextAppearance(R.style.error_appearance);
            txtSName.requestFocus();
            return false;
        }

        String cardType = getCardType(cardNumber);

        boolean validCard = checkCard(cardNumber, cardType);
        boolean validCVV = checkCVV(cardType, securityCode);
        boolean validExpiry = checkExpiry(expiryDate);

        if (!cardSupported){
            txtCardError.setVisibility(View.VISIBLE);
            txtCardError.setText(getString(R.string.cardNotSupportedError));
            tnl_CardNumber.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
            tnl_CardNumber.setHintTextAppearance(R.style.error_appearance);
            txtCardNumber.requestFocus();
            return false;
        }
        else if (!validCard){
            txtCardError.setVisibility(View.VISIBLE);
            txtCardError.setText(getString(R.string.cardNumberDigitError));
            tnl_CardNumber.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
            tnl_CardNumber.setHintTextAppearance(R.style.error_appearance);
            txtCardNumber.requestFocus();
            return false;
        }
        else if (!LuhnCheck(cardNumber)){
            txtCardError.setVisibility(View.VISIBLE);
            txtCardError.setText(getString(R.string.cardNumberDigitError));
            tnl_CardNumber.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
            tnl_CardNumber.setHintTextAppearance(R.style.error_appearance);
            txtCardNumber.requestFocus();
            return false;
        }
        else if (!expiryDate.matches("\\d{2}/\\d{2}")){
            txtExpiryError.setVisibility(View.VISIBLE);
            txtExpiryError.setText(getString(R.string.expiryFormatError));
            tnl_Expiry.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
            tnl_Expiry.setHintTextAppearance(R.style.error_appearance);
            txtExpiry.requestFocus();
            return false;
        }
        else if (!validExpiry){
            txtExpiryError.setVisibility(View.VISIBLE);
            txtExpiryError.setText(getString(R.string.cardExpiredError));
            tnl_Expiry.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
            tnl_Expiry.setHintTextAppearance(R.style.error_appearance);
            txtExpiry.requestFocus();
            return false;
        }
        else if (!validCVV){
            txtSecurityError.setVisibility(View.VISIBLE);
            txtSecurityError.setText(getString(R.string.securityCodeLengthError));
            tnl_SecurityCode.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
            tnl_SecurityCode.setHintTextAppearance(R.style.error_appearance);
            txtSecurityCode.requestFocus();
            return false;
        }
        else if (!firstName.matches("[A-Za-z]+$")){
            txtFNameError.setVisibility(View.VISIBLE);
            txtFNameError.setText(getString(R.string.fNameStringError));
            tnl_fName.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
            tnl_fName.setHintTextAppearance(R.style.error_appearance);
            txtFName.requestFocus();
            return false;
        }
        else if (!lastName.matches("[A-Za-z]+$")){
            txtSNameError.setVisibility(View.VISIBLE);
            txtSNameError.setText(getString(R.string.lNameStringError));
            tnl_sName.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
            tnl_sName.setHintTextAppearance(R.style.error_appearance);
            txtSName.requestFocus();
            return false;
        }

        return true;
    }

    private boolean LuhnCheck(String cardNumber) {
        int cardLength = cardNumber.length();
        int nSum = 0;
        boolean isSecond = false;

        for (int i = cardLength -1; i >= 0; i--){
            int d = cardNumber.charAt(i) - '0';

            if (isSecond){
                d = d * 2;
            }

            nSum += d / 10;
            nSum += d % 10;

            isSecond = !isSecond;
        }
        return nSum % 10 == 0;
    }

    @SuppressLint("SetTextI18n")
    private boolean checkExpiry(String expiryDate) {
        try{
            String[] seperated = expiryDate.split("/");
            String mm = seperated[0];
            String yy = seperated[1];
            String eDate = mm + "/" + yy;
            @SuppressLint("SimpleDateFormat") SimpleDateFormat myFormat = new SimpleDateFormat("MM/yy");
            Date d = new Date();
            String today = myFormat.format(d.getTime());
            Date currentDate = myFormat.parse(today);
            Date expiry = myFormat.parse(eDate);
            assert currentDate != null;
            assert expiry != null;
            int difference = (int) (expiry.getTime() - currentDate.getTime());
            return  difference >=0;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            txtExpiryError.setText("Please Enter Valid Date");
            return false;
        }
    }

    private boolean checkCVV(String cardType, String securityCode) {
        if (cardType.equals(getString(R.string.visa)) || cardType.equals(getString(R.string.master)) || cardType.equals(getString(R.string.discover))){
            return securityCode.length() == 3;
        }
        else if (cardType.equals(getString(R.string.american))){
            return securityCode.length() == 4;
        }
        else {
            return false;
        }
    }

    private boolean checkCard(String cardNumber, String cardType) {
        if (cardType.equals(getString(R.string.visa)) && cardNumber.length() == 16){
            return true;
        }
        else if (cardType.equals(getString(R.string.master)) && cardNumber.length() == 16){
            return true;
        }
        else if (cardType.equals(getString(R.string.discover)) && cardNumber.length() == 16){
            return true;
        }
        else if (cardType.equals(getString(R.string.american)) && cardNumber.length() == 15){
            return true;
        }
        else {
            return false;
        }
    }

    private String getCardType(String cardNumber) {
        if (String.valueOf(cardNumber.charAt(0)).equals("3")) {
            return getString(R.string.american);
        }
        else if (String.valueOf(cardNumber.charAt(0)).equals("4")) {
            return getString(R.string.visa);
        }
        else if (String.valueOf(cardNumber.charAt(0)).equals("5")) {
            return getString(R.string.master);
        }
        else if (String.valueOf(cardNumber.charAt(0)).equals("6")) {
            return getString(R.string.discover);
        }
        else {
            cardSupported = false;
            return "";
        }
    }
}