package com.kfouri.futbol.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.kfouri.futbol.Adapter.TemporadaAdapter;
import com.kfouri.futbol.Bean.Temporada;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TemporadasActivity extends AppCompatActivity {

    ProgressDialog pDialog;
    ArrayList<Temporada> Temporadas = new ArrayList<Temporada>();
    ListView lv;
    TemporadaAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporadas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv = (ListView)findViewById(android.R.id.list);

        new LeerWS().execute();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3)
            {
                Temporada temp = (Temporada)adapter.getItemAtPosition(position);

                //Toast.makeText(getApplicationContext(), temp.getLeagueTable(),Toast.LENGTH_LONG).show();

                Intent i=new Intent(getApplicationContext(),PrincipalActivity.class);
                Bundle b = new Bundle();
                b.putString("LeagueTable", temp.getLeagueTable());
                b.putString ("Fixtures", temp.getFixtures());
                b.putString ("caption",temp.getCaption());
                i.putExtras(b);

                startActivity(i);

            }
        });

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    class LeerWS extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(TemporadasActivity.this);
            pDialog.setMessage("Buscando Temporadas, por favor espere");
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
                int anioActual = obtenerAnio();
                int anioTemporada = anioActual - 1;
                request = new HttpGet("http://api.football-data.org/v1/soccerseasons/?season="+anioTemporada);
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
                    JSONObject jObject = null;
                    JSONArray temporadas = new JSONArray(Salida);

                    for (int i = 0; i < temporadas.length(); i++) {
                        JSONObject c = temporadas.getJSONObject(i);

                        int id = c.getInt("id");
                        String caption = c.getString("caption");
                        String league = c.getString("league");
                        int currentMatchday = c.getInt("currentMatchday");
                        int numberOfTeams = c.getInt("numberOfTeams");
                        int numberOfGames = c.getInt("numberOfGames");

                        String teams = c.getJSONObject("_links").getString("teams").substring(8);
                        teams = teams.substring(1,teams.length()-2);
                        teams = teams.replace("\\","");

                        String fixtures = c.getJSONObject("_links").getString("fixtures").substring(8);
                        fixtures = fixtures.substring(1,fixtures.length()-2);
                        fixtures = fixtures.replace("\\","");

                        String leagueTable = c.getJSONObject("_links").getString("leagueTable").substring(8);
                        leagueTable = leagueTable.substring(1,leagueTable.length()-2);
                        leagueTable = leagueTable.replace("\\","");

                        //String fixtures = c.getString("fixtures");
                        //String leagueTable = c.getString("leagueTable");

                        Temporadas.add(new Temporada(id, caption, league, 0, 0, 0, "", "", teams, fixtures, leagueTable));

                    }

                } catch (JSONException e) {
                    Toast.makeText(TemporadasActivity.this, "Error 1: " + e.getMessage(), Toast.LENGTH_LONG).show();

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
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            //texto.setText("Cnt temporadas:" + Temporadas.size());
            runOnUiThread(new Runnable() {
                public void run() {

                    listAdapter = new TemporadaAdapter(TemporadasActivity.this, R.layout.temporada_row, Temporadas);
                    //lv.setItemsCanFocus(false);
                    lv.setAdapter(listAdapter);
                    listAdapter.notifyDataSetChanged();
                }
            });
        }

    }

    private int obtenerAnio()
    {
        String date = "";

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy");
        date = DATE_FORMAT.format(new Date());

        return Integer.parseInt(date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_app)
        {
            Intent intent1 = new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pub:MAKP+Team"));
            startActivity(intent1);
        }
        else if (id == R.id.action_compartir)
        {
            String texto;
            texto = getString(R.string.app_name)+"\n";
            texto = texto +getString(R.string.compartir)+"\n";
            texto = texto +"https://play.google.com/store/apps/details?id=com.kfouri.futbol";

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, texto);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }


        return super.onOptionsItemSelected(item);
    }
}
