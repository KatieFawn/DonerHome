package com.jiromo5.donerhome.splash;

import android.content.Context;
import android.graphics.*;
import android.util.Log;
import android.widget.ImageView;
import java.io.*;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * SplashHandler is a helper class responsible for managing the splash screen display,
 * including loading an image asynchronously, displaying it on the screen.
 */

public class SplashHandler {

    //Contains a path to image.
    private static final String PATH_TO_LOGO = "splash/logo.png";

    //Need to manage concurrent threads and perform operations internal it.
    private Disposable disposable;
    //Context reflect specified activity, SplashActivity.
    private Context context;

    public SplashHandler(Context context){
        this.context = context;
    }

    /**
     * Sets the loaded image on the screen using an observer.
     *
     * @param imageView need to show image on screen, it contain a configuration for current image.
     */

    public void setLogoOnScreen(ImageView imageView){
        //Assign sign to disposable.
        disposable = readImageFromAssets(PATH_TO_LOGO)
                .subscribeOn(Schedulers.io()) //Operation is perform on parallel thread.
                .observeOn(AndroidSchedulers.mainThread()) //Result from 'subscribeOn' well be performing on main thread.
                .subscribe( bitmap -> {
                    //Result, set image on screen.
                    imageView.setImageBitmap(bitmap);
                }, throwable -> {
                    Log.e("SplashActivity:","Logo not show on screen.");
                    throwable.printStackTrace();
                }); //Here well be result on performing.

    }

    /**
     * Reads an image from the assets asynchronously using an observable.
     *
     * @param fileName The name of the image file in the assets folder.
     * @return An Observable that emits the loaded Bitmap.
     */

    /**
     * Reads an image from the assets synchronously using an observable.
     *
     * @param fileName The name of the image file in the assets folder.
     * @return An Observable that emits the loaded Bitmap.
     */

    private Observable<Bitmap> readImageFromAssets(String fileName){
        //Return result of operation reading image from directory.
        //Create thread with use Observable and lambdas expression.
        return Observable.create( emitter -> {
            try {
                //Open stream.
                InputStream inputStream = context.getAssets().open(fileName);
                //Decode stream for read image.
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                //Next operation.
                emitter.onNext(bitmap);
                //Complete.
                emitter.onComplete();
            } catch (IOException e){
                emitter.onError(e);
            }
        });
    }

}
