package entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ivan on 1.11.2017..
 */

public class UserModel {
    @SerializedName("email")
    String email;

    @SerializedName("lozinka")
    String lozinka;
}
