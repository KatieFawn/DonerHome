package com.jiromo5.donerhome.main.shopping;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.activities.main.shopping.CartActivity;
import com.jiromo5.donerhome.main.menu.OrderDetails;

public class CartManager {

    private Context context;

    private TextView welcome;
    private ImageView logo;
    private ImageButton payButton;

    private FrameLayout[] frameLayout;
    private ImageView colaOrderImageView;
    private ImageButton[] removeOrderButton;
    private TextView priceView;
    private Spinner countOfItem;

    private TextView totalPriceView;
    private float totalPrice;


    public CartManager(Context context, TextView welcome, ImageView logo, TextView totalPriceView, ImageButton payButton){
        this.context = context;
        this.welcome = welcome;
        this.logo = logo;
        this.totalPriceView = totalPriceView;
        this.payButton = payButton;

        OrderDetails.totalPrice = 0;

        frameLayout = new FrameLayout[OrderDetails.countOfOrder];
        removeOrderButton = new ImageButton[OrderDetails.countOfOrder];
    }

    public void addItemToCart(LinearLayout orderList){

        for (int i = 0; i < OrderDetails.countOfOrder; i ++) {
            if (OrderDetails.colaSizeSOrder.size() > 0) {
                if (OrderDetails.colaSizeSOrder.containsKey(i)) {
                    createImageOrder("cola");
                    createRemoveItemButton(i);
                    createPriceView("cola", OrderDetails.colaSizeSOrder.get(i));
                    createCountOfItemView();
                    orderList.addView(createFrameLayout(i));
                }
            }

            if (OrderDetails.cheeseburgerOrder.size() > 0) {
                if (OrderDetails.cheeseburgerOrder.containsKey(i)) {
                    createImageOrder("cheeseburger");
                    createRemoveItemButton(i);
                    createPriceView("cheeseburger", OrderDetails.cheeseburgerOrder.get(i));
                    createCountOfItemView();
                    orderList.addView(createFrameLayout(i));
                }
            }
        }
    }

    public void removeItemFromCart(LinearLayout orderList){
        for (int i = 0; i < removeOrderButton.length; i ++){
            int finalI = i;
            Log.d("CartManager", "Number of order: " + finalI);
            removeOrderButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("CartManager", "Click to remove button #" + finalI);
                    if (OrderDetails.colaSizeSOrder.containsKey(finalI)) {
                        OrderDetails.colaSizeSOrder.remove(finalI);
                        OrderDetails.countOfOrder = OrderDetails.countOfOrder - 1;
                    } else if (OrderDetails.cheeseburgerOrder.containsKey(finalI)){
                        OrderDetails.cheeseburgerOrder.remove(finalI);
                        OrderDetails.countOfOrder = OrderDetails.countOfOrder - 1;
                    }
                    orderList.removeView(frameLayout[finalI]);

                }
            });
        }

        //Intent intent = new Intent(context, CartActivity.class);
        //context.startActivity(intent);
    }

    private FrameLayout createFrameLayout(int numberOfOrder){
        // Create FrameLayout.
        frameLayout[numberOfOrder] = new FrameLayout(context);
        FrameLayout.LayoutParams frameLayoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                (int) context.getResources().getDimension(R.dimen.frame_height));  // Высота 80dp
        frameLayout[numberOfOrder].setLayoutParams(frameLayoutParams);

        frameLayout[numberOfOrder].addView(colaOrderImageView);
        frameLayout[numberOfOrder].addView(removeOrderButton[numberOfOrder]);
        frameLayout[numberOfOrder].addView(priceView);
        frameLayout[numberOfOrder].addView(countOfItem);

        return frameLayout[numberOfOrder];
    }

    private void createImageOrder(String nameOfItem){
        // Create ImageView
        colaOrderImageView = new ImageView(context);
        FrameLayout.LayoutParams imageParams = new FrameLayout.LayoutParams(
                (int) context.getResources().getDimension(R.dimen.cola_order_width),  // Ширина 350dp
                (int) context.getResources().getDimension(R.dimen.cola_order_height));  // Высота 60dp
        imageParams.gravity = Gravity.CENTER;  // Центрируем изображение
        colaOrderImageView.setLayoutParams(imageParams);
        switch (nameOfItem) {
            case "cola":
                colaOrderImageView.setBackgroundResource(R.drawable.cola_cart);
                break;
            case "cheeseburger":
                colaOrderImageView.setBackgroundResource(R.drawable.cheeseburger_cart);
                break;
        }
        colaOrderImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    private void createRemoveItemButton(int numberOfOrder){
        // Create ImageButton
        removeOrderButton[numberOfOrder] = new ImageButton(context);
        FrameLayout.LayoutParams removeButtonParams = new FrameLayout.LayoutParams(
                (int) context.getResources().getDimension(R.dimen.remove_button_width),  // Ширина 25dp
                (int) context.getResources().getDimension(R.dimen.remove_button_height));  // Высота 35dp
        removeButtonParams.rightMargin = dpToPx(context, 30); // Устанавливаем отступ через setMargins
        removeButtonParams.gravity = Gravity.END | Gravity.CENTER_VERTICAL;
        removeOrderButton[numberOfOrder].setLayoutParams(removeButtonParams);
        removeOrderButton[numberOfOrder].setBackgroundResource(R.drawable.remove_button);
    }

    private void createPriceView(String nameOfItem, int count){
        // Create TextView for show a price.
        int priceCola = 1;
        float priceCheeseburger = 2.25f;

        priceView = new TextView(context);
        FrameLayout.LayoutParams priceParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        priceParams.gravity = Gravity.END | Gravity.CENTER_VERTICAL; // Позиционирование
        priceParams.rightMargin = dpToPx(context, 85); // Устанавливаем отступ через setMargins
        priceView.setLayoutParams(priceParams);
        switch (nameOfItem) {
            case "cola":
                priceCola = priceCola * count;
                totalPrice = totalPrice + priceCola;
                priceView.setText(String.valueOf(priceCola) + "$");
                totalPriceView.setText(String.valueOf(totalPrice) + "$");
                break;
            case "cheeseburger":
                priceCheeseburger = priceCheeseburger * count;
                totalPrice = totalPrice + priceCheeseburger;
                priceView.setText(String.valueOf(priceCheeseburger) + "$");
                totalPriceView.setText(String.valueOf(totalPrice) + "$");
                break;
        }
        priceView.setTextColor(Color.BLACK);
        priceView.setTextSize(22);
        OrderDetails.totalPrice = totalPrice;
    }

    private void createCountOfItemView(){
        //Create a Spinner for show count of item
        countOfItem = new Spinner(context);

        // Настройка LayoutParams
        FrameLayout.LayoutParams spinnerParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        spinnerParams.setMargins(dpToPx(context, 35), 0, 0, 0); // Соответствует android:layout_marginStart="35dp"
        spinnerParams.gravity = Gravity.CENTER; // Соответствует android:layout_gravity="center"
        countOfItem.setLayoutParams(spinnerParams);

        // Установка паддингов (paddingStart, paddingEnd)
        countOfItem.setPadding(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, context.getResources().getDisplayMetrics()), // paddingStart
                0,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, context.getResources().getDisplayMetrics()), // paddingEnd
                0
        );

        // Настройка адаптера
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                context,
                R.array.spinner_items,
                R.layout.spinner_item // Ваш кастомный макет
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Выпадающий вид
        countOfItem.setAdapter(adapter);
    }

    private int dpToPx(Context context, int dp) {
        Resources resources = context.getResources();
        float density = resources.getDisplayMetrics().density;
        return (int) (dp * density);
    }

    public void toggleVisibility() {
        if (OrderDetails.countOfOrder > 0) {
            welcome.setVisibility(View.INVISIBLE);
            logo.setVisibility(View.INVISIBLE);
            payButton.setVisibility(View.VISIBLE);
            totalPriceView.setVisibility(View.VISIBLE);
        } else {
            welcome.setVisibility(View.VISIBLE);
            logo.setVisibility(View.VISIBLE);
            payButton.setVisibility(View.INVISIBLE);
            totalPriceView.setVisibility(View.INVISIBLE);
        }
    }
}
