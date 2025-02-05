package com.jiromo5.donerhome.service.payment;

import android.content.Context;
import android.util.Log;

import com.jiromo5.donerhome.activities.main.menu.MenuActivity;
import com.jiromo5.donerhome.common.DisposableHandler;
import com.jiromo5.donerhome.data.dto.LoginData;
import com.jiromo5.donerhome.network.AuthFormPutRequest;
import com.jiromo5.donerhome.network.OrderPutRequest;
import com.jiromo5.donerhome.service.auth.RequestService;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class OrderController implements RequestService, DisposableHandler {

    private Disposable disposable;
    private Single<Map<String, String>> networkDataSingle;
    private OrderPutRequest orderPutRequest;
    private OrderRequestDTO orderRequestDTO;

    //Баг фикс с обновлением вью и коммит.

    public OrderController(OrderRequestDTO orderRequestDTO){
        this.orderRequestDTO = orderRequestDTO;
        orderPutRequest = new OrderPutRequest(orderRequestDTO);
    }

    @Override
    public void fetchNetworkData() {
        networkDataSingle = Single.create(emitter -> {
            orderPutRequest.buildRequest();
            orderPutRequest.sendRequest(emitter);
        });
    }

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

    @Override
    public void dispose() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
