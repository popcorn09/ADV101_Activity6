package com.example.adv101_activity6;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder2>{

    Context context;
    String data1[], data2[];
    int images[], quantity[];
    Dialog myDialog;
    View temp;

    public MyAdapter2(Context context, String data1[], String data2[], int images[], int quantity[]) {
        this.context = context;
        this.data1 = data1;
        this.data2 = data2;
        this.images = images;
        this.quantity = quantity;
    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.food_row2, parent, false);
        temp = view;
        myDialog = new Dialog(context);
        SecondFragment.updateAllTotal();
        return new MyViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, final int position) {
        holder.textView1.setText(data1[position]);
        double price = Double.parseDouble(data2[position]);
        double ans = price * quantity[position];
        holder.textView2.setText(String.valueOf(ans));
        holder.imageView.setImageResource(images[position]);
        holder.textView3.setText("Quantity: " + String.valueOf(quantity[position]));

        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUp(data1[position], data2[position]);
            }
        });

    }

    @Override
    public int getItemCount() {
        return MyData.getSize();
    }


    public class MyViewHolder2 extends RecyclerView.ViewHolder {

        TextView textView1, textView2, textView3;
        ImageView imageView;

        public MyViewHolder2 (@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView_food);
            textView2 = itemView.findViewById(R.id.textView_price);
            imageView = itemView.findViewById(R.id.imageView_food);
            textView3 = itemView.findViewById(R.id.textView_quantity);
        }
    }

    public void displayToast(String a) {
        Toast.makeText(context, a, Toast.LENGTH_SHORT).show();
    }

    public void updateData() {
        notifyDataSetChanged();
    }

    public void popUp(String fn, String p) {
        final int[] tempNumber = new int [1];
        final String priceString = p;
        final String food_name = fn;
        final double price = Double.parseDouble(priceString);

        final Button cancel, change, plus, minus;
        final TextView foodName, totalPrice;
        final EditText amount;
        final ImageView deleteButton;

        myDialog.setContentView(R.layout.pop_up2);

        cancel = (Button) myDialog.findViewById(R.id.button_cancel);
        change = (Button) myDialog.findViewById(R.id.button_change);
        plus = (Button) myDialog.findViewById(R.id.button_plus);
        minus = (Button) myDialog.findViewById(R.id.button_minus);
        foodName = (TextView) myDialog.findViewById(R.id.textView_foodName);
        amount = (EditText) myDialog.findViewById(R.id.editTextNumber_amount);
        totalPrice = (TextView) myDialog.findViewById(R.id.textView_price);
        deleteButton = (ImageView) myDialog.findViewById(R.id.imageView_delete);

        tempNumber[0] = MyData.getFoodQuantity(fn);

        amount.setText(String.valueOf(tempNumber[0]));
        totalPrice.setText(priceString);
        foodName.setText(fn);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyData.deleteSelectedFood(food_name);
                updateData();
                SecondFragment.updateAllTotal();
                myDialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyData.setFoodQuantity(food_name, tempNumber[0]);
                updateData();
                SecondFragment.updateAllTotal();
                myDialog.dismiss();
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tempNumber[0] < 99) {
                    tempNumber[0]++;
                    amount.setText(String.valueOf(tempNumber[0]));
                    double ans = price * tempNumber[0];
                    totalPrice.setText(String.valueOf(ans));
                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tempNumber[0] > 1) {
                    tempNumber[0]--;
                    amount.setText(String.valueOf(tempNumber[0]));
                    double ans = price * tempNumber[0];
                    totalPrice.setText(String.valueOf(ans));
                }
            }
        });

        myDialog.show();
    }

}
