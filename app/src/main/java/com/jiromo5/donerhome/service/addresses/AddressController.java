package com.jiromo5.donerhome.service.addresses;

import android.util.Log;

import com.jiromo5.donerhome.common.DisposableHandler;
import com.jiromo5.donerhome.network.AddressPutRequest;
import com.jiromo5.donerhome.network.OrderPutRequest;
import com.jiromo5.donerhome.service.auth.RequestService;
import com.jiromo5.donerhome.service.payment.OrderRequestDTO;
import com.jiromo5.donerhome.service.payment.RequestStatus;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AddressController implements RequestService, DisposableHandler {

    private Disposable disposable;
    private Single<Map<String, String>> networkDataSingle;
    private AddressPutRequest addressPutRequest;
    private AddressDTO addressDTO;

    //Баг фикс с обновлением вью и коммит.

    public AddressController(AddressDTO AddressDTO){
        this.addressDTO = AddressDTO;
        addressPutRequest = new AddressPutRequest(addressDTO);
    }

    @Override
    public void fetchNetworkData() {
        networkDataSingle = Single.create(emitter -> {
            addressPutRequest.buildRequest();
            addressPutRequest.sendRequest(emitter);
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
                    if (result.get("addressSaveDetails").equals("success")) {
                        Log.i("AddressesActivity", "Address added successfully !");
                    } else {
                        Log.e("AddressesActivity", "The address has not been added");
                    }
                }, throwable -> {
                    // Handles errors during request.
                    Log.e("AddressesActivity", "Web server is unavailable !", throwable);
                });
    }

    @Override
    public void dispose() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}