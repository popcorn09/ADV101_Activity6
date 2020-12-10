package com.example.adv101_activity6;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FirstFragment extends Fragment {

    RecyclerView recyclerView;
    Button reviewOrder;
    View view;
    Context context;
    FragmentTransaction fragmentTransaction;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_first, container, false);

        reviewOrder = view.findViewById(R.id.button_reviewOrder);
        recyclerView = view.findViewById(R.id.recyclerView);

        MyData.s1 = getResources().getStringArray(R.array.food_options);
        MyData.s2 = getResources().getStringArray(R.array.price);

        MyData.price = new double[MyData.s2.length];
        for(int j = 0; j < MyData.s2.length; j++) {
            MyData.price[j] = Double.parseDouble(MyData.s2[j]);
        }

        MyAdapter myAdapter = new MyAdapter(context, MyData.s1, MyData.s2, MyData.images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        reviewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyData.totalPrice > 0) {
                    openSecondFragment();
                }
            }
        });

        return view;
    }

    public void openSecondFragment() {
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_xml, MyData.sf);
        fragmentTransaction.commit();
    }

}