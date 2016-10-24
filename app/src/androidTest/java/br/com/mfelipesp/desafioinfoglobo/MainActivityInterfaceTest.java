package br.com.mfelipesp.desafioinfoglobo;

import android.app.Instrumentation;
import android.content.Context;
import android.content.IntentFilter;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;

/**
 * Created by markFelipe on 23/10/16.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityInterfaceTest {

    @Test
    public void isCorretoAbertura() throws Exception {

        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("br.com.mfelipesp.desafioinfoglobo", appContext.getPackageName());
    }


}
