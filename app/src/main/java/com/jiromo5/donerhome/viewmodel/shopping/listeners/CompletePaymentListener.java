package com.jiromo5.donerhome.viewmodel.shopping.listeners;

import android.content.Context;
import android.content.Intent;
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

public class CompletePaymentListener implements View.OnTouchListener {

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
    public boolean onTouch(View v, MotionEvent event) {

        ViewHandler viewHandler = new ViewHandler(context);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Когда нажали на изображение, показываем pressed состояние
                viewHandler.setImageOnScreen(v.findViewById(R.id.complete_payment), CartResources.PAYMENT_BUTTON_IMAGE_CLICK);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // Когда отпустили или отменили нажатие, возвращаем нормальное состояние
                viewHandler.setImageOnScreen(v.findViewById(R.id.complete_payment), CartResources.PAYMENT_BUTTON_IMAGE);
                break;
        }

        if (validateCartNumber() & validateCartDate() & validateCvvCart()) {

            PaymentCard.cardNumber = cartNumber.getText().toString();
            PaymentCard.expiryDate = dateCart.getText().toString();
            PaymentCard.cvv = cvvCart.getText().toString();

            Intent intent = new Intent(context, PaymentVerificationActivity.class);
            context.startActivity(intent);
        }

        return false;
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

        if (!number.isEmpty() & number.length() <= 5 & number.contains("/")) {
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
