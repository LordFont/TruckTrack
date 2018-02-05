package hr.foi.air.trucktrack;

import android.content.Context;
import android.content.Intent;


import android.graphics.Typeface;
import android.net.ConnectivityManager;

import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import entities.UserModel;
import hr.foi.air.trucktrack.Singleton.Session;
import hr.foi.air.webservice.ApiClient;
import hr.foi.air.webservice.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    EditText user, password;
    ApiInterface apiService;
    TextView wrongUserPass;
    Intent intent;

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        user = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        Button signInButton = (Button)findViewById(R.id.loginButton);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        wrongUserPass = (TextView) findViewById(R.id.txtWrongEmailOrPassword);

        password.setTypeface(Typeface.DEFAULT);
        password.setTextSize(18);
        user.setTypeface(Typeface.DEFAULT);
        user.setTextSize(18);

        signInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = user.getText().toString();
                String lozinka = password.getText().toString();
                if(((CheckBox)findViewById(R.id.cbIsDriver)).isChecked()) {
                    email = "pperic@gmail.com";
                    lozinka = "peric";
                    //intent = new Intent(getApplicationContext(), DriverJobs.class);
                }
                else {
                    //intent = new Intent(getApplicationContext(), DisponentHome.class);
                }
                //startActivity(intent);

                Call<Boolean> call = apiService.authUser(new UserModel(email,lozinka));
                call.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if (response.code() == 200) {
                            wrongUserPass.setVisibility(View.GONE);
                            Intent intent;
                            boolean rjesenje = response.body();
                            if (rjesenje) {
                                intent = new Intent(getApplicationContext(), DriverJobs.class);
                            }
                            else intent = new Intent(getApplicationContext(), DisponentHome.class);

                            Session.Instance();
                            Session.Instance().setEmail(user.getText().toString());

                            startActivityForResult(intent, 1000);
                        } else {
                            wrongUserPass.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.loginButton), "Problem sa serverom!", Snackbar.LENGTH_LONG );
                        mySnackbar.show();
                    }
                });
                //startActivityForResult(intent, 1000);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();

        if(!isNetworkConnected()){
            Snackbar mySnackbar = Snackbar.make(this.findViewById(R.id.loginButton), "Niste povezani na internet!", Snackbar.LENGTH_LONG )
                    .setAction("Postavke", new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                        }
                    });
            mySnackbar.setActionTextColor(getResources().getColor(R.color.colorPrimary));
            mySnackbar.show();
        }
    }
}

