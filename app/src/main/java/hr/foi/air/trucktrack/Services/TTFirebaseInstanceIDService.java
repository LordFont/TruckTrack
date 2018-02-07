package hr.foi.air.trucktrack.Services;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import hr.foi.air.trucktrack.Singleton.Session;

/**
 * Created by roman on 1/31/18.
 */

public class TTFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String FCM_TOKEN = "FCM_TOKEN";
    private static final String TAG = "TRUCKTRACK_FCM_TOKEN";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        saveToken(refreshedToken);
    }

    private void saveToken(String token)
    {
        //save to shared preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putString(FCM_TOKEN, token).apply();
        Log.e(TAG, "sendRegistrationToServer: " + token);

        //send to your own web service
        //TODO

    }
}
