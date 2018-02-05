package hr.foi.air.trucktrack;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.ArrayList;

import entities.RouteModel;
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
public class DriverJobsUnitTest {
    ArrayList<RouteModel> jobs;

    @Test
    public void jobs_areRetrived() {
        Response<ArrayList<RouteModel>> myResponse;
        int expected = 0;
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<RouteModel>> call = apiService.getDriverJobs("3");
        try {
            myResponse = call.execute();
            jobs = myResponse.body();
        } catch (IOException e) {

        }
        assertNotEquals(expected, jobs.size());
    }

    @Test
    public void jobs_areNotRetrived() {
        Response<ArrayList<RouteModel>> myResponse;
        int expected = 0;
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<RouteModel>> call = apiService.getDriverJobs("undefined");
        try {
            myResponse = call.execute();
            jobs = myResponse.body();
        } catch (IOException e) {

        }
        assertEquals(expected, jobs.size());
    }

}
