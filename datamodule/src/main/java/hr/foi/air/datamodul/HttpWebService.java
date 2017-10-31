package hr.foi.air.datamodul;

import android.os.AsyncTask;

import org.json.JSONArray;

/**
 * Created by ssajcic on 31.10.17..
 */

public class HttpWebService extends AsyncTask<String, Void, JSONArray> {

    @Override
    protected JSONArray doInBackground(String... urls) {
        JSONArray response = null;
        String url = urls[0];

        return response;
    }
}


