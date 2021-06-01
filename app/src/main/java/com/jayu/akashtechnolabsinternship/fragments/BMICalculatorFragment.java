package com.jayu.akashtechnolabsinternship.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputEditText;
import com.jayu.akashtechnolabsinternship.R;
import java.text.DecimalFormat;

public class BMICalculatorFragment extends Fragment {

    TextInputEditText txtWeight,txtHeight;
    Button btnSubmit;
    TextView bmiAnswer,bmiResult,bmiHealthyWeight1,bmiHealthyWeight2;
    String strWeight, strHeight;
    LinearLayout bmiAnswerLinear,bmiResultLinear,bmiHealthyWeightLinear;
    float floatWeight,floatHeight,floatAnswer,floatHealthyWeight1,floatHealthyWeight2;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("BMI Calculator");
        View view = inflater.inflate(R.layout.fragment_b_m_i_calculator, container, false);

        txtWeight = view.findViewById(R.id.txtWeight);
        txtHeight = view.findViewById(R.id.txtHeight);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        bmiAnswer = view.findViewById(R.id.bmiAnswer);
        bmiResult = view.findViewById(R.id.bmiResult);
        bmiHealthyWeight1 = view.findViewById(R.id.bmiHealthyWeight1);
        bmiHealthyWeight2 = view.findViewById(R.id.bmiHealthyWeight2);
        bmiAnswerLinear = view.findViewById(R.id.bmiAnswerLinear);
        bmiResultLinear = view.findViewById(R.id.bmiResultLinear);
        bmiHealthyWeightLinear = view.findViewById(R.id.bmiHealthyWeightLinear);

        btnSubmit.setOnClickListener(view1 -> {
            strWeight = txtWeight.getText().toString().trim();
            strHeight = txtHeight.getText().toString().trim();

            if (strWeight.isEmpty()){
                txtWeight.setError("Age is mandatory");
                txtWeight.requestFocus();
                return;
            }
            else if (strHeight.isEmpty()){
                txtHeight.setError("Height is mandatory");
                txtHeight.requestFocus();
                return;
            }
            floatWeight = Float.parseFloat(strWeight);
            floatHeight = Float.parseFloat(strHeight);
            bmiAnswerLinear.setVisibility(View.VISIBLE);
            bmiResultLinear.setVisibility(View.VISIBLE);
            bmiHealthyWeightLinear.setVisibility(View.VISIBLE);
            calculator(floatHeight,floatWeight);
            bmiAnswer.setText(new DecimalFormat("##.##").format(floatAnswer));
            bmiHealthyWeight1.setText(new DecimalFormat("##.##").format(floatHealthyWeight1) + " kg");
            bmiHealthyWeight2.setText(new DecimalFormat("##.##").format(floatHealthyWeight2) + " kg");
        });
        return view;
    }

    private void calculator(float floatHeight, float floatWeight) {
        floatAnswer = floatWeight / (floatHeight * floatHeight) *100 *100;
        floatHealthyWeight1 = (float) (18.5 * floatHeight * floatHeight / (100 * 100));
        floatHealthyWeight2 = (float) (24.9 * floatHeight * floatHeight / (100 * 100));

        setColor(floatAnswer);
    }

    @SuppressLint("SetTextI18n")
    private void setColor(float floatAnswer) {
        if (floatAnswer<18.5){
            bmiResult.setText("Underweight");
            bmiResult.setTextColor(getResources().getColor(R.color.colorRed));
            bmiAnswer.setTextColor(getResources().getColor(R.color.colorRed));
        }
        else if (floatAnswer>=18.5 && floatAnswer<25){
            bmiResult.setText("Normal");
            bmiResult.setTextColor(getResources().getColor(R.color.colorGreen));
            bmiAnswer.setTextColor(getResources().getColor(R.color.colorGreen));
        }
        else if (floatAnswer>=25 && floatAnswer<30){
            bmiResult.setText("Overweight");
            bmiResult.setTextColor(getResources().getColor(R.color.colorRed));
            bmiAnswer.setTextColor(getResources().getColor(R.color.colorRed));
        }
        else if (floatAnswer>=30){
            bmiResult.setText("Obese");
            bmiResult.setTextColor(getResources().getColor(R.color.colorRed));
            bmiAnswer.setTextColor(getResources().getColor(R.color.colorRed));
        }
    }

}