package entities;

/**
 * Created by Marko on 6.12.2017..
 */


public class NewJobRequest {
    String mMjestoUtovara;
    String mMjestoIstovara;
    String mUtovarDatum;
    String mIstovarDatum;
    String koordinateUtovar;
    String koordinateIstovar;
    String dodjeljeniVozac;

    public NewJobRequest(String mMjestoUtovara, String mMjestoIstovara, String mUtovarDatum, String mIstovarDatum, String koordinateUtovar, String koordinateIstovar, String dodjeljeniVozac) {
        this.mMjestoUtovara = mMjestoUtovara;
        this.mMjestoIstovara = mMjestoIstovara;
        this.mUtovarDatum = mUtovarDatum;
        this.mIstovarDatum = mIstovarDatum;
        this.koordinateUtovar = koordinateUtovar;
        this.koordinateIstovar = koordinateIstovar;
        this.dodjeljeniVozac = dodjeljeniVozac;
    }

    public String getmMjestoUtovara() {
        return mMjestoUtovara;
    }

    public void setmMjestoUtovara(String mMjestoUtovara) {
        this.mMjestoUtovara = mMjestoUtovara;
    }

    public String getmMjestoIstovara() {
        return mMjestoIstovara;
    }

    public void setmMjestoIstovara(String mMjestoIstovara) {
        this.mMjestoIstovara = mMjestoIstovara;
    }

    public String getmUtovarDatum() {
        return mUtovarDatum;
    }

    public void setmUtovarDatum(String mUtovarDatum) {
        this.mUtovarDatum = mUtovarDatum;
    }

    public String getmIstovarDatum() {
        return mIstovarDatum;
    }

    public void setmIstovarDatum(String mIstovarDatum) {
        this.mIstovarDatum = mIstovarDatum;
    }

    public String getKoordinateUtovar() {
        return koordinateUtovar;
    }

    public void setKoordinateUtovar(String koordinateUtovar) {
        this.koordinateUtovar = koordinateUtovar;
    }

    public String getKoordinateIstovar() {
        return koordinateIstovar;
    }

    public void setKoordinateIstovar(String koordinateIstovar) {
        this.koordinateIstovar = koordinateIstovar;
    }

    public String getDodjeljeniVozac() {
        return dodjeljeniVozac;
    }

    public void setDodjeljeniVozac(String dodjeljeniVozac) {
        this.dodjeljeniVozac = dodjeljeniVozac;
    }
}
