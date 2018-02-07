package entities;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by roman on 2/7/18.
 */

public class FCMPushNotifRequest {
    private class msgData {
        @SerializedName("title")
        private String title;
        @SerializedName("body")
        private String body;

        public msgData(String title, String body) {
            this.title = title;
            this.body = body;
        }
    }

    @SerializedName("to")
    private String tokenTo;
    @SerializedName("notification")
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

    public void setData(String title, String body) {
        this.data = new msgData(title, body);
    }
}
