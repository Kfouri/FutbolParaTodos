package com.kfouri.futbol.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
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
import android.widget.TabHost;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.kfouri.futbol.Adapter.FixtureAdapter;
import com.kfouri.futbol.Adapter.TablaAdapter;
import com.kfouri.futbol.Bean.Fixture;
import com.kfouri.futbol.Bean.Tabla;
import com.kfouri.futbol.Bean.cabeceraFixture;
import com.kfouri.futbol.Bean.itemFixture;
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
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class PrincipalActivity extends AppCompatActivity {

    ProgressDialog pDialog;
    ArrayList<Tabla> Tablas = new ArrayList<Tabla>();
    ArrayList<itemFixture> fix = new ArrayList<itemFixture>();

    ListView lv_tablas;
    ListView lv_fixture;

    TablaAdapter listAdapter;
    FixtureAdapter listAdapterFixture;

    String xLeagueTable;
    String xFixture;

    JSONArray tablas = null;
    JSONArray fixtures = null;

    int posHoy = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle b = getIntent().getExtras();

        getSupportActionBar().setTitle(b.getString("caption"));

        Resources res = getResources();

        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Tabla",
                res.getDrawable(android.R.drawable.ic_btn_speak_now));
        tabs.addTab(spec);

        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Fixture",
                res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);

        tabs.setCurrentTab(0);


        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


//--------------------------------------------------------------------------------------
//--------- PESTAÑA TABLA
//--------------------------------------------------------------------------------------

        xLeagueTable = b.getString("LeagueTable");

        lv_tablas = (ListView)findViewById(R.id.list_tabla);

        new LeerWSTabla().execute();

        lv_tablas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3)
            {
                Tabla temp = (Tabla)adapter.getItemAtPosition(position);

                //Toast.makeText(getApplicationContext(), temp.getLeagueTable(),Toast.LENGTH_LONG).show();

                final Intent intent = new Intent(PrincipalActivity.this,EquipoActivity.class);
                Bundle b = new Bundle();
                b.putString("IdEquipo", "" + temp.getIdTeam());
                b.putString("NombreEquipo", temp.getTeamName());
                intent.putExtras(b);
                PrincipalActivity.this.startActivity(intent);

            }
        });
//--------------------------------------------------------------------------------------
//--------- PESTAÑA Fixture
//--------------------------------------------------------------------------------------
        xFixture = b.getString("Fixtures");

        lv_fixture = (ListView)findViewById(R.id.list_fixture);




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

    class LeerWSTabla extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(PrincipalActivity.this);
            pDialog.setMessage("Buscando Tabla, por favor espere...");
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
            String results = "";
            try{
                httpclient = new DefaultHttpClient();

                request = new HttpGet(xLeagueTable);
                response = httpclient.execute(request);
            } catch (Exception e){
                results = "error1";
            }

            try{
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line="";
                while((line = rd.readLine()) != null){
                    results = results + line;
                }
            } catch(Exception e){
                results = "error2"+e.getMessage();
            }

            String Salida = results;

            if (!results.equals(null) && !results.equals("{\"error\":\"The resource you are looking for does not exist.\"}"))
            {
                try {
                    JSONObject jsonObj = new JSONObject(Salida);
                    tablas = jsonObj.getJSONArray("standing");

                    for (int i = 0; i < tablas.length(); i++) {
                        JSONObject c = tablas.getJSONObject(i);

                        String _links = c.getString("_links");
                        _links = _links.substring(17, _links.length() - 3);
                        _links = _links.replace("\\","");

                        String idTeam = _links.substring(_links.lastIndexOf("/")+1);
                        int position = c.getInt("position");
                        String teamName = c.getString("teamName");
                        String crestURI = c.getString("crestURI");
                        int playedGames = c.getInt("playedGames");
                        int points = c.getInt("points");
                        int goals = c.getInt("goals");
                        int goalsAgainst = c.getInt("goalsAgainst");
                        int goalDifference = c.getInt("goalDifference");
                        int wins = c.getInt("wins");
                        int draws = c.getInt("draws");
                        int losses = c.getInt("losses");

                        Tablas.add(new Tabla(Integer.parseInt(idTeam),_links,position,teamName,crestURI,playedGames,points,goals,goalsAgainst,goalDifference,wins,draws,losses));

                    }


                } catch (JSONException e) {
                    Toast.makeText(PrincipalActivity.this, "Error 1: " + e.getMessage(), Toast.LENGTH_LONG).show();

                    e.printStackTrace();
                }
            }
            return results;
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


                    listAdapter = new TablaAdapter(PrincipalActivity.this, R.layout.tabla_row, Tablas);

 //                   View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
 //                   lv_tablas.addHeaderView(header);

                    //lv.setItemsCanFocus(false);
                    lv_tablas.setAdapter(listAdapter);
                    listAdapter.notifyDataSetChanged();

                    new LeerWSFixture().execute();
                }
            });
        }

    }



    class LeerWSFixture extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(PrincipalActivity.this);
            pDialog.setMessage("Buscando Fixture, por favor espere...");
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
            String results = "";
            try{
                httpclient = new DefaultHttpClient();

                request = new HttpGet(xFixture);
                response = httpclient.execute(request);
            } catch (Exception e){
                results = "error1";
            }

            try{
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line="";
                while((line = rd.readLine()) != null){
                    results = results + line;
                }
            } catch(Exception e){
                results = "error2"+e.getMessage();
            }

            String Salida = results;
            int j = 0;

            if (!results.equals(null))
            {

                try {
                    JSONObject jsonObj = new JSONObject(Salida);
                    fixtures = jsonObj.getJSONArray("fixtures");

                    String fechaLeida = "";
                    String fechaAnterior = "";
                    posHoy = 0;

                    for (int i = 0; i < fixtures.length(); i++) {
                        JSONObject c = fixtures.getJSONObject(i);

                        String _links = c.getString("_links");

                        JSONObject myJson0 = new JSONObject(c.getString("_links"));
                        //String homeTeam = myJson0.optString("homeTeam");
                        JSONObject aux = (JSONObject) myJson0.get("homeTeam");
                        String homeTeam = aux.get("href").toString();

                        JSONObject aux2 = (JSONObject) myJson0.get("awayTeam");
                        String awayTeam = aux2.get("href").toString();

                        JSONObject aux3 = (JSONObject) myJson0.get("soccerseason");
                        String soccerseason = aux.get("href").toString();

                        String date = c.getString("date");
                        String status = c.getString("status");
                        String homeTeamName = c.getString("homeTeamName");
                        String awayTeamName = c.getString("awayTeamName");
                        String result = c.getString("result");
                        int matchday = c.getInt("matchday");

                        JSONObject myJson = new JSONObject(c.getString("result"));

                        String goalsAwayTeam = "";
                        String goalsHomeTeam = "";

                        if (myJson.optString("goalsHomeTeam").equals("null"))
                        {
                            goalsHomeTeam = "-";
                        }
                        else
                        {
                            goalsHomeTeam = myJson.optString("goalsHomeTeam");
                        }

                        if (myJson.optString("goalsAwayTeam").equals("null"))
                        {
                            goalsAwayTeam = "-";
                        }
                        else
                        {
                            goalsAwayTeam = myJson.optString("goalsAwayTeam");
                        }


                        int idHome=0;
                        int idAway=0;

                        idHome = Integer.parseInt(homeTeam.substring(homeTeam.lastIndexOf("/")+1));
                        idAway = Integer.parseInt(awayTeam.substring(awayTeam.lastIndexOf("/") + 1));

                        fechaLeida = obtenerFecha(date);

                        if (posHoy==0)
                        {
                            if (mayorIgualHoy(fechaLeida))
                            {
                                posHoy = j;
                            }
                        }

                        if (!fechaAnterior.equals(fechaLeida))
                        {
                            fix.add(new cabeceraFixture(fechaLeida));
                            j=j+1;
                        }

                        String hora=obtenerHora(date);

                        fix.add(new Fixture(idHome, idAway, soccerseason,homeTeam,awayTeam,date,status,matchday,homeTeamName,awayTeamName,goalsHomeTeam,goalsAwayTeam, hora));

                        fechaAnterior = fechaLeida;
                        j=j+1;
                    }


                } catch (JSONException e) {
                    Toast.makeText(PrincipalActivity.this, "Error 1: " + e.getMessage(), Toast.LENGTH_LONG).show();

                    e.printStackTrace();
                }
            }
            return results;
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


                    //listAdapterFixture = new FixtureAdapter(PrincipalActivity.this, R.layout.fixture_row, Fixtures);
                    listAdapterFixture = new FixtureAdapter(PrincipalActivity.this, fix);
                    //lv.setItemsCanFocus(false);
                    lv_fixture.setAdapter(listAdapterFixture);
                    listAdapterFixture.notifyDataSetChanged();
                    lv_fixture.setSelection(posHoy);
                }
            });
        }

    }

    private String obtenerFecha(String pFecha)
    {

        Date fDate = null;
        String date = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            fDate = simpleDateFormat.parse(pFecha);

            //Log.d("TemporadasActivity", "Fecha1: " + fDate.toString());

            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
            date = DATE_FORMAT.format(fDate);

            //Log.d("TemporadasActivity", "Fecha2: " + date);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return date;
    }

    private String obtenerHora(String pFecha)
    {

        Date fDate = null;
        String date = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            fDate = simpleDateFormat.parse(pFecha);

            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm");
            date = DATE_FORMAT.format(fDate);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return date;
    }

    private String obtenerHoy()
    {
        String date = "";

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
        date = DATE_FORMAT.format(new Date());

        return date;
    }

    private boolean mayorIgualHoy(String pFecha)
    {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaLeida = sdf.parse(pFecha , new ParsePosition(0));

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
        String hoy = DATE_FORMAT.format(new Date());

        Date Hoy = sdf.parse(hoy , new ParsePosition(0));

        if (fechaLeida.equals(Hoy))
        {
            return true;
        }
        else if (fechaLeida.after(Hoy))
        {
            return true;
        }
        else
        {
            return false;
        }

    }
}
