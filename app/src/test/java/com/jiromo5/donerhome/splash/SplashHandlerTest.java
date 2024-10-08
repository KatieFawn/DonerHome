package com.jiromo5.donerhome.splash;

import static org.mockito.Mockito.when;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.util.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import io.reactivex.rxjava3.observers.TestObserver;

@RunWith(RobolectricTestRunner.class)
public class SplashHandlerTest {

    private static final String PATH_TO_LOGO = "splash/logo.png";

    private SplashHandler splashHandler;

    @Mock
    private AssetManager mockContext;

    /**
     * Create a mocks for tests.
     */

    @Before
    public void setUp(){
        //Create a mocks.
        mockContext = Mockito.mock(AssetManager.class);
        splashHandler = new SplashHandler(mockContext);
        Log.d("SplashHandlerTest", "Create mocks.");
    }

    /**
     * Test read image from {@link ByteArrayInputStream} and open received image on
     * {@link #mockContext}.
     *
     * Then receive observable which return a image, check what observer complete
     * without error.
     *
     * @throws IOException
     */

    @Test
    public void testSplashImageObservableSuccess() throws IOException {
        //Create mock InputStream for image.
        InputStream mockInputStream = new ByteArrayInputStream(new byte[]{});
        when(mockContext.open(PATH_TO_LOGO)).thenReturn(mockInputStream);

        //Receive a observable.
        TestObserver<Bitmap> testObserver = splashHandler.getSplashImageObservable(PATH_TO_LOGO).test();

        //Check what observable complete without error !
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertValue(bitmap -> bitmap != null);

        Log.d("SplashHandlerTest", "testSplashImageObservableSuccess is complete !");
    }

    /**
     * Check what {@link #mockContext} complete with error after open is image.
     * Test what receive image from observable also complete with error.
     *
     * @throws IOException
     */

    @Test
    public void testSplashImageObservableError() throws IOException {
        //Configure a mock what be method open throws is IOException.
        when(mockContext.open(PATH_TO_LOGO)).thenThrow(new IOException());

        //Create TestObservable for test.
        TestObserver<Bitmap> testObserver = splashHandler.getSplashImageObservable(PATH_TO_LOGO).test();
        //Test what well be exception is throws when happen is error.
        testObserver.assertError(IOException.class);
        Log.d("SplashHandlerTest", "testSplashImageObservableError is complete !");
    }



}
