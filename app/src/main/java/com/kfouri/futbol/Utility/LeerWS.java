package com.kfouri.futbol.Utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by mkfouri on 07/01/2016.
 */
public class LeerWS extends AsyncTask<String, String, String> {

    private Context context;
    private ProgressDialog pDialog;
    private String strUrl;
    private String strTextoBuscar;

    public LeerWS(Context context,ProgressDialog pDialog,String strUrl,String strTextoBuscar)
    {
        this.context = context;
        this.pDialog = pDialog;
        this.strUrl = strUrl;
        this.strTextoBuscar = strTextoBuscar;
    }
    /**
     * Before starting background thread Show Progress Dialog
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(context);
        pDialog.setMessage(strTextoBuscar);
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    /**
     * getting All products from url
     * */
    protected String doInBackground(String... args) {
        HttpClient httpclient;
        HttpGet request;
        HttpResponse response = null;
        String result = "";
        try{
            httpclient = new DefaultHttpClient();

            request = new HttpGet(strUrl);
            response = httpclient.execute(request);
        } catch (Exception e){
            result = "error1";
        }

        try{
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line="";
            while((line = rd.readLine()) != null){
                result = result + line;
            }
        } catch(Exception e){
            result = "error2"+e.getMessage();
        }

        return result;
    }

    /**
     * After completing background task Dismiss the progress dialog
     * **/
    protected void onPostExecute(String file_url)
    {
        // dismiss the dialog after getting all products
        pDialog.dismiss();
    }

}