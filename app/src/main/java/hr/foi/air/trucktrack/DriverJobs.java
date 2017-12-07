package hr.foi.air.trucktrack;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

import entities.DriverJobsResponse;
import entities.DriverModel;
import entities.RouteModel;
import hr.foi.air.trucktrack.Callbacks.CallbackDriverJobs;
import hr.foi.air.trucktrack.Callbacks.CallbackDriverList;
import hr.foi.air.webservice.ApiClient;
import hr.foi.air.webservice.ApiInterface;
import retrofit2.Call;

public class DriverJobs extends AppCompatActivity {
    private ApiInterface apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_jobs);

        initToolbar();

        //kreiranje dummy podataka u svrhu testiranja bez servera
        RouteModel routeJob1 = new RouteModel();
        RouteModel routeJob2 = new RouteModel();
        routeJob1.CreateTestData();
        routeJob2.CreateTestData();
        ArrayList<RouteModel> testList = new ArrayList<>();
        testList.add(routeJob1);
        testList.add(routeJob2);

        //showFragment(DriverJobsFragment.getInstance(testList));
        Fragment fragment = DriverJobsFragment.getInstance(testList);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<DriverJobsResponse>> call = apiService.getDriverJobs("3");
        call.enqueue(new CallbackDriverJobs(this,fragment));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_standard, menu);
        return true;
    }

    private void showFragment(Fragment f) {
        FragmentTransaction mTransactiont = getSupportFragmentManager().beginTransaction();
        mTransactiont.replace(R.id.main_container, f, f.getClass().getName());
        mTransactiont.commit();
    }

    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.driver_jobs_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_view_headline_white_48px);
        getSupportActionBar().setTitle(getResources().getString(R.string.toolbarPoslovi));
    }
}
