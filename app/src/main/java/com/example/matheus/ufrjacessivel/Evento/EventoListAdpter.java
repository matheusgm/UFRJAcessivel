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
        return eventoList.size();
    }

    @Override
    public Object getItem(int position) {
        return eventoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.evento_item_list,null);
        TextView name = v.findViewById(R.id.nome_evento);
        TextView data_hora = v.findViewById(R.id.data_hora_evento);
        TextView endereco = v.findViewById(R.id.endereco_evento);
        TextView dia = v.findViewById(R.id.dia_evento);
        TextView mes = v.findViewById(R.id.mes_evento);

        String[] info = transformaDataHora(eventoList.get(position).getDataEvento(),eventoList.get(position).getHorario());

        name.setText(eventoList.get(position).getNome());
        data_hora.setText(info[0]);
        endereco.setText(eventoList.get(position).getEndereco());
        dia.setText(info[1]);
        mes.setText(info[2]);

        v.setTag(eventoList.get(position).getId());

        return v;
    }

    private String[] transformaDataHora(String data, String hora){
        String[] result = new String[3];
        String[] listaData = data.split("/");

        String mes = nomeDoMes(listaData[1]);
        String dia = listaData[0];


        result[1] = dia; // Dia
        result[2] = mes.substring(0,3); // Mes
        result[0] = dia+" de "+mes+" as "+hora;

        return result;
    }

    private String nomeDoMes(String num) {
        String mes = "";
        switch (num){
            case "01":
                mes = "JANEIRO";
                break;
            case "02":
                mes = "FEVEREIRO";
                break;
            case "03":
                mes = "MARÇO";
                break;
            case "04":
                mes = "ABRIL";
                break;
            case "05":
                mes = "MAIO";
                break;
            case "06":
                mes = "JUNHO";
                break;
            case "07":
                mes = "JULHO";
                break;
            case "08":
                mes = "AGOSTO";
                break;
            case "09":
                mes = "SETEMBRO";
                break;
            case "10":
                mes = "OUTUBRO";
                break;
            case "11":
                mes = "NOVEMBRO";
                break;
            case "12":
                mes = "DEZEMBRO";
                break;
        }
        return mes;
    }
}
