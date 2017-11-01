package hr.foi.air.trucktrack;

import android.content.Intent;
import android.graphics.Movie;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        Button signInButton = (Button)findViewById(R.id.loginButton);

        signInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ApiInterface apiService =
                        ApiClient.getClient().create(ApiInterface.class);
                String email = user.getText().toString();
                String lozinka = password.getText().toString();
                Call<UserModel> call = apiService.authUser(email,lozinka);
                call.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        if (response.code() == 400) {
                            Toast.makeText(getApplicationContext(), "Pogrešan unos!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Preusmjeravanje...", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), DisponentHome.class));
                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Problem sa serverom!", Toast.LENGTH_SHORT).show();
                    }
                });
//                if (user.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
//                    Toast.makeText(getApplicationContext(), "Preusmjeravanje...", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(getApplicationContext(), DisponentHome.class));
//                } else {
//                    Toast.makeText(getApplicationContext(), "Pogrešan unos!", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }
}

