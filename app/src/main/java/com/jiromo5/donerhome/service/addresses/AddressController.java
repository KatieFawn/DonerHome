package com.jiromo5.donerhome.service.addresses;

import android.util.Log;

import com.jiromo5.donerhome.common.DisposableHandler;
import com.jiromo5.donerhome.network.AddressPutRequest;
import com.jiromo5.donerhome.service.auth.RequestService;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * This class handles user interactions related to the address management process.
 * It implements the {@link RequestService} interface for fetching network data and
 * the {@link DisposableHandler} interface for managing RxJava disposables.
 */

public class AddressController implements RequestService, DisposableHandler {

    private Disposable disposable;
    private Single<Map<String, String>> networkDataSingle;
    private AddressPutRequest addressPutRequest;
    private AddressDTO addressDTO;

    /**
     * Constructor that initializes the AddressController with the provided {@link AddressDTO}.
     * It also initializes the AddressPutRequest to be used for network communication.
     *
     * @param addressDTO The AddressDTO containing the address data to be sent in the request.
     */
    public AddressController(AddressDTO AddressDTO){
        this.addressDTO = AddressDTO;
        addressPutRequest = new AddressPutRequest(addressDTO);
    }

    /**
     * Initializes the network data request by creating a {@link Single} instance that will
     * handle the address PUT request. The request is built and sent through the
     * {@link AddressPutRequest} instance.
     */
    @Override
    public void fetchNetworkData() {
        networkDataSingle = Single.create(emitter -> {
            addressPutRequest.buildRequest();
            addressPutRequest.sendRequest(emitter);
        });
    }

    /**
     * Handles the user authorization by subscribing to the network data request.
     * The request is performed on the IO thread, and the result is observed on the main thread.
     * A 2-second delay is added before processing the result. If the address is successfully added,
     * a success message is logged; otherwise, an error message is logged.
     */
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

    /**
     * Disposes the current RxJava disposable to prevent memory leaks when no longer needed.
     */
    @Override
    public void dispose() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}