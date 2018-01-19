package hr.foi.air.trucktrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DriverHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String expectedParentActivity = getPackageName()+".LoginActivity";
        String expectedPackage = getPackageName();

        if(getCallingActivity().getPackageName().equals(expectedPackage) && getCallingActivity().getClassName().equals(expectedParentActivity)) {
            startActivity(new Intent(getApplicationContext(), DriverJobs.class));
        } else {
            setContentView(R.layout.activity_driver_home);
            startActivity(new Intent(getApplicationContext(), NoContentActivity.class).putExtra("ERROR_MSG", true));
            finish();
        }
    }
}
