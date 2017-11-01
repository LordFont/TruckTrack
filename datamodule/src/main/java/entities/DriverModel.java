package entities;

import android.test.InstrumentationTestSuite;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Marko on 28.10.2017..
 */

public class DriverModel {
    int id;
    String ime;
    String prezime;
    String oib;
    String email;
    String lozinka;
    int datum_zap;
    String slika;
    boolean tipKorisnika;

    public DriverModel(int id, String ime, String prezime, String oib, String email, String lozinka, int datum_zap, String slika, boolean tipKorisnika) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.oib = oib;
        this.email = email;
        this.lozinka = lozinka;
        this.datum_zap = datum_zap;
        this.slika = slika;
        this.tipKorisnika = tipKorisnika;
        Log.d("SSTELLA", "data"+this.oib+this.prezime+this.ime);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
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

    public int getDatum_zap() {
        return datum_zap;
    }

    public void setDatum_zap(int datum_zap) {
        this.datum_zap = datum_zap;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public boolean getTipKorisnika() {
        return tipKorisnika;
    }

    public void setTipKorisnika(boolean tipKorisnika) {
        this.tipKorisnika = tipKorisnika;
    }
}
