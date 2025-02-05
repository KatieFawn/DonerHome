package com.jiromo5.donerhome.main.shopping;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.jiromo5.donerhome.main.menu.OrderDetails;

public class PaymentManager {

    private ImageButton backToMenuButton;
    private ImageButton tryAgainButton;
    private ImageView processingView;
    private ImageView successView;
    private ImageView cancelView;

    public PaymentManager(ImageButton backToMenuButton, ImageButton tryAgainButton,
                          ImageView processingView, ImageView successView, ImageView cancelView) {
        this.backToMenuButton = backToMenuButton;
        this.tryAgainButton = tryAgainButton;
        this.processingView = processingView;
        this.successView = successView;
        this.cancelView = cancelView;
    }

    public void viewController(String statusRequest){
        if (statusRequest == "wait"){
            processingView.setVisibility(View.VISIBLE);
        } else if (statusRequest == "success"){
            processingView.setVisibility(View.INVISIBLE);

            successView.setVisibility(View.VISIBLE);
            backToMenuButton.setVisibility(View.VISIBLE);

            OrderDetails.orderQuantity = 0;
            OrderDetails.totalPrice = 0;
            OrderDetails.colaSizeSOrder.clear();
            OrderDetails.cheeseburgerOrder.clear();
        } else if (statusRequest == "error"){
            processingView.setVisibility(View.INVISIBLE);

            cancelView.setVisibility(View.VISIBLE);
            tryAgainButton.setVisibility(View.VISIBLE);
        }

    }

}
