package com.jiromo5.donerhome.activities.home.shopping;

import android.os.*;
import android.util.Log;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.*;
import com.jiromo5.donerhome.data.state.paths.CartResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.menu.OrderDetails;
import com.jiromo5.donerhome.viewmodel.menu.listeners.BackToMenuListener;
import com.jiromo5.donerhome.viewmodel.shopping.PaymentManager;
import com.jiromo5.donerhome.viewmodel.shopping.listeners.BackToCartListener;
import com.jiromo5.donerhome.service.payment.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * PaymentVerificationActivity handles the user interface for payment verification during the checkout process.
 * It includes payment status checks, order creation, and display of success, processing, or cancellation views.
 */

public class PaymentVerificationActivity extends AppCompatActivity {


    // UI elements for payment verification screen
    private ImageButton backToMenuButton; // Button to navigate back to the menu
    private ImageButton tryAgainButton;   // Button to retry payment
    private ImageView processingView;    // Image view showing payment is being processed
    private ImageView successView;       // Image view showing payment success
    private ImageView cancelView;        // Image view showing payment cancelation

    private PaymentManager paymentManager; // Payment manager to handle payment flow

    private final Handler handler = new Handler(); // Handler to update UI at regular intervals
    private final Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {
            updateUI(); // Method to update the user interface based on payment status
            handler.postDelayed(this, 3000); // Repeat the update every 3 seconds
            Log.d("PaymentVerificationActivity", "UI updated.");
        }
    };

    /**
     * Updates the user interface based on the current payment status.
     * This method uses the PaymentManager to update the view with the latest order status.
     */
    private void updateUI() {
        paymentManager.viewController(RequestStatus.orderStatus); // Update UI based on order status
        Log.d("PaymentVerificationActivity", "UI updated with order status: " + RequestStatus.orderStatus);
    }

    /**
     * Called when the activity is resumed. Starts the periodic UI update using a handler.
     */
    @Override
    protected void onResume() {
        super.onResume();
        handler.post(updateRunnable); // Start periodic UI updates
        Log.i("PaymentVerificationActivity", "Activity resumed, UI updates started.");
    }

    /**
     * Called when the activity is paused. Stops the periodic UI update.
     */
    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(updateRunnable); // Stop periodic UI updates when the activity is paused
        Log.i("PaymentVerificationActivity", "Activity paused, UI updates stopped.");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_verification_activity);
        overridePendingTransition(0, 0);

        Log.i("PaymentVerificationActivity", "Activity created.");

        // Initialize UI elements
        backToMenuButton = findViewById(R.id.back_to_menu);
        tryAgainButton = findViewById(R.id.try_again_button);
        processingView = findViewById(R.id.payment_is_processed);
        successView = findViewById(R.id.payment_is_success);
        cancelView = findViewById(R.id.payment_is_cancel);

        // Initialize PaymentManager
        paymentManager = new PaymentManager(backToMenuButton, tryAgainButton, processingView, successView, cancelView);

        createOrder(); // Create an order based on user input
        setView(); // Set up the UI with appropriate images
        eventHandler(); // Set up event listeners for user actions
        PaymentCard.clear(); // Clear the payment card details after processing

        Log.d("PaymentVerificationActivity", "UI components initialized and events set.");
    }

    /**
     * Sets up event listeners for the buttons and actions in the payment verification screen.
     */
    private void eventHandler() {
        // Listeners for navigating back to the cart and menu
        BackToCartListener backToCartListener = new BackToCartListener(this);
        BackToMenuListener backToMenuListener = new BackToMenuListener(this);

        // Set click listeners for the buttons
        backToMenuButton.setOnClickListener(backToMenuListener);
        tryAgainButton.setOnClickListener(backToCartListener);

        Log.d("PaymentVerificationActivity", "Event handlers set for buttons.");
    }

    /**
     * Creates an order and sends it to the server for processing.
     * It sets the order details, payment information, and user address, then sends a request to the server.
     */
    private void createOrder() {
        Log.i("PaymentVerificationActivity", "Creating order.");

        // Initialize order DTO
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setUserId(UserData.userId); // Set user ID
        ordersDTO.setOrderDate(Calendar.getInstance().getTime()); // Set the current date as the order date
        ordersDTO.setStatus("pending"); // Set the initial status of the order as 'pending'
        ordersDTO.setTotalPrice(new BigDecimal(OrderDetails.totalPrice)); // Set total price based on the cart
        ordersDTO.setPaymentMethod("credit_card"); // Set payment method to credit card

        // Set user address details
        for (int i = 0; i < 5; i++) {
            if (UserAddress.addressName[i].equals(PaymentAddress.paymentAddress)) {
                ordersDTO.setStreet(UserAddress.street[i]);
                ordersDTO.setBuild(UserAddress.build[i]);
                ordersDTO.setApartment(UserAddress.apartment[i]);
                break;
            }
        }

        // Create payment card DTO
        PaymentCardDTO paymentCardDTO = new PaymentCardDTO();
        paymentCardDTO.setCardNumber(PaymentCard.cardNumber); // Set the card number
        paymentCardDTO.setExpiryDate(PaymentCard.expiryDate); // Set the card expiry date
        paymentCardDTO.setCvv(PaymentCard.cvv); // Set the card CVV

        // Prepare order request DTO
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO(ordersDTO, addItemToOrder(ordersDTO), paymentCardDTO);

        // Create order controller and send request
        OrderController orderController = new OrderController(orderRequestDTO);
        orderController.fetchNetworkData(); // Fetch network data to process the order
        orderController.handleUserAuthorization(); // Handle user authorization for payment

        Log.d("PaymentVerificationActivity", "Order created and network request sent.");
    }

    /**
     * Adds items to the order based on the details in the `OrderDetails` class.
     * @param ordersDTO The order to which the items will be added.
     * @return A list of items to be added to the order.
     */
    private List<OrderItemsDTO> addItemToOrder(OrdersDTO ordersDTO) {
        Log.i("PaymentVerificationActivity", "Adding items to the order.");

        List<OrderItemsDTO> listOfItems = new ArrayList<>();

        // Price constants
        int priceCola = 1;
        float priceCheeseburger = 2.25f;

        // Loop through order details and add items
        for (int i = 0; i < OrderDetails.orderQuantity; i++) {
            if (OrderDetails.colaSizeSOrder.size() > 0) {
                if (OrderDetails.colaSizeSOrder.containsKey(i)) {
                    // Create an item for Cola Size S
                    OrderItemsDTO orderItemsDTO = new OrderItemsDTO();
                    orderItemsDTO.setOrderId(ordersDTO.getUserId());
                    orderItemsDTO.setProductId(ProductsData.getId("Cola size S"));
                    orderItemsDTO.setQuantity(OrderDetails.colaSizeSOrder.get(i));
                    orderItemsDTO.setPrice(new BigDecimal(priceCola * OrderDetails.colaSizeSOrder.get(i)));
                    listOfItems.add(orderItemsDTO);
                }
                Log.d("PaymentVerificationActivity", "Added Cola size S to order with quantity " + OrderDetails.colaSizeSOrder.get(i));
            }

            if (OrderDetails.cheeseburgerOrder.size() > 0) {
                if (OrderDetails.cheeseburgerOrder.containsKey(i)) {
                    // Create an item for Cheeseburger
                    OrderItemsDTO orderItemsDTO = new OrderItemsDTO();
                    orderItemsDTO.setOrderId(ordersDTO.getUserId());
                    orderItemsDTO.setProductId(ProductsData.getId("Cheeseburger"));
                    orderItemsDTO.setQuantity(OrderDetails.cheeseburgerOrder.get(i));
                    orderItemsDTO.setPrice(new BigDecimal(priceCheeseburger * OrderDetails.cheeseburgerOrder.get(i)));
                    listOfItems.add(orderItemsDTO);
                }
                Log.d("PaymentVerificationActivity", "Added Cheeseburger to order with quantity " + OrderDetails.cheeseburgerOrder.get(i));
            }
        }
        return listOfItems;
    }

    /**
     * Sets up the view with images and resources related to payment verification.
     */

    private void setView(){
        Log.i("PaymentVerificationActivity", "Setting UI images.");

        ViewHandler viewHandler = new ViewHandler(this);
        viewHandler.setImageOnScreen(findViewById(R.id.back_to_menu), CartResources.BACK_TO_MENU_IMAGE);
        viewHandler.setImageOnScreen(findViewById(R.id.try_again_button), CartResources.TRY_AGAIN_IMAGE);
        viewHandler.setImageOnScreen(findViewById(R.id.payment_is_processed), CartResources.PROCESS_MESSAGE_IMAGE);
        viewHandler.setImageOnScreen(findViewById(R.id.payment_is_success), CartResources.SUCCESS_MESSAGE_IMAGE);
        viewHandler.setImageOnScreen(findViewById(R.id.payment_is_cancel), CartResources.CANCEL_MESSAGE_IMAGE);

        Log.d("PaymentVerificationActivity", "UI images set.");
    }
}
