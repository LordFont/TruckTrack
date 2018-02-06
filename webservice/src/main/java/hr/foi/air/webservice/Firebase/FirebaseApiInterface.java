package hr.foi.air.webservice.Firebase;

import org.json.JSONObject;

import entities.FCMResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by roman on 2/5/18.
 */

public interface FirebaseApiInterface {
    @POST("send")
    Call<FCMResponse> sendMessageToDevice(@Body JSONObject fcmMessage);
}

