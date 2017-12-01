package entities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ivan on 21.11.2017..
 */

public class JobModel {
    String mMjestoUtovara;
    String mMjestoIstovara;
    String mUtovarDatum;
    String mIstovarDatum;

    JobModel(String mjestoUtovara, String mjestoIstovara, String utovarDatum, String istovarDatum) {
        mMjestoUtovara = mjestoUtovara;
        mMjestoIstovara = mjestoIstovara;
        mUtovarDatum = utovarDatum;
        mIstovarDatum = istovarDatum;
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
}
