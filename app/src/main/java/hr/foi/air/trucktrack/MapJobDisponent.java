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
    private String end;
    private String[] endArray = null;
    private LatLng endCoor;
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
        end = intent.getExtras().getString("End", "");
        Log.d("MAP", end+"");

        if (end.length() > 1) endArray = end.split(",");
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
        Log.d("maps ", endArray + "");
        if (endArray != null) {
            endCoor = new LatLng(Double.parseDouble(endArray[0].toString()), Double.parseDouble(endArray[1].toString()));
            mMap.addMarker(new MarkerOptions().position(endCoor).title("End").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(endCoor));
            mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );
        } else {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(46.306363, 16.339872)));
            mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );
        }

        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);

        if (endArray == null) {

            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    String coord = latLng.toString();
                    numOfClickes++;
                    if (numOfClickes <= 1) {
                        endArray = coord.split(",");
                        mMap.addMarker(new MarkerOptions().position(latLng).title("End").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                        StringBuilder latString = new StringBuilder();
                        latString.append(latLng.latitude).append(",").append(latLng.longitude);
                        intent.putExtra("END", latString.toString());
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        if(!intent.hasExtra("END")) {
            intent.putExtra("END", end);
        }

        setResult(ENTER_IN_MAP, intent);
        super.onBackPressed();
    }
}
