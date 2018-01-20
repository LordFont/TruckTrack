package entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Marko on 19.1.2018..
 */

public class RouteIdRequest {
    @SerializedName("rutaID")
    int mIdRuta;
    public int getmIdRuta() {
        return mIdRuta;
    }

    public void setmIdRuta(int mIdRuta) {
        this.mIdRuta = mIdRuta;
    }

}
