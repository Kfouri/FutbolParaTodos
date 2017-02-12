package com.kfouri.futbol.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kfouri.futbol.Bean.Tabla;
import com.kfouri.futbol.R;

import java.util.ArrayList;

public class TablaAdapter extends ArrayAdapter
{

    private Context mContext;
    private int layoutResourceId;
    ArrayList<Tabla> data = new ArrayList<Tabla>();

    public TablaAdapter(Context context, int layoutResourceId , ArrayList<Tabla> data)
    {
        super(context, layoutResourceId, data);
        mContext = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        TablaHolder holder = null;

        if (row == null)
        {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new TablaHolder();

            holder.imagen = (ImageView) row.findViewById(R.id.imageView);
            holder.linksTeam = (TextView) row.findViewById(R.id.linksTeam);
            holder.position = (TextView) row.findViewById(R.id.position);
            holder.teamName = (TextView) row.findViewById(R.id.teamName);
            holder.crestURI = (TextView) row.findViewById(R.id.crestURI);
            holder.playedGames = (TextView) row.findViewById(R.id.playedGames);
            holder.points = (TextView) row.findViewById(R.id.points);
            //holder.goals = (TextView) row.findViewById(R.id.goals);
            //holder.goalsAgainst = (TextView) row.findViewById(R.id.goalsAgainst);
            holder.goalDifference = (TextView) row.findViewById(R.id.goalDifference);
            holder.wins = (TextView) row.findViewById(R.id.wins);
            holder.draws = (TextView) row.findViewById(R.id.draws);
            holder.losses = (TextView) row.findViewById(R.id.losses);

            row.setTag(holder);
        }
        else
        {
            holder = (TablaHolder) row.getTag();
        }

        final Tabla temp = data.get(position);

        holder.position.setText(String.valueOf(temp.getPosition()));
        holder.teamName.setText(String.valueOf(temp.getTeamName()));
        holder.crestURI.setText(String.valueOf(temp.getCrestURI()));
        holder.playedGames.setText(String.valueOf(temp.getPlayedGames()));
        holder.points.setText(String.valueOf(temp.getPoints()));
//        holder.goals.setText(String.valueOf(temp.getGoals()));
//        holder.goalsAgainst.setText(String.valueOf(temp.getGoalsAgainst()));
        holder.goalDifference.setText(String.valueOf(temp.getGoalDifference()));
        holder.wins.setText(String.valueOf(temp.getWins()));
        holder.draws.setText(String.valueOf(temp.getDraws()));
        holder.losses.setText(String.valueOf(temp.getLosses()));

        return row;
    }

    static class TablaHolder {
        ImageView imagen;

        TextView position;
        TextView teamName;
        TextView crestURI;
        TextView playedGames;
        TextView points;
        TextView goals;
        TextView goalsAgainst;
        TextView goalDifference;
        TextView wins;
        TextView draws;
        TextView losses;
        TextView linksTeam;
    }
}
