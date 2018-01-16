package hr.foi.air.trucktrack;

import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapJobDisponent extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String start, end;
    private String[] startArray = null, endArray = null;
    private LatLng startCoor, endCoor;
    private Boolean isFirstClick = true;
    private Integer numOfClickes = 0;
    final Integer ENTER_IN_MAP = 3003;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_job);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        intent = getIntent();
        start = intent.getExtras().getString("Start", "");
        end = intent.getExtras().getString("End", "");

        if (start.length() > 0) startArray = start.toString().split(",");
        if (end.length() > 0) endArray = end.toString().split(",");
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
        Log.d("maps ", startArray + " " + endArray);
        if (startArray != null) {
            startCoor = new LatLng(Double.parseDouble(startArray[0]), Double.parseDouble(startArray[1]));
            mMap.addMarker(new MarkerOptions().position(startCoor).title("Start").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(startCoor));
        }

        if (endArray != null) {
            endCoor = new LatLng(Double.parseDouble(endArray[0].toString()), Double.parseDouble(endArray[1].toString()));
            mMap.addMarker(new MarkerOptions().position(endCoor).title("End").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(endCoor));
        }

        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);

        if (startArray == null && endArray == null) {

            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    String coord = latLng.toString();
                    numOfClickes++;
                    if(numOfClickes <= 2) {
                        if (isFirstClick) {

                            mMap.addMarker(new MarkerOptions().position(latLng).title("Start").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                            isFirstClick = false;
                            StringBuilder latString = new StringBuilder();
                            latString.append(latLng.latitude).append(",").append(latLng.longitude);
                            intent.putExtra("START", latString.toString());
                        } else {
                            endArray = coord.split(",");
                            mMap.addMarker(new MarkerOptions().position(latLng).title("End").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                            StringBuilder latString = new StringBuilder();
                            latString.append(latLng.latitude).append(",").append(latLng.longitude);
                            intent.putExtra("END", latString.toString());
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        setResult(ENTER_IN_MAP, intent);
        super.onBackPressed();
    }
}
