package com.jiromo5.donerhome.main.shopping.listeners;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.EditText;

import com.jiromo5.donerhome.activities.main.shopping.PaymentVerificationActivity;

import java.util.Calendar;

public class CompletePaymentListener implements View.OnClickListener {

    private Context context;
    private EditText cartNumber;
    private EditText dateCart;
    private EditText cvvCart;

    public CompletePaymentListener(Context context, EditText cartNumber, EditText dateCart, EditText cvvCart){
        this.context = context;
        this.cartNumber = cartNumber;
        this.dateCart = dateCart;
        this.cvvCart = cvvCart;
    }

    @Override
    public void onClick(View view) {
        if (validateCartNumber() & validateCartDate() & validateCvvCart()){
            Intent intent = new Intent(context, PaymentVerificationActivity.class);
            context.startActivity(intent);
        }
    }

    private boolean validateCartNumber(){
        String number = cartNumber.getText().toString();

        if (number.matches("\\d+") && number.length() == 16){
            cartNumber.getBackground().mutate().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
            return true;
        } else {
            cartNumber.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }
        return false;
    }

    private boolean validateCartDate(){

        String number = dateCart.getText().toString();

        if (!number.isEmpty()) {
            // Разделяем дату на месяц и год
            String[] parts = number.split("/");
            int month = Integer.parseInt(parts[0]); // MM
            int year = Integer.parseInt(parts[1]) + 2000; // Преобразуем YY в полный год

            // Получаем текущий месяц и год
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);
            int currentMonth = calendar.get(Calendar.MONTH) + 1; // Месяцы начинаются с 0

            if (number.matches("(0[1-9]|1[0-2])/\\d{2}") &&
                    (year > currentYear || (year == currentYear && month >= currentMonth))) {
                dateCart.getBackground().mutate().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
                return true;
            } else {
                dateCart.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            }
        } else {
            dateCart.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }


        // Делаем проверку даты. Затем можно сделать меню с надписью "платёж обрабатывается" с
        // задержкой в 3 секунды, отображать успех или провал операции исходя из ответа сервера.
        // Готовим базы данных, и затем подготавливаем андроид приложение и веб-сервер на взаимодействие.

        //24.01 - завершили PaymentActivity и сейчас делаем PaymentVerificationActivity !!!
        return false;
    }

    private boolean validateCvvCart(){
        String number = cvvCart.getText().toString();

        if (number.matches("\\d+") && number.length() == 3){
            cvvCart.getBackground().mutate().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
            return true;
        } else {
            cvvCart.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }
        return false;
    }
}
