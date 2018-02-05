package hr.foi.air.trucktrack.Singleton;

/**
 * Created by roman on 2/4/18.
 */

public class Session {
    private static Session instance = null;
    private String userID;
    private String username;
    private String email;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
