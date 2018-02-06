package hr.foi.air.trucktrack.Singleton;

/**
 * Created by roman on 2/4/18.
 */

public class Session {
    private static Session instance = null;
    private String userID;
    private boolean vozac;
    private String email;
    private String disponentEmail;

    public static Session Instance() {
        if(instance == null) instance = new Session();
        return instance;
    }

    private Session() {}

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean isVozac() {
        return vozac;
    }

    public void setVozac(boolean vozac) {
        this.vozac = vozac;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisponentEmail() {
        return disponentEmail;
    }

    public void setDisponentEmail(String disponentEmail) {
        this.disponentEmail = disponentEmail;
    }
}
