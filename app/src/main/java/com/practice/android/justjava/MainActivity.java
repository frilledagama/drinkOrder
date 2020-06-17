package com.practice.android.justjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.InputType;
import android.view.View;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

//display and order form to order drinks
public class MainActivity extends AppCompatActivity {
    //global variables
    private int quantity;
    //private boolean addWhippedcream;
    private CheckBox whippedcream;
    private CheckBox chocolate;
    private String name;

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

        whippedcream = (CheckBox)findViewById(R.id.whippedcream_checkbox);
        chocolate = (CheckBox)findViewById(R.id.chocolate_checkbox);

    }
    /*
    //get instances of checkboxes from activity_main
    public void addListenerOnButtonClick(){
        CheckBox whipcream = (CheckBox)findViewById(R.id.whipcream);
    }
    */

    //method gets input name from edittext view to display in order summary
    //TODO
    // get the edit text input as a string

    public String getInputName(){
        EditText nameEdtText = (EditText) findViewById(R.id.name_text_input);
        nameEdtText.setInputType(InputType.TYPE_CLASS_TEXT);
        name = nameEdtText.getText().toString();
        return name;
    }

    //this method is called when the "+" button is pressed
    public void increaseQuantity(View view){
        if (quantity < 100) {
            quantity = quantity + 1;
        }

        display(quantity);
    }

    //this method is called when the "-" button is pressed
    public void decreaseQuantity(View view){
        if (quantity > 0) {
            quantity = quantity - 1;
            display(quantity);
        }
    }

    //this method will calculate the price of the order based on current quantity
    private double calculatePrice(int quantity, double unitCost) {
        double price = 0;

        if (whippedcream.isChecked()) {
            unitCost = unitCost + 0.99;
        }

        if (chocolate.isChecked()) {
            unitCost = unitCost + 1.99;
        }

        price = quantity * unitCost;

        return price;
    }

    //this method display the given quantity value on the screen
    private void display(int quantity){
        //TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        //quantityTextView.setText("hewwo");
        ((TextView) findViewById(R.id.quantity_text_view)).setText("" + quantity);

        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);

        double price = calculatePrice(quantity,4.99);

        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(price));
    }

    //this method displays the price value on the screen
    private void displayMessage(String message){

        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);

        orderSummaryTextView.setText(message);
    }

    //TODO
    //print the order summary to a textview and have it show when the order button is pressed?
    //this method is called when the Order button is pressed and displays the order summary
    public void submitOrder(View view) {
        String orderSummaryString = "";
        String toppingsStr = "";
        int numOfToppings = 0;

        //add name to order summary string
        orderSummaryString = orderSummaryString + "Name: " + getInputName() + "\n";

        //add toppings to order summary string
        orderSummaryString = orderSummaryString + "Toppings: ";

        if (whippedcream.isChecked()){
            toppingsStr = toppingsStr + "Whipped cream";
            numOfToppings++;
        }

        if (chocolate.isChecked()){
            if (numOfToppings != 0) {
                toppingsStr = toppingsStr + ", Chocolate";
            } else {
                toppingsStr = toppingsStr + "Chocolate";
            }
        }

       orderSummaryString = orderSummaryString + toppingsStr + "\n";

        //quantity and price
       orderSummaryString = orderSummaryString + "Quantity: " + quantity + "\n";
       orderSummaryString = orderSummaryString + ("Total: " + calculatePrice(quantity,4.99)) + "\n";
       orderSummaryString = orderSummaryString + "Thank you!";

       displayMessage(orderSummaryString);
    }

    //TODO
    //add scrolling support
}
