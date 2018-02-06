package entities;


import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.http.HEAD;

/**
 * Created by Ivan on 21.11.2017..
 */

public class JobModel {
    @SerializedName("adresa_primatelja")
    String mMjestoIstovara;

    @SerializedName("datum_isporuke")
    String mIstovarDatum;

    @SerializedName("latitude")
    String latitude;

    @SerializedName("longitude")
    String longitude;

    public JobModel(String mjestoIstovara, String istovarDatum) {
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

    public String getLatitude() {
        if(latitude != null)
            return latitude;
        else return "";
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        if(longitude != null)
            return longitude;
        else return "";
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTimestamp() {
        String out = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(mIstovarDatum);
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            out = (String.valueOf(timestamp.getTime()/1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return out;
    }
}
