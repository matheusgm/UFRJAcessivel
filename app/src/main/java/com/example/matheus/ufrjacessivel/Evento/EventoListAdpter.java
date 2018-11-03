package com.example.matheus.ufrjacessivel.Evento;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.matheus.ufrjacessivel.R;

import java.util.List;

public class EventoListAdpter extends BaseAdapter {

    private Context context;
    private List<Evento> eventoList;

    public EventoListAdpter(Context context, List<Evento> eventoList) {
        this.context = context;
        this.eventoList = eventoList;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.evento_item_list,null);
        TextView name = v.findViewById(R.id.lbl1);

        name.setText(eventoList.get(position).getNome());

        v.setTag(eventoList.get(position).getId());

        return v;
    }
}
