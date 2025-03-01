package com.jiromo5.donerhome.service.payment;

import android.util.Log;

import com.jiromo5.donerhome.common.DisposableHandler;
import com.jiromo5.donerhome.network.OrderPutRequest;
import com.jiromo5.donerhome.service.auth.RequestService;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Controller class that manages the order creation process and interacts with network services.
 * Implements {@link RequestService} and {@link DisposableHandler} to manage network requests
 * and their lifecycle.
 */
public class OrderController implements RequestService, DisposableHandler {

    private Disposable disposable;  // Manages the lifecycle of the subscription.
    private Single<Map<String, String>> networkDataSingle;  // RxJava Single that will hold the result of the network request.
    private OrderPutRequest orderPutRequest;  // The request object for submitting the order.
    private OrderRequestDTO orderRequestDTO;  // Data transfer object containing order data.

    /**
     * Constructs an OrderController instance with the given OrderRequestDTO.
     *
     * @param orderRequestDTO The data object containing the order details.
     */
    public OrderController(OrderRequestDTO orderRequestDTO){
        this.orderRequestDTO = orderRequestDTO;
        orderPutRequest = new OrderPutRequest(orderRequestDTO);
    }

    /**
     * Prepares and initiates the network request to submit the order.
     * Creates a Single observable to handle the network request.
     */
    @Override
    public void fetchNetworkData() {
        networkDataSingle = Single.create(emitter -> {
            orderPutRequest.buildRequest();  // Builds the request.
            orderPutRequest.sendRequest(emitter);  // Sends the request and emits the response.
        });
    }

    /**
     * Handles the user authorization (payment verification) process.
     * Subscribes to the network request result and processes the response.
     */
    @Override
    public void handleUserAuthorization() {
        disposable = networkDataSingle
                .subscribeOn(Schedulers.io()) // Performs network request on IO thread.
                .observeOn(AndroidSchedulers.mainThread()) // Observes results on the main thread.
                .delay(2, TimeUnit.SECONDS) // Delays the processing by 2 seconds.
                .subscribe(result -> {
                    // Handles successful response.
                    if (result.get("payment_status").equals("success")) {
                        Log.i("PaymentVerificationActivity", "Payment successfully completed !");
                        RequestStatus.orderStatus = "success";
                    } else {
                        Log.e("PaymentVerificationActivity", "The payment was not processed !");
                        RequestStatus.orderStatus = "error";
                    }
                }, throwable -> {
                    // Handles errors during request.
                    Log.e("PaymentVerificationActivity", "Web server is unavailable !", throwable);
                    RequestStatus.orderStatus = "error"; // Sets statusRequest to false to indicate failure.
                });
    }

    /**
     * Disposes of the network request subscription if it exists.
     * This is necessary to avoid memory leaks.
     */
    @Override
    public void dispose() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();  // Disposes of the subscription to free resources.
        }
    }
}
