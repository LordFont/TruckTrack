package entities;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Ivan on 21.11.2017..
 */

public class RouteModel {
    transient String  mMjestoUtovara;
    transient String mMjestoIstovara;
    transient String mUtovarDatum;
    transient String mIstovarDatum;
    transient String mStatus;

    public int getmIdRute() {
        return mIdRute;
    }

    transient int mIdRute;

    @SerializedName("3")
    ArrayList<JobModel> poslovi;

    public RouteModel() {
        poslovi = new ArrayList<JobModel>();
    }

    public void AddJob(String mjestoUtovara, String mjestoIstovara, String utovarDatum, String istovarDatum) {
        mMjestoUtovara = mjestoUtovara;
        mMjestoIstovara = mjestoIstovara;
        mUtovarDatum = utovarDatum;
        mIstovarDatum = istovarDatum;
        mStatus = "Čekanje Potvrde";

        JobModel job = new JobModel(mjestoUtovara,mjestoIstovara,utovarDatum,istovarDatum);
        poslovi.add(job);
    }

    public void CreateTestData() {
        Random rnd = new Random();
        Date date1 = new Date(Math.abs(System.currentTimeMillis() - rnd.nextLong()));
        rnd = new Random();
        Date date2 = new Date(Math.abs(System.currentTimeMillis() - rnd.nextLong()));
        AddJob("Mjesto A", "Mjesto B", "12.10.2017","14.10.2017");
        AddJob("Mjesto C", "Mjesto D", "12.10.2017","14.10.2017");
    }

    public String getMjestoUtovara() {
        return mMjestoUtovara;
    }

    public void setMjestoUtovara(String mjestoUtovara) {
        mMjestoUtovara = mjestoUtovara;
    }

    public String getMjestoIstovara() {
        return mMjestoIstovara;
    }

    public void setMjestoIstovara(String mjestoIstovara) {
        mMjestoIstovara = mjestoIstovara;
    }

    public String getUtovarDatum() {
        return mUtovarDatum;
    }

    public void setUtovarDatum(String utovarDatum) {
        mUtovarDatum = utovarDatum;
    }

    public String getIstovarDatum() {
        return mIstovarDatum;
    }

    public void setIstovarDatum(String istovarDatum) {
        mIstovarDatum = istovarDatum;
    }

    public ArrayList<JobModel> getPoslovi() {
        return poslovi;
    }

    public void setPoslovi(ArrayList<JobModel> poslovi) {
        this.poslovi = poslovi;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }
}
