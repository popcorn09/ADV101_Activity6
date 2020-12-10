package com.example.adv101_activity6;


import androidx.fragment.app.FragmentTransaction;

public class MyData {

    FragmentTransaction fragmentTransaction;

    // First Fragment Use
    public static String s1[], s2[];
    public static int images[] = {R.drawable.pizza, R.drawable.burger, R.drawable.fries, R.drawable.coke_float, R.drawable.fried_chicken};
    public static double price[];

    // Fragments
    public static FirstFragment ff = new FirstFragment();
    public static SecondFragment sf = new SecondFragment();
    public static ThirdFragment tf = new ThirdFragment();
    public static ForthFragment fthf = new ForthFragment();

    // For the chosen foods, C stand for chosen
    public static double totalPrice;
    public static int Cimage[] = new int[100];
    public static String Cproduct[] = new String[100];
    public static String Cprice[] = new String[100];
    public static int Cquantity[] = new int[100];

    // Also to know where to put the new data on the array
    public static int getSize() {
        int size = 0;
        for ( ; Cproduct[size] != null; ) {
            size++;
        }
        return size;
    }

    public static String foodPrice(String a) {
        int size = getSize();
        int j = 0;
        for ( ; j < size; j++) {
            if (Cproduct[j].equals(a)) {
                break;
            }
        }
        return Cprice[j];
    }

    public static int getFoodQuantity(String a) {
        int size = getSize();
        int j = 0;
        for ( ; j < size; j++) {
            if (Cproduct[j].equals(a)) {
                break;
            }
        }
        return Cquantity[j];
    }

    public static void setFoodQuantity(String a, int quantity) {
        int size = getSize();
        for (int j = 0; j < size; j++) {
            if (Cproduct[j].equals(a)) {
                Cquantity[j] = quantity;
            }
        }
    }

    // Check if present, if present just add the quantity
    public static boolean isNotPresent(String a) {
        int size = getSize();
        int j = 0;
        for ( ; j < size; j++) {
            if (Cproduct[j].equals(a)) {
                return false;
            }
        }
        return true;
    }

    public static void addTheQuantity(String a, double q) {
        int size = getSize();
        int j = 0;
        for ( ; j < size; j++) {
            if (Cproduct[j].equals(a)) {
                Cquantity[j] += q;
                break;
            }
        }
    }

    public static void deleteSelectedFood(String a) {
        int size = getSize();
        int j = 0;
        for ( ; j < size; j++) {
            if(Cproduct[j].equals(a)) {
                for (int s = j; j < size; j++) {
                    Cproduct[j] = Cproduct[j+1];
                    Cprice[j] = Cprice[j+1];
                    Cquantity[j] = Cquantity[j+1];
                    Cimage[j] = Cimage[j+1];
                }
            }
        }
    }

    public static double getAllTotal() {
        double rtn = 0;
        int size = getSize();
        for (int j = 0; j < size; j++) {
            double price = Double.parseDouble(Cprice[j]);
            double priceQuantity = price * Cquantity[j];
            rtn += priceQuantity;
            totalPrice = rtn;
        }
        return rtn;
    }

    public static void clearData() {
        int size = getSize();
        for (int j = 0; j < size; j++) {
            Cproduct[j] = null;
        }
        totalPrice = 0;
    }

    public static void setTotalPrice() {
        totalPrice = 1;
    }

}