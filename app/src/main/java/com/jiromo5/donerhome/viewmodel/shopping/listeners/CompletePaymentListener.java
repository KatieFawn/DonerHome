package com.jiromo5.donerhome.viewmodel.shopping.listeners;

import android.content.*;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.activities.home.shopping.PaymentVerificationActivity;
import com.jiromo5.donerhome.data.state.PaymentCard;
import com.jiromo5.donerhome.data.state.paths.CartResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;

import java.util.Calendar;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * A listener that handles touch events on the complete payment button, validates the payment details,
 * and navigates to the PaymentVerificationActivity if the details are correct.
 */
public class CompletePaymentListener implements View.OnTouchListener {

    private Context context;
    private EditText cartNumber;
    private EditText dateCart;
    private EditText cvvCart;

    /**
     * Constructor to initialize the context and EditText fields for card number, expiry date, and CVV.
     *
     * @param context   The context to start the PaymentVerificationActivity.
     * @param cartNumber The EditText for the card number.
     * @param dateCart The EditText for the card expiry date.
     * @param cvvCart The EditText for the CVV code.
     */
    public CompletePaymentListener(Context context, EditText cartNumber, EditText dateCart, EditText cvvCart){
        this.context = context;
        this.cartNumber = cartNumber;
        this.dateCart = dateCart;
        this.cvvCart = cvvCart;
    }

    /**
     * Handles touch events on the complete payment button, changes button image based on touch state,
     * validates the input, and starts the PaymentVerificationActivity if the validation is successful.
     *
     * @param v The view that was touched.
     * @param event The motion event containing the touch details.
     * @return boolean Whether the event was handled.
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        ViewHandler viewHandler = new ViewHandler(context);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // When the button is pressed, show the pressed state image
                Log.d("CompletePaymentListener", "onTouch: Button pressed.");
                viewHandler.setImageOnScreen(v.findViewById(R.id.complete_payment), CartResources.PAYMENT_BUTTON_IMAGE_CLICK);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // When the button is released or touch is canceled, revert to the normal image
                Log.d("CompletePaymentListener", "onTouch: Button released or touch canceled.");
                viewHandler.setImageOnScreen(v.findViewById(R.id.complete_payment), CartResources.PAYMENT_BUTTON_IMAGE);
                break;
        }

        // Validate the input and proceed if everything is valid
        if (validateCartNumber() && validateCartDate() && validateCvvCart()) {

            // Store the validated card details for use in the next activity
            PaymentCard.cardNumber = cartNumber.getText().toString();
            PaymentCard.expiryDate = dateCart.getText().toString();
            PaymentCard.cvv = cvvCart.getText().toString();

            // Start the PaymentVerificationActivity
            Log.d("CompletePaymentListener", "onTouch: Starting PaymentVerificationActivity.");
            Intent intent = new Intent(context, PaymentVerificationActivity.class);
            context.startActivity(intent);
        }

        return false;
    }

    /**
     * Validates the card number.
     * The card number must be 16 digits long and contain only numbers.
     *
     * @return boolean Whether the card number is valid.
     */
    private boolean validateCartNumber(){
        String number = cartNumber.getText().toString();

        if (number.matches("\\d+") && number.length() == 16){
            cartNumber.getBackground().mutate().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
            Log.d("CompletePaymentListener", "validateCartNumber: Card number is valid.");
            return true;
        } else {
            cartNumber.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            Log.d("CompletePaymentListener", "validateCartNumber: Card number is invalid.");
        }
        return false;
    }

    /**
     * Validates the expiry date of the card.
     * The date must be in the format MM/YY and must not be in the past.
     *
     * @return boolean Whether the expiry date is valid.
     */
    private boolean validateCartDate(){

        String number = dateCart.getText().toString();

        if (!number.isEmpty() && number.length() <= 5 && number.contains("/")) {
            // Split the date into month and year
            String[] parts = number.split("/");
            int month = Integer.parseInt(parts[0]); // MM
            int year = Integer.parseInt(parts[1]) + 2000; // Convert YY to full year

            // Get the current month and year
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);
            int currentMonth = calendar.get(Calendar.MONTH) + 1; // Months are 0-based

            if (number.matches("(0[1-9]|1[0-2])/\\d{2}") &&
                    (year > currentYear || (year == currentYear && month >= currentMonth))) {
                dateCart.getBackground().mutate().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
                Log.d("CompletePaymentListener", "validateCartDate: Expiry date is valid.");
                return true;
            } else {
                dateCart.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                Log.d("CompletePaymentListener", "validateCartDate: Expiry date is invalid.");
            }
        } else {
            dateCart.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            Log.d("CompletePaymentListener", "validateCartDate: Expiry date format is invalid.");
        }

        return false;
    }

    /**
     * Validates the CVV number.
     * The CVV must be 3 digits long and contain only numbers.
     *
     * @return boolean Whether the CVV is valid.
     */
    private boolean validateCvvCart(){
        String number = cvvCart.getText().toString();

        if (number.matches("\\d+") && number.length() == 3){
            cvvCart.getBackground().mutate().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
            Log.d("CompletePaymentListener", "validateCvvCart: CVV is valid.");
            return true;
        } else {
            cvvCart.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            Log.d("CompletePaymentListener", "validateCvvCart: CVV is invalid.");
        }
        return false;
    }
}

