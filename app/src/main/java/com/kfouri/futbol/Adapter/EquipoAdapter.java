package com.kfouri.futbol.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kfouri.futbol.Bean.Equipo;
import com.kfouri.futbol.R;

import java.util.ArrayList;

public class EquipoAdapter extends ArrayAdapter
{

    private Context mContext;
    ArrayList<Equipo> data = new ArrayList<Equipo>();
    private int layoutResourceId;


    public EquipoAdapter(Context context, int layoutResourceId , ArrayList<Equipo> data)
    {

        super(context, 0, data);
        mContext = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }


    @Override
    public View getView(final int pos, View convertView, ViewGroup parent)
    {
        View row = convertView;
        EquipoHolder holder = null;

        if (row == null)
        {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new EquipoHolder();

            holder.name = (TextView) row.findViewById(R.id.name);
            holder.position = (TextView) row.findViewById(R.id.position);
            holder.jerseyNumber = (TextView) row.findViewById(R.id.jerseyNumber);
            holder.dateOfBirth = (TextView) row.findViewById(R.id.dateOfBirth);
            holder.nationality = (TextView) row.findViewById(R.id.nationality);
            holder.contractUntil = (TextView) row.findViewById(R.id.contractUntil);
            holder.marketValue = (TextView) row.findViewById(R.id.marketValue);

            row.setTag(holder);
        }
        else
        {
            holder = (EquipoHolder) row.getTag();
        }


        final Equipo temp = data.get(pos);

        holder.name.setText("Nombre: "+String.valueOf(temp.getName()));
        holder.position.setText("Posición: "+String.valueOf(temp.getPosition()));
        holder.jerseyNumber.setText("Camiseta: "+String.valueOf(temp.getJerseyNumber()));
        holder.dateOfBirth.setText("Fecha Nacimiento: "+String.valueOf(temp.getDateOfBirth()));
        holder.nationality.setText("Nacionalidad: "+String.valueOf(temp.getNationality()));
        holder.contractUntil.setText("Contrato Hasta: "+String.valueOf(temp.getContractUntil()));
        holder.marketValue.setText("Valor en el mercado: "+String.valueOf(temp.getMarketValue()));

        return row;
    }

    static class EquipoHolder {
        TextView name;
        TextView position;
        TextView jerseyNumber;
        TextView dateOfBirth;
        TextView nationality;
        TextView contractUntil;
        TextView marketValue;

    }
}