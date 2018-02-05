package hr.foi.air.trucktrack;

import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import entities.UserModel;
import hr.foi.air.webservice.ApiClient;
import hr.foi.air.webservice.ApiInterface;
import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Marko on 5.2.2018..
 */

@RunWith(JUnit4.class)
public class LoginActivityUnitTest {
    Response myResponse;

    @Test
    public void credentials_areCorret() throws Exception{
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        String email = "pperic@gmail.com";
        String lozinka = "peric";
        final int expected = 200;
        Call<Boolean> call = apiService.authUser(new UserModel(email,lozinka));
        myResponse = call.execute();
        assertEquals(expected,myResponse.code());
    }

    @Test
    public void credentials_areNotCorret() throws Exception{
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        String email = "pperic@gmail.com";
        String lozinka = "peric123";
        final int expected = 200;
        Call<Boolean> call = apiService.authUser(new UserModel(email,lozinka));
        myResponse = call.execute();
        assertNotEquals(expected,myResponse.code());
    }
}
