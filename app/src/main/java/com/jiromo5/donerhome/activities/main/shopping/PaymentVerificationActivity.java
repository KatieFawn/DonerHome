package com.jiromo5.donerhome.activities.main.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.CartsData;
import com.jiromo5.donerhome.data.state.PaymentCard;
import com.jiromo5.donerhome.data.state.UserAddress;
import com.jiromo5.donerhome.data.state.UserData;
import com.jiromo5.donerhome.main.menu.OrderDetails;
import com.jiromo5.donerhome.main.menu.listeners.BackToMenuListener;
import com.jiromo5.donerhome.main.shopping.PaymentManager;
import com.jiromo5.donerhome.main.shopping.listeners.BackToCartListener;
import com.jiromo5.donerhome.main.shopping.listeners.CompletePaymentListener;
import com.jiromo5.donerhome.service.payment.OrderController;
import com.jiromo5.donerhome.service.payment.OrderItemsDTO;
import com.jiromo5.donerhome.service.payment.OrderRequestDTO;
import com.jiromo5.donerhome.service.payment.OrdersDTO;
import com.jiromo5.donerhome.service.payment.PaymentCardDTO;
import com.jiromo5.donerhome.service.payment.RequestStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PaymentVerificationActivity extends AppCompatActivity {


    private ImageButton backToMenuButton;
    private ImageButton tryAgainButton;
    private ImageView processingView;
    private ImageView successView;
    private ImageView cancelView;

    private PaymentManager paymentManager;

    private final Handler handler = new Handler();
    private final Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {
            updateUI(); // Метод для обновления интерфейса
            handler.postDelayed(this, 3000); // Повтор через 5 секунд
        }
    };

    private void updateUI(){
        paymentManager.viewController(RequestStatus.orderStatus);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.post(updateRunnable); // Запуск обновления
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(updateRunnable); // Остановка при выходе

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_verification);
        overridePendingTransition(0, 0);

        backToMenuButton = findViewById(R.id.back_to_menu);
        tryAgainButton = findViewById(R.id.try_again_button);
        processingView = findViewById(R.id.payment_is_processed);
        successView = findViewById(R.id.payment_is_success);
        cancelView = findViewById(R.id.payment_is_cancel);

        paymentManager = new PaymentManager(backToMenuButton, tryAgainButton, processingView, successView, cancelView);

        createOrder();

        eventHandler();

        PaymentCard.clear();
    }

    private void eventHandler() {
        BackToCartListener backToCartListener = new BackToCartListener(this);
        BackToMenuListener backToMenuListener = new BackToMenuListener(this);

        backToMenuButton.setOnClickListener(backToMenuListener);
        tryAgainButton.setOnClickListener(backToCartListener);
    }

    private void createOrder(){
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setUserId(UserData.userId);
        ordersDTO.setOrderDate(Calendar.getInstance().getTime());   // Дата и время заказа
        ordersDTO.setStatus("pending");            // Статус заказа
        ordersDTO.setTotalPrice(new BigDecimal(OrderDetails.totalPrice));     // Общая стоимость заказа
        ordersDTO.setShippingAddressId(1L);    // Ссылка на адрес доставки
        ordersDTO.setPaymentMethod("credit_card");

        PaymentCardDTO paymentCardDTO = new PaymentCardDTO();
        paymentCardDTO.setCardNumber(PaymentCard.cardNumber);
        paymentCardDTO.setExpiryDate(PaymentCard.expiryDate);
        paymentCardDTO.setCvv(PaymentCard.cvv);

        OrderRequestDTO orderRequestDTO = new OrderRequestDTO(ordersDTO, addItemToOrder(ordersDTO), paymentCardDTO);
        OrderController orderController = new OrderController(orderRequestDTO);

        orderController.fetchNetworkData();
        orderController.handleUserAuthorization();
    }

    private List<OrderItemsDTO> addItemToOrder(OrdersDTO ordersDTO){

        List<OrderItemsDTO> listOfItems = new ArrayList<>();

        int priceCola = 1;
        float priceCheeseburger = 2.25f;

        for (int i = 0; i < OrderDetails.orderQuantity; i ++) {
            if (OrderDetails.colaSizeSOrder.size() > 0) {
                if (OrderDetails.colaSizeSOrder.containsKey(i)) {

                    OrderItemsDTO orderItemsDTO = new OrderItemsDTO();
                    orderItemsDTO.setOrderId(ordersDTO.getUserId());
                    orderItemsDTO.setProductId(1L);    // Ссылка на товар
                    orderItemsDTO.setQuantity(OrderDetails.colaSizeSOrder.get(i));      // Количество товара
                    orderItemsDTO.setPrice(new BigDecimal(priceCola * OrderDetails.colaSizeSOrder.get(i)));  // Цена товара на момент заказа

                    listOfItems.add(orderItemsDTO);
                }
            }

            if (OrderDetails.cheeseburgerOrder.size() > 0) {
                if (OrderDetails.cheeseburgerOrder.containsKey(i)) {

                    OrderItemsDTO orderItemsDTO = new OrderItemsDTO();
                    orderItemsDTO.setOrderId(ordersDTO.getUserId());
                    orderItemsDTO.setProductId(2L);    // Ссылка на товар
                    orderItemsDTO.setQuantity(OrderDetails.cheeseburgerOrder.get(i));      // Количество товара
                    orderItemsDTO.setPrice(new BigDecimal(priceCheeseburger * OrderDetails.cheeseburgerOrder.get(i)));  // Цена товара на момент заказа

                    listOfItems.add(orderItemsDTO);
                }
            }
        }
        return listOfItems;
    }
}
