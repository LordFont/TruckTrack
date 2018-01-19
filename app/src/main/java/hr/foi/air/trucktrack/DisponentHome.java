package hr.foi.air.trucktrack;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DisponentHome extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String expectedParentActivity = getPackageName()+".LoginActivity";
        String expectedPackage = getPackageName();

        if(getCallingActivity().getPackageName().equals(expectedPackage) && getCallingActivity().getClassName().equals(expectedParentActivity)) {
            setContentView(R.layout.activity_disponent_home);
            findViewById(R.id.jobListContainer).setOnClickListener(this);
            findViewById(R.id.driverListContainer).setOnClickListener(this);
        } else {
            startActivity(new Intent(getApplicationContext(), NoContentActivity.class).putExtra("ERROR_MSG", true));
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.jobListContainer:
                startActivity(new Intent(getApplicationContext(), DisponentJobs.class));
                break;
            case R.id.driverListContainer:
                startActivity(new Intent(getApplicationContext(), Drivers.class));
                break;
        }
    }
}
