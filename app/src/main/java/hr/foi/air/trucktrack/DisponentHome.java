package hr.foi.air.trucktrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DisponentHome extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disponent_home);

        findViewById(R.id.jobListContainer).setOnClickListener(this);
        findViewById(R.id.driverListContainer).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.jobListContainer: 
                break;
            case R.id.driverListContainer:
                startActivity(new Intent(getApplicationContext(), Drivers.class));
                break;
        }
    }
}
