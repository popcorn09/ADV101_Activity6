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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Dialog myDialog;
    String data1[], data2[];
    int images[];
    Context context;
    View temp; // For setOnCLickListener

    public MyAdapter(Context context, String data1[], String data2[], int images[]) {
        this.context = context;
        this.data1 = data1;
        this.data2 = data2;
        this.images = images;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.food_row, parent, false);
        temp = view;
        myDialog = new Dialog(context);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.textView1.setText(data1[position]);
        holder.textView2.setText(data2[position]);
        holder.imageView.setImageResource(images[position]);
        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // displayToast(data1[position]);
                popUp(images[position], data1[position], data2[position]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView1, textView2;
        ImageView imageView;

        public MyViewHolder (@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView_food);
            textView2 = itemView.findViewById(R.id.textView_price);
            imageView = itemView.findViewById(R.id.imageView_food);
        }
    }

    public void displayToast(String a) {
        Toast.makeText(context, a, Toast.LENGTH_SHORT).show();
    }

    public void popUp(int images, String fn, String p) {
        final int[] tempNumber = new int [1];
        tempNumber[0] = 1;
        final String priceString = p;
        final String food_name = fn;
        final double price = Double.parseDouble(priceString);
        final int img = images;

        final Button close, add, plus, minus;
        final TextView foodName, totalPrice;
        final EditText amount;

        myDialog.setContentView(R.layout.pop_up);

        close = (Button) myDialog.findViewById(R.id.button_cancel);
        add = (Button) myDialog.findViewById(R.id.button_change);
        plus = (Button) myDialog.findViewById(R.id.button_plus);
        minus = (Button) myDialog.findViewById(R.id.button_minus);
        foodName = (TextView) myDialog.findViewById(R.id.textView_foodName);
        amount = (EditText) myDialog.findViewById(R.id.editTextNumber_amount);
        totalPrice = (TextView) myDialog.findViewById(R.id.textView_price);

        amount.setText(String.valueOf(tempNumber[0]));
        totalPrice.setText(priceString);
        foodName.setText(fn);

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

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyData.isNotPresent(food_name)) {
                    MyData.Cquantity[MyData.getSize()] = tempNumber[0];
                    MyData.Cprice[MyData.getSize()] = priceString;
                    MyData.Cimage[MyData.getSize()] = img;
                    MyData.Cproduct[MyData.getSize()] = food_name;
                    MyData.setTotalPrice();
                } else {
                    MyData.addTheQuantity(food_name, tempNumber[0]);
                }
                myDialog.dismiss();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.show();
    }

}
