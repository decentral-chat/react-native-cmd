
package com.decentralchat.reactnative;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RNReactNativeCmdModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private final String TAG = "react-native-cmd";
  private final int MAX_LOGSIZE= 100000; // 100Kbytes

  public RNReactNativeCmdModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNReactNativeCmd";
  }

  @ReactMethod
  public void executeCmd(String cmdStr, Promise promise) {
    // display log
    Log.d(TAG, "executeCmd() being called.");
    try {
        Process process = Runtime.getRuntime().exec(cmdStr);
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
        StringBuilder log = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            log.append(line + "\n");
            if (log.length() > MAX_LOGSIZE) {
                log.delete(0, log.length() - MAX_LOGSIZE);
            }
        }
        Log.d(TAG, "returned length:" + log.length());
        promise.resolve(log.toString());
    } catch (IOException e) {
        // Handle Exception
        Log.d(TAG, e.getStackTrace().toString());
        Log.d(TAG, "exception happened.");
        promise.reject("exception happened in executeCmd", e);
    }
  }
}
