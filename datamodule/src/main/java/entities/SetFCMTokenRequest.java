package entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by roman on 2/6/18.
 */

public class SetFCMTokenRequest {
    @SerializedName("token")
    private String token;
    @SerializedName("korisnikID")
    private int userID;

    public SetFCMTokenRequest(String token, int userID) {
        this.token = token;
        this.userID = userID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
