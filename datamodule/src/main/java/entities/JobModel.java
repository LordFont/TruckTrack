package entities;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ivan on 21.11.2017..
 */

public class JobModel {
    @SerializedName("adresa_primatelja")
    String mMjestoIstovara;

    @SerializedName("datum_isporuke")
    String mIstovarDatum;

    JobModel(String mjestoIstovara, String istovarDatum) {
        mMjestoIstovara = mjestoIstovara;
        mIstovarDatum = istovarDatum;
    }

    public String getMjestoIstovara() {
        return mMjestoIstovara;
    }

    public void setMjestoIstovara(String mjestoIstovara) {
        mMjestoIstovara = mjestoIstovara;
    }

    public String getIstovarDatum() {
        return mIstovarDatum;
    }

    public void setIstovarDatum(String istovarDatum) {
        mIstovarDatum = istovarDatum;
    }
}
