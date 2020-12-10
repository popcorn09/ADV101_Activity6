package com.example.adv101_activity6;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ThirdFragment extends Fragment {

    FragmentTransaction fragmentTransaction;

    View view;
    Context context;
    Button back_button, pay_button;
    TextView totalPrice_textView, change_textView;
    static EditText cash_editTextNumber;
    TextWatcher text = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_third, container, false);

        back_button = view.findViewById(R.id.button_back);
        pay_button = view.findViewById(R.id.button_pay);
        totalPrice_textView = view.findViewById(R.id.textView_totalPrice);
        change_textView = view.findViewById(R.id.textView_change);
        cash_editTextNumber = view.findViewById(R.id.editTextNumber);

        totalPrice_textView.setText(String.valueOf(MyData.totalPrice));

        text = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!cash_editTextNumber.getText().toString().isEmpty()) {
                    double cash = Double.parseDouble(String.valueOf(cash_editTextNumber.getText()));
                    if (cash > MyData.totalPrice) {
                        change_textView.setText(String.valueOf(cash - MyData.totalPrice));
                    } else {
                        change_textView.setText("00.00");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        cash_editTextNumber.addTextChangedListener(text);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSecondFragment();
            }
        });

        pay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cash_editTextNumber.setText("");
                MyData.clearData();
                openForthFragment();
            }
        });

        return view;
    }

    public void openSecondFragment() {
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_xml,MyData.sf);
        fragmentTransaction.commit();
    }

    public void openForthFragment() {
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_xml,MyData.fthf);
        fragmentTransaction.commit();
    }

}