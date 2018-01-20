package hr.foi.air.trucktrack.Helpers;

import android.content.Context;
import android.util.Log;

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;

import entities.UserModel;


/**
 * Created by Ivan on 19.1.2018..
 */

public class MailHelper {
    public void sendMail(Context context, String mail) {
        BackgroundMail.newBuilder(context)
                .withUsername("protect1zu95@gmail.com")
                .withPassword("crypto2101pi#")
                .withMailTo(mail) //IVAN - treba razmisliti o proslljeđivanju Korisnika kao modela
                .withType(BackgroundMail.TYPE_PLAIN)
                .withSubject("TruckTrack - Posao")
                .withBody("Vozač je potvrdio rutu.")
                .withProcessVisibility(false)
                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                    @Override
                    public void onSuccess() {
                        //Log.d("sending mail...","i did it");
                    }
                })
                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                    @Override
                    public void onFail() {
                        //do some magic
                    }
                })
                .send();
    }
}
