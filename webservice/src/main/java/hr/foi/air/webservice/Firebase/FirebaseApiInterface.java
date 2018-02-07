package hr.foi.air.webservice.Firebase;

import org.json.JSONObject;

import entities.FCMPushNotifRequest;
import entities.FCMResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by roman on 2/5/18.
 */

public interface FirebaseApiInterface {
    @Headers("Authorization:key=AIzaSyBv8Vh-tVK_KUd0VgcuwZchAIDViyulw2Q")
    @POST("send")
    Call<FCMResult> sendMessageToDevice(@Body FCMPushNotifRequest fcmMessage);
}

