package com.jiromo5.donerhome.viewmodel.shopping;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.jiromo5.donerhome.viewmodel.menu.OrderDetails;

/**
 * PaymentManager manages the UI elements related to the payment status.
 * Based on the request status (waiting, success, or error), it updates the visibility of UI elements.
 */

public class PaymentManager {

    private ImageButton backToMenuButton;
    private ImageButton tryAgainButton;
    private ImageView processingView;
    private ImageView successView;
    private ImageView cancelView;

    // Constants for status types
    private static final String STATUS_WAIT = "wait";
    private static final String STATUS_SUCCESS = "success";
    private static final String STATUS_ERROR = "error";

    /**
     * Constructor for PaymentManager. Initializes UI elements to reflect the payment status.
     *
     * @param backToMenuButton button to go back to the menu
     * @param tryAgainButton button for retrying
     * @param processingView image for "processing" state
     * @param successView image for "success" state
     * @param cancelView image for "cancel" state
     */

    public PaymentManager(ImageButton backToMenuButton, ImageButton tryAgainButton,
                          ImageView processingView, ImageView successView, ImageView cancelView) {
        this.backToMenuButton = backToMenuButton;
        this.tryAgainButton = tryAgainButton;
        this.processingView = processingView;
        this.successView = successView;
        this.cancelView = cancelView;
    }

    /**
     * Manages the display of various UI elements based on the request status.
     *
     * @param statusRequest the status of the request (e.g., "wait", "success", "error")
     */
    public void viewController(String statusRequest){
        Log.d("PaymentManager", "Status request received: " + statusRequest);  // Log the status request
        if (statusRequest.equals(STATUS_WAIT)){
            processingView.setVisibility(View.VISIBLE);
        } else if (statusRequest.equals(STATUS_SUCCESS)){
            processingView.setVisibility(View.INVISIBLE);

            successView.setVisibility(View.VISIBLE);
            backToMenuButton.setVisibility(View.VISIBLE);

            OrderDetails.orderQuantity = 0;
            OrderDetails.totalPrice = 0;
            OrderDetails.colaSizeSOrder.clear();
            OrderDetails.cheeseburgerOrder.clear();
        } else if (statusRequest.equals(STATUS_ERROR)){
            processingView.setVisibility(View.INVISIBLE);

            cancelView.setVisibility(View.VISIBLE);
            tryAgainButton.setVisibility(View.VISIBLE);
        }

    }

}
