package com.kfouri.futbol.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kfouri.futbol.Activity.EquipoActivity;
import com.kfouri.futbol.Bean.Fixture;
import com.kfouri.futbol.Bean.cabeceraFixture;
import com.kfouri.futbol.Bean.itemFixture;
import com.kfouri.futbol.R;

import java.util.ArrayList;


public class FixtureAdapter extends ArrayAdapter
{

    private Context mContext;
    ArrayList<itemFixture> data = new ArrayList<itemFixture>();
    private LayoutInflater vi;


    public FixtureAdapter(Context context, ArrayList<itemFixture> data)
    {

        super(context, 0, data);
        mContext = context;
        this.data = data;
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        View v = convertView;
        final itemFixture i = data.get(position);
        if (i != null) {
            if(i.isSection()){
                cabeceraFixture si = (cabeceraFixture)i;
                v = vi.inflate(R.layout.listview_header_row, null);

                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);

                final TextView sectionView = (TextView) v.findViewById(R.id.txtHeader);
                sectionView.setText(si.getTitle());
            }
            else
            {
                final Fixture ei = (Fixture)i;
                v = vi.inflate(R.layout.fixture_row, null);

                final TextView soccerseason = (TextView) v.findViewById(R.id.soccerseason);
                final TextView homeTeam = (TextView) v.findViewById(R.id.homeTeam);
                final TextView awayTeam = (TextView) v.findViewById(R.id.awayTeam);
                final TextView date = (TextView) v.findViewById(R.id.date);
                final TextView status = (TextView) v.findViewById(R.id.status);
                final TextView matchday = (TextView) v.findViewById(R.id.matchday);
                final TextView homeTeamName = (TextView) v.findViewById(R.id.homeTeamName);
                final TextView awayTeamName = (TextView) v.findViewById(R.id.awayTeamName);
                final TextView goalsHomeTeam = (TextView) v.findViewById(R.id.goalsHomeTeam);
                final TextView goalsAwayTeam = (TextView) v.findViewById(R.id.goalsAwayTeam);

                final ImageView imagenHome = (ImageView) v.findViewById(R.id.imagenHome);
                final ImageView imagenAway = (ImageView) v.findViewById(R.id.imagenAway);

                final TextView medio = (TextView) v.findViewById(R.id.medio);

                int idHome;
                int idAway;

                idHome = mContext.getResources().getIdentifier("i"+ei.getIdHome(), "raw", mContext.getPackageName());

                if (idHome == 0)
                {
                    idHome = mContext.getResources().getIdentifier("i0", "raw", mContext.getPackageName());
                    imagenHome.setImageDrawable(mContext.getResources().getDrawable(idHome));
                }
                else
                {
                    imagenHome.setImageDrawable(mContext.getResources().getDrawable(idHome));
                }
                imagenHome.setClickable(true);


                final Intent intent = new Intent(mContext,EquipoActivity.class);

                imagenHome.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle b = new Bundle();
                        b.putString("IdEquipo", ""+ei.getIdHome());
                        b.putString("NombreEquipo", ei.getHomeTeamName());
                        intent.putExtras(b);
                        mContext.startActivity(intent);
                    }
                });

                homeTeamName.setClickable(true);
                homeTeamName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Bundle b = new Bundle();
                        b.putString("IdEquipo", "" + ei.getIdHome());
                        b.putString("NombreEquipo",ei.getHomeTeamName());
                        intent.putExtras(b);
                        mContext.startActivity(intent);
                    }
                });

                idAway = mContext.getResources().getIdentifier("i"+ei.getIdAway(), "raw", mContext.getPackageName());
                if (idAway == 0)
                {
                    idAway = mContext.getResources().getIdentifier("i0", "raw", mContext.getPackageName());
                    imagenAway.setImageDrawable(mContext.getResources().getDrawable(idAway));
                }
                else
                {
                    imagenAway.setImageDrawable(mContext.getResources().getDrawable(idAway));
                }
                imagenAway.setClickable(true);
                final int finalIdAway = idAway;
                imagenAway.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle b = new Bundle();
                        b.putString("IdEquipo", "" + ei.getIdAway());
                        b.putString("NombreEquipo",ei.getAwayTeamName());
                        intent.putExtras(b);
                        mContext.startActivity(intent);
                    }
                });

                awayTeamName.setClickable(true);
                awayTeamName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle b = new Bundle();
                        b.putString("IdEquipo", "" + ei.getIdAway());
                        b.putString("NombreEquipo",ei.getAwayTeamName());
                        intent.putExtras(b);
                        mContext.startActivity(intent);
                    }
                });

                if (soccerseason != null)
                    soccerseason.setText(ei.getSoccerseason());

                if (soccerseason != null)
                    homeTeam.setText(String.valueOf(ei.getHomeTeam()));

                if (awayTeam != null)
                    awayTeam.setText(String.valueOf(ei.getAwayTeam()));

                if (date != null)
                    date.setText(String.valueOf(ei.getDate()));

                if (status != null)
                    status.setText(String.valueOf(ei.getStatus()));

                if (matchday != null)
                    matchday.setText(String.valueOf(ei.getMatchday()));

                if (homeTeamName != null)
                    homeTeamName.setText(String.valueOf(ei.getHomeTeamName()));

                if (awayTeamName != null)
                    awayTeamName.setText(String.valueOf(ei.getAwayTeamName()));

                if (goalsHomeTeam != null)
                    goalsHomeTeam.setText(String.valueOf(ei.getGoalsHomeTeam()));

                if (goalsAwayTeam != null)
                    goalsAwayTeam.setText(String.valueOf(ei.getGoalsAwayTeam()));

                //Log.d("Fix","GOL:"+)

                if (goalsHomeTeam.getText().equals("-") || goalsAwayTeam.getText().equals("-") )
                {
                    //ocultar el resultado y mostrar la hora del partido
                    medio.setText(String.valueOf(ei.getHora()));

                    ViewGroup.LayoutParams params = medio.getLayoutParams();
                    params.width = 200;
                    medio.setLayoutParams(params);

                    goalsHomeTeam.setVisibility(View.GONE);
                    goalsAwayTeam.setVisibility(View.GONE);
                } else
                {
                    medio.setText("-");

                    ViewGroup.LayoutParams params = medio.getLayoutParams();
                    params.width = 15;
                    medio.setLayoutParams(params);

                    goalsHomeTeam.setVisibility(View.VISIBLE);
                    goalsAwayTeam.setVisibility(View.VISIBLE);
                }

            }
        }

        return v;
    }
}
