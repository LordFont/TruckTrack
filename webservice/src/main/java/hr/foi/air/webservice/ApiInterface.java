package hr.foi.air.webservice;

import java.util.List;

import entities.DriverModel;
import entities.DriverResponse;
import entities.UserModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Ivan on 1.11.2017..
 */

public interface ApiInterface {
    @GET("driver/all")
    Call<List<DriverModel>> getDrivers();

    @POST("auth/user")
    Call<UserModel> authUser(@Query("email") String email, @Query("email") String lozinka);
}
