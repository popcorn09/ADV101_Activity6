package com.example.adv101_activity6;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SecondFragment extends Fragment {

    View view;
    Context context;
    FragmentTransaction fragmentTransaction;
    Button back, checkOut_button;
    RecyclerView recyclerView2;
    static TextView textView_totalPrice;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_second, container, false);

        back = view.findViewById(R.id.button_back);
        recyclerView2 = view.findViewById(R.id.recyclerView2);
        textView_totalPrice = view.findViewById(R.id.textView_totalPrice);
        checkOut_button = view.findViewById(R.id.button_chekcOut);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFirstFragment();
            }
        });

        checkOut_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openThirdFragment();
            }
        });

        MyAdapter2 myAdapter2 = new MyAdapter2(context, MyData.Cproduct, MyData.Cprice, MyData.Cimage, MyData.Cquantity);
        recyclerView2.setAdapter(myAdapter2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    public void openFirstFragment() {
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_xml,MyData.ff);
        fragmentTransaction.commit();
    }

    public void openThirdFragment() {
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_xml,MyData.tf);
        fragmentTransaction.commit();
    }

    public static void updateAllTotal() {
        textView_totalPrice.setText(String.valueOf(MyData.getAllTotal()));
    }

}