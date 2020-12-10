package com.example.adv101_activity6;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ForthFragment extends Fragment {

    FragmentTransaction fragmentTransaction;

    View view;
    Context context;
    Button home_button;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_forth, container, false);

        home_button = view.findViewById(R.id.button_home);

        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFirstFragment();
            }
        });

        return view;
    }

    public void openFirstFragment() {
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_xml,MyData.ff);
        fragmentTransaction.commit();
    }

}