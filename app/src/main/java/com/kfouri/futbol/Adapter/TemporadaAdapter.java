package com.kfouri.futbol.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kfouri.futbol.Bean.Temporada;
import com.kfouri.futbol.R;

import java.util.ArrayList;


public class TemporadaAdapter extends ArrayAdapter {

    private Context mContext;
    private int layoutResourceId;
    ArrayList<Temporada> data = new ArrayList<Temporada>();

    public TemporadaAdapter(Context context, int layoutResourceId , ArrayList<Temporada> data)
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
        TemporadaHolder holder = null;

        if (row == null)
        {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new TemporadaHolder();

            holder.imagen = (ImageView) row.findViewById(R.id.imageView);
            holder.caption = (TextView) row.findViewById(R.id.caption);

            row.setTag(holder);
        }
        else
        {
            holder = (TemporadaHolder) row.getTag();
        }

        final Temporada temp = data.get(position);

        String Liga = String.valueOf(temp.getLeague());
        holder.caption.setText(String.valueOf(temp.getCaption()));

        if (Liga.equals("BL1")||Liga.equals("BL2")||Liga.equals("BL3"))
        {
            holder.imagen.setImageResource(R.drawable.alemania2);
        }
        else if (Liga.equals("FL1")||Liga.equals("FL2"))
        {
            holder.imagen.setImageResource(R.drawable.francia);
        }
        else if (Liga.equals("PL"))
        {
            holder.imagen.setImageResource(R.drawable.reinounido);
        }
        else if (Liga.equals("PD")||Liga.equals("SD"))
        {
            holder.imagen.setImageResource(R.drawable.espana2);
        }
        else if (Liga.equals("SA"))
        {
            holder.imagen.setImageResource(R.drawable.italia2);
        }
        else if (Liga.equals("PPL"))
        {
            holder.imagen.setImageResource(R.drawable.portugal2);
        }
        else if (Liga.equals("DED"))
        {
            holder.imagen.setImageResource(R.drawable.holanda2);
        }
        else if (Liga.equals("CL"))
        {
            holder.imagen.setImageResource(R.drawable.uefa2);
        }

        return row;
    }

    static class TemporadaHolder {
        ImageView imagen;
        TextView caption;

    }
}
