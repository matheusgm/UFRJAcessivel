package com.example.matheus.ufrjacessivel.Local;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.matheus.ufrjacessivel.R;

import java.util.List;

public class LocalListAdpter extends BaseAdapter {

    private Context context;
    private List<Local> localList;

    public LocalListAdpter(Context context, List<Local> localList) {
        this.context = context;
        this.localList = localList;
    }

    @Override
    public int getCount() {
        return localList.size();
    }

    @Override
    public Object getItem(int position) {
        return localList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context,R.layout.item_list,null);
        TextView name = v.findViewById(R.id.lbl1);

        ImageView imageLibra = v.findViewById(R.id.imageLibra);
        ImageView imageRampa = v.findViewById(R.id.imageRampa);
        ImageView imageInterprete = v.findViewById(R.id.imageInterpretes);
        ImageView imageMapa = v.findViewById(R.id.imageMap);

        /*imageLibra.setColorFilter(context.getResources().getColor(R.color.colorGreen));
        imageRampa.setColorFilter(context.getResources().getColor(R.color.colorRed));
        imageInterprete.setColorFilter(context.getResources().getColor(R.color.colorBlack));*/
        imageMapa.setColorFilter(context.getResources().getColor(R.color.colorGrey));

        setaCores(imageLibra,localList.get(position).getNumLibraUp(),localList.get(position).getNumLibraDown());
        setaCores(imageRampa,localList.get(position).getNumRampaUp(),localList.get(position).getNumRampaDown());
        setaCores(imageInterprete,localList.get(position).getNumInterpretesUp(),localList.get(position).getNumInterpretesDown());

        name.setText(localList.get(position).getName());

        v.setTag(localList.get(position).getId());

        return v;
    }

    private void setaCores(ImageView image, String up, String down){

        if(Integer.parseInt(up) > Integer.parseInt(down)){
            image.setColorFilter(context.getResources().getColor(R.color.colorGreen));
        }else if(Integer.parseInt(up) < Integer.parseInt(down)){
            image.setColorFilter(context.getResources().getColor(R.color.colorRed));
        }else{
            image.setColorFilter(context.getResources().getColor(R.color.colorBlack));
        }

      /*4 4     6 5   6 5
        2 1     4 4   2 1
        6 5     2 5   4 4
        2 5     2 1   2 5*/

    }
}
