package hr.foi.air.trucktrack;

import android.support.v4.app.Fragment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import entities.DriverModel;
import hr.foi.air.trucktrack.Callbacks.CallbackDriverList;
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
public class DriverUnitTest {
    List<DriverModel> drivers;

    @Test
    public void drivers_areRetrived() {
        Response<List<DriverModel>> myResponse;
        int expected = 0;
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<DriverModel>> call = apiService.getDrivers();
        try {
            myResponse = call.execute();
            drivers = myResponse.body();
        } catch (IOException e) {

        }
        assertNotEquals(expected, drivers.size());
    }
}
