package com.kfouri.futbol.Activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Toast;

import com.kfouri.futbol.Adapter.EquipoAdapter2;
import com.kfouri.futbol.Bean.Equipo;
import com.kfouri.futbol.Bean.cabeceraEquipo;
import com.kfouri.futbol.Bean.itemEquipo;
import com.kfouri.futbol.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class EquipoActivity extends AppCompatActivity {

    ProgressDialog pDialog;
    ArrayList<itemEquipo> Equipos = new ArrayList<itemEquipo>();
    ListView lv;
    JSONArray equipos = null;
    EquipoAdapter2 listAdapter;

    String idEquipo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv = (ListView)findViewById(android.R.id.list);
        Bundle b = getIntent().getExtras();

        getSupportActionBar().setTitle(b.getString("NombreEquipo"));

        idEquipo = b.getString("IdEquipo");

        new LeerWS().execute();

    }

    class LeerWS extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(EquipoActivity.this);
            pDialog.setMessage("Buscando Jugadores, por favor espere");
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


                request = new HttpGet("http://api.football-data.org/v1/teams/"+idEquipo+"/players");
                //request = new HttpGet("http://kfouri.onlinewebshop.net/prueba.php");
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

            String Salida = result;

            if (!result.equals(null))
            {
                try {
                    JSONObject jsonObj = new JSONObject(Salida);
                    equipos = jsonObj.getJSONArray("players");

                    for (int i = 0; i < equipos.length(); i++) {
                        JSONObject c = equipos.getJSONObject(i);

                        String name          = c.getString("name");
                        String position      = c.getString("position");
                        String jerseyNumber  = c.getString("jerseyNumber");
                        String dateOfBirth   = c.getString("dateOfBirth");
                        String nationality   = c.getString("nationality");
                        String contractUntil = c.getString("contractUntil");
                        String marketValue   = c.getString("marketValue");

                        Equipos.add(new cabeceraEquipo(name));

                        Equipos.add(new Equipo(name, position, jerseyNumber, dateOfBirth, nationality, contractUntil, marketValue));

                    }

                } catch (JSONException e) {
                    Toast.makeText(EquipoActivity.this, "Error 1: " + e.getMessage(), Toast.LENGTH_LONG).show();

                    e.printStackTrace();
                }
            }
            return result;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url)
        {
            pDialog.dismiss();


            runOnUiThread(new Runnable() {
                public void run() {

                    listAdapter = new EquipoAdapter2(EquipoActivity.this, Equipos);
                    //lv.setItemsCanFocus(false);
                    lv.setAdapter(listAdapter);
                    listAdapter.notifyDataSetChanged();
                }
            });

        }

    }

}
