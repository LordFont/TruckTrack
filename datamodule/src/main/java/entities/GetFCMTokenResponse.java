package entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by roman on 2/7/18.
 */

public class GetFCMTokenResponse {
    @SerializedName("token")
    private String token;

    public GetFCMTokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
