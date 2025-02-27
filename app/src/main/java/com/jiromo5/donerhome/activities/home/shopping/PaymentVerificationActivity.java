package com.jiromo5.donerhome.activities.home.shopping;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.PaymentAddress;
import com.jiromo5.donerhome.data.state.PaymentCard;
import com.jiromo5.donerhome.data.state.ProductsData;
import com.jiromo5.donerhome.data.state.UserAddress;
import com.jiromo5.donerhome.data.state.UserData;
import com.jiromo5.donerhome.data.state.paths.CartResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.menu.OrderDetails;
import com.jiromo5.donerhome.viewmodel.menu.listeners.BackToMenuListener;
import com.jiromo5.donerhome.viewmodel.shopping.PaymentManager;
import com.jiromo5.donerhome.viewmodel.shopping.listeners.BackToCartListener;
import com.jiromo5.donerhome.service.payment.OrderController;
import com.jiromo5.donerhome.service.payment.OrderItemsDTO;
import com.jiromo5.donerhome.service.payment.OrderRequestDTO;
import com.jiromo5.donerhome.service.payment.OrdersDTO;
import com.jiromo5.donerhome.service.payment.PaymentCardDTO;
import com.jiromo5.donerhome.service.payment.RequestStatus;

import java.math.BigDecimal;
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
        setContentView(R.layout.payment_verification_activity);
        overridePendingTransition(0, 0);

        backToMenuButton = findViewById(R.id.back_to_menu);
        tryAgainButton = findViewById(R.id.try_again_button);
        processingView = findViewById(R.id.payment_is_processed);
        successView = findViewById(R.id.payment_is_success);
        cancelView = findViewById(R.id.payment_is_cancel);

        paymentManager = new PaymentManager(backToMenuButton, tryAgainButton, processingView, successView, cancelView);

        createOrder();
        setView();
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
        ordersDTO.setPaymentMethod("credit_card");

        for (int i = 0; i < 5; i ++){
            if (UserAddress.addressName[i].equals(PaymentAddress.paymentAddress)){
                ordersDTO.setStreet(UserAddress.street[i]);
                ordersDTO.setBuild(UserAddress.build[i]);
                ordersDTO.setApartment(UserAddress.apartment[i]);
                break;
            }
        }

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
                    orderItemsDTO.setProductId(ProductsData.getId("Cola size S"));    // Ссылка на товар
                    orderItemsDTO.setQuantity(OrderDetails.colaSizeSOrder.get(i));      // Количество товара
                    orderItemsDTO.setPrice(new BigDecimal(priceCola * OrderDetails.colaSizeSOrder.get(i)));  // Цена товара на момент заказа

                    listOfItems.add(orderItemsDTO);
                }
            }

            if (OrderDetails.cheeseburgerOrder.size() > 0) {
                if (OrderDetails.cheeseburgerOrder.containsKey(i)) {

                    OrderItemsDTO orderItemsDTO = new OrderItemsDTO();
                    orderItemsDTO.setOrderId(ordersDTO.getUserId());
                    orderItemsDTO.setProductId(ProductsData.getId("Cheeseburger"));    // Ссылка на товар
                    orderItemsDTO.setQuantity(OrderDetails.cheeseburgerOrder.get(i));      // Количество товара
                    orderItemsDTO.setPrice(new BigDecimal(priceCheeseburger * OrderDetails.cheeseburgerOrder.get(i)));  // Цена товара на момент заказа

                    listOfItems.add(orderItemsDTO);
                }
            }
        }
        return listOfItems;
    }

    private void setView(){
        ViewHandler viewHandler = new ViewHandler(this);
        viewHandler.setImageOnScreen(findViewById(R.id.back_to_menu), CartResources.BACK_TO_MENU_IMAGE);
        viewHandler.setImageOnScreen(findViewById(R.id.try_again_button), CartResources.TRY_AGAIN_IMAGE);
        viewHandler.setImageOnScreen(findViewById(R.id.payment_is_processed), CartResources.PROCESS_MESSAGE_IMAGE);
        viewHandler.setImageOnScreen(findViewById(R.id.payment_is_success), CartResources.SUCCESS_MESSAGE_IMAGE);
        viewHandler.setImageOnScreen(findViewById(R.id.payment_is_cancel), CartResources.CANCEL_MESSAGE_IMAGE);

    }
}
