package hr.foi.air.trucktrack.Callbacks;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import entities.FCMPushNotifRequest;
import entities.FCMResult;
import entities.GetFCMTokenResponse;
import entities.RouteModel;
import hr.foi.air.trucktrack.Helpers.FragmentManager;
import hr.foi.air.webservice.ApiClient;
import hr.foi.air.webservice.ApiInterface;
import hr.foi.air.webservice.Firebase.FirebaseApiClient;
import hr.foi.air.webservice.Firebase.FirebaseApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ivan on 21.1.2018..
 */

public class CallbackRouteNew extends FragmentManager implements Callback<RouteModel> {
    RouteModel mRouteModel = new RouteModel();
    FirebaseApiInterface firebaseApiService;
    ApiInterface apiService;

    public CallbackRouteNew (Object curr, RouteModel routeModel) {
        if (curr instanceof Fragment) {
            mCurrent = ((Fragment) curr).getActivity();
        }
        if (curr instanceof Activity) {
            mCurrent = (FragmentActivity)curr;
        }
        mRouteModel = routeModel;
    }

    @Override
    public void onResponse(Call<RouteModel> call, Response<RouteModel> response) {
        Log.d("NEW ROUTE RESPONSE", String.valueOf(response.code()));
        if (response.code() == 200) {
            //ovdje se obavljaju radnje kada su uspjesno poslani podaci
            //mCurrent.startActivity(new Intent(mCurrent.getApplicationContext(), DisponentJobs.class));


            apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<GetFCMTokenResponse> callFCM = apiService.getUserFCMToken(String.valueOf(mRouteModel.getKorisnikID())); //ovdje ide id korisnika, za testiranje uzet id 2 (Pero Perić)
            callFCM.enqueue(new Callback<GetFCMTokenResponse>() {
                @Override
                public void onResponse(Call<GetFCMTokenResponse> call, Response<GetFCMTokenResponse> response) {
                    GetFCMTokenResponse userTokenResponse = response.body();
                    FCMPushNotifRequest fcmPushNotifRequest = new FCMPushNotifRequest();
                    fcmPushNotifRequest.setTokenTo(userTokenResponse.getToken());
                    fcmPushNotifRequest.setData("Nova ruta!", "Dodijeljena vam je nova ruta!");

                    firebaseApiService = FirebaseApiClient.getClient().create(FirebaseApiInterface.class);
                    Call<FCMResult> pushMessageCall = firebaseApiService.sendMessageToDevice(fcmPushNotifRequest);
                    pushMessageCall.enqueue(new Callback<FCMResult>() {
                        @Override
                        public void onResponse(Call<FCMResult> call, Response<FCMResult> response) {
                            Log.d("Push notif OK", String.valueOf(response.code()));
                        }

                        @Override
                        public void onFailure(Call<FCMResult> call, Throwable t) {
                            Log.d("Push notif FAIL", t.toString());
                        }
                    });
                }

                @Override
                public void onFailure(Call<GetFCMTokenResponse> call, Throwable t) {

                }
            });
        } else {
            //ovdje se obavljaju radnje kada nisu uspjesno poslani podaci
        }
        mCurrent.onBackPressed();
    }

    public void onFailure(Call<RouteModel> call, Throwable t) {
        Log.d("JakoCoolGreska-RouteNew", t.toString());
        //mCurrent.startActivity(new Intent(mCurrent.getApplicationContext(), DisponentJobs.class));

        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<GetFCMTokenResponse> callFCM = apiService.getUserFCMToken(String.valueOf(mRouteModel.getKorisnikID())); //ovdje ide id korisnika, za testiranje uzet id 2 (Pero Perić)
        callFCM.enqueue(new Callback<GetFCMTokenResponse>() {
            @Override
            public void onResponse(Call<GetFCMTokenResponse> call, Response<GetFCMTokenResponse> response) {
                GetFCMTokenResponse userTokenResponse = response.body();
                FCMPushNotifRequest fcmPushNotifRequest = new FCMPushNotifRequest();
                fcmPushNotifRequest.setTokenTo(userTokenResponse.getToken());
                fcmPushNotifRequest.setData("Nova ruta!", "Dodijeljena vam je nova ruta!");

                firebaseApiService = FirebaseApiClient.getClient().create(FirebaseApiInterface.class);
                Call<FCMResult> pushMessageCall = firebaseApiService.sendMessageToDevice(fcmPushNotifRequest);
                pushMessageCall.enqueue(new Callback<FCMResult>() {
                    @Override
                    public void onResponse(Call<FCMResult> call, Response<FCMResult> response) {
                        Log.d("Push notif OK", String.valueOf(response.code()));
                    }

                    @Override
                    public void onFailure(Call<FCMResult> call, Throwable t) {
                        Log.d("Push notif FAIL", t.toString());
                    }
                });
            }

            @Override
            public void onFailure(Call<GetFCMTokenResponse> call, Throwable t) {

            }
        });

        mCurrent.onBackPressed();
    }
}
