package com.kfouri.futbol.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kfouri.futbol.Bean.Equipo;
import com.kfouri.futbol.Bean.cabeceraEquipo;
import com.kfouri.futbol.Bean.itemEquipo;
import com.kfouri.futbol.R;

import java.util.ArrayList;

public class EquipoAdapter2 extends ArrayAdapter
{

    private Context mContext;
    ArrayList<itemEquipo> data = new ArrayList<itemEquipo>();
    private LayoutInflater vi;

    public EquipoAdapter2(Context context, ArrayList<itemEquipo> data)
    {

        super(context, 0, data);
        mContext = context;
        this.data = data;
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(final int pos, View convertView, ViewGroup parent)
    {
        View v = convertView;
        final itemEquipo i = data.get(pos);
        if (i != null) {
            if(i.isSection()){
                cabeceraEquipo si = (cabeceraEquipo)i;
                v = vi.inflate(R.layout.listview_header_row, null);

                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);

                final TextView sectionView = (TextView) v.findViewById(R.id.txtHeader);
                sectionView.setText(si.getTitle());
            }
            else
            {
                final Equipo ei = (Equipo)i;
                v = vi.inflate(R.layout.equipo_row, null);

                TextView name = (TextView) v.findViewById(R.id.name);
                TextView position = (TextView) v.findViewById(R.id.position);
                TextView jerseyNumber = (TextView) v.findViewById(R.id.jerseyNumber);
                TextView dateOfBirth = (TextView) v.findViewById(R.id.dateOfBirth);
                TextView nationality = (TextView) v.findViewById(R.id.nationality);
                TextView contractUntil = (TextView) v.findViewById(R.id.contractUntil);
                TextView marketValue = (TextView) v.findViewById(R.id.marketValue);


                if (name != null)
                    name.setText(ei.getName());

                if (position != null)
                    position.setText(mContext.getResources().getString(R.string.jugador_posicion)+" "+String.valueOf(ei.getPosition()));

                if (jerseyNumber != null)
                    jerseyNumber.setText(mContext.getResources().getString(R.string.jugador_posicion)+" "+String.valueOf(ei.getJerseyNumber()));

                if (dateOfBirth != null)
                    dateOfBirth.setText(mContext.getResources().getString(R.string.jugador_fecha_nac)+" "+String.valueOf(ei.getDateOfBirth()));


                if (nationality != null)
                    nationality.setText(mContext.getResources().getString(R.string.jugador_nacionalidad)+" "+String.valueOf(ei.getNationality()));

                if (contractUntil != null)
                    contractUntil.setText(mContext.getResources().getString(R.string.jugador_contrato)+" "+String.valueOf(ei.getContractUntil()));

                if (marketValue != null)
                    marketValue.setText(mContext.getResources().getString(R.string.jugador_valor)+" "+String.valueOf(ei.getMarketValue()));

                name.setVisibility(View.GONE);

            }
        }

        return v;
    }

}