package com.practice.android.justjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import android.os.Bundle;
import android.widget.TextView;

import java.text.NumberFormat;

//display and order form to order drinks
public class MainActivity extends AppCompatActivity {
    //global variables
    private int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        //create toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
         }

        */
        quantity = 0;

    }
    //this method is called when the "+" button is pressed
    public void increaseQuantity(View view){
        quantity = quantity + 1;
        display(quantity);
    }

    //this method is called when the "-" button is pressed
    public void decreaseQuantity(View view){
        if (quantity > 0) {
            quantity = quantity - 1;
            display(quantity);
        }
    }

    //this method is called when the Order button is pressed
    public void submitOrder(View view) {
        /*
        price = quantity*5;
        display(quantity);
        displayPrice(price);
        */
    }

    //this method display the given quantity value on the screen
    private void display(int quantity){
        //TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        //quantityTextView.setText("hewwo");
        ((TextView) findViewById(R.id.quantity_text_view)).setText("" + quantity);

        displayPrice(quantity * (4.99));
    }

    //this method displays the price value on the screen
    private void displayPrice(double price){
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(price));
    }

}
