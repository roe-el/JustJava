package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
int quantity = 1;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
}

/**
 * This method is called when the add button is clicked.
 */
public void increment(View view) {
    if (quantity == 100) {
        //Toast to show
        Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
        return; //leaves method
    }
    quantity += 1;
    displayQuantity(quantity);

}

/**
 * This method is called when the minus button is clicked.
 */
public void decrement(View view) {
    if (quantity == 1) {
        //Toast to show
        Toast.makeText(this, "You cannot have less than 1 coffees", Toast.LENGTH_SHORT).show();
        return; //leaves method
    }
    quantity -= 1;
    displayQuantity(quantity);

}

/**
 * This method is called when the order button is clicked.
 */
public void submitOrder(View view) {
    //figure out if user wants chocolate topping
    CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
    boolean hasChocolate = chocolateCheckBox.isChecked();
    //figure out if user wants whipped topping
    CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
    boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
    //Getting name and passing it to createOrderSummary
    EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
    Editable nameOfCustomer = nameEditText.getText();

    String priceMessage = createOrderSummary(nameOfCustomer, quantity, calculatePrice(hasWhippedCream, hasChocolate), hasWhippedCream, hasChocolate);


    Intent intent = new Intent(Intent.ACTION_SENDTO);
    intent.setData(Uri.parse("mailto:")); // only email apps should handle this
    intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
    intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for " + nameOfCustomer);
    if (intent.resolveActivity(getPackageManager()) != null) {
        startActivity(intent);
    }
}


/**
 * Summary of the order
 *
 * @param nameEntered     is the text that was entered by the user
 * @param addWhippedCream is whether user wants whipped cream topping
 * @param addChocolate    is whether user wants chocolate topping
 * @param quantity        is the amount of coffes
 * @param price           is the price
 * @return the message
 */
private String createOrderSummary(Editable nameEntered, int quantity, int price, boolean addWhippedCream, boolean addChocolate) {


    return "Name: " + nameEntered +
            "\nQuantity: " + quantity +
            "\nHas Whipped Cream: " + addWhippedCream +
            "\nHas Chocolate: " + addChocolate +
            "\nTotal: $" + price +
            "\nThank you!";


}

/**
 * Calculates the price of the order.
 *
 * @param addWhippedCream is if user added whipped cream topping
 * @param addChocolate    is if user added chocolate topping
 * @return total price
 */
private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
    //Price of 1 cup of coffee
    int price = 5;

    if (addWhippedCream) {
        //If user wants whipped cream, then $1 is added
        price += 1;
    }
    if (addChocolate) {
        //If user wants chocolate topping, then $2 is added
        price += 2;
    }
    //Price of total number of cups of coffee with optional topping selected
    return quantity * (price);

}


/**
 * This method displays the given quantity value on the screen.
 *
 * @param amount of coffees
 */
private void displayQuantity(int amount) {
    TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
    quantityTextView.setText("" + amount);
}
}
