package hr.foi.air.trucktrack;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import hr.foi.air.trucktrack.Helpers.MailHelper;

public class DisponentHome extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CheckExtrasForNotificationData(getIntent());

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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        CheckExtrasForNotificationData(intent);
    }

    private void CheckExtrasForNotificationData(Intent i)
    {
        Bundle data = i.getExtras();

        if (data != null) {
            String b = data.containsKey("body") ? data.getString("body") : "";
            if (!b.isEmpty())
            {
                showMyDialog("Message", b);
            }
        }
    }

    private void showMyDialog(String t, String b) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.notification_dialog, null);

        dialog.setView(dialogView);

        dialog.setTitle(t);
        TextView tv = (TextView) dialogView.findViewById(R.id.message);
        tv.setText(b);
        dialog.setPositiveButton(getString(R.string.btnPotvrdi), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
