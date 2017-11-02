package entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ivan on 1.11.2017..
 */

public class UserModel {
    @SerializedName("email")
    String email;

    @SerializedName("lozinka")
    String lozinka;

    public UserModel(String email, String lozinka) {
        this.email = email;
        this.lozinka = lozinka;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
}
