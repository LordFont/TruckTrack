package hr.foi.air.trucktrack;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import com.wang.avi.AVLoadingIndicatorView;
import java.util.ArrayList;


import entities.RouteIdRequest;
import entities.RouteModel;
import hr.foi.air.trucktrack.Callbacks.CallbackDriverJobs;

import hr.foi.air.trucktrack.Helpers.MailHelper;
import hr.foi.air.trucktrack.Interface.CustomDialog;
import hr.foi.air.trucktrack.Singleton.Session;
import hr.foi.air.webservice.ApiClient;
import hr.foi.air.webservice.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverJobs extends AppCompatActivity implements CustomDialog {
    private ApiInterface apiService;
    AVLoadingIndicatorView avi;
    final int DIALOG_SET_DONE = 400;
    final int DIALOG_ACK_TO_JOB = 500;
    final int DIALOG_FAIL_JOB = 600;
    MailHelper mail = new MailHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_jobs);

        initToolbar();

        //kreiranje dummy podataka u svrhu testiranja bez servera
        RouteModel routeJob1 = new RouteModel();
        RouteModel routeJob2 = new RouteModel();
        routeJob1.CreateTestData();
        routeJob2.CreateTestData();
        ArrayList<RouteModel> testList = new ArrayList<>();
        testList.add(routeJob1);

        //showFragment(DriverJobsFragment.getInstance(testList));
        Fragment fragment = DriverJobsFragment.getInstance(testList);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<RouteModel>> call = apiService.getDriverJobs("3"); //ovdje ide id korisnika, za testiranje uzet id 3
        call.enqueue(new CallbackDriverJobs(this,fragment));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_standard, menu);
        return true;
    }

    private void showFragment(Fragment f) {
        FragmentTransaction mTransactiont = getSupportFragmentManager().beginTransaction();
        mTransactiont.replace(R.id.main_container, f, f.getClass().getName());
        mTransactiont.commit();
    }

    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.driver_jobs_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_view_headline_white_48px);
        getSupportActionBar().setTitle(getResources().getString(R.string.toolbarPoslovi));
    }

    @Override
    public void showCustomDialog(int type, final int idRuta) {
        //Toast.makeText(this, "idRuta: " + idRuta, Toast.LENGTH_SHORT).show();
        Log.d("TYPE", String.valueOf(type));
        if(type == DIALOG_SET_DONE) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setNegativeButton(getResources().getString(R.string.btnOdustani), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.setPositiveButton(getResources().getString(R.string.btnPotvrdi), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    /*DRIVER-DONE
                    * Klikom na ikonu done otvara se dialog. Pritiskom na “potvrdi” mora se pozvati
                    * api poziv za slanje id posla  koji je odrađen. Kada se vrati uspješan response
                    * vratiti će se email disponenta koji je nadređen vozaču koji će služiti za GMAIL
                    * api za slanje emaila. Nakon što se email bude poslao prikazat će se SnackBar
                    * poruka “Uspješno poslano” u protivnom “Neuspješno poslano”. Prije nego GMAIL
                    * API bude gotov, nakon dolaska prvog response-a refreshati listu poslova vozača !*/
                    RouteIdRequest request = new RouteIdRequest();
                    request.setmIdRuta(idRuta);
                    Call<Void> call = apiService.routeDone(request);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 200) {
                                mail.sendMail(getApplicationContext(),"marhren2@gmail.com","Vozač je odradio rutu");
                                Snackbar.make(findViewById(R.id.driver_jobs_toolbar), getResources().getString(R.string.msg_ruta_odradena), Snackbar.LENGTH_LONG ).show();
                            } else {
                                Snackbar mySnackbar2 = Snackbar.make(findViewById(R.id.driver_jobs_toolbar), "Ruta nije odrađena!", Snackbar.LENGTH_LONG );
                                mySnackbar2.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Snackbar mySnackbar3 = Snackbar.make(findViewById(R.id.driver_jobs_toolbar), getResources().getString(R.string.msg_ruta_nije_odradena), Snackbar.LENGTH_LONG );
                            mySnackbar3.show();
                        }
                    });
                }
            });
            dialog.setTitle(getResources().getString(R.string.title_potvrditi_odradenu_rutu));
            dialog.setMessage(getResources().getString(R.string.msg_potvrda_odradene_rute));
            dialog.show();
        } else if (type == DIALOG_ACK_TO_JOB) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setNegativeButton(getResources().getString(R.string.btnOdustani), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.setPositiveButton(getResources().getString(R.string.btnPotvrdi), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   /*DRIVER-ACK
                   * Otvara se dialog. klikom na potvrdi se šalje api poziv za ažuriranje statusa na temelju id posla.
                   * te se mora ažutirati lista poslova ! */

                    RouteIdRequest request = new RouteIdRequest();
                    request.setmIdRuta(idRuta);
                    Call<Void> call = apiService.routeAccept(request);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 200) {

                                mail.sendMail(getApplicationContext(),"marhren2@gmail.com", "Vozač je potvrdio rutu");
                                Snackbar mySnackbar = Snackbar.make(findViewById(R.id.driver_jobs_toolbar), getResources().getString(R.string.msg_ruta_potvrdena), Snackbar.LENGTH_LONG );
                                mySnackbar.show();
                            } else {
                                Snackbar mySnackbar4 = Snackbar.make(findViewById(R.id.driver_jobs_toolbar), "Ruta nije potvrđena!", Snackbar.LENGTH_LONG );
                                mySnackbar4.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Snackbar mySnackbar3 = Snackbar.make(findViewById(R.id.driver_jobs_toolbar), getResources().getString(R.string.msg_ruta_odbijena), Snackbar.LENGTH_LONG );
                            mySnackbar3.show();
                            //nemam ideje stacu "onFailure"
                        }
                    });

                }
            });
            dialog.setTitle(getResources().getString(R.string.title_prihvatiti_rutu));
            dialog.setMessage(getResources().getString(R.string.msg_potvrdom_rute));
            dialog.show();
        } else if (type == DIALOG_FAIL_JOB) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setNegativeButton(getResources().getString(R.string.btnOdustani), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.setPositiveButton(getResources().getString(R.string.btnPotvrdi), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mail.sendMail(getApplicationContext(),"romantomask@gmail.com", "Vozač " + Session.Instance().getEmail() + " neće biti u mogućnosti na vrijeme obaviti prijevoz " + String.valueOf(idRuta));
                }
            });
            dialog.setTitle(getResources().getString(R.string.title_potvrda_neuspjeha));
            dialog.setMessage(getResources().getString(R.string.msg_potvrda_neuspjeha));
            dialog.show();
        }
    }


}
