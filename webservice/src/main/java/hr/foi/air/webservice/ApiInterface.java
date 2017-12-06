package hr.foi.air.webservice;

import java.util.List;

import entities.DriverModel;
import entities.UserModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Ivan on 1.11.2017..
 */

public interface ApiInterface {
    @GET("driver/all")
    Call<List<DriverModel>> getDrivers();

    @POST("user/auth")
    Call<Void> authUser(@Body UserModel body);
}
