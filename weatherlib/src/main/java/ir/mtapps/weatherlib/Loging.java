package ir.mtapps.weatherlib;

import android.util.Log;

class Loging {

    private final static String TAG = "weatherlib";

    static void log_v(String message) {

        if (message == null || message.isEmpty()) {
            return;
        }

        Log.v(TAG, message);
    }

    static void log_i(String message) {

        if (message == null || message.isEmpty()) {
            return;
        }

        Log.i(TAG, message);
    }

    static void log_e(String message) {

        if (message == null || message.isEmpty()) {
            return;
        }

        Log.e(TAG, message);
    }

    static void log_d(String message) {

        if (message == null || message.isEmpty()) {
            return;
        }

        Log.d(TAG, message);
    }

    static void log_w(String message) {

        if (message == null || message.isEmpty()) {
            return;
        }

        Log.w(TAG, message);
    }


    static void log_v_debug(String message) {

        if (message == null || message.isEmpty()) {
            return;
        }

        if (BuildConfig.DEBUG) {
            Log.v(TAG, message);
        }
    }

    static void log_i_debug(String message) {

        if (message == null || message.isEmpty()) {
            return;
        }

        if (BuildConfig.DEBUG) {
            Log.i(TAG, message);
        }
    }

    static void log_e_debug(String message) {

        if (message == null || message.isEmpty()) {
            return;
        }

        if (BuildConfig.DEBUG) {
            Log.e(TAG, message);
        }
    }

    static void log_d_debug(String message) {

        if (message == null || message.isEmpty()) {
            return;
        }

        if (BuildConfig.DEBUG) {
            Log.d(TAG, message);
        }
    }

    static void log_w_debug(String message) {

        if (message == null || message.isEmpty()) {
            return;
        }

        if (BuildConfig.DEBUG) {
            Log.w(TAG, message);
        }
    }
}
