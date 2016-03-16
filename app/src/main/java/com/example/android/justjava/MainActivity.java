package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
int quantity = 0;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
}

/**
 * This method is called when the add button is clicked.
 */
public void increment(View view) {
    quantity = 1 + quantity;
    displayQuantity(quantity);

}

/**
 * This method is called when the minus button is clicked.
 */
public void decrement(View view) {
    quantity = quantity - 1;
    displayQuantity(quantity);

}

/**
 * This method is called when the order button is clicked.
 */
public void submitOrder(View view) {
    int price = 5 * quantity;
    String priceMessage = "Total= $" + price;
    priceMessage = priceMessage + "\n Thank You!";
    displayMessage(priceMessage);
}

/**
 * This method displays the given text on the screen.
 */
private void displayMessage(String message) {
    TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
    priceTextView.setText(message);
}

/**
 * This method displays the given quantity value on the screen.
 */
private void displayQuantity(int amount) {
    TextView quantityTextView = (TextView) findViewById(
            R.id.quantity_text_view);
    quantityTextView.setText("" + amount);
}

/**
 * This method displays the given price on the screen.
 */
private void displayPrice(int amount) {
    TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
    priceTextView.setText(NumberFormat.getCurrencyInstance().format(amount));
}
}