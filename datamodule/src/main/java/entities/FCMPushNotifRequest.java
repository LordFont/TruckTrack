package entities;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by roman on 2/7/18.
 */

public class FCMPushNotifRequest {
    private class msgData {
        @SerializedName("message")
        private String message;

        public msgData(String message) {
            this.message = message;
        }
    }

    @SerializedName("to")
    private String tokenTo;
    @SerializedName("data")
    private msgData data;

    public FCMPushNotifRequest() {}

    public String getTokenTo() {
        return tokenTo;
    }

    public void setTokenTo(String tokenTo) {
        this.tokenTo = tokenTo;
    }

    public msgData getData() {
        return data;
    }

    public void setData(String message) {
        this.data = new msgData(message);
    }
}
