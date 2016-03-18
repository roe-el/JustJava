package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
    CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
    CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
    EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
    Editable nameOfCustomer = nameEditText.getText();
    boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
    boolean hasChocolate = chocolateCheckBox.isChecked();
    String priceMessage = createOrderSummary(nameOfCustomer, calculatePrice(quantity), hasWhippedCream, hasChocolate);
    displayMessage(priceMessage);

}


/**
 * Summary of the order
 *@param nameEntered      is the text that was entered by the user
 * @param addWhippedCream is whether user wants whipped cream topping
 * @param addChocolate    is whether user wants chocolate topping
 * @param amount          is the price
 * @return the message
 */
private String createOrderSummary(Editable nameEntered, int amount, boolean addWhippedCream, boolean addChocolate) {


    return "Name: " + nameEntered +
            "\nQuantity: " + amount +
            "\nHas Whipped Cream: " + addWhippedCream +
            "\nHas Chocolate: " + addChocolate +
            "\nTotal: $" + amount +
            "\nThank you!";

}

/**
 * Calculates the price of the order.
 *
 * @param quantity is the number of cups of coffee ordered
 * @return total price
 */
private int calculatePrice(int quantity) {

    return quantity * 5;

}

/**
 * This method displays the given text on the screen.
 */
private void displayMessage(String message) {
    TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
    orderSummaryTextView.setText(message);
}

/**
 * This method displays the given quantity value on the screen.
 *
 * @param amount of coffees
 */
private void displayQuantity(int amount) {
    TextView quantityTextView = (TextView) findViewById(
            R.id.quantity_text_view);
    quantityTextView.setText("" + amount);
}
}
