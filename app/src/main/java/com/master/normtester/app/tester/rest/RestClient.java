package com.master.normtester.app.tester.rest;

import android.os.AsyncTask;
import android.util.Log;
import com.master.normtester.app.tester.Image;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by lpalonek on 04/01/16.
 */
public class RestClient extends AsyncTask<Void, Void, Image[]> {

    private static final String SERVER_ADDRESS = "192.168.1.25";

    private static final int PORT = 8080;

    @Override
    protected Image[] doInBackground(Void... voids) {
        final String url = "http://"+SERVER_ADDRESS+":"+PORT+"/files";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return restTemplate.getForObject(url, Image[].class);
    }

    @Override
    protected void onPostExecute(Image[] imageData){
            Log.e("", imageData[0].getName());
    }
}
