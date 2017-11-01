package hr.foi.air.webservice;

import entities.DriverResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Ivan on 1.11.2017..
 */

public interface ApiInterface {
    @GET("driver/all")
    Call<DriverResponse> getDrivers();
}
