package com.jiromo5.donerhome.viewmodel.shopping;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.*;
import android.view.*;
import android.widget.*;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.CartResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.menu.OrderDetails;
import com.jiromo5.donerhome.viewmodel.menu.OrderState;
import com.jiromo5.donerhome.utils.CartStorage;

/**
 * This class is responsible for managing the shopping cart functionality in the application.
 * It allows adding items to the cart, removing items from the cart, and displaying the
 * contents of the cart in a user-friendly way.
 */
public class CartManager {

    // Context for accessing application resources and system services
    private Context context;

    // Views related to UI components in the cart screen
    private TextView welcome; // Welcome message displayed in the cart
    private ImageView logo; // Logo image displayed in the cart
    private ImageButton payButton; // Button to initiate payment

    // Arrays for holding views related to each order item in the cart
    private FrameLayout[] frameLayout; // FrameLayouts for displaying order items
    private ImageView colaOrderImageView; // ImageView displaying the order image (e.g., for cola)
    private ImageButton[] removeOrderButton; // Buttons to remove individual orders
    private TextView priceView; // TextView for displaying the price of an item
    private Spinner countOfItem; // Spinner for selecting the quantity of an item
    private ViewHandler viewHandler; // Utility class for setting images and views on the screen

    // Views and fields for displaying the total price
    private TextView totalPriceView; // TextView for displaying the total price of the cart
    private float totalPrice; // Total price of the cart

    /**
     * Constructs a CartManager instance for managing the shopping cart.
     *
     * @param context The context used to access resources and system services.
     * @param welcome The TextView for the welcome message.
     * @param logo The ImageView for the logo.
     * @param totalPriceView The TextView for displaying the total price.
     * @param payButton The ImageButton for the payment button.
     */
    public CartManager(Context context, TextView welcome, ImageView logo, TextView totalPriceView, ImageButton payButton) {
        this.context = context;
        this.welcome = welcome;
        this.logo = logo;
        this.totalPriceView = totalPriceView;
        this.payButton = payButton;

        viewHandler = new ViewHandler(context);

        // Initialize the total price in the OrderDetails class
        OrderDetails.totalPrice = 0;
    }

    /**
     * Adds items to the cart by iterating through the orders for cheeseburgers and cola.
     * For each order, it creates the corresponding views (image, price, remove button, and item count spinner)
     * and adds them to the order list layout.
     *
     * @param orderList The LinearLayout where the items will be added to.
     */

    public void addItemToCart(LinearLayout orderList){
        // If there are orders for both cheeseburgers and cola, increment the total order quantity
        if (OrderDetails.cheeseburgerOrder.size() > 0 & OrderDetails.colaSizeSOrder.size() > 0) {
            OrderDetails.orderQuantity += 1;
            Log.d("CartManager", "Incremented order quantity: " + OrderDetails.orderQuantity);
        }

        // Initialize the arrays for holding FrameLayouts and ImageButtons based on the order quantity
        frameLayout = new FrameLayout[OrderDetails.orderQuantity];
        removeOrderButton = new ImageButton[OrderDetails.orderQuantity];

        // Iterate through all the items in the order
        for (int i = 0; i < OrderDetails.orderQuantity; i ++) {

            // If there is an order for cola, create and add its views
            if (OrderDetails.colaSizeSOrder.size() > 0) {
                if (OrderDetails.colaSizeSOrder.containsKey(i)) {
                    Log.d("CartManager", "Adding cola order at position " + i);
                    createImageOrder("cola");
                    createRemoveItemButton(i);
                    createPriceView("cola", OrderDetails.colaSizeSOrder.get(i));
                    createCountOfItemView();
                    orderList.addView(createFrameLayout(i));
                }
            }

            // If there is an order for cheeseburgers, create and add its views
            if (OrderDetails.cheeseburgerOrder.size() > 0) {
                if (OrderDetails.cheeseburgerOrder.containsKey(i)){
                    Log.d("CartManager", "Adding cheeseburger order at position " + i);
                    createImageOrder("cheeseburger");
                    createRemoveItemButton(i);
                    createPriceView("cheeseburger", OrderDetails.cheeseburgerOrder.get(i));
                    createCountOfItemView();
                    orderList.addView(createFrameLayout(i));
                }
            }
        }
    }


    /**
     * Removes an item from the cart based on the button clicked. It checks whether the order is for cola or cheeseburger
     * and removes the corresponding item from the order list. After removal, it decreases the order quantity
     * and removes the corresponding view from the `orderList` layout.
     *
     * @param orderList The LinearLayout containing the items to be removed.
     */
    public void removeItemFromCart(LinearLayout orderList){
        // Iterate through all remove order buttons to set up listeners
        for (int i = 0; i < removeOrderButton.length; i ++) {
            int finalI = i;

            // Log the current order number
            Log.d("CartManager", "Number of order: " + finalI);

            // Set click listener to remove the item from the cart
            removeOrderButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Log which button was clicked
                    Log.d("CartManager", "Click to remove button #" + finalI);

                    // Check if the order is for cola and remove it
                    if (OrderDetails.colaSizeSOrder.containsKey(finalI)) {
                        Log.d("CartManager", "Removing cola order #" + finalI);
                        OrderDetails.colaSizeSOrder.remove(finalI);
                        OrderDetails.orderQuantity--;
                    }
                    // Check if the order is for cheeseburgers and remove it
                    else if (OrderDetails.cheeseburgerOrder.containsKey(finalI)){
                        Log.d("CartManager", "Removing cheeseburger order #" + finalI);
                        OrderDetails.cheeseburgerOrder.remove(finalI);
                        OrderDetails.orderQuantity--;
                    }

                    // Remove the corresponding view from the layout
                    orderList.removeView(frameLayout[finalI]);
                }
            });
        }
    }


    /**
     * Creates a new FrameLayout to display an order with an image, a remove button,
     * price, and quantity of the item.
     *
     * @param numberOfOrder The order index, used to create a unique representation.
     * @return The created FrameLayout with the added views.
     */
    private FrameLayout createFrameLayout(int numberOfOrder){
        // Create a new FrameLayout for the current order
        frameLayout[numberOfOrder] = new FrameLayout(context);

        // Set layout parameters for FrameLayout
        FrameLayout.LayoutParams frameLayoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                (int) context.getResources().getDimension(R.dimen.frame_height));  // Height 80dp
        frameLayout[numberOfOrder].setLayoutParams(frameLayoutParams);

        // Log to track the creation of the new FrameLayout
        Log.d("CartManager", "Creating FrameLayout for order #" + numberOfOrder);

        // Add views to the FrameLayout
        frameLayout[numberOfOrder].addView(colaOrderImageView); // Item image (e.g., cola)
        frameLayout[numberOfOrder].addView(removeOrderButton[numberOfOrder]); // Remove button for the item
        frameLayout[numberOfOrder].addView(priceView); // Price view for the item
        frameLayout[numberOfOrder].addView(countOfItem); // Quantity view for the item

        // Log to track which views were added to the FrameLayout
        Log.d("CartManager", "Added views to FrameLayout for order #" + numberOfOrder);

        // Return the created FrameLayout
        return frameLayout[numberOfOrder];
    }


    /**
     * Creates an ImageView to display an item image (e.g., cola or cheeseburger)
     * based on the given item name.
     *
     * @param nameOfItem The name of the item, used to determine which image to display.
     */
    private void createImageOrder(String nameOfItem){
        // Create an ImageView to display the item image
        colaOrderImageView = new ImageView(context);

        // Set layout parameters for the ImageView (width and height from resources)
        FrameLayout.LayoutParams imageParams = new FrameLayout.LayoutParams(
                (int) context.getResources().getDimension(R.dimen.cola_order_width),  // Width 350dp
                (int) context.getResources().getDimension(R.dimen.cola_order_height));  // Height 60dp
        imageParams.gravity = Gravity.CENTER;  // Center the image within its container
        colaOrderImageView.setLayoutParams(imageParams);

        // Log which item image is being set
        Log.d("CartManager", "Creating ImageView for item: " + nameOfItem);

        // Set the appropriate image based on the item name
        switch (nameOfItem) {
            case "cola":
                // Set the cola image
                viewHandler.setImageOnScreen(colaOrderImageView, CartResources.COLA_IMAGE);
                break;
            case "cheeseburger":
                // Set the cheeseburger image
                viewHandler.setImageOnScreen(colaOrderImageView, CartResources.CHEESEBURGER_IMAGE);
                break;
            default:
                // Log if an unrecognized item name is passed
                Log.w("CartManager", "Unrecognized item: " + nameOfItem);
                break;
        }

        // Set the scale type for the ImageView to center-crop the image
        colaOrderImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }


    /**
     * Creates an ImageButton to serve as a remove item button, with specific layout
     * parameters, image, and scaling set for each order.
     *
     * @param numberOfOrder The index of the order, used to associate the remove button
     *                      with a particular item in the order.
     */
    private void createRemoveItemButton(int numberOfOrder) {
        // Create an ImageButton for removing an item from the order
        removeOrderButton[numberOfOrder] = new ImageButton(context);

        // Set layout parameters for the remove button (width and height from resources)
        FrameLayout.LayoutParams removeButtonParams = new FrameLayout.LayoutParams(
                (int) context.getResources().getDimension(R.dimen.remove_button_width),  // Width 25dp
                (int) context.getResources().getDimension(R.dimen.remove_button_height)   // Height 35dp
        );

        // Set right margin for the remove button (to space it away from the edge)
        removeButtonParams.rightMargin = dpToPx(context, 30); // Convert dp to px for right margin
        removeButtonParams.gravity = Gravity.END | Gravity.CENTER_VERTICAL;  // Align button to the right and center vertically

        // Apply the layout parameters to the remove button
        removeOrderButton[numberOfOrder].setLayoutParams(removeButtonParams);

        // Set the background color of the button to transparent
        removeOrderButton[numberOfOrder].setBackgroundColor(Color.TRANSPARENT);

        // Remove padding inside the button
        removeOrderButton[numberOfOrder].setPadding(0, 0, 0, 0);

        // Set the scale type for the image button (fit to the bounds of the button)
        removeOrderButton[numberOfOrder].setScaleType(ImageView.ScaleType.FIT_XY);

        // Set the image on the button (use remove button image from resources)
        viewHandler.setImageOnScreen(removeOrderButton[numberOfOrder], CartResources.REMOVE_BUTTON_IMAGE);

        // Log the creation of the remove item button for the order
        Log.d("CartManager", "Remove button created for order #" + numberOfOrder);
    }



    /**
     * Creates a TextView to display the price for an item, calculates the price
     * based on the item count, and updates the total price.
     *
     * @param nameOfItem The name of the item (either "cola" or "cheeseburger").
     * @param count The quantity of the item to calculate the total price.
     */
    private void createPriceView(String nameOfItem, int count) {
        // Define price constants for cola and cheeseburger
        int priceCola = 1;
        float priceCheeseburger = 2.25f;

        // Create TextView for showing the price of the item
        priceView = new TextView(context);

        // Set the layout parameters for the price view (wrap content)
        FrameLayout.LayoutParams priceParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        // Position the price view to the right and center vertically
        priceParams.gravity = Gravity.END | Gravity.CENTER_VERTICAL;

        // Set the right margin to space the price view from other elements
        priceParams.rightMargin = dpToPx(context, 85);

        // Apply the layout parameters to the price view
        priceView.setLayoutParams(priceParams);

        // Calculate price and update total price based on the item name
        switch (nameOfItem) {
            case "cola":
                priceCola = priceCola * count;
                totalPrice = totalPrice + priceCola;  // Update the total price
                priceView.setText(String.valueOf(priceCola) + "$");  // Set the price text
                totalPriceView.setText(String.valueOf(totalPrice) + "$");  // Update the total price view
                break;
            case "cheeseburger":
                priceCheeseburger = priceCheeseburger * count;
                totalPrice = totalPrice + priceCheeseburger;  // Update the total price
                priceView.setText(String.valueOf(priceCheeseburger) + "$");  // Set the price text
                totalPriceView.setText(String.valueOf(totalPrice) + "$");  // Update the total price view
                break;
        }

        // Set text color and text size for the price view
        priceView.setTextColor(Color.BLACK);
        priceView.setTextSize(22);

        // Log the price information for debugging
        Log.d("CartManager", "Price for " + nameOfItem + ": " + priceView.getText());

        // Update the total price in OrderDetails
        OrderDetails.totalPrice = totalPrice;
    }


    /**
     * Creates a Spinner to show the count of an item, sets its layout parameters,
     * padding, and adapter, and configures the dropdown view.
     */
    private void createCountOfItemView() {
        // Create a Spinner to display the count of the item
        countOfItem = new Spinner(context);

        // Set LayoutParams for the Spinner
        FrameLayout.LayoutParams spinnerParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        // Set margins and gravity to center the Spinner
        spinnerParams.setMargins(dpToPx(context, 35), 0, 0, 0); // Equivalent to android:layout_marginStart="35dp"
        spinnerParams.gravity = Gravity.CENTER; // Equivalent to android:layout_gravity="center"

        // Apply the layout parameters to the Spinner
        countOfItem.setLayoutParams(spinnerParams);

        // Set padding for the Spinner (start and end)
        countOfItem.setPadding(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, context.getResources().getDisplayMetrics()), // paddingStart
                0,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, context.getResources().getDisplayMetrics()), // paddingEnd
                0
        );

        // Create an adapter for the Spinner using an array of items (defined in strings.xml)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                context,
                R.array.spinner_items, // Reference to the array resource
                R.layout.spinner_item // Custom layout for spinner items
        );

        // Set the dropdown view resource
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Standard dropdown view

        // Set the adapter for the Spinner
        countOfItem.setAdapter(adapter);

        // Log the configuration of the Spinner for debugging
        Log.d("CartManager", "Count Spinner configured with adapter: " + adapter.getCount() + " items.");
    }


    /**
     * Converts dp (density-independent pixels) to px (pixels) based on the device's screen density.
     *
     * @param context The application context, used to access display metrics.
     * @param dp The value in dp to be converted to px.
     * @return The equivalent value in pixels (px).
     */
    private int dpToPx(Context context, int dp) {
        // Get the display metrics from the context
        Resources resources = context.getResources();
        float density = resources.getDisplayMetrics().density; // Get the screen density factor

        // Convert dp to pixels and return the result as an integer
        return (int) (dp * density);
    }


    /**
     * Toggles the visibility of UI elements based on the order quantity.
     *
     * If there are items in the order (i.e., the order quantity is greater than 0),
     * the welcome message and logo will be hidden, and the payment button and total price view will be shown.
     *
     * If the order is empty (i.e., the order quantity is 0),
     * the welcome message and logo will be shown, and the payment button and total price view will be hidden.
     */
    public void toggleVisibility() {
        // Check if there are any items in the order
        if (OrderDetails.orderQuantity > 0) {
            // Hide the welcome message and logo, show the payment button and total price view
            welcome.setVisibility(View.INVISIBLE);
            logo.setVisibility(View.INVISIBLE);
            payButton.setVisibility(View.VISIBLE);
            totalPriceView.setVisibility(View.VISIBLE);
        } else {
            // Show the welcome message and logo, hide the payment button and total price view
            welcome.setVisibility(View.VISIBLE);
            logo.setVisibility(View.VISIBLE);
            payButton.setVisibility(View.INVISIBLE);
            totalPriceView.setVisibility(View.INVISIBLE);
        }
    }


    /**
     * This method restores the cart data from the saved products stored in `CartStorage`.
     * It parses each product entry, extracting the item name, quantity, and count of each item.
     * Then, it updates the `OrderDetails` with the restored data for Cola and Cheeseburger orders.
     */
    public static void restoreCart() {
        // Get all the products stored in CartStorage.
        String[] allProducts = CartStorage.getAllProducts();

        String line = "";

        // Variables for parsing each product entry
        int orderQuantity = 0;
        String nameOfItem = "";
        int countOfItem = 0;

        // Loop through each product entry in the stored cart data
        for (int i = 0; i < allProducts.length; i++) {
            line = allProducts[i];

            // Example format: "0_Cola Size S_1"
            for (int j = 0; j < line.length(); j++) {
                if (j == 0) {
                    // Extract the order quantity (the first character before '_')
                    orderQuantity = Integer.parseInt(String.valueOf(line.charAt(j)));
                    j++; // Move to the next character
                    continue;
                }

                // Check for the separator '_', and then extract the item name
                if (line.charAt(j) != '_') {
                    nameOfItem += String.valueOf(line.charAt(j));
                } else {
                    // After the name, extract the count of the item (next character after '_')
                    countOfItem = Integer.parseInt(String.valueOf(line.charAt(j + 1)));
                    break;
                }
            }

            // Determine the item type and update the corresponding order details
            if (nameOfItem.equals("Cola Size S")) {
                OrderState.countOfItem = countOfItem;  // Store count of item in OrderState
                OrderDetails.colaSizeSOrder.put(orderQuantity, countOfItem);  // Add to Cola order details
                OrderDetails.orderQuantity = orderQuantity;  // Update the total order quantity
            } else if (nameOfItem.equals("Cheeseburger")) {
                OrderState.countOfItem = countOfItem;  // Store count of item in OrderState
                OrderDetails.cheeseburgerOrder.put(orderQuantity, countOfItem);  // Add to Cheeseburger order details
                OrderDetails.orderQuantity = orderQuantity;  // Update the total order quantity
            }

            // Reset variables for the next iteration
            line = "";
            orderQuantity = 0;
            nameOfItem = "";
            countOfItem = 0;
        }
    }
}
