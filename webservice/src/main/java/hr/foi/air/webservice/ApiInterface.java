package hr.foi.air.webservice;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import entities.DriverJobsResponse;
import entities.DriverModel;
import entities.GetFCMTokenResponse;
import entities.RouteIdRequest;
import entities.RouteModel;
import entities.SetFCMTokenRequest;
import entities.SortRequest;
import entities.UserModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ivan on 1.11.2017..
 */

public interface ApiInterface {
    @GET("driver/all")
    Call<List<DriverModel>> getDrivers();

    @POST("user/auth")
    Call<Boolean> authUser(@Body UserModel body);

    @GET("route/{id}")
    Call <ArrayList<RouteModel>> getDriverJobs(@Path("id") String id);

    @GET("route/all")
    Call <ArrayList<RouteModel>> getAllRoutes();

    @POST("route/accept")
    Call<Void> routeAccept(@Body RouteIdRequest body);

    @POST("route/done")
    Call<Void> routeDone(@Body RouteIdRequest body);

    @POST("route/delete")
    Call<Void> routeDelete(@Body RouteIdRequest body);

    @POST("route/new")
    Call<RouteModel> routeNew(@Body RouteModel body);

    @POST("driver/sort")
    Call<List<DriverModel>> driverSort(@Body SortRequest body);

    @GET("user/token/{id}")
    Call<GetFCMTokenResponse> getUserFCMToken(@Path("id") String id);

    @POST("user/token")
    Call<Void> setUserFCMToken(@Body SetFCMTokenRequest body);
}
