package com.kfouri.futbol.Utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.kfouri.futbol.Bean.Temporada;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by mkfouri on 07/01/2016.
 */
public class Lecturas
{



    public static ArrayList<Temporada> ObtenerTemporadas(Context context, ProgressDialog pDialog, int pAno)
    {
        ArrayList<Temporada> Temporadas = new ArrayList<Temporada>();

        LeerWS leerWS = new LeerWS(context,pDialog,"http://api.football-data.org/v1/soccerseasons/?season="+pAno,"Buscando datos");

        try
        {
            String Salida = leerWS.execute().get();
            try
            {
                JSONObject jObject=null;
                JSONArray temporadas = new JSONArray(Salida);

                Toast.makeText(context, "Cnt: "+temporadas.length(), Toast.LENGTH_SHORT).show();

                for (int i = 0; i < temporadas.length(); i++) {
                    JSONObject c = temporadas.getJSONObject(i);

                    int id = c.getInt("id");
                    String caption = c.getString("caption");
                    String league = c.getString("league");

                    Temporadas.add(new Temporada(id,caption,league,0,0,0,"","","","",""));

                }

            }
            catch (JSONException e)
            {
                Toast.makeText(context, "Error 1: "+e.getMessage(), Toast.LENGTH_LONG).show();

                e.printStackTrace();
            }

        }
        catch (InterruptedException e)
        {
            Toast.makeText(context, "Error 2: "+e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            Toast.makeText(context, "Error 3: "+e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        return Temporadas;
    }
}
