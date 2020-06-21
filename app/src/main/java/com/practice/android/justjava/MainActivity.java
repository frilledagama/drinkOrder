package com.practice.android.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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

        quantity = 0;

        whippedcream = findViewById(R.id.whippedcream_checkbox);
        chocolate = findViewById(R.id.chocolate_checkbox);

    }


    //method gets input name from edittext view
    public String getInputName(){
        EditText nameEdtText = findViewById(R.id.name_text_input);
        nameEdtText.setInputType(InputType.TYPE_CLASS_TEXT);
        name = nameEdtText.getText().toString();
        return name;
    }

    //method is called when the "+" button is pressed
    public void increaseQuantity(View view){
        if (quantity < 100) {
            quantity = quantity + 1;
        }

        display(quantity);
    }

    //method is called when the "-" button is pressed
    public void decreaseQuantity(View view){
        if (quantity > 0) {
            quantity = quantity - 1;
            display(quantity);
        }
    }

    //this method will calculate the price of the order based on current quantity
    private double calculatePrice(int quantity, double unitCost) {
        double price;

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

        ((TextView) findViewById(R.id.quantity_text_view)).setText("" + quantity);

        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);

        double price = calculatePrice(quantity,4.99);

        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(price));
    }

    //this method displays the price value on the screen
    private void displayMessage(String message){

        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);

        orderSummaryTextView.setText(message);
    }
    //method to send order summary to user email
    private void sendEmailConfirmation(String message){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","maya.scottlourenco@gmail.com",null));
        //The intent does not have a URI so declare plain type MIME
        //emailIntent.setType("message/rfc822");
        //emailIntent.setData(Uri.parse("mailto:maya.scottlourenco@gmail.com"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "JustJava Order Confirmation");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        startActivity(Intent.createChooser(emailIntent,"Send Order Summary"));
    }

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

       //displayMessage(orderSummaryString);
        sendEmailConfirmation(orderSummaryString);
    }

}
