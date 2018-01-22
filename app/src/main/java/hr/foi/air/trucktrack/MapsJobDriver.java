package hr.foi.air.trucktrack;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import entities.DriverModel;
import entities.RouteModel;
import hr.foi.air.trucktrack.Callbacks.CallbackDriverJobsForMap;
import hr.foi.air.trucktrack.Singleton.JobCoordinates;
import hr.foi.air.webservice.ApiClient;
import hr.foi.air.webservice.ApiInterface;
import retrofit2.Call;

public class MapsJobDriver extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_job_driver);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent i = getIntent();
        list = new ArrayList();
        int idOfJob = i.getExtras().getInt("JOB_ID", -1);
        if(idOfJob != -1) {
            list.add("46.30685429425279,16.3228223310410976");
            list.add("45.829488492817834,15.97651831805706");
            list.add("47.1513,15.854156");
        }

        int ruteId = i.getExtras().getInt("EDIT_JOB", -1); //dobim index
        if(ruteId != -1) {
            //new JobCoordinates().getJobs(ruteId);
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for(int i = 0; i < list.size(); i++) {
            if(i == 0) {
                String [] p = list.get(i).toString().split(",");
                LatLng position = new LatLng(Double.parseDouble(p[0].toString()), Double.parseDouble(p[1].toString()));
                mMap.addMarker(new MarkerOptions().position(position).title("Start").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            } else {
                String [] p = list.get(i).toString().split(",");
                LatLng position = new LatLng(Double.parseDouble(p[0].toString()), Double.parseDouble(p[1].toString()));
                mMap.addMarker(new MarkerOptions().position(position).title("End").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            }

        }
        // Add a marker in Sydney and move the camera

    }
}
