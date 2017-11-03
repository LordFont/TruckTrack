package hr.foi.air.trucktrack;

import android.content.Context;
import android.content.Intent;


import android.graphics.Movie;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.ConnectivityManager;

import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import entities.UserModel;
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

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(!isNetworkConnected()){
            Snackbar mySnackbar = Snackbar.make(this.findViewById(R.id.loginButton), "Niste povezani na internet!", Snackbar.LENGTH_LONG )
                    .setAction("Postavke", new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                        }
                    });
            mySnackbar.show();
        }
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

                Call<Void> call = apiService.authUser(new UserModel(email,lozinka));
                //Log.d("Call", call.toString());

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            wrongUserPass.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(), DisponentHome.class));
                        } else {
                            wrongUserPass.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Problem sa serverom!", Toast.LENGTH_SHORT).show();
                    }
                });
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
            mySnackbar.show();
        }
    }
}

